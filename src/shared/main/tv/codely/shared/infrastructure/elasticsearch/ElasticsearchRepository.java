package tv.codely.shared.infrastructure.elasticsearch;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import tv.codely.shared.domain.criteria.Criteria;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class ElasticsearchRepository<T> {
    private final ElasticsearchClient            client;
    private final ElasticsearchCriteriaConverter criteriaConverter;

    public ElasticsearchRepository(ElasticsearchClient client) {
        this.client            = client;
        this.criteriaConverter = new ElasticsearchCriteriaConverter();
    }

    abstract protected String moduleName();

    protected List<T> searchAllInElastic(Function<Map<String, Object>, T> unserializer) {
        return searchAllInElastic(unserializer, new SearchSourceBuilder());
    }

	protected Optional<T> searchById(String id, Function<Map<String, Object>, T> unserializer) {
		GetRequest request = new GetRequest(client.indexFor(moduleName()), "_doc", id);

		try {
			GetResponse getResponse = client.highLevelClient().get(request, RequestOptions.DEFAULT);

			if (!getResponse.isExists()) {
				return Optional.empty();
			}

			return Optional.of(unserializer.apply(getResponse.getSourceAsMap()));
		} catch (IOException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}


	protected List<T> searchAllInElastic(
        Function<Map<String, Object>, T> unserializer,
        SearchSourceBuilder sourceBuilder
    ) {
        SearchRequest request = new SearchRequest(client.indexFor(moduleName())).source(sourceBuilder);
        try {
            SearchResponse response = client.highLevelClient().search(request, RequestOptions.DEFAULT);

            return Arrays.stream(response.getHits().getHits())
                         .map(hit -> unserializer.apply(hit.getSourceAsMap()))
                         .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    protected List<T> searchByCriteria(Criteria criteria, Function<Map<String, Object>, T> unserializer) {
        return searchAllInElastic(unserializer, criteriaConverter.convert(criteria));
    }

    protected void persist(String id, HashMap<String, Serializable> plainBody) {
        try {
            client.persist(moduleName(), id, plainBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

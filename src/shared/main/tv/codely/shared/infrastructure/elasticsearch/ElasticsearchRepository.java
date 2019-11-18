package tv.codely.shared.infrastructure.elasticsearch;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class ElasticsearchRepository<T> {
    private final ElasticsearchClient client;

    public ElasticsearchRepository(ElasticsearchClient client) {
        this.client = client;
    }

    abstract protected String moduleName();

    protected List<T> searchAllInElastic(Function<Map<String, Object>, T> unserializer) {
        SearchRequest request = new SearchRequest(client.indexFor(moduleName()));
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

    protected void persist(String id, HashMap<String, Serializable> plainBody) {
        try {
            client.persist(moduleName(), id, plainBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

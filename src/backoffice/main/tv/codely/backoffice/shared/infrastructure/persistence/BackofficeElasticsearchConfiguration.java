package tv.codely.backoffice.shared.infrastructure.persistence;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import tv.codely.shared.domain.Utils;
import tv.codely.shared.infrastructure.config.Parameter;
import tv.codely.shared.infrastructure.config.ParameterNotExist;
import tv.codely.shared.infrastructure.elasticsearch.ElasticsearchClient;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

@Configuration
public class BackofficeElasticsearchConfiguration {
    private final Parameter               config;
    private final ResourcePatternResolver resourceResolver;

    public BackofficeElasticsearchConfiguration(Parameter config, ResourcePatternResolver resourceResolver) {
        this.config           = config;
        this.resourceResolver = resourceResolver;
    }

    @Bean
    public ElasticsearchClient elasticsearchClient() throws ParameterNotExist, Exception {
		ElasticsearchClient client = new ElasticsearchClient(
			new RestHighLevelClient(
				RestClient.builder(
					new HttpHost(
						config.get("BACKOFFICE_ELASTICSEARCH_HOST"),
						config.getInt("BACKOFFICE_ELASTICSEARCH_PORT"),
						"http"
					)
				)
			),
			RestClient.builder(
				new HttpHost(
					config.get("BACKOFFICE_ELASTICSEARCH_HOST"),
					config.getInt("BACKOFFICE_ELASTICSEARCH_PORT"),
					"http"
				)).build(),
			config.get("BACKOFFICE_ELASTICSEARCH_INDEX_PREFIX")
		);

		Utils.retry(10, 10000, () -> {
            try {
                generateIndexIfNotExists(client, "backoffice");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return client;
    }

    private void generateIndexIfNotExists(ElasticsearchClient client, String contextName) throws IOException {
        Resource[] jsonsIndexes = resourceResolver.getResources(
            String.format("classpath:database/%s/*.json", contextName)
        );

        for (Resource jsonIndex : jsonsIndexes) {
            String indexName = Objects.requireNonNull(jsonIndex.getFilename()).replace(".json", "");

            if (!indexExists(indexName, client)) {
                String indexBody = new Scanner(
                    jsonIndex.getInputStream(),
                    "UTF-8"
                ).useDelimiter("\\A").next();

                Request request = new Request("PUT", indexName);
                request.setJsonEntity(indexBody);

                client.lowLevelClient().performRequest(request);
            }
        }
    }

    private boolean indexExists(String indexName, ElasticsearchClient client) throws IOException {
        return client.highLevelClient().indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
    }
}

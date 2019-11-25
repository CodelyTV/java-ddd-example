package tv.codely.shared.infrastructure.elasticsearch;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public final class ElasticsearchClient {
    private final RestHighLevelClient highLevelClient;
    private final RestClient          lowLevelClient;
    private final String              indexPrefix;

    public ElasticsearchClient(RestHighLevelClient highLevelClient, RestClient lowLevelClient, String indexPrefix) {
        this.highLevelClient = highLevelClient;
        this.lowLevelClient  = lowLevelClient;
        this.indexPrefix     = indexPrefix;
    }

    public RestHighLevelClient highLevelClient() {
        return highLevelClient;
    }

    public RestClient lowLevelClient() {
        return lowLevelClient;
    }

    public String indexPrefix() {
        return indexPrefix;
    }

    public void persist(String moduleName, String id, HashMap<String, Serializable> plainBody) throws IOException {
        IndexRequest request = new IndexRequest(indexFor(moduleName), moduleName, id).source(plainBody);

        highLevelClient().index(request, RequestOptions.DEFAULT);
    }

    public String indexFor(String moduleName) {
        return String.format("%s_%s", indexPrefix(), moduleName);
    }

    public void delete(String index) throws IOException {
        highLevelClient.indices().delete(new DeleteIndexRequest(index), RequestOptions.DEFAULT);
    }
}

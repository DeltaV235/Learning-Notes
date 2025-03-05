package com.deltav.es;

import com.deltav.es.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

public class RestClientTest {
    private static final Logger log = LogManager.getLogger(RestClientTest.class);
    private static RestHighLevelClient restClient;
    private static final String INDEX_NAME = "rest";
    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    static void createRestClient() {
        restClient = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.0.31", 9200)));
    }

    @AfterAll
    static void closeRestClient() throws IOException {
        restClient.close();
    }

    @Test
    void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("rest");
        CreateIndexResponse createIndexResponse = restClient.indices().create(request, RequestOptions.DEFAULT);
        log.info("create index response: {}", createIndexResponse.isAcknowledged());
    }

    @Test
    void queryIndexInfo() throws IOException {
        GetIndexResponse getIndexResponse = restClient.indices().get(new GetIndexRequest(INDEX_NAME), RequestOptions.DEFAULT);
        log.info("index aliases info: {}", getIndexResponse.getAliases());
        log.info("index mapping info: {}", getIndexResponse.getMappings());
        log.info("index setting info: {}", getIndexResponse.getSettings());
    }

    @Test
    void deleteIndex() throws IOException {
        AcknowledgedResponse ackResp = restClient.indices().delete(new DeleteIndexRequest(INDEX_NAME), RequestOptions.DEFAULT);
        log.info("delete index response: {}", ackResp.isAcknowledged());
    }

    @Test
    void insertDoc() throws IOException {
        IndexRequest request = new IndexRequest();
        User user = User.builder().name("deltav").gender("male").age(18).build();
        request.index(INDEX_NAME).id("10001").source(mapper.writeValueAsBytes(user), XContentType.JSON);
        IndexResponse indexResp = restClient.index(request, RequestOptions.DEFAULT);
        log.info("insert doc result: {}", indexResp.getResult());
    }

    @Test
    void updateDocSpecField() throws IOException {
        UpdateRequest request = new UpdateRequest();
        request.index(INDEX_NAME).id("10001").doc(XContentType.JSON, "age", 29);
        UpdateResponse updateResp = restClient.update(request, RequestOptions.DEFAULT);
        log.info("update doc result: {}", updateResp.getResult());
    }

    @Test
    void queryDocWithId() throws IOException {
        GetRequest request = new GetRequest();
        request.index(INDEX_NAME).id("10001");
        GetResponse resp = restClient.get(request, RequestOptions.DEFAULT);
        log.info("query doc string: {}", resp.getSourceAsString());
    }

    @Test
    void deleteDocWithId() throws IOException {
        DeleteRequest request = new DeleteRequest();
        request.index(INDEX_NAME).id("10001");
        DeleteResponse resp = restClient.delete(request, RequestOptions.DEFAULT);
        log.info("delete doc result: {}", resp.getResult());
    }

    @Test
    void bulkInsertDoc() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest().index(INDEX_NAME).id("10001").source(XContentType.JSON, "name", "de", "age", 19, "gender", "male"))
                .add(new IndexRequest().index(INDEX_NAME).id("10002").source(XContentType.JSON, "name", "lta", "age", 23, "gender", "female"))
                .add(new IndexRequest().index(INDEX_NAME).id("10003").source(XContentType.JSON, "name", "V", "age", 29, "gender", "male"))
                .add(new IndexRequest().index(INDEX_NAME).id("10004").source(XContentType.JSON, "name", "23", "age", 39, "gender", "female"))
                .add(new IndexRequest().index(INDEX_NAME).id("10005").source(XContentType.JSON, "name", "5", "age", 49, "gender", "male"))
                .add(new IndexRequest().index(INDEX_NAME).id("10006").source(XContentType.JSON, "name", "wangwu", "age", 49, "gender", "male"))
                .add(new IndexRequest().index(INDEX_NAME).id("10007").source(XContentType.JSON, "name", "wangwu1", "age", 49, "gender", "male"))
                .add(new IndexRequest().index(INDEX_NAME).id("10008").source(XContentType.JSON, "name", "wang22wu", "age", 49, "gender", "male"));
        BulkResponse bulkResp = restClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        log.info("bulk result: {}", Arrays.stream(bulkResp.getItems())
                .map(bulkItemResponse -> bulkItemResponse.getResponse().getResult())
                .toList()
        );
        log.info("bulk took: {}", bulkResp.getTook());
    }

    @Test
    void bulkDeleteDoc() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new DeleteRequest().index(INDEX_NAME).id("10001"))
                .add(new DeleteRequest().index(INDEX_NAME).id("10002"))
                .add(new DeleteRequest().index(INDEX_NAME).id("10003"));
        BulkResponse bulkResp = restClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        log.info("bulk result: {}", Arrays.stream(bulkResp.getItems())
                .map(bulkItemResponse -> bulkItemResponse.getResponse().getResult())
                .toList()
        );
        log.info("bulk took: {}", bulkResp.getTook());
    }

    @Test
    void searchMatchAll() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX_NAME).source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
        SearchResponse searchResp = restClient.search(searchRequest, RequestOptions.DEFAULT);
        printLogForHitResult(searchResp);
    }

    @Test
    void searchTermQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX_NAME).source(new SearchSourceBuilder().query(QueryBuilders.termQuery("name", "lta")));
        SearchResponse searchResp = restClient.search(searchRequest, RequestOptions.DEFAULT);
        printLogForHitResult(searchResp);
    }

    @Test
    void searchPagination() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX_NAME).source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()).from(0).size(2));
        SearchResponse searchResp = restClient.search(searchRequest, RequestOptions.DEFAULT);
        printLogForHitResult(searchResp);
    }

    @Test
    void searchExcludeField() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX_NAME).source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()).fetchSource(new String[]{"name"}, null));
        SearchResponse searchResp = restClient.search(searchRequest, RequestOptions.DEFAULT);
        printLogForHitResult(searchResp);
    }

    @Test
    void searchSorted() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX_NAME).source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()).sort("age", SortOrder.DESC));
        SearchResponse searchResp = restClient.search(searchRequest, RequestOptions.DEFAULT);

        printLogForHitResult(searchResp);
    }

    @Test
    void searchBoolQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

//        boolQuery.must(QueryBuilders.termQuery("gender", "male"))
//                .mustNot(QueryBuilders.termQuery("age", 29));
        boolQuery.should(QueryBuilders.matchQuery("name", "V"))
                .should(QueryBuilders.matchQuery("age", 19));

        searchRequest.indices(INDEX_NAME).source(new SearchSourceBuilder().query(boolQuery));
        SearchResponse searchResp = restClient.search(searchRequest, RequestOptions.DEFAULT);

        printLogForHitResult(searchResp);
    }

    @Test
    void searchRange() throws IOException {
        SearchRequest searchRequest = new SearchRequest();

        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        rangeQuery.gt(19).lte(29);

        searchRequest.indices(INDEX_NAME).source(new SearchSourceBuilder().query(rangeQuery));
        SearchResponse searchResp = restClient.search(searchRequest, RequestOptions.DEFAULT);

        printLogForHitResult(searchResp);
    }

    @Test
    void searchFuzzy() throws IOException {
        SearchRequest searchRequest = new SearchRequest();

        FuzzyQueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("name", "wangwu");
        fuzzyQuery.fuzziness(Fuzziness.TWO);

        searchRequest.indices(INDEX_NAME).source(new SearchSourceBuilder().query(fuzzyQuery));
        SearchResponse searchResp = restClient.search(searchRequest, RequestOptions.DEFAULT);

        printLogForHitResult(searchResp);
    }

    @Test
    void searchHighlight() throws IOException {
        SearchRequest searchRequest = new SearchRequest();

        TermQueryBuilder termQuery = QueryBuilders.termQuery("name", "v");
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<em>").postTags("</em>").field("name");

        searchRequest.indices(INDEX_NAME).source(new SearchSourceBuilder().query(termQuery).highlighter(highlightBuilder));
        SearchResponse searchResp = restClient.search(searchRequest, RequestOptions.DEFAULT);

        printLogForHitResult(searchResp);
        for (SearchHit hit : searchResp.getHits()) {
            log.info("highlight fields: {}", hit.getHighlightFields());
        }
    }

    @Test
    void searchAggregationMax() throws IOException {
        SearchRequest searchRequest = new SearchRequest();

        MaxAggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");
        searchRequest.indices(INDEX_NAME).source(new SearchSourceBuilder().aggregation(aggregationBuilder));
        SearchResponse searchResp = restClient.search(searchRequest, RequestOptions.DEFAULT);

        printLogForHitResult(searchResp);
        log.info("resp: {}", searchResp.toString());
    }

    @Test
    void searchAggregationTerm() throws IOException {
        SearchRequest searchRequest = new SearchRequest();

        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
        searchRequest.indices(INDEX_NAME).source(new SearchSourceBuilder().aggregation(aggregationBuilder));
        SearchResponse searchResp = restClient.search(searchRequest, RequestOptions.DEFAULT);

        printLogForHitResult(searchResp);
        log.info("resp: {}", searchResp.toString());
    }


    private static void printLogForHitResult(SearchResponse searchResp) {
        SearchHits hits = searchResp.getHits();
        log.info("totalHits: {}", hits.getTotalHits());
        log.info("took: {}", searchResp.getTook());
        Arrays.stream(hits.getHits()).map(SearchHit::getSourceAsString).forEach(hit -> log.info("hit: {}", hit));
    }
}

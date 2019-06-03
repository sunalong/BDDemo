package com.itcode.elasticsearch.hdfs;

import com.itcode.elasticsearch.es_hrset_client.InitDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ESUtils {
    private static Logger logger = LogManager.getRootLogger();

    /**
     * 创建ES index
     *
     * @param indexName index 名称
     */
    public static void createIndex(String indexName) {
        try (RestHighLevelClient client = InitDemo.getClient();) {

            // 1、创建 创建索引request 参数：索引名mess
            CreateIndexRequest request = new CreateIndexRequest(indexName);

            // 2、设置索引的settings
            request.settings(Settings.builder().put("index.number_of_shards", 3) // 分片数
                    .put("index.number_of_replicas", 2) // 副本数
                    .put("analysis.analyzer.default.tokenizer", "ik_smart") // 默认分词器
            );

            // 3、设置索引的mappings
            request.mapping("_doc",
                    "  {\n" +
                            "    \"_doc\": {\n" +
                            "      \"properties\": {\n" +
                            "        \"message\": {\n" +
                            "          \"type\": \"text\"\n" +
                            "        }\n" +
                            "      }\n" +
                            "    }\n" +
                            "  }",
                    XContentType.JSON);

            // 4、 设置索引的别名
            request.alias(new Alias("mmm_" + indexName));

            // 5、 发送请求
            // 5.1 同步方式发送请求
            CreateIndexResponse createIndexResponse = client.indices().create(request);

            // 6、处理响应
            boolean acknowledged = createIndexResponse.isAcknowledged();
            boolean shardsAcknowledged = createIndexResponse
                    .isShardsAcknowledged();
            System.out.println("acknowledged = " + acknowledged);
            System.out.println("shardsAcknowledged = " + shardsAcknowledged);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 插入数据到ES中
     *
     * @param jsonStr
     * @param indexName 存储时一般为hdfs,
     * @param idName    一般设置为库名，如dm_pwd、dim
     */
    public static void insertSingle(String jsonStr, String indexName, String idName) {
        try (RestHighLevelClient client = InitDemo.getClient();) {

            // 1、创建批量操作请求
            BulkRequest request = new BulkRequest();

//            request.add(new IndexRequest(indexName, "_doc", idName).source(jsonStr, XContentType.JSON));
            request.add(new IndexRequest(indexName, "_doc", idName).source(jsonStr, XContentType.JSON));

            // 2、可选的设置
            /*
            request.timeout("2m");
			request.setRefreshPolicy("wait_for");
			request.waitForActiveShards(2);
			*/

            //3、发送请求
            BulkResponse bulkResponse = client.bulk(request);

            //4、处理响应
            if (bulkResponse != null) {
                for (BulkItemResponse bulkItemResponse : bulkResponse) {
                    DocWriteResponse itemResponse = bulkItemResponse.getResponse();

                    if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
                            || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                        IndexResponse indexResponse = (IndexResponse) itemResponse;
                        //TODO 新增成功的处理
                        System.out.println("新增文档成功，处理逻辑代码写到这里:" + indexResponse.toString());

                    } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                        UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                        //TODO 修改成功的处理
                        System.out.println("新增文档成功，处理逻辑代码写到这里:" + updateResponse.toString());

                    } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
                        DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                        //TODO 删除成功的处理
                        System.out.println("TODO 删除成功的处理:" + deleteResponse.toString());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertMulti(List<String> jsonStrList, String indexName, String idName) {
        try (RestHighLevelClient client = InitDemo.getClient();) {

            // 1、创建批量操作请求
            BulkRequest request = new BulkRequest();

            for (int i = 0; i < jsonStrList.size(); i++) {
                request.add(new IndexRequest(indexName, "_doc", idName).source(jsonStrList.get(i), XContentType.JSON));
            }

            // 2、可选的设置
            /*
			request.timeout("2m");
			request.setRefreshPolicy("wait_for");
			request.waitForActiveShards(2);
			*/

            //3、发送请求
            BulkResponse bulkResponse = client.bulk(request);

            //4、处理响应
            if (bulkResponse != null) {
                for (BulkItemResponse bulkItemResponse : bulkResponse) {
                    DocWriteResponse itemResponse = bulkItemResponse.getResponse();

                    if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
                            || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                        IndexResponse indexResponse = (IndexResponse) itemResponse;
                        //TODO 新增成功的处理
                        System.out.println("新增文档成功，处理逻辑代码写到这里:" + indexResponse.toString());

                    } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                        UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                        //TODO 修改成功的处理
                        System.out.println("新增文档成功，处理逻辑代码写到这里:" + updateResponse.toString());

                    } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
                        DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                        //TODO 删除成功的处理
                        System.out.println("TODO 删除成功的处理:" + deleteResponse.toString());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从ES中搜索数据
     *
     * @param indexName
     * @param idName
     */
    public static void search(String indexName, String idName) {
        try (RestHighLevelClient client = InitDemo.getClient();) {
            // 1、创建获取文档请求
            GetRequest request = new GetRequest(
                    indexName,   //索引
                    "_doc",     // mapping type
                    idName);     //文档id
//            GetRequest request = new GetRequest("dim_1");
            // 2、可选的设置
            //request.routing("routing");
            //request.version(2);

            //request.fetchSourceContext(new FetchSourceContext(false)); //是否获取_source字段
            //选择返回的字段
            String[] includes = new String[]{"tableName", "*"};
            String[] excludes = Strings.EMPTY_ARRAY;
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
            request.fetchSourceContext(fetchSourceContext);

            //3、发送请求
            GetResponse getResponse = null;
            try {
                // 同步请求
                getResponse = client.get(request);
            } catch (ElasticsearchException e) {
                if (e.status() == RestStatus.NOT_FOUND) {
                    logger.error("没有找到该id的文档");
                }
                if (e.status() == RestStatus.CONFLICT) {
                    logger.error("获取时版本冲突了，请在此写冲突处理逻辑！");
                }
                logger.error("获取文档异常", e);
            }
            System.out.println("-------1" + getResponse);
            //4、处理响应
            if (getResponse != null) {
                String index = getResponse.getIndex();
                String type = getResponse.getType();
                String id = getResponse.getId();
                if (getResponse.isExists()) { // 文档存在
                    long version = getResponse.getVersion();
                    String sourceAsString = getResponse.getSourceAsString(); //结果取成 String
                    Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();  // 结果取成Map
                    byte[] sourceAsBytes = getResponse.getSourceAsBytes();    //结果取成字节数组

                    logger.info("index:" + index + "  type:" + type + "  id:" + id);
                    logger.info(sourceAsString);

                } else {
                    logger.error("没有找到该id的文档");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.itcode.elasticsearch.hdfs;

import com.itcode.elasticsearch.es_hrset_client.InitDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author lgs
 * @Description: 批量索引文档，即批量往索引里面放入文档数据.类似于数据库里面批量向表里面插入多行数据，一行数据就是一个文档
 * @date 2018年6月23日
 */
public class MultiInsert {
    private static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {
        try (RestHighLevelClient client = InitDemo.getClient();) {

            // 1、创建批量操作请求
            BulkRequest request = new BulkRequest();

//            String shopFieldsCommentJsonStr = "{"
//                    + "\"dim_shop_id\":\"门店ID\","
//                    + "\"shop_code\":\"门店编码\","
//                    + "\"branch_code\":\"分公司编码\","
//                    + "\"branch_name\":\"分公司名称\""
//                    + "}";

            String shopFieldsTypeJsonStr = "{"
                    + "\"dim_shop_id\":\"string\","
                    + "\"shop_code\":\"string\","
                    + "\"branch_code\":\"string\","
                    + "\"branch_name\":\"string\""
                    + "}";

            String memberJsonStr = "";
            String channelJsonStr = "";
            request.add(new IndexRequest("fuck_dim", "_doc_dim_shop", "7").source(shopFieldsTypeJsonStr,XContentType.JSON));
//            request.add(new IndexRequest("_index_dim", "_doc_dim_shop", "2").source(shopFieldsTypeJsonStr,XContentType.JSON));
//            request.add(new IndexRequest("_index_dim", "_doc_dim_member", "2").source(XContentType.JSON, memberJsonStr));
//            request.add(new IndexRequest("_index_dim", "_doc_dim_channel", "3").source(XContentType.JSON, channelJsonStr));

			/*
			request.add(new DeleteRequest("mess", "_doc", "3")); 
			request.add(new UpdateRequest("mess", "_doc", "2").doc(XContentType.JSON,"other", "test"));
			request.add(new IndexRequest("mess", "_doc", "4").source(XContentType.JSON,"field", "baz"));
			*/

            // 2、可选的设置
			/*
			request.timeout("2m");
			request.setRefreshPolicy("wait_for");  
			request.waitForActiveShards(2);
			*/


            //3、发送请求

            // 同步请求
            BulkResponse bulkResponse = client.bulk(request);

            //4、处理响应
            if (bulkResponse != null) {
                for (BulkItemResponse bulkItemResponse : bulkResponse) {
                    DocWriteResponse itemResponse = bulkItemResponse.getResponse();

                    if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
                            || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                        IndexResponse indexResponse = (IndexResponse) itemResponse;
                        //TODO 新增成功的处理
                        System.out.println("新增文档成功，处理逻辑代码写到这里:"+indexResponse.toString());

                    } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                        UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                        //TODO 修改成功的处理
                        System.out.println("新增文档成功，处理逻辑代码写到这里:"+updateResponse.toString());

                    } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
                        DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                        //TODO 删除成功的处理
                        System.out.println("TODO 删除成功的处理:"+deleteResponse.toString());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

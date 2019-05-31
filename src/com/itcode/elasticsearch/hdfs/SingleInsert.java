package com.itcode.elasticsearch.hdfs;

import com.itcode.elasticsearch.es_hrset_client.InitDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;

/**
 * 
 * @Description: 索引文档，即往索引里面放入文档数据.类似于数据库里面向表里面插入一行数据，一行数据就是一个文档
 * @author lgs
 * @date 2018年6月23日
 *
 */
public class SingleInsert {
	
	private static Logger logger = LogManager.getRootLogger();  

	public static void main(String[] args) {
		try (RestHighLevelClient client = InitDemo.getClient();) {
			// 1、创建索引请求
			IndexRequest request = new IndexRequest(
			        "dim_1",   //索引
			        "_doc",     // mapping type
			        "type");     //文档id
			
			// 2、准备文档数据
			// 方式一：直接给JSON串
			String jsonString = "{"
			        + "\"tableName\":\"kimchy\","
			        + "\"user\":\"kimchy\","
			        + "\"dim_shop_id\":\"9D01\","
			        + "\"postDate\":\"2013-01-30\","
			        + "\"message\":\"trying out Elasticsearch\"" +
			        "}";
			request.source(jsonString, XContentType.JSON); 

			//3、其他的一些可选设置
			/*
			request.routing("routing");  //设置routing值
			request.timeout(TimeValue.timeValueSeconds(1));  //设置主分片等待时长
			request.setRefreshPolicy("wait_for");  //设置重刷新策略
			request.version(2);  //设置版本号
			request.opType(DocWriteRequest.OpType.CREATE);  //操作类别  
			*/
			
			//4、发送请求
			IndexResponse indexResponse = null;
			try {
				// 同步方式
				indexResponse = client.index(request);			
			} catch(ElasticsearchException e) {
				// 捕获，并处理异常
				//判断是否版本冲突、create但文档已存在冲突
			    if (e.status() == RestStatus.CONFLICT) {
			    	logger.error("冲突了，请在此写冲突处理逻辑！\n" + e.getDetailedMessage());
			    }
			    
			    logger.error("索引异常", e);
			}
			
			//5、处理响应
			if(indexResponse != null) {
				String index = indexResponse.getIndex();
				String type = indexResponse.getType();
				String id = indexResponse.getId();
				long version = indexResponse.getVersion();
				if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
				    System.out.println("新增文档成功，处理逻辑代码写到这里。");
				} else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
				    System.out.println("修改文档成功，处理逻辑代码写到这里。");
				}
				// 分片处理信息
				ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
				if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
				    
				}
				// 如果有分片副本失败，可以获得失败原因信息
				if (shardInfo.getFailed() > 0) {
				    for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
				        String reason = failure.reason(); 
				        System.out.println("副本失败原因：" + reason);
				    }
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

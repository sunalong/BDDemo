package com.itcode.elasticsearch.hdfs;

import com.itcode.elasticsearch.es_hrset_client.InitDemo;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * 
 * @Description: 创建索引
 * @author lgs
 * @date 2018年6月23日
 *
 */
public class CreateIndex {

	public static void main(String[] args) {
		try (RestHighLevelClient client = InitDemo.getClient();) {

			// 1、创建 创建索引request 参数：索引名mess
			CreateIndexRequest request = new CreateIndexRequest("dim_1");

			// 2、设置索引的settings
			request.settings(Settings.builder().put("index.number_of_shards", 3) // 分片数
					.put("index.number_of_replicas", 2) // 副本数
					.put("analysis.analyzer.default.tokenizer", "ik_smart") // 默认分词器
			);

			// 3、设置索引的mappings
			request.mapping("_doc",
					"  {\n" +
				    "    \"doc_dim_member\": {\n" +
				    "      \"properties\": {\n" +
				    "        \"message\": {\n" +
				    "          \"type\": \"text\"\n" +
				    "        }\n" +
				    "      }\n" +
				    "    }\n" +
				    "  }",
					XContentType.JSON);

			// 4、 设置索引的别名
			request.alias(new Alias("mmm_dim_1"));

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
}

package com.itcode.elasticsearch.hdfs;

import com.itcode.elasticsearch.es_hrset_client.InitDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.util.Map;

/**
 * @author lgs
 * @Description: 获取文档数据
 * @date 2018年6月23日
 */
public class GetMulti {

    private static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {
        try (RestHighLevelClient client = InitDemo.getClient();) {
            // 1、创建获取文档请求
			GetRequest request = new GetRequest(
			        "dim_1",   //索引
			        "_doc",     // mapping type
//			        "type");     //文档id
			        "dim");     //文档id
//			        "comment");     //文档id
//            GetRequest request = new GetRequest("dim_1");
            // 2、可选的设置
            //request.routing("routing");
            //request.version(2);

            //request.fetchSourceContext(new FetchSourceContext(false)); //是否获取_source字段
            //选择返回的字段
            String[] includes = new String[]{"dim_shop_id", "*"};
            String[] excludes = Strings.EMPTY_ARRAY;
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
            request.fetchSourceContext(fetchSourceContext);

            //也可写成这样
            /*String[] includes = Strings.EMPTY_ARRAY;
			String[] excludes = new String[]{"message"};
			FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
			request.fetchSourceContext(fetchSourceContext);*/


            // 取stored字段
			/*request.storedFields("message"); 
			GetResponse getResponse = client.get(request);
			String message = getResponse.getField("message").getValue();*/
//            String message = getResponse.getField("message").getValue();

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


            //异步方式发送获取文档请求
			/*
			ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
			    @Override
			    public void onResponse(GetResponse getResponse) {
			        
			    }
			
			    @Override
			    public void onFailure(Exception e) {
			        
			    }
			};
			client.getAsync(request, listener);
			*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

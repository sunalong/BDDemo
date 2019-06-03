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
        ESUtils.search("hdfs","dim");
    }

}

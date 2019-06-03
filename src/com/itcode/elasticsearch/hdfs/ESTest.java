package com.itcode.elasticsearch.hdfs;

public class ESTest {
    public static void main(String[] args) {
//        ESUtils.createIndex("hdfs");
        String jsonStr = createJsonStr();
//        ESUtils.insert(jsonStr, "hdfs", "dim");
        ESUtils.search("hdfs", "dim");
    }

    private static String createJsonStr() {
        String dimShopJsonStr = "{\n" +
                "  \"tableName\": \"dim_shop\",\n" +
                "  \"fieldsType\": {\n" +
                "    \"dim_shop_id\":\"string\",\n" +
                "    \"shop_code\":\"string\",\n" +
                "    \"branch_code\":\"string\",\n" +
                "    \"branch_name\":\"string\",\n" +
                "    \"shop_name\":\"string\",\n" +
                "    \"department_id\":\"string\",\n" +
                "    \"department_code\":\"string\",\n" +
                "    \"department_name\":\"string\",\n" +
                "    \"province_id\":\"string\",\n" +
                "    \"province_code\":\"string\",\n" +
                "    \"province_name\":\"string\",\n" +
                "    \"city_code\":\"string\",\n" +
                "    \"city_name\":\"string\",\n" +
                "    \"area_id\":\"string\",\n" +
                "    \"area_name\":\"string\",\n" +
                "    \"open_date\":\"string\",\n" +
                "    \"close_date\":\"string\",\n" +
                "    \"status\":\"string\",\n" +
                "    \"seller_type_id\":\"string\",\n" +
                "    \"seller_type_name\":\"string\",\n" +
                "    \"seller_id\":\"string\",\n" +
                "    \"seller_name\":\"string\",\n" +
                "    \"actual_shop_id\":\"string\",\n" +
                "    \"are_code\":\"string\",\n" +
                "    \"are_name\":\"string\",\n" +
                "    \"org_code\":\"string\",\n" +
                "    \"org_name\":\"string\",\n" +
                "    \"region_code\":\"string\",\n" +
                "    \"region_name\":\"string\",\n" +
                "    \"fin_open_date\":\"string\",\n" +
                "    \"actual_open_date\":\"string\",\n" +
                "    \"longitude\":\"string\",\n" +
                "    \"latitude\":\"string\",\n" +
                "    \"p_price_code\":\"string\",\n" +
                "    \"p_price_name\":\"string\",\n" +
                "    \"address\":\"string\",\n" +
                "    \"source\":\"string\",\n" +
                "    \"is_hc_enabled\":\"string\",\n" +
                "    \"shop_type\":\"string\",\n" +
                "    \"update_time\":\"timestamp\"\n" +
                "},\n" +
                "  \"fieldsComment\": {\n" +
                "    \"dim_shop_id\":\"门店ID\",\n" +
                "    \"shop_code\":\"门店编码\",\n" +
                "    \"branch_code\":\"分公司编码\",\n" +
                "    \"branch_name\":\"分公司名称\",\n" +
                "    \"shop_name\":\"门店名称\",\n" +
                "    \"department_id\":\"部门ID\",\n" +
                "    \"department_code\":\"部门编码\",\n" +
                "    \"department_name\":\"部门名称\",\n" +
                "    \"province_id\":\"省id\",\n" +
                "    \"province_code\":\"省编码\",\n" +
                "    \"province_name\":\"省名称\",\n" +
                "    \"city_code\":\"城市编码\",\n" +
                "    \"city_name\":\"城市名称\",\n" +
                "    \"area_id\":\"区/县id\",\n" +
                "    \"area_name\":\"区/县名称\",\n" +
                "    \"open_date\":\"开店日期\",\n" +
                "    \"close_date\":\"闭店日期\",\n" +
                "    \"status\":\"门店状态\",\n" +
                "    \"seller_type_id\":\"业态类型\",\n" +
                "    \"seller_type_name\":\"业态类型名称\",\n" +
                "    \"seller_id\":\"业态id\",\n" +
                "    \"seller_name\":\"业态名称\",\n" +
                "    \"actual_shop_id\":\"报表实际编码\",\n" +
                "    \"are_code\":\"营运大区ID\",\n" +
                "    \"are_name\":\"营运大区名称\",\n" +
                "    \"org_code\":\"大区ID\",\n" +
                "    \"org_name\":\"大区名称\",\n" +
                "    \"region_code\":\"区域ID\",\n" +
                "    \"region_name\":\"区域名称\",\n" +
                "    \"fin_open_date\":\"财务维护开业时间\",\n" +
                "    \"actual_open_date\":\"实际开业时间\",\n" +
                "    \"longitude\":\"经度\",\n" +
                "    \"latitude\":\"纬度\",\n" +
                "    \"p_price_code\":\"进价群编码\",\n" +
                "    \"p_price_name\":\"进价群名称\",\n" +
                "    \"address\":\"门店地址\",\n" +
                "    \"source\":\"门店来源 1 辉创 2 WMS 3其他(现有门店)\",\n" +
                "    \"is_hc_enabled\":\"是否启用辉创系统 0 否 1 是 2 永久启用辉创\",\n" +
                "    \"shop_type\":\"门店类型 1:门店 2:配送中心\",\n" +
                "    \"update_time\":\"更新时间\"\n" +
                "  }\n" +
                "}\n";
        return dimShopJsonStr;
    }
}

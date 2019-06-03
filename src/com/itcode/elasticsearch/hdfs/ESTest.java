package com.itcode.elasticsearch.hdfs;

import java.util.ArrayList;
import java.util.List;

public class ESTest {
    public static void main(String[] args) {
//        ESUtils.createIndex("hdfs");
//        String jsonStr = createJsonStr();
        ESUtils.insertSingle(createJsonStr(), "hdfs", "dim");
//        ESUtils.insertMulti(createJsonList(), "hdfs", "dim");
        ESUtils.search("hdfs", "dim");
    }

    private static List<String> createJsonList() {
        String dimCategoryJsonStr = "{\n" +
                "  \"tableName\": \"dim_category\",\n" +
                "  \"fieldsType\": {\n" +
                "    \"dim_category_id\":\"string\",\n" +
                "    \"category_code\":\"string\",\n" +
                "    \"category_name\":\"string\",\n" +
                "    \"mcategory_code\":\"string\",\n" +
                "    \"mcategory_name\":\"string\",\n" +
                "    \"bcategory_code\":\"string\",\n" +
                "    \"bcategory_name\":\"string\",\n" +
                "    \"workshop_code\":\"string\",\n" +
                "    \"work_shop_name\":\"string\",\n" +
                "    \"old_workshop_code\":\"string\",\n" +
                "    \"old_work_shop_name\":\"string\",\n" +
                "    \"small_group_code\":\"string\",\n" +
                "    \"small_group_name\":\"string\",\n" +
                "    \"class_code\":\"string\",\n" +
                "    \"class_name\":\"string\",\n" +
                "    \"class_group_code\":\"string\",\n" +
                "    \"class_group_name\":\"string\",\n" +
                "    \"group_code\":\"string\",\n" +
                "    \"group_name\":\"string\",\n" +
                "    \"yhlife_group_code\":\"string\",\n" +
                "    \"yhlife_group_name\":\"string\",\n" +
                "    \"yhlife_group_code1\":\"string\",\n" +
                "    \"yhlife_group_name1\":\"string\",\n" +
                "    \"yhlife_group_code2\":\"string\",\n" +
                "    \"yhlife_group_name2\":\"string\",\n" +
                "    \"species_group_code\":\"string\",\n" +
                "    \"species_group_name\":\"string\",\n" +
                "    \"species_group_code1\":\"string\",\n" +
                "    \"species_group_name1\":\"string\",\n" +
                "    \"species_group_code2\":\"string\",\n" +
                "    \"species_group_name2\":\"string\",\n" +
                "    \"dept_code\":\"string\",\n" +
                "    \"dept_name\":\"string\",\n" +
                "    \"goods_dept_code\":\"string\",\n" +
                "    \"goods_dept_name\":\"string\",\n" +
                "    \"small_shop_code\":\"string\",\n" +
                "    \"small_shop_name\":\"string\",\n" +
                "    \"update_time\":\"timestamp\"\n" +
                "},\n" +
                "  \"fieldsComment\": {\n" +
                "    \"dim_category_id\":\"品类id\",\n" +
                "    \"category_code\":\"品类编码\",\n" +
                "    \"category_name\":\"品类名称\",\n" +
                "    \"mcategory_code\":\"中类编码\",\n" +
                "    \"mcategory_name\":\"中类名称\",\n" +
                "    \"bcategory_code\":\"大类编码\",\n" +
                "    \"bcategory_name\":\"大类名称\",\n" +
                "    \"workshop_code\":\"工坊编码\",\n" +
                "    \"work_shop_name\":\"工坊名称\",\n" +
                "    \"old_workshop_code\":\"旧工坊编码\",\n" +
                "    \"old_work_shop_name\":\"旧工坊名称\",\n" +
                "    \"small_group_code\":\"小商行编码\",\n" +
                "    \"small_group_name\":\"小商行名称\",\n" +
                "    \"class_code\":\"课组编码\",\n" +
                "    \"class_name\":\"课组名称\",\n" +
                "    \"class_group_code\":\"课组集编码\",\n" +
                "    \"class_group_name\":\"课组集名称\",\n" +
                "    \"group_code\":\"商行编码\",\n" +
                "    \"group_name\":\"商行名称\",\n" +
                "    \"yhlife_group_code\":\"永辉生活商行编码\",\n" +
                "    \"yhlife_group_name\":\"永辉生活商行名称\",\n" +
                "    \"yhlife_group_code1\":\"永辉生活商行编码1\",\n" +
                "    \"yhlife_group_name1\":\"永辉生活商行名称1\",\n" +
                "    \"yhlife_group_code2\":\"永辉生活商行编码2\",\n" +
                "    \"yhlife_group_name2\":\"永辉生活商行名称2\",\n" +
                "    \"species_group_code\":\"永辉生活物种编码\",\n" +
                "    \"species_group_name\":\"永辉生活物种名称\",\n" +
                "    \"species_group_code1\":\"永辉生活物种编码1\",\n" +
                "    \"species_group_name1\":\"永辉生活物种名称1\",\n" +
                "    \"species_group_code2\":\"永辉生活物种编码2\",\n" +
                "    \"species_group_name2\":\"永辉生活物种名称2\",\n" +
                "    \"dept_code\":\"部类编码\",\n" +
                "    \"dept_name\":\"部类名称\",\n" +
                "    \"goods_dept_code\":\"食用品划分代码\",\n" +
                "    \"goods_dept_name\":\"食用品划分名称\",\n" +
                "    \"small_shop_code\":\"小店编码\",\n" +
                "    \"small_shop_name\":\"小店名称\",\n" +
                "    \"update_time\":\"更新时间\"\n" +
                "  }\n" +
                "}\n";
        String dimShopJsonStr="{\n" +
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
        List<String> jsonStrList = new ArrayList<>();
        jsonStrList.add(dimCategoryJsonStr);
        jsonStrList.add(dimShopJsonStr);
        return jsonStrList;
    }

    private static String createJsonStr() {
        String dimShopJsonStr = "\n" +
                "{\n" +
                "\"dbName\": \"dim\", \"tableList\": [{\t\n" +
                "  \"tabName\": \"dim_shop\",\"columns\": [\t\n" +
                "      {\"fieldsName\": \"dim_shop_id\",\"type\": \"string\",\"Comment\": \"门店ID\"},\n" +
                "      {\"fieldsName\": \"shop_code\",\"type\": \"string\",\"Comment\": \"门店编码\"},\n" +
                "      {\"fieldsName\": \"branch_code\",\"type\": \"string\",\"Comment\": \"分公司编码\"},\n" +
                "      {\"fieldsName\": \"branch_name\",\"type\": \"string\",\"Comment\": \"分公司名称\"},\n" +
                "      {\"fieldsName\": \"shop_name\",\"type\": \"string\",\"Comment\": \"门店名称\"},\n" +
                "      {\"fieldsName\": \"department_id\",\"type\": \"string\",\"Comment\": \"部门ID\"},\n" +
                "      {\"fieldsName\": \"department_code\",\"type\": \"string\",\"Comment\": \"部门编码\"},\n" +
                "      {\"fieldsName\": \"department_name\",\"type\": \"string\",\"Comment\": \"部门名称\"},\n" +
                "      {\"fieldsName\": \"province_id\",\"type\": \"string\",\"Comment\": \"省id\"},\n" +
                "      {\"fieldsName\": \"province_code\",\"type\": \"string\",\"Comment\": \"省编码\"},\n" +
                "      {\"fieldsName\": \"province_name\",\"type\": \"string\",\"Comment\": \"省名称\"},\n" +
                "      {\"fieldsName\": \"city_code\",\"type\": \"string\",\"Comment\": \"城市编码\"},\n" +
                "      {\"fieldsName\": \"city_name\",\"type\": \"string\",\"Comment\": \"城市名称\"},\n" +
                "      {\"fieldsName\": \"area_id\",\"type\": \"string\",\"Comment\": \"区/县id\"},\n" +
                "      {\"fieldsName\": \"area_name\",\"type\": \"string\",\"Comment\": \"区/县名称\"},\n" +
                "      {\"fieldsName\": \"open_date\",\"type\": \"string\",\"Comment\": \"开店日期\"},\n" +
                "      {\"fieldsName\": \"close_date\",\"type\": \"string\",\"Comment\": \"闭店日期\"},\n" +
                "      {\"fieldsName\": \"status\",\"type\": \"string\",\"Comment\": \"门店状态\"},\n" +
                "      {\"fieldsName\": \"seller_type_id\",\"type\": \"string\",\"Comment\": \"业态类型\"},\n" +
                "      {\"fieldsName\": \"seller_type_name\",\"type\": \"string\",\"Comment\": \"业态类型名称\"},\n" +
                "      {\"fieldsName\": \"seller_id\",\"type\": \"string\",\"Comment\": \"业态id\"},\n" +
                "      {\"fieldsName\": \"seller_name\",\"type\": \"string\",\"Comment\": \"业态名称\"},\n" +
                "      {\"fieldsName\": \"actual_shop_id\",\"type\": \"string\",\"Comment\": \"报表实际编码\"},\n" +
                "      {\"fieldsName\": \"are_code\",\"type\": \"string\",\"Comment\": \"营运大区ID\"},\n" +
                "      {\"fieldsName\": \"are_name\",\"type\": \"string\",\"Comment\": \"营运大区名称\"},\n" +
                "      {\"fieldsName\": \"org_code\",\"type\": \"string\",\"Comment\": \"大区ID\"},\n" +
                "      {\"fieldsName\": \"org_name\",\"type\": \"string\",\"Comment\": \"大区名称\"},\n" +
                "      {\"fieldsName\": \"region_code\",\"type\": \"string\",\"Comment\": \"区域ID\"},\n" +
                "      {\"fieldsName\": \"region_name\",\"type\": \"string\",\"Comment\": \"区域名称\"},\n" +
                "      {\"fieldsName\": \"fin_open_date\",\"type\": \"string\",\"Comment\": \"财务维护开业时间\"},\n" +
                "      {\"fieldsName\": \"actual_open_date\",\"type\": \"string\",\"Comment\": \"实际开业时间\"},\n" +
                "      {\"fieldsName\": \"longitude\",\"type\": \"string\",\"Comment\": \"经度\"},\n" +
                "      {\"fieldsName\": \"latitude\",\"type\": \"string\",\"Comment\": \"纬度\"},\n" +
                "      {\"fieldsName\": \"p_price_code\",\"type\": \"string\",\"Comment\": \"进价群编码\"},\n" +
                "      {\"fieldsName\": \"p_price_name\",\"type\": \"string\",\"Comment\": \"进价群名称\"},\n" +
                "      {\"fieldsName\": \"address\",\"type\": \"string\",\"Comment\": \"门店地址\"},\n" +
                "      {\"fieldsName\": \"source\",\"type\": \"string\",\"Comment\": \"门店来源 1 辉创 2 WMS 3其他(现有门店)\"},\n" +
                "      {\"fieldsName\": \"is_hc_enabled\",\"type\": \"string\",\"Comment\": \"是否启用辉创系统 0 否 1 是 2 永久启用辉创\"},\n" +
                "      {\"fieldsName\": \"shop_type\",\"type\": \"string\",\"Comment\": \"门店类型 1:门店 2:配送中心\"},\n" +
                "      {\"fieldsName\": \"update_time\",\"type\": \"timestamp\",\"Comment\": \"更新时间\"}\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"tabName\": \"dim_category\",\"columns\": [\t\n" +
                "      {\"fieldsName\": \"dim_category_id\",\"type\": \"string\",\"Comment\": \"品类id\"},\n" +
                "      {\"fieldsName\": \"category_code\",\"type\": \"string\",\"Comment\": \"品类编码\"},\n" +
                "      {\"fieldsName\": \"category_name\",\"type\": \"string\",\"Comment\": \"品类名称\"},\n" +
                "      {\"fieldsName\": \"mcategory_code\",\"type\": \"string\",\"Comment\": \"中类编码\"},\n" +
                "      {\"fieldsName\": \"mcategory_name\",\"type\": \"string\",\"Comment\": \"中类名称\"},\n" +
                "      {\"fieldsName\": \"bcategory_code\",\"type\": \"string\",\"Comment\": \"大类编码\"},\n" +
                "      {\"fieldsName\": \"bcategory_name\",\"type\": \"string\",\"Comment\": \"大类名称\"},\n" +
                "      {\"fieldsName\": \"workshop_code\",\"type\": \"string\",\"Comment\": \"工坊编码\"},\n" +
                "      {\"fieldsName\": \"work_shop_name\",\"type\": \"string\",\"Comment\": \"工坊名称\"},\n" +
                "      {\"fieldsName\": \"old_workshop_code\",\"type\": \"string\",\"Comment\": \"旧工坊编码\"},\n" +
                "      {\"fieldsName\": \"old_work_shop_name\",\"type\": \"string\",\"Comment\": \"旧工坊名称\"},\n" +
                "      {\"fieldsName\": \"small_group_code\",\"type\": \"string\",\"Comment\": \"小商行编码\"},\n" +
                "      {\"fieldsName\": \"small_group_name\",\"type\": \"string\",\"Comment\": \"小商行名称\"},\n" +
                "      {\"fieldsName\": \"class_code\",\"type\": \"string\",\"Comment\": \"课组编码\"},\n" +
                "      {\"fieldsName\": \"class_name\",\"type\": \"string\",\"Comment\": \"课组名称\"},\n" +
                "      {\"fieldsName\": \"class_group_code\",\"type\": \"string\",\"Comment\": \"课组集编码\"},\n" +
                "      {\"fieldsName\": \"class_group_name\",\"type\": \"string\",\"Comment\": \"课组集名称\"},\n" +
                "      {\"fieldsName\": \"group_code\",\"type\": \"string\",\"Comment\": \"商行编码\"},\n" +
                "      {\"fieldsName\": \"group_name\",\"type\": \"string\",\"Comment\": \"商行名称\"},\n" +
                "      {\"fieldsName\": \"yhlife_group_code\",\"type\": \"string\",\"Comment\": \"永辉生活商行编码\"},\n" +
                "      {\"fieldsName\": \"yhlife_group_name\",\"type\": \"string\",\"Comment\": \"永辉生活商行名称\"},\n" +
                "      {\"fieldsName\": \"yhlife_group_code1\",\"type\": \"string\",\"Comment\": \"永辉生活商行编码1\"},\n" +
                "      {\"fieldsName\": \"yhlife_group_name1\",\"type\": \"string\",\"Comment\": \"永辉生活商行名称1\"},\n" +
                "      {\"fieldsName\": \"yhlife_group_code2\",\"type\": \"string\",\"Comment\": \"永辉生活商行编码2\"},\n" +
                "      {\"fieldsName\": \"yhlife_group_name2\",\"type\": \"string\",\"Comment\": \"永辉生活商行名称2\"},\n" +
                "      {\"fieldsName\": \"species_group_code\",\"type\": \"string\",\"Comment\": \"永辉生活物种编码\"},\n" +
                "      {\"fieldsName\": \"species_group_name\",\"type\": \"string\",\"Comment\": \"永辉生活物种名称\"},\n" +
                "      {\"fieldsName\": \"species_group_code1\",\"type\": \"string\",\"Comment\": \"永辉生活物种编码1\"},\n" +
                "      {\"fieldsName\": \"species_group_name1\",\"type\": \"string\",\"Comment\": \"永辉生活物种名称1\"},\n" +
                "      {\"fieldsName\": \"species_group_code2\",\"type\": \"string\",\"Comment\": \"永辉生活物种编码2\"},\n" +
                "      {\"fieldsName\": \"species_group_name2\",\"type\": \"string\",\"Comment\": \"永辉生活物种名称2\"},\n" +
                "      {\"fieldsName\": \"dept_code\",\"type\": \"string\",\"Comment\": \"部类编码\"},\n" +
                "      {\"fieldsName\": \"dept_name\",\"type\": \"string\",\"Comment\": \"部类名称\"},\n" +
                "      {\"fieldsName\": \"goods_dept_code\",\"type\": \"string\",\"Comment\": \"食用品划分代码\"},\n" +
                "      {\"fieldsName\": \"goods_dept_name\",\"type\": \"string\",\"Comment\": \"食用品划分名称\"},\n" +
                "      {\"fieldsName\": \"small_shop_code\",\"type\": \"string\",\"Comment\": \"小店编码\"},\n" +
                "      {\"fieldsName\": \"small_shop_name\",\"type\": \"string\",\"Comment\": \"小店名称\"},\n" +
                "      {\"fieldsName\": \"update_time\",\"type\": \"timestamp\",\"Comment\": \"更新时间\"}\n" +
                "  ]}\n" +
                "]\n" +
                "}\n";
        System.out.println(dimShopJsonStr);
        return dimShopJsonStr;
    }
}

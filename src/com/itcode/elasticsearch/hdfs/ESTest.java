package com.itcode.elasticsearch.hdfs;

import java.util.ArrayList;
import java.util.List;

public class ESTest {
    public static void main(String[] args) {
//        hdfs_insert();
        business_insert();
    }

    /**
     * 插入hdfs元数据信息到ES中
     */
    private static void hdfs_insert() {
        //        ESUtils.createIndex("hdfs");
//        String jsonStr = createJsonStr();
        ESUtils.insertSingle(createJsonStr(), "hdfs", "dm_cjwz");
//        ESUtils.insertMulti(createJsonList(), "hdfs", "dim");
        ESUtils.search("hdfs", "dm_cjwz");
    }

    /**
     * 插入业务数据信息到ES中
     */
    private static void business_insert() {
//        ESUtils.createIndex("business", "bs");
//        ESUtils.insertSingle(createJsonStr(), "business", "供应链");
        ESUtils.search("business", "供应链");
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
        List<String> jsonStrList = new ArrayList<>();
        jsonStrList.add(dimCategoryJsonStr);
        jsonStrList.add(dimShopJsonStr);
        return jsonStrList;
    }

    private static String createJsonStr() {
        String dimShopJsonStr = "{\"businessName\": \"供应链\", \"db_tableList\": [\n" +
                "  {\"tableName\": \"dim\",\"tables\": [\n" +
                "      {\"tableName\": \"dim_shop\",\"Comment\": \"门店维表\"},\n" +
                "      {\"tableName\": \"dim_category\",\"Comment\": \"种类维表\"},\n" +
                "      {\"tableName\": \"dim_channel\" ,\"Comment\": \"渠道维表\"}]},\n" +
                "  {\"tableName\": \"dw\",\"tables\": [\n" +
                "      {\"tableName\": \"dwb_fct_order\",\"Comment\": \"订单表\"},\n" +
                "      {\"tableName\": \"dwb_fct_order_header\",\"Comment\": \"订单头表\"}]},\n" +
                "  {\"tableName\": \"dm\",\"tables\": [\n" +
                "    {\"tableName\": \"dm_member_sale_date\" ,\"Comment\": \"会员销售日期表\"}]}]\n" +
                "}";
//        String dimShopJsonStr ="";
        System.out.println(dimShopJsonStr);
        return dimShopJsonStr;
    }
}

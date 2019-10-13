//package com.example.springbootdemo;
//
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.IndicesClient;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @program: springbootdemo
// * @description: es测试类
// * @author: andy
// * @create: 2019-10-11 11:26
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class TestESRestClient {
//
//    @Autowired
//    RestHighLevelClient restHighLevelClient;    //ES连接对象
//
//    @Autowired
//    RestClient restClient;
//
//    /**
//    *@Description:  添加索引库
//    *@Param: []
//    *@return: void
//    *@Author: andy
//    *@date: 2019/10/11
//    */
//    @Test
//    public void testCreateIndex() throws IOException {
//        CreateIndexRequest request = new CreateIndexRequest("xc_class");
//        /**
//         * {
//         * 	"settings":{
//         * 		"index":{
//         * 			"number_of_shards":1,
//         * 			"number_of_replicas":0
//         *       }
//         *    }
//         * }
//         */
//        request.settings(Settings.builder().put("number_of_shards", 1).put("number_of_replicas", 0));
//        IndicesClient indicesClient = restHighLevelClient.indices();    //通过ES连接对象获取索引库管理对象
//        CreateIndexResponse response = indicesClient.create(request);
//        System.out.println(response.isAcknowledged());  //操作是否成功
//    }
//    /**
//    *@Description: 删除索引库
//    *@Param:
//    *@return:
//    *@Author: andy
//    *@date: 2019/10/11
//    */
//    @Test
//    public void testDeleteIndex() throws IOException {
//        DeleteIndexRequest request = new DeleteIndexRequest("xc_class");
//        IndicesClient indicesClient = restHighLevelClient.indices();
//        DeleteIndexResponse response = indicesClient.delete(request);
//        System.out.println(response.isAcknowledged());
//    }
//
//    /**
//    *@Description:创建索引库时指定映射
//    *@Param: []
//    *@return: void
//    *@Author: andy
//    *@date: 2019/10/11
//    */
//    @Test
//    public void testCreateIndexWithMapping() throws IOException {
//        CreateIndexRequest request = new CreateIndexRequest("xc_class");
//        request.settings(Settings.builder().put("number_of_shards", 1).put("number_of_replicas", 0));
//        request.mapping("doc", "{\n" +
//                "    \"properties\": {\n" +
//                "        \"name\": {\n" +
//                "            \"type\": \"text\",\n" +
//                "            \"analyzer\": \"ik_max_word\",\n" +
//                "            \"search_analyzer\": \"ik_smart\"\n" +
//                "        },\n" +
//                "        \"price\": {\n" +
//                "            \"type\": \"scaled_float\",\n" +
//                "            \"scaling_factor\": 100\n" +
//                "        },\n" +
//                "        \"timestamp\": {\n" +
//                "            \"type\": \"date\",\n" +
//                "            \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd\"\n" +
//                "        }\n" +
//                "    }\n" +
//                "}", XContentType.JSON);
//        IndicesClient indicesClient = restHighLevelClient.indices();
//        CreateIndexResponse response = indicesClient.create(request);
//        System.out.println(response.isAcknowledged());
//    }
//
//    /**
//    *@Description:   添加文档
//    *@Param:
//    *@return:
//    *@Author: andy
//    *@date: 2019/10/11
//    */
//    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    @Test
//    public void testAddDocument() throws IOException {
//        Map<String, Object> jsonMap = new HashMap<>();
//        jsonMap.put("name", "Java核心技术");
//        jsonMap.put("price", 66.6);
//        jsonMap.put("timestamp", FORMAT.format(new Date()));
//        IndexRequest request = new IndexRequest("xc_class", "doc","2");
//        request.source(jsonMap);
//        IndexResponse response = restHighLevelClient.index(request);
//        System.out.println(response);
//    }
//    /**
//    *@Description: 根据id查询文档
//    *@Param: []
//    *@return: void
//    *@Author: andy
//    *@date: 2019/10/11
//    */
//    @Test
//    public void testFindById() throws IOException {
//        GetRequest request = new GetRequest("xc_class", "doc", "2");
//        GetResponse response = restHighLevelClient.get(request);
//        System.out.println(response);
//    }
//    /**
//    *@Description: 根据id删除文档
//    *@Param: []
//    *@return: void
//    *@Author: andy
//    *@date: 2019/10/11
//    */
//    @Test
//    public void testDeleteDoc() throws IOException {
//        DeleteRequest request = new DeleteRequest("xc_class", "doc", "Ng7xuG0BDGljUW47IvKV");
//        DeleteResponse response = restHighLevelClient.delete(request);
//        System.out.println(response);
//    }
//
//    /**
//    *@Description:  根据id更新文档   ES更新文档有两种方式：全量替换和局部更新
//    *@Param: []       全量替换：ES首先会根据id查询文档并删除然后将该id作为新文档的id插入。
//     *                  局部更新：只会更新相应字段
//    *@return: void
//    *@Author: andy
//    *@date: 2019/10/11
//    */
//
//    @Test
//    public void testUpdateDoc() throws IOException {
//        UpdateRequest request = new UpdateRequest("xc_class", "doc", "2");
//        Map<String, Object> docMap = new HashMap<>();
//        docMap.put("name", "Spring核心技术");
//        docMap.put("price", 99.8);
//        docMap.put("timestamp", FORMAT.format(new Date(System.currentTimeMillis())));
//        request.doc(docMap);
//        UpdateResponse response = restHighLevelClient.update(request);
//        System.out.println(response);
//        testFindById();
//    }
//
//}

package com.nof.chatbot.api.test;


import org.apache.hc.core5.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;


/**
*单元测试
*@author Nof
*@date 2023-03-15 14:03:08
*/

public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");
        get.addHeader("cookie","sensorsdata2015jssdkcross={\"distinct_id\":\"185aea720987e3-03e3d62419909c4-26021151-1327104-185aea72099204\",\"first_id\":\"\",\"props\":{\"$latest_traffic_source_type\":\"引荐流量\",\"$latest_search_keyword\":\"未取到值\",\"$latest_referrer\":\"https://www.yuque.com/\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg1YWVhNzIwOTg3ZTMtMDNlM2Q2MjQxOTkwOWM0LTI2MDIxMTUxLTEzMjcxMDQtMTg1YWVhNzIwOTkyMDQifQ==\",\"history_login_id\":{\"name\":\"\",\"value\":\"\"},\"$device_id\":\"185aea720987e3-03e3d62419909c4-26021151-1327104-185aea72099204\"}; zsxq_access_token=BBDFA1D7-9B4A-7B9C-B226-43BC0061AD4D_BE89622F42EF7C54; abtest_env=product; zsxqsessionid=f0153ec262305fb74342d22d1f6aca9e");
        get.addHeader("Content-Type","application/json, text/plain, */*");
        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String res= EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }
        else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://api.zsxq.com/v2/topics/412812118155458/comments");
        httpPost.addHeader("cookie","sensorsdata2015jssdkcross={\"distinct_id\":\"185aea720987e3-03e3d62419909c4-26021151-1327104-185aea72099204\",\"first_id\":\"\",\"props\":{\"$latest_traffic_source_type\":\"引荐流量\",\"$latest_search_keyword\":\"未取到值\",\"$latest_referrer\":\"https://www.yuque.com/\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg1YWVhNzIwOTg3ZTMtMDNlM2Q2MjQxOTkwOWM0LTI2MDIxMTUxLTEzMjcxMDQtMTg1YWVhNzIwOTkyMDQifQ==\",\"history_login_id\":{\"name\":\"\",\"value\":\"\"},\"$device_id\":\"185aea720987e3-03e3d62419909c4-26021151-1327104-185aea72099204\"}; zsxq_access_token=BBDFA1D7-9B4A-7B9C-B226-43BC0061AD4D_BE89622F42EF7C54; abtest_env=product; zsxqsessionid=f0153ec262305fb74342d22d1f6aca9e");
        httpPost.addHeader("Content-Type","application/json, text/plain, */*");
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"zhendemale\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String res= EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }
        else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }


}

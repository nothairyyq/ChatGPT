package com.priska.chatgptapi;

import com.priska.chatgptapi.domain.security.service.JwtUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ApiTest {
    @Test
    public void test_jwt() {
        JwtUtil util = new JwtUtil("Priska", SignatureAlgorithm.HS256);
        // 以tom作为秘钥，以HS256加密
        Map<String, Object> map = new HashMap<>();
        map.put("username", "Priska");
        map.put("password", "123");
        map.put("age", 100);

        String jwtToken = util.encode("Priska", 30000, map);

        util.decode(jwtToken).forEach((key, value) -> System.out.println(key + ": " + value));
    }

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 用获取的 token 替换，默认有效期60分钟。地址非长期有效，只做学习验证。如果api.xfg.im链接已不存在，一种是可以在aws服务部署个自己的。另外是继续往下走就可以不耽误课程学习。
        HttpPost post = new HttpPost("https://api.xfg.im/b8b6/v1/completions?token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4ZmciLCJleHAiOjE2ODMyMDQzMzYsImlhdCI6MTY4MzIwMDczNiwianRpIjoiYjM2Njg3ZjgtOWM5Yi00NzE1LWI2ZjctYjM0YmEyNzE2MWE3IiwidXNlcm5hbWUiOiJ4ZmcifQ.qBskmFVqx_0CKXdhtuSpqWn6XqB5Qq1Qu-c6_4-UoDg");

        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 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");

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        System.out.println("请求入参：" + paramJson);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            // api.xfg.im:443 requested authentication 表示 token 错误或者过期。
            System.out.println("测试结果：" + res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void test_chatGPT_3_5() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            // 如果你有自己的apihost、apikey也可以替换使用。
            HttpPost httpPost = new HttpPost("https://service-d6wuqy4n-1320869466.cd.apigw.tencentcs.com/v1/chat/completions");
            String json = "{\n" +
                    "    \"model\": \"gpt-3.5-turbo-1106\",\n" +
                    "    \"max_tokens\": 1024,\n" +
                    "    \"messages\": [\n" +
                    "        {\n" +
                    "            \"role\": \"user\",\n" +
                    "            \"content\": [\n" +
                    "                {\n" +
                    "                    \"text\": \"写个java冒泡排序\",\n" +
                    "                    \"type\": \"text\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);

            httpPost.setEntity(requestEntity);
            httpPost.setHeader("Authorization", "Bearer 阅读链接评论置顶第一条获取key https://t.zsxq.com/163o5FKvc");
            httpPost.setHeader("Content-Type", "application/json");

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                // 打印响应体的内容
                String result = EntityUtils.toString(responseEntity);
                System.out.println(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }

        // 等待
        countDownLatch.await();
    }
}

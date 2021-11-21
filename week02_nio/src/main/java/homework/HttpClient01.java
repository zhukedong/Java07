package homework;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;


public class HttpClient01 {
    public static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String httpPost(String url, String json) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        //设置content_type
        httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
        //设置请求参数
        StringEntity entity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(entity);

        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpPost);
            System.out.println(response.getStatusLine());
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                response.close();
            }
        }
        return null;
    }

    public static String httpGet(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpGet);
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=response){
                response.close();
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        String url = "http://127.0.0.1:8801/";
        String text = httpGet(url);
        System.out.println("url: " + url + " ; response: \n" + text);
    }
}

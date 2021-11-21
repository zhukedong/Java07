package homework;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OKClient01 {
    public static OkHttpClient client = new OkHttpClient();
    public static String okClinetGetHelper(String url) {
        Request request =   new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }
    public static void main(String[] args) {
        String url = "https://localhost:8801";
        String text = OKClient01.okClinetGetHelper(url);
        System.out.println("url: " + url + " ; response: \n" + text);

        // 清理资源
        OKClient01.client = null;
    }
}

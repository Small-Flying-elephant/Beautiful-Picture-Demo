package com.example.hwx631346.beautifulpicturedemo.network;

import android.util.Log;

import com.example.hwx631346.beautifulpicturedemo.bean.SisterBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SisterApi {

    private static final String TAG = "Network";

    private static final String BASE_URL = "http://gank.io/api/data/福利/";
    private static final OkHttpClient client = new OkHttpClient();
    private static boolean isOk = false;
    /**
     * 查詢妹子信息
     */
    public static ArrayList<SisterBean> fetchSister(int count, int page) {
        String fetchUrl = BASE_URL + count + "/" + page;
        ArrayList<SisterBean> sisters = new ArrayList<>();
        try {
            URL url = new URL(fetchUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream in = conn.getInputStream();
                byte[] data = readFromStream(in);
                String result = new String(data, "UTF-8");
                sisters = parseSister(result);
            } else {
                Log.e(TAG, "请求失败：" + code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sisters;
    }


    private static ArrayList<SisterBean> parseSister(String result) throws JSONException {
        ArrayList<SisterBean> sisterBeans = new ArrayList<>();
        JSONObject object = new JSONObject(result);
        JSONArray array = object.getJSONArray("results");
        for (int i = 0; i < array.length(); i++) {
            JSONObject results = (JSONObject) array.get(i);
            SisterBean sister = new SisterBean();
            sister.set_id(results.getString("_id"));
            sister.setCreateAt(results.getString("createdAt"));
            sister.setDesc(results.getString("desc"));
            sister.setPublishedAt(results.getString("publishedAt"));
            sister.setSource(results.getString("source"));
            sister.setType(results.getString("type"));
            sister.setUrl(results.getString("url"));
            sister.setUsed(results.getBoolean("used"));
            sister.setWho(results.getString("who"));
            sisterBeans.add(sister);
        }
        return sisterBeans;
    }

    private static byte[] readFromStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        return outputStream.toByteArray();
    }



    public static boolean urlIsOk(String URL) throws Exception {

        Request request = new Request.Builder()
                .url(URL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                isOk = false;
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    isOk = false;
                    Log.d("linsheng", "onResponse: ");
                } else {
                    Log.d("linsheng", "onResponse: asdfasdfasdfaf");
                    isOk = true;
                }
//                Headers responseHeaders = response.headers();
//                for (int i = 0; i < responseHeaders.size(); i++) {
//                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                }
//                System.out.println(response.body().string());
            }
        });
        Log.d("linsheng", "urlIsOk: " + isOk  + "  " +URL);
        return isOk;
    }
}

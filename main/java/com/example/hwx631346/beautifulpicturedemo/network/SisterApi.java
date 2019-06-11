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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SisterApi {

    private static final String TAG = "Network";

    private static final String BASE_URL = "http://gank.io/api/data/福利/";

    /**
     * 查詢妹子信息
     */
    public static ArrayList<SisterBean> fetchSister(int count, int page) {
        String fetchUrl = BASE_URL + count + "/" + page;
        Log.d("linsheng", "fetchSister: " + count  + page + fetchUrl);
        ArrayList<SisterBean> sisters = new ArrayList<>();
        try {
            URL url = new URL(fetchUrl);
            Log.d("linsheng", "fetchSister: " + url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.d("linsheng", "fetchSister: " + conn);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            Log.d("linsheng", "fetchSister: " + code);
            if (code == 200) {
                InputStream in = conn.getInputStream();
                byte[] data = readFromStream(in);
                String result = new String(data, "UTF-8");
                sisters = parseSister(result);
            } else {
                Log.e(TAG, "请求失败：" + code);
                Log.d("linsheng", "请求失败 " + code);
            }
        } catch (Exception e) {
            Log.d("linsheng", "fetchSister: dsd" + e.getMessage() + e);
            e.printStackTrace();
        }
        return sisters;
    }



    private static ArrayList<SisterBean> parseSister(String result) throws JSONException {
        ArrayList<SisterBean> sisterBeans = new ArrayList<>();
        JSONObject object = new JSONObject(result);
        JSONArray array = object.getJSONArray("results");
        for (int i = 0 ; i < array.length() ; i++){
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
        int len ;
        while ((len = inputStream.read(buffer)) != -1){
            outputStream.write(buffer,0,len);
        }
        inputStream.close();
        return outputStream.toByteArray();
    }
}

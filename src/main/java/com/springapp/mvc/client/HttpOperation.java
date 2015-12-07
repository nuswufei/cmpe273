package com.springapp.mvc.client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by WU on 15/11/2015.
 */
public class HttpOperation {
    static HttpClient httpClient = HttpClientBuilder.create().build();
    static ObjectMapper jacksonObjectMapper= new ObjectMapper();
    public synchronized static void post(String url, Object object) {
        try {
            String jsonString = jacksonObjectMapper.writeValueAsString(object);
            HttpPost request = new HttpPost(url);
            StringEntity params =new StringEntity(jsonString, ContentType.create("application/json"));
            request.addHeader("Content-Type", "application/json");
            request.setEntity(params);
            HttpResponse httpResponse = httpClient.execute(request);
            httpResponse.getStatusLine();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static String get(String url) {
        HttpGet request = new HttpGet(url);
        String responseString = null;
        try {
            HttpResponse response = httpClient.execute(request);
            responseString = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public static void delete(String url, String id) {
        HttpDelete request = new HttpDelete(url + "/" + id);
        try {
            httpClient.execute(request);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
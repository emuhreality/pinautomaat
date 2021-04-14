package services;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author E. Koo
 */
public class UniversalPostRequest {

    /**
     * Universal Post Request
     * @param params given input
     * @param path for request
     * @return Json response
     */
    public String postRequest(Map<String, String> params, String path){
        // map hashmap naar Json object
        Gson gson = new Gson();
        String jsonBody = gson.toJson(params);
        String responseBody = "niet geldig";

        // create HttpClient Object
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // Create HttPost Object
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/" + path);

        // request parameters and other properties
        httpPost.setHeader("Content-type", "application/json");
        try {
            httpPost.setEntity(new StringEntity(jsonBody));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            InputStream inputStream = httpResponse.getEntity().getContent();
            responseBody = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            httpClient.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return responseBody;
    }
}

package mypackages;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Authenticate {
    public static String apiCall(String apiurl, String method, String body) {
        try {
            URL url = new URL(apiurl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // String body = "{'login_id' : 'test@sunbasedata.com','password' :'Test@123'}";

            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                byte[] postDataBytes = body.getBytes(StandardCharsets.UTF_8);
                wr.write(postDataBytes);
            }

            int responseCode = connection.getResponseCode();
            String respCode = String.valueOf(responseCode);
            System.out.println("Response Code: " + respCode);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.toString());
                System.out.println("Authentication Successful!");
                String value = jsonNode.get("access_token").asText();
                return value;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

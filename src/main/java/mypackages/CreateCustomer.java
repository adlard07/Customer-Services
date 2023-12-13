package mypackages;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CreateCustomer {
    public static void createCustomer(String apiurl, String method, String accessToken, String parameters, String header) {
        try {
            String newapi = apiurl + parameters;
            URL url = new URL(newapi);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                byte[] postDataBytes = header.getBytes(StandardCharsets.UTF_8);
                wr.write(postDataBytes);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Create Customer Response Code: " + responseCode);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuilder resp = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    resp.append(line);
                }
                System.out.println("Customer Created!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

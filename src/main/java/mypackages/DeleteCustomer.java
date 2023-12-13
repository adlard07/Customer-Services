package mypackages;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteCustomer {
    public static void deleteCustomer(String apiurl, String method, String accessToken, String deleteParams, String uuid) {
        try {
            String newapi = apiurl + deleteParams + uuid;
            URL url = new URL(newapi);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            System.out.println("Delete Customer Response Code: " + responseCode);

            StringBuilder responseStringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    responseStringBuilder.append(line);
                }
            } System.out.println("Customer Deleted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package mypackages;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetCustomers {
    
    public static String getCustomer(String apiurl, String accessToken, String getMethod, String params){
        try{
            String newapi = apiurl + params;
            URL url = new URL(newapi);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(getMethod);
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                System.out.println("Process Complete!");
                return response.toString();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

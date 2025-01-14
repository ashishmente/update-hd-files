import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class IP {
    public static void main(String[] args) {
        String ipAddress = "24.48.0.1"; // Replace with the IP address you want to query
        String apiUrl = "http://ip-api.com/json/" + ipAddress;

        try {
            // Create a URL object
			URL url = new URL(apiUrl);

            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Extract the country
                String country = jsonResponse.getString("country");
                String countryCode = jsonResponse.getString("countryCode");

                System.out.println("Country: " + country);
                System.out.println("Country Code: " + countryCode);
            } else {
                System.out.println("Error: Unable to fetch data. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

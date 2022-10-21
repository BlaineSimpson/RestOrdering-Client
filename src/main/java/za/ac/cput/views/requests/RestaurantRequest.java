package za.ac.cput.views.requests;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Restaurant;
import za.ac.cput.factory.RestaurantFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRequest {
    private static OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static String getRequest(String URL) throws IOException {
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("Authorization", Credentials.basic("User", "1234"))
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static List<Object> getAll(){
        List<Object> restaurantList = new ArrayList<>();
        try{
            final String URL = "http://localhost:8080/restaurant/restaurant/all";
            String responseBody = getRequest(URL);
            JSONArray restaurant = new JSONArray(responseBody);

            for (int i = 0; i < restaurant.length(); i++) {
                JSONObject restaurantObject = restaurant.getJSONObject(i);
                Gson g = new Gson();
                Object a = g.fromJson(restaurantObject.toString(), Restaurant.class);
                restaurantList.add(a);
                System.out.println(a.toString());
            }
            System.out.println("\n");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return restaurantList;
    }


    public static void viewById(String restaurantId) {
        try {
            final String URL = "http://localhost:8080/restaurant/restaurant/find/" + restaurantId;
            String responseBody = getRequest(URL);

            System.out.println(responseBody);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String postRequest(final String url, String json) throws IOException{
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", Credentials.basic("User", "1234"))
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void save(String restaurantId, String restaurantName, String restaurantAddress, Boolean isOpen){
        try{
            final String URL = "http://localhost:8080/restaurant/restaurant/save";
            Restaurant restaurant = RestaurantFactory.createRestaurant(restaurantId, restaurantName,restaurantAddress,isOpen );
            Gson g = new Gson();
            String jsonString = g.toJson(restaurant);
            String r = postRequest(URL, jsonString);
            if (r != null)
                JOptionPane.showMessageDialog(null, "Successfully saved");
            else
                JOptionPane.showMessageDialog(null, "Sorry, could not save");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static String deleteRequest(String url, String restaurantId) throws IOException{
        String deleteUrl = url + restaurantId;
        Request request = new Request.Builder()
                .url(deleteUrl)
                .delete()
                //.addHeader("Authorization", Credentials.basic("User", "1234"))
                .build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteAdmin(String restaurantId) {
        try {
            final String URL = "http://localhost:8080/restaurant/restaurant/delete/";
            final String getId = restaurantId;
            deleteRequest(URL, getId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

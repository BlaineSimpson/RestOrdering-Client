package za.ac.cput.views.requests;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.ItemOrder;
import za.ac.cput.factory.ItemOrderFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemOrderRequest {

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

    public static List<Object> getAll() {
        List<Object> itemOrderList = new ArrayList<>();
        try {
            final String URL = "http://localhost:8080/restaurant/orderItem/all";
            String responseBody = getRequest(URL);
            JSONArray itemOrders = new JSONArray(responseBody);

            for (int i = 0; i < itemOrders.length(); i++) {
                JSONObject itemOrderObject = itemOrders.getJSONObject(i);
                Gson g = new Gson();
                Object a = g.fromJson(itemOrderObject.toString(), ItemOrder.class);
                itemOrderList.add(a);
                System.out.println(a.toString());
            }
            System.out.println("\n");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return itemOrderList;
    }

    public static void viewById(String id){
        try{
            final String URL = "http://localhost:8080/restaurant/orderItem/find/" + id;
            String responseBody = getRequest(URL);

            System.out.println(responseBody);

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String postRequest(final String url, String json) throws IOException {
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

    public static void save(String itemId, String itemName, String numberOfPlates) {
        try {
            final String URL = "http://localhost:8080/restaurant/orderItem/save";
            ItemOrder itemOrder = ItemOrderFactory.createItemOrder(itemId, itemName, numberOfPlates);
            Gson g = new Gson();
            String jsonString = g.toJson(itemOrder);
            String r = postRequest(URL, jsonString);
            if (r != null)
                JOptionPane.showMessageDialog(null, "Successfully saved");
            else
                JOptionPane.showMessageDialog(null, "Sorry, could not save");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static String deleteRequest(String url, String id) throws IOException {
        String deleteUrl = url + id;
        Request request = new Request.Builder()
                .url(deleteUrl)
                .delete()
                .addHeader("Authorization", Credentials.basic("User", "1234")) //fix
                .build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(String id) {
        try {
            final String URL = "http://localhost:8080/restaurant/orderItem/delete/";
            final String getId = id;
            deleteRequest(URL, getId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

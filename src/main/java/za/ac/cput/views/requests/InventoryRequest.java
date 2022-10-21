package za.ac.cput.views.requests;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Inventory;
import za.ac.cput.factory.InventoryFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryRequest {
    private static OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static String getRequest(String URL) throws IOException {
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("Authorization", Credentials.basic("Admin", "1234"))
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static List<Object> getAll(){
        List<Object> inventoryList = new ArrayList<>();
        try{
            final String URL = "http://localhost:8080/restaurant/inventory/all";
            String responseBody = getRequest(URL);
            JSONArray inventory = new JSONArray(responseBody);

            for (int i = 0; i < inventory.length(); i++) {
                JSONObject inventoryObject = inventory.getJSONObject(i);
                Gson g = new Gson();
                Object a = g.fromJson(inventoryObject.toString(), Inventory.class);
                inventoryList.add(a);
                System.out.println(a.toString());
            }
            System.out.println("\n");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return inventoryList;
    }

    public static void viewById(String inv) {
        try {
            final String URL = "http://localhost:8080/restaurant/inventory/find/" + inv;
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
                .addHeader("Authorization", Credentials.basic("Admin", "1234"))
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void save(String inv, String itemName, String category, String vendor, String vendorInv, String vendorPrice){
        try{
            final String URL = "http://localhost:8080/restaurant/inventory/save";
            Inventory inventory = InventoryFactory.createInventory(inv, itemName, category,vendor, vendorInv, vendorPrice);
            Gson g = new Gson();
            String jsonString = g.toJson(inventory);
            String r = postRequest(URL, jsonString);
            if (r != null)
                JOptionPane.showMessageDialog(null, "Successfully saved");
            else
                JOptionPane.showMessageDialog(null, "Sorry, inventory could not save");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static String deleteRequest(String url, String inv) throws IOException{
        String deleteUrl = url + inv;
        Request request = new Request.Builder()
                .url(deleteUrl)
                .delete()
                .addHeader("Authorization", Credentials.basic("Admin", "1234"))
                .build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteAdmin(String inv) {
        try {
            final String URL = "http://localhost:8080/restaurant/inventory/delete/";
            final String getId = inv;
            deleteRequest(URL, getId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

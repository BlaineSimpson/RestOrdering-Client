package za.ac.cput.views.requests;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Supplier;
import za.ac.cput.factory.SupplierFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRequest {
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
        List<Object> supplierList = new ArrayList<>();
        try{
            final String URL = "http://localhost:8080/restaurant/supplier/all";
            String responseBody = getRequest(URL);
            JSONArray supplier = new JSONArray(responseBody);

            for (int i = 0; i < supplier.length(); i++) {
                JSONObject supplierObject = supplier.getJSONObject(i);
                Gson g = new Gson();
                Object a = g.fromJson(supplierObject.toString(), Supplier.class);
                supplierList.add(a);
                System.out.println(a.toString());
            }
            System.out.println("\n");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return supplierList;
    }

    public static void viewById(String suppID) {
        try {
            final String URL = "http://localhost:8080/restaurant/supplier/find/" + suppID;
            String responseBody = getRequest(URL);

            System.out.println(responseBody);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //
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

    public static void save(String suppName, String suppPhysAddress, String suppEmail, String suppPhone,  String suppID){
        try{
            final String URL = "http://localhost:8080/restaurant/supplier/save";
            Supplier supplier = SupplierFactory.createSupplier(suppName, suppPhysAddress, suppEmail, suppPhone, suppID);
            Gson g = new Gson();
            String jsonString = g.toJson(supplier);
            String r = postRequest(URL, jsonString);
            if (r != null)
                JOptionPane.showMessageDialog(null, "Successfully saved");
            else
                JOptionPane.showMessageDialog(null, "Sorry, supplier could not save");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static String deleteRequest(String url, String suppID) throws IOException{
        String deleteUrl = url + suppID;
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

    public static void delete(String suppID) {
        try {
            final String URL = "http://localhost:8080/restaurant/supplier/delete/";
            final String getId = suppID;
            deleteRequest(URL, getId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

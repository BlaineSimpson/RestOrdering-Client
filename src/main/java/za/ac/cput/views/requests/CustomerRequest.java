package za.ac.cput.views.requests;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRequest {

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
        List<Object> customerList = new ArrayList<>();
        try{
            final String URL = "http://localhost:8080/restaurant/customer/all";
            String responseBody = getRequest(URL);
            JSONArray customer = new JSONArray(responseBody);

            for (int i = 0; i < customer.length(); i++) {
                JSONObject customerObject = customer.getJSONObject(i);
                Gson g = new Gson();
                Object a = g.fromJson(customerObject.toString(), Customer.class);
                customerList.add(a);
                System.out.println(a.toString());
            }
            System.out.println("\n");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return customerList;
    }

    public static void viewById(String customerId) {
        try {
            final String URL = "http://localhost:8080/restaurant/customer/find/" + customerId;
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

    public static void save(String cusId, String cusFName, String cusLName,String cusEmail,String cusAddress ){
        try{
            final String URL = "http://localhost:8080/restaurant/customer/save";
            Customer customer = CustomerFactory.createCustomer(cusId, cusFName, cusLName, cusEmail, cusAddress);
            Gson g = new Gson();
            String jsonString = g.toJson(customer);
            String r = postRequest(URL, jsonString);
            if (r != null)
                JOptionPane.showMessageDialog(null, "Successfully saved");
            else
                JOptionPane.showMessageDialog(null, "Sorry, customer could not save");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static String deleteRequest(String url, String cusId) throws IOException{
        String deleteUrl = url + cusId;
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

    public static void delete(String cusId) {
        try {
            final String URL = "http://localhost:8080/restaurant/customer/delete/";
            final String getId = cusId;
            deleteRequest(URL, getId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

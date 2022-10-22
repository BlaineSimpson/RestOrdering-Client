package za.ac.cput.views.requests;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Bill;
import za.ac.cput.factory.BillFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BillRequest {
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
        List<Object> billList = new ArrayList<>();
        try{
            final String URL = "http://localhost:8080/restaurant/bill/all";
            String responseBody = getRequest(URL);
            JSONArray bill = new JSONArray(responseBody);

            for (int i = 0; i < bill.length(); i++) {
                JSONObject billObject = bill.getJSONObject(i);
                Gson g = new Gson();
                Object a = g.fromJson(billObject.toString(), Bill.class);
                billList.add(a);
                System.out.println(a.toString());
            }
            System.out.println("\n");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return billList;
    }

    public static void viewById(String billId) {
        try {
            final String URL = "http://localhost:8080/restaurant/bill/find/" + billId;
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
                .addHeader("Authorization", Credentials.basic("User", "1234"))
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void save(String billId, String billDate, String cusId, String restaurantId, String billDescr, int billAmount){
        try{
            final String URL = "http://localhost:8080/restaurant/bill/save";
            Bill bill = BillFactory.createBill(billId, billDate, cusId, restaurantId,billDescr,billAmount);
            Gson g = new Gson();
            String jsonString = g.toJson(bill);
            String r = postRequest(URL, jsonString);
            if (r != null)
                JOptionPane.showMessageDialog(null, "Successfully saved");
            else
                JOptionPane.showMessageDialog(null, "Sorry, bill could not save");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static String deleteRequest(String url, String billId) throws IOException{
        String deleteUrl = url + billId;
        Request request = new Request.Builder()
                .url(deleteUrl)
                .delete()
                .addHeader("Authorization", Credentials.basic("User", "1234"))
                .build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void delete(String billId) {
        try {
            final String URL = "http://localhost:8080/restaurant/bill/delete/";
            final String getId = billId;
            deleteRequest(URL, getId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

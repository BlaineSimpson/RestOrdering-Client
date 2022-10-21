package za.ac.cput.views.requests;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Tablee;
import za.ac.cput.factory.TableFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableRequest {

    private static OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static String getRequest(String URL) throws IOException{
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("Authorization", Credentials.basic("User","1234"))
                .build();
        try (Response response= client.newCall(request).execute()){
            return response.body().string();
        }
    }
    public static List<Object> getAll(){
        List<Object>tableList=new ArrayList<>();
        try{
            final String URL= "http://localhost:8080/restaurant/tablee/all";
            String responseBody = getRequest(URL);
            JSONArray tablee= new JSONArray(responseBody);

            for(int i=0; i< tablee.length();i++){
                JSONObject tabObject= tablee.getJSONObject(i);
                Gson g=new Gson();
                Object a=g.fromJson(tabObject.toString(), Tablee.class);
                tableList.add(a);
                System.out.println(a.toString());
            }
            System.out.println("\n");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return tableList;
    }
    public static void ViewById(String id){
        try{
            final String URL= "http://localhost:8080/restaurant/tablee/find/";
            String responseBody = getRequest(URL);

            System.out.println(responseBody);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private static String postRequest(final String url, String json) throws IOException{
        RequestBody body =RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization",Credentials.basic("User","1234"))
                .build();
        try(Response response=client.newCall(request).execute()){
            return  response.body().string();
        }

    }
    public static void save (String tableId, String tableNo, String noOfSeats, Boolean isTableAvailable ){
        try{
            final String URL= "http://localhost:8080/restaurant/tablee/save";
            Tablee tablee= TableFactory.createTable(tableId,tableNo,noOfSeats,isTableAvailable);
            Gson g=new Gson();
            String jsonString =g.toJson(tablee);
            String r=postRequest(URL, jsonString);
            if(r!=null)
                JOptionPane.showMessageDialog(null, "Successfully saved");
            else
                JOptionPane.showMessageDialog(null, "Sorry, could not save");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private static String deleteRequest(String url, String tableId) throws IOException{
        String deleteUrl= url+ tableId;
        Request request= new Request.Builder()
                .url(deleteUrl)
                .delete()
                .addHeader("Authorization",Credentials.basic("User","1234"))
                .build();
        try(Response response= client.newCall(request).execute()){
            return response.body().string();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    public static void deleteTable(String tableId){
        try{
            final String URL=  "http://localhost:8080/restaurant/tablee/delete/";
            final String getId= tableId;
            deleteRequest(URL, getId);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}

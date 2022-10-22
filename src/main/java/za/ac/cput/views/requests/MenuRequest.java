package za.ac.cput.views.requests;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.AdminLogin;
import za.ac.cput.domain.Menu;
import za.ac.cput.factory.MenuFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuRequest {

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
        List<Object> menuList = new ArrayList<>();
        try {
            final String URL = "http://localhost:8080/restaurant/menu/all";
            String responseBody = getRequest(URL);
            JSONArray menus = new JSONArray(responseBody);

            for (int i = 0; i < menus.length(); i++){
                JSONObject itemObject = menus.getJSONObject(i);
                Gson g = new Gson();
                Object a = g.fromJson(itemObject.toString(), Menu.class);
                menuList.add(a);
                System.out.println(a.toString());
            }
            System.out.println("\n");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return menuList;
    }

    public static void viewById(String id){
        try {
            final String URL = "http://localhost:8080/restaurant/menu/find/" + id;
            String responseBody = getRequest(URL);

            System.out.println(responseBody);

        }catch (IOException e) {
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

    public static void save(String menuId, String menuType){
        try {
            final String URL = "http://localhost:8080/restaurant/menu/save";
            Menu menu = MenuFactory.createMenu(menuId, menuType);
            Gson g = new Gson();
            String jsonString = g.toJson(menu);
            String r = postRequest(URL, jsonString);
            if (r != null)
                JOptionPane.showMessageDialog(null, "Successfully saved");
            else
                JOptionPane.showMessageDialog(null, "Sorry, could not save");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static String deleteRequest(String url, String id) throws IOException{
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
            final String URL = "http://localhost:8080/restaurant/menu/delete/";
            final String getId = id;
            deleteRequest(URL, getId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

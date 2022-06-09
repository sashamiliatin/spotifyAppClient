package com.hit.model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hit.client.Song;
import com.hit.client.Request;
import com.hit.client.Response;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.*;

public class MyModel1 {
    public Gson gson;
    public PrintWriter writer;
    public Response response;
    public Request request;
    public Scanner reader;
    public Socket toServer;
    public int port;


    public MyModel1(int port){
        gson = new GsonBuilder().create();
        this.port = port;
    }


    public List<Song> getSong(String searchVal,boolean user){
        String command = "";
        if(user){
            command = "user/song";
        }
        else {
            command = "admin/song";
        }
        Map<String, String> headers = new HashMap<>();
        Map<String, String> body = new HashMap<>();
        body.put("searchVal",searchVal);
        headers.put("action", command);
        System.out.println();response = sendRequest(headers, body);

        return response.songs;
    }

    public String saveGame(List<String> input,boolean user){
        String command = "";
        if(user){
            command = "user/save";
        }
        else {
            command = "admin/save";
        }
        Map<String, String> headers = new HashMap<>();
        Map<String, String> body = new HashMap<>();
        headers.put("action",command);
        body.put("SongName",input.get(0));
        body.put("Genre",input.get(1));
        body.put("Artist",input.get(2));
        body.put("Link",input.get(3));
        response = sendRequest(headers,body);
        return response.json;
    }

    public String updateGame(List<String> input){
        String command = "song/update";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> body = new HashMap<>();
        headers.put("action",command);
        body.put("Name",input.get(0));
        body.put("toUpdate",input.get(1));
        body.put("Val",input.get(2));

        response = sendRequest(headers,body);
        return response.json;
    }

    public String deleteGame(String songLink,boolean user) {
        String command = "";
        if(user){
            command = "user/delete";
        }
        else {
            command = "admin/delete";
        }
        Map <String, String> headers = new HashMap <>();
        Map <String, String> body = new HashMap <>();
        headers.put("action", command);
        body.put("Link",songLink);
        response = sendRequest(headers, body);
        return response.json;
    }



    public  Response sendRequest(Map <String, String> headers, Map <String, String> body)  {

        try {
            toServer = new Socket("localhost", port);
            writer = new PrintWriter(toServer.getOutputStream());
            reader = new Scanner(toServer.getInputStream());
            request = new Request(headers, body);
            System.out.println(gson.toJson(request));
            writer.println(gson.toJson(request));
            writer.flush();
            Type type = new TypeToken<Response>() {}.getType();
            response = gson.fromJson(reader.nextLine(), type);
            writer.close();
            reader.close();
            toServer.close();
            return response;
        }
        catch (Exception ex){ ex.printStackTrace();}
        return new Response("Error");

    }
}
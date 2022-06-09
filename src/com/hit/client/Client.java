package com.hit.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public Gson gson = new GsonBuilder().create();;
    public PrintWriter writer ;
    public Response response;
    public Request request;
    public Scanner reader;
    public Socket toServer;
    public int port = 3000;


    public List<Song> getSongs( boolean user){
        String command = "";
        if(user){
            command = "user/songs";
        }
        else {
            command = "admin/songs";
        }
        Map<String, String> headers = new HashMap<>();
        Map<String, String> body = new HashMap<>();
        headers.put("action", command);
        System.out.println();
        response = sendRequest(headers, body);
        return response.songs;
    }

    public List<Song> getSong(String searchVal, boolean user){
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
        System.out.println();
        response = sendRequest(headers, body);
        return response.songs;
    }

    public String saveSong(Song song,boolean user){
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
        body.put("SongName",song.getSongName());
        body.put("Genre",song.getSongGenre());
        body.put("Artist",song.getSongArtist());
        body.put("Link",song.getSongLink());
        response = sendRequest(headers,body);
        return response.json;
    }

    public String updateSong(List<String> input){
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

    public String deleteSong(String songLink,boolean user) {
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

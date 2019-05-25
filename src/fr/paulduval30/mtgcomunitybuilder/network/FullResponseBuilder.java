package fr.paulduval30.mtgcomunitybuilder.network;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FullResponseBuilder
{
    // HTTP GET request
    public static HashMap<String, String> getImage(String name) throws Exception {

        String url = "https://api.scryfall.com/cards/named?exact=" + name;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        String responce = response.toString();
        String tmp = responce.substring(responce.indexOf("image_uris") + "image_uris".length() + 3);
        String[] urlTable = tmp.substring(0, tmp.indexOf("}")).replace("}", "").split(",");
        HashMap<String, String> imageUrl= new HashMap<>();
        for(String s : urlTable)
        {
            String toPush = s.replace("\"", "");
            imageUrl.put(toPush.substring(0, toPush.indexOf(":")), toPush.substring(toPush.indexOf(":") + 1));
        }

        return imageUrl;
    }
}
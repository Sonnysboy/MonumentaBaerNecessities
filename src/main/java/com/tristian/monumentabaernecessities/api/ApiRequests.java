package com.tristian.monumentabaernecessities.api;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRequests {
    private static final String ITEM_ENDPOINT = "https://api.playmonumenta.com/itemswithnbt";



    public static String fetchResponse() {
        try {

            URL url = new URL(ITEM_ENDPOINT);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();

            if (status != 200) throw new RuntimeException("AHHHHHHHHHHH");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String inputLine;

            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();

            return content.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

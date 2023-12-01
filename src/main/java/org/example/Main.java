package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

        URL uri = new URL("https://jsonplaceholder.typicode.com/posts");
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();

        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");

        User user = new User(1,1, "delectus aut autem", true);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);


        conn.getOutputStream().write(json.getBytes());
        int resCode = conn.getResponseCode();

        InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuffer stringBuffer = new StringBuffer();
        String inputLine = bufferedReader.readLine();

        while ( inputLine != null) {
            stringBuffer.append(inputLine);
            inputLine = bufferedReader.readLine();
        }
        bufferedReader.close();

        System.out.println("Status Code: " + resCode);
        System.out.println("Body: " + stringBuffer.toString());

    }
}
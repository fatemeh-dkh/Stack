package com.example.fatemeh_d.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonReader {
    String getStreamFromURL(String urlString,String method){
        try {
            URL url=new URL(urlString);
            HttpURLConnection huc=(HttpURLConnection)url.openConnection();
            huc.setReadTimeout(10000);
            huc.setConnectTimeout(15000);
            huc.setRequestMethod(method);
            huc.setDoInput(true);

            huc.connect();
            String s = streamToString(huc.getInputStream());
            return s;
        } catch (Exception e) {
            return null;
        }

    }
    String streamToString(InputStream is){
        String result="";
        String line=null;
        BufferedReader br=new BufferedReader(new InputStreamReader(is));

        try {
            while((line=br.readLine())!=null){
                result+=line+"\n";
            }
        } catch (IOException e) {}

        return result;
    }
}
package com.example.fatemeh_d.stack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Splashactivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);
        Handler handler = new Handler();
        if (isOnline()) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    startActivity(new Intent(Splashactivity.this, MainActivity.class));
                }
            }, 2000);
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Splashactivity.this);
            dialog.setTitle("Error");
            dialog.setMessage("connection unavailable");
            dialog.setIcon(R.drawable.noin);
            dialog.show();
        }
    }

    private  boolean isDeviceOnline() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    public static boolean isOnline() {
        InetAddress inetAddress = null;
        try {
            Future<InetAddress> future = Executors.newSingleThreadExecutor().submit(new Callable<InetAddress>() {
                @Override
                public InetAddress call() {
                    try {
                        return InetAddress.getByName("google.com");
                    } catch (UnknownHostException e) {
                        return null;
                    }
                }
            });
            inetAddress = future.get(2000, TimeUnit.MILLISECONDS);
            future.cancel(true);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return inetAddress != null && !inetAddress.equals("");
    }

  /*  private  boolean checkActiveInternetConnection() {
        boolean success = false;
        try {
            URL url = new URL("https://google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.connect();
            success = connection.getResponseCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }*/
 /*   public  boolean hasActiveInternetConnection(Context context) {
        if (isDeviceOnline()) {
            try {
                URL url = new URL("http://www.google.com/");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
              //  urlc.setReadTimeout(10000);
              //  urlc.setDoOutput(true);
              //  urlc.connect();
               // return (urlc.getResponseCode() == 200);
                Toast.makeText(getApplicationContext(), urlc.toString(), Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Log.e(MainActivity.class.getSimpleName(), "Error checking internet connection", e);
            }
        } else {
            Log.d(MainActivity.class.getSimpleName(), "No network available!");
        }
       return  false ;
    }
*/

}

package com.example.mom.json_practice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class URLJson extends AppCompatActivity {

    private final String FILE_URL = "http://hmkcode.appspot.com/rest/controller/get.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urljson);

        new LoadTask().execute(FILE_URL);
    }

    class LoadTask extends AsyncTask< String , Void , String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer sb = new StringBuffer();
            try{
                URL url = new URL(strings[0]);
                BufferedReader in = new BufferedReader( new InputStreamReader( url.openStream()));
                String line = "";
                while( (line = in.readLine()) !=null){
                    Log.d("HTTP" , line);
                    sb.append(line+"\n");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<Article> Data = Article.fromJsonString(s);
            Log.d("DATA" , Data.get(0).getTitle()+"/"+Data.get(0).getCategories().toString());

        }
    }
}

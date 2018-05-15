package com.example.mom.json_practice;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Article {
    private String title;
    private String url;
    private ArrayList<String> categories ;
    private ArrayList<String> tags;

    public Article(String title , String url , ArrayList<String> categories , ArrayList<String> tags){
        super();
        this.title = title;
        this.url = url;
        this.categories = categories;
        this.tags = tags;
    }
    public static ArrayList<Article> fromJsonString(String json){
        ArrayList<Article> Data = null;
        if(json == null)
            return null;

        try{
            JSONObject jsonObj = new JSONObject(json);
            String jsonArray = jsonObj.getJSONArray("articleList").toString();
            Log.d("JsonArray" , jsonArray);
            Type listType = new TypeToken< ArrayList<Article>>(){}.getType();
            Gson gson = new Gson();
            Data = gson.fromJson(jsonArray , listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Data;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }

    public void setCategories(ArrayList<String> categories){
        this.categories = categories;
    }
    public ArrayList<String> getCategories(){
        return this.categories;
    }
    public void setTags(ArrayList<String> tags){
        this.tags = tags;
    }
    public ArrayList<String> getTags(){
        return this.tags;
    }
}

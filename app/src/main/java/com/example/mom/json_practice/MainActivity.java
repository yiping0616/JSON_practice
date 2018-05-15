package com.example.mom.json_practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //利用宣告的object Book , 練習 Object to JSON , JSON to Object , List to JSON , JSON to List
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String jsonStr1 = "";
        String jsonStr2 = "";
        Book book1 = new Book("Java" , 500 , "Mark");
        Book book2 = new Book("Android" , 600 , "John");
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        //Object to JSON
        Gson gson = new Gson();
        jsonStr1 = gson.toJson(book1);

        //JSON to Object
        try {
            JSONObject jsonObject = new JSONObject(jsonStr1);
            String name = jsonObject.getString("name");
            double price = jsonObject.getDouble("price");
            String author = jsonObject.getString("author");
            StringBuilder builder = new StringBuilder();
            builder.append(name+"/").append(price+"/").append(author);
            Log.d("TEST" , builder.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //List to JSON
        jsonStr2 = gson.toJson(bookList);

        //JSON to List
        ArrayList< HashMap<String,Object>> books = new ArrayList<>();
        try{
            JSONArray jsonArray = new JSONArray(jsonStr2);
            /*Gson
            String jsonStr3 = jsonArray.toString();
            Type listType = new TypeToken< ArrayList<Book>>(){}.getType();
            books = gson.fromJson(jsonStr3 , listType);
            Log.d("TEST" , books.get(0).getName()+"/"+books.get(1).getAuthor());
            */
            for( int i=0 ; i< jsonArray.length() ; i++){
                JSONObject json_book = jsonArray.getJSONObject(i);
                String name = json_book.getString("name");
                double price = json_book.getDouble("price");
                String author = json_book.getString("author");
                HashMap<String , Object> book = new HashMap<String , Object>();
                book.put("name",name);
                book.put("price",price);
                book.put("author",author);
                books.add(book);
            }
            Log.d("TEST" , books.get(0).get("name").toString());
        } catch (JSONException e){
            e.printStackTrace();
        }

        ListView list = (ListView) findViewById(R.id.listView);
        SimpleAdapter adapter = new SimpleAdapter(
                this , books , R.layout.item_book ,
                new String[]{"name","price","author"}, new int[]{R.id.name , R.id.price , R.id.author});
        list.setAdapter(adapter);

    }
}

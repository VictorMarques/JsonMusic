package com.example.musicjson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream.PutField;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.example.musicjson.R.string;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowList extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.show);
        
        ListView listname = (ListView) findViewById(R.id.list);
        
        //Criando a array list que vai receber o resultado do json 
        final ArrayList<String> list = new ArrayList<String>();
        
        ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        final String response = makeRequest("http://192.168.0.10:3000/musics.json");
        Log.d("JSON", response);
        
        try{
        
        	  JSONArray jsonA = new JSONArray(response);
        //String artist = json.getString("artist");
        
        //nameText.setText(name);
        //artistText.setText(artist);
     
        
        for(int i = 0; i < jsonA.length(); i++){
        	String n = jsonA.getJSONObject(i).getString("name");
    
         	list.add(n);
        }   
        
        }catch (JSONException e){
        	e.printStackTrace();
        
        }
        //Fazendo nossa ListView receber a arraylist
        ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listname.setAdapter(myarrayAdapter);
        
        
       listname.setOnItemClickListener(new OnItemClickListener(){
			
			
        //Dar uma ação para o LIstemView
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
	        	  JSONArray jsonA;
	   //  Redirecionando para o name e o artist INICIANDO MEU INTENT3
				try {
					jsonA = new JSONArray(response);
					String jsoname= jsonA.getJSONObject(position).getString("name");
					String jsonartist= jsonA.getJSONObject(position).getString("artist");
					Intent intent = new Intent(ShowList.this, List3.class);
					intent.putExtra("name", jsoname);
					intent.putExtra("artist", jsonartist);
					startActivity(intent);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
    }

	

    private String makeRequest(String urlAddress) {
		HttpURLConnection con = null;
		URL url = null;
		String response = null;
		try {
			url = new URL(urlAddress);
			con = (HttpURLConnection) url.openConnection();
			response = readStream(con.getInputStream());
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			con.disconnect();
		}
		return response;
    }
		private String readStream(InputStream in) {
			BufferedReader reader = null;
			StringBuilder builder = new StringBuilder();
			
			try{
				reader = new BufferedReader(new InputStreamReader(in));
				String line = null;
				while ((line = reader.readLine()) != null) {
					builder.append(line + "\n");
				}
			}catch (IOException e){
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return builder.toString();
		}
}
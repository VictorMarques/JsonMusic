package com.example.musicjson;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class List3 extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list3);
		
		//Meu Intent que será usado na minha classe Show
		
		Bundle extras = getIntent().getExtras();
		String name = extras.getString("name");
		String artist = extras.getString("artist");
		
		TextView nameTextView = (TextView) findViewById(R.id.name);
		TextView artistTextView = (TextView) findViewById(R.id.artist);
		
		nameTextView.setText(getString(R.string.user_name, name));
		artistTextView.setText(getString(R.string.user_artist, artist));
		

}
}
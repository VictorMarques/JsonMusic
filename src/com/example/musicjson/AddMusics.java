package com.example.musicjson;

import static us.monoid.web.Resty.data;
import static us.monoid.web.Resty.form;

import java.io.IOException;

import us.monoid.web.Resty;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddMusics extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list2);

		// GET no JSON, e trazer os dados
		setThreadPolicy();

		try {
			postMusics();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressLint("NewApi")
	public void setThreadPolicy() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	public void postMusics() throws IOException {

		Button button = (Button) findViewById(R.id.next_button);

		button.setOnClickListener(new OnClickListener() {
			Resty r = new Resty();

			public void onClick(View v) {

				try {
					final EditText nameEditText = (EditText) findViewById(R.id.name_edit);
					final EditText artistEditText = (EditText) findViewById(R.id.artist_edit);
					
					String name = nameEditText.getEditableText().toString();
					String artist = artistEditText.getEditableText().toString();

					r.json("http://192.168.0.10:3000/musics",
							form(data("music[artist]", artist),
									data("music[name]", name)));

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}
}

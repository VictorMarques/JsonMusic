package com.example.musicjson;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	// Botões para adicionar músicas e para mostrar a lista de músicas
	private Button btn1;
	private Button btn2;

	// Botões para meu player music
	Button btplay, btpausar;
	MediaPlayer Player;
	boolean Click;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		// Meu player de música
		btplay = (Button) findViewById(R.id.bttocar);
		btpausar = (Button) findViewById(R.id.btpausar);
		Player = MediaPlayer.create(this, R.raw.b);

		try {
			Player.prepare();
		} catch (Exception e) {
		}

		btplay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Player.start();

			}
		});

		btpausar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Player.pause();

			}
		});

		// Meu ClickListiner para acessar e adicionar músicas

		btn1 = (Button) findViewById(R.button.btn1);
		btn2 = (Button) findViewById(R.button.btn2);

		btn1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, AddMusics.class);
				startActivity(intent);

			}
		}

		);

		btn2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, ShowList.class);
				startActivity(intent);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
package com.example.ejemploproyecto;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Principal extends Activity {

	private ImageButton botonmates;
	private ImageButton botonlengua;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		botonmates=(ImageButton)findViewById(R.id.imageButton1);
		botonmates.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				lanzarmates();
			}
		});
		
		botonlengua=(ImageButton)findViewById(R.id.imageButton2);
		botonlengua.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				lanzarlengua();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
	
	public void lanzarmates() {
	Intent mates = new Intent(this, Mates.class);
	startActivity(mates);
	}

	public void lanzarlengua() {
	Intent lengua = new Intent(this, Lengua.class);
	startActivity(lengua);
	}
}

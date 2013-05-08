package com.edume;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Principal extends Activity {


	private ImageButton botonmates;
	private ImageButton botonlengua;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_principal);
					
		botonmates=(ImageButton)findViewById(R.id.imageButton1);
		botonmates.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				Intent mates = new Intent(Principal.this, materias.Mates.class);
				Principal.this.startActivity(mates);
				overridePendingTransition(R.anim.slyde, R.anim.slyde2);
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


	private void lanzarlengua() {
		Intent lengua = new Intent(Principal.this, materias.Lengua.class);
		Principal.this.startActivity(lengua);
		overridePendingTransition(R.anim.slyde4, R.anim.slyde3);
			}
		
	
	
}

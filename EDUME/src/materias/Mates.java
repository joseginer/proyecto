package materias;

import com.edume.ActivitySwitcher;
import com.edume.R;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Mates extends Activity {

	private ImageButton contaglobos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mates);
		

		contaglobos=(ImageButton)findViewById(R.id.imageButton1);
		contaglobos.setOnClickListener(new OnClickListener(){
			public void onClick(View ejer){
				lanzarejermates();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mates, menu);
		return true;
	}
	
	public void lanzarejermates() {
		final Intent intent = new Intent(getApplicationContext(), ejermates.ContaGlobos.class);
		// animacion de salida
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		ActivitySwitcher.animationOut(findViewById(R.id.container1), getWindowManager(), new ActivitySwitcher.AnimationFinishedListener() {
			@Override
			public void onAnimationFinished() {
				startActivity(intent);
			}
		});
		}
	@Override
	public void onBackPressed() {
	  this.finish();
	  overridePendingTransition(R.anim.slyde4, R.anim.slyde3);
	}

}

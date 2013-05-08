package ejermates;

import com.edume.ActivitySwitcher;
import com.edume.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class ContaGlobos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_conta_globos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.conta_globos, menu);
		return true;
	}

	protected void onResume() {
		// animación de entrada
		ActivitySwitcher.animationIn(findViewById(R.id.globos), getWindowManager());
		super.onResume();
	}
}

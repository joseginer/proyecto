package materias;

import com.edume.R;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class Lengua extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lengua);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lengua, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
	  this.finish();
	  overridePendingTransition(R.anim.slyde, R.anim.slyde2);
	}
}

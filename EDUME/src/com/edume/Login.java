package com.edume;


import org.json.JSONException;
import org.json.JSONObject;

import library.DatabaseHandler;
import library.UserFunctions;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {

	private Button boton;
	Button btnLogin;
	Button btnLinkToRegister;
	EditText inputEmail;
	EditText inputPassword;
	TextView loginErrorMsg;
	    // Respuesta JSON
	private static String KEY_SUCCESS = "success";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
	//ejecutar sin login
		boton=(Button)findViewById(R.id.button1);
		boton.setOnClickListener(new OnClickListener(){
			public void onClick(View view){		
				Intent principal = new Intent(Login.this, Principal.class);
				startActivity(principal);
			}
		});
	
	 // Importa todos los elementos del xml
		inputEmail = (EditText) findViewById(R.id.loginEmail);
		inputPassword = (EditText) findViewById(R.id.loginPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);

		btnLogin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.loginUser(email, password);
			// respuesta para el login
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						loginErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS);
						if(Integer.parseInt(res) == 1){
                        // usuario logeado
                        // datos del usuario en la base de datos SQLite
							DatabaseHandler db = new DatabaseHandler(getApplicationContext());
							JSONObject json_user = json.getJSONObject("user");
                        // Limpia todos los datos de la base de datos
							userFunction.logoutUser(getApplicationContext());
							db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));                       
                        // Lanza la actividad Principal
							Intent principal = new Intent(getApplicationContext(), Principal.class);
                        // Cierra la actividad para abrir Principal
							principal.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(principal);
                        // Cierra la ventana de Login
							finish();
						}else{
                        // Error en login
							loginErrorMsg.setText("Nombre de usuario o password incorrectos");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}

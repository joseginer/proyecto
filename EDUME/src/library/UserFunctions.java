package library;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.content.Context;


public class UserFunctions {
	
   private JSONParser jsonParser;
   // Las pruebas estan hechas en localhost
   private static String loginURL = "http://10.0.2.2/ah_login_api/";
   private static String login_tag = "login";
   // constructor
   public UserFunctions(){
       jsonParser = new JSONParser();
   }
   /**
    * funcion para recuperar el password
    * @param email
    * @param password
    * */
   public JSONObject loginUser(String email, String password){
       // Construye los parametros
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", login_tag));
       params.add(new BasicNameValuePair("email", email));
       params.add(new BasicNameValuePair("password", password));
       JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
       // return json
       // Log.e("JSON", json.toString());
       return json;
   }
   
   /**
    * Funcion recoge el estado del Login
    * */
   public boolean isUserLoggedIn(Context context){
       DatabaseHandler db = new DatabaseHandler(context);
       int count = db.getRowCount();
       if(count > 0){
           // Usuario conectado
           return true;
       }
       return false;
   }
   /**
    * Funcion para salir del Login
    * Resetea Base de datos
    * */
   public boolean logoutUser(Context context){
       DatabaseHandler db = new DatabaseHandler(context);
       db.resetTables();
       return true;
   }
}
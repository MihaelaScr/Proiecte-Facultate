package org.example.discoversuceava;

import database.DatabaseWorker;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends ActionBarActivity{
	SimpleCursorAdapter cursorAdapter;
	SQLiteDatabase db;
	Cursor c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		DatabaseWorker worker = new DatabaseWorker(this);
		db= worker.open();
		c = db.rawQuery("SELECT * FROM users", null);
		
		//atunci cand userul se inregistreaza populez baza mea de date cu atractiile disponibile ale aplicatiei 
		
		long id = worker.insertAttractions("Padrino Restaurant");
		id = worker.insertAttractions("Taco Loco Restaurant");
		id = worker.insertAttractions("Latino Restaurant");
		id = worker.insertAttractions("Vama Veche Restaurant");
		id = worker.insertAttractions("Oscar Wilde Pub");
		id = worker.insertAttractions("Mosaik Restaurant");
		id = worker.insertAttractions("Sonnenhof Hotel");
		id = worker.insertAttractions("Imperium Hotel");
		id = worker.insertAttractions("Continental Hotel");
		id = worker.insertAttractions("Zamca Hotel");
		id = worker.insertAttractions("Bucovina Hotel");
		id = worker.insertAttractions("Cetatea de Scaun a Sucevei");
		id = worker.insertAttractions("Sucevita Monastery");
		id = worker.insertAttractions("Voronet Monastery");
		id = worker.insertAttractions("Putna Monastery");
		id = worker.insertAttractions("The Salt Mine from Cacica");
		id = worker.insertAttractions("Ciprian Porumbescu's Memorial House");
		id = worker.insertAttractions("Iulius Mall");
		id = worker.insertAttractions("Galleria Mall");
		id = worker.insertAttractions("Metro");
		id = worker.insertAttractions("Carrefour");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if(id == R.id.actions_exit){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void signUp(View v){
		EditText emailet = (EditText) findViewById(R.id.emailEdit);
		EditText passet = (EditText) findViewById(R.id.passwordEdit);
		EditText screen_nameet = (EditText) findViewById(R.id.screenNameEdit);
		String email = emailet.getText().toString();
		String pass = passet.getText().toString();
		String screen_name = screen_nameet.getText().toString();
		
		SharedPreferences setari= getSharedPreferences("UserSettings", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editorProp= setari.edit();
		editorProp.putString("screen_name", screen_name);
		editorProp.commit();
		
		int sw = 0;
		if(c.moveToFirst()){ //am inregistrari in BD
	        do{
	        	//c.getString(1) = email; c.getString(2) = password; c.getString(3) = screen_name; c.getInt(0) = _id
	           if(c.getString(1).equals(email) && c.getString(2).equals(pass) && c.getString(3).equals(screen_name)){ //am gasit userul meu in BD
	      		   Toast.makeText(this, "This user already exists!", Toast.LENGTH_SHORT).show();
	      		   sw = 1;
	      		   Intent i = new Intent(SignUpActivity.this, SignUpActivity.class);
	 	           startActivity(i);
	           }
	        }while(c.moveToNext() && sw == 0);
	        c.close();
	        if(sw == 0){ //am inregistrari in BD dar nu se gaseste userul meu
	        	ContentValues valori= new ContentValues();
	     	    valori.put("email", email);
	     	    valori.put("password", pass);
	     	    valori.put("screen_name", screen_name);
	     	    long rez= db.insert("users", null, valori);
	     	    
	     	    c = db.rawQuery("SELECT * FROM users", null);
	     	    boolean b = c.moveToLast();
	     	    Intent i = new Intent(this, SignInActivity.class);
	     	    i.putExtra("KEY",c.getInt(0));
	     	    c.close();
	            db.close();
	   		    startActivity(i);
	        }else{  //am inregistrari in BD si am gasit userul meu
	        	db.close();
	        }
	    }else{ //nu am inregistrari in BD
	    	ContentValues valori= new ContentValues();
     	    valori.put("email", email);
     	    valori.put("password", pass);
     	    valori.put("screen_name", screen_name);
     	    long rez= db.insert("users", null, valori);
     	    
     	    c.close();
     	    c = db.rawQuery("SELECT * FROM users", null);
     	    boolean b = c.moveToFirst();
     	    Intent i = new Intent(this, SignInActivity.class);
     	    i.putExtra("KEY",c.getInt(0));
     	    c.close();
            db.close();
   		    startActivity(i);
		}
	}
}

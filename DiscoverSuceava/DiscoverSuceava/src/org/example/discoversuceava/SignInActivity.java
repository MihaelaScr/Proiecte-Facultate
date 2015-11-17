package org.example.discoversuceava;

import database.DatabaseWorker;
import drawer.SettingsActivity;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class SignInActivity extends ActionBarActivity{
	SimpleCursorAdapter cursorAdapter;
	SQLiteDatabase db;
	Cursor c;
	public String screen_name;
	public int user_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		
		DatabaseWorker worker = new DatabaseWorker(this);
		db= worker.open();
		c = db.rawQuery("SELECT * FROM users", null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
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
	
	public void signIn(View v){
		EditText emailet = (EditText) findViewById(R.id.emailEdit2);
		EditText passet = (EditText) findViewById(R.id.passwordEdit2);
		String email = emailet.getText().toString();
		String pass = passet.getText().toString();
		
		//memorez datele userului in fisiere de proprietati
		SharedPreferences setari= getSharedPreferences("UserSettings", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editorProp= setari.edit();
		editorProp.putString("email", email);
		editorProp.putString("password", pass);
		editorProp.commit();
		
		int sw = 0;
		if(c.moveToFirst()){
	        do{
	            //c.getString(1) = email; c.getString(2) = password; c.getString(3) = screen_name; c.getInt(0) = _id
	           //System.out.println(c.getString(1) + "  "+c.getString(2)+"   "+c.getString(3)+"   "+c.getInt(0));
	           if(c.getString(1).equals(email) && c.getString(2).equals(pass)){
	        	    sw = 1;
	        	    screen_name = new String(c.getString(3));
	        	    user_id = c.getInt(0);
	        	    AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
		       		String message = new String(getResources().getString(R.string.sign_in_message_first)).concat(" ").concat(screen_name).concat(" ").concat(getResources().getString(R.string.sign_in_message_second));
		       		builder.setMessage(message).setTitle(R.string.sign_in_title);
		       		
		       		builder.setPositiveButton(R.string.sign_in_ok_button, new DialogInterface.OnClickListener() {
		       	           public void onClick(DialogInterface dialog, int id) {
		       	        	   Intent i = new Intent(SignInActivity.this, WelcomeActivity.class);
		       	        	   i.putExtra("KEY", user_id);
		       	        	   startActivity(i);
		       	           }
		       	       });
		       		builder.setNegativeButton(R.string.settings, new DialogInterface.OnClickListener() {
		       	           public void onClick(DialogInterface dialog, int id) {
			       	        	 Intent i = new Intent(SignInActivity.this, SettingsActivity.class);
						       	 i.putExtra("KEY", screen_name);
			       	        	 startActivity(i);
		       	           }
		       	       });
		       		AlertDialog dialog = builder.create();
		       		dialog.show();
	           }
	        }while(c.moveToNext() && sw == 0);
	        if(sw == 0){
	        	Toast.makeText(this, "This user doesn't exist!", Toast.LENGTH_SHORT).show();
	        	c.close();
	            db.close();
	            Intent i = new Intent(SignInActivity.this, SignInActivity.class);
  	        	startActivity(i);
	        }
	    }else{
		    Toast.makeText(this, "This user doesn't exist!", Toast.LENGTH_SHORT).show();
		    c.close();
	        db.close();
	        Intent i = new Intent(SignInActivity.this, SignInActivity.class);
	        startActivity(i);
		}
	}
}

package drawer;

import org.example.discoversuceava.MainActivity;
import org.example.discoversuceava.R;
import org.example.discoversuceava.SignInActivity;
import org.example.discoversuceava.SignUpActivity;

import database.DatabaseWorker;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends ActionBarActivity {
	private String email, password, screen_name, userQuery;
	private SQLiteDatabase db;
	private Cursor cUser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.DarkGreen)));
		
		TextView et1= (TextView) findViewById(R.id.logoutTextView1);
		TextView et = (TextView) findViewById(R.id.logoutTextView2);
		Button butt = (Button) findViewById(R.id.signInButton);
		Button butt1 = (Button) findViewById(R.id.signUpButton);
		SharedPreferences setari= getSharedPreferences("UserSettings", Activity.MODE_PRIVATE);
	    email = setari.getString("email", "no_user");
		password = setari.getString("password", "no_user");
	
		 if(email.equals("no_user") && password.equals("no_user")){
			 Button b = (Button) findViewById(R.id.logoutButton);
			 b.setVisibility(View.INVISIBLE);
			 et1.setVisibility(View.INVISIBLE);
			 et.setText(getString(R.string.no_user));
			 
		}else{
			db = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(DatabaseWorker.DATABASE_NAME), null);
			userQuery = "select screen_name from users where email = ? AND password = ?";
			cUser = db.rawQuery( userQuery, new String[]{email, password});
			cUser.moveToFirst();
			screen_name = cUser.getString(0);
			et.setText(screen_name);
			butt.setVisibility(View.INVISIBLE);
			butt1.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id == R.id.actions_exit){
			this.moveTaskToBack(true);
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void signOut(View v){
		SharedPreferences setari= getSharedPreferences("UserSettings", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editorProp= setari.edit();
		editorProp.putString("email", "no_user");
		editorProp.putString("password", "no_user");
		editorProp.commit();
		
		Intent i = new Intent(SettingsActivity.this, MainActivity.class);
    	startActivity(i);
    	
	}
	
	public void signIn(View v){
		Intent i = new Intent(SettingsActivity.this, SignInActivity.class);
		startActivity(i);
	}
	
	public void signUp(View v){
		Intent i = new Intent(SettingsActivity.this, SignUpActivity.class);
		startActivity(i);
	}
}

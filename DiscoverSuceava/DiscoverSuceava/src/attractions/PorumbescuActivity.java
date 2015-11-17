package attractions;

import menu.MapActivity;

import org.example.discoversuceava.R;

import database.ThoughtsMemo;
import drawer.SettingsActivity;
import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class PorumbescuActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_porumbescu);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.DarkGreen)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.porumbescu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent i = new Intent(this, SettingsActivity.class);
			startActivity(i);
		}
		if(id == R.id.actions_exit){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void add(View v){
		ThoughtsMemo tm = new ThoughtsMemo(PorumbescuActivity.this,"Ciprian Porumbescu's Memorial House");
		boolean success = tm.addThoughts();
		if(success == false){
			  Toast.makeText(this, "You have to be logged in order to keep up a journal !", Toast.LENGTH_LONG).show();
		}
	}
	
	public void checkIn(View v){
		ThoughtsMemo tm = new ThoughtsMemo(PorumbescuActivity.this,"Ciprian Porumbescu's Memorial House");
		boolean success = tm.checkIn();
		if(success == false){
			  Toast.makeText(this, "You have to be logged in order to keep up a journal !", Toast.LENGTH_LONG).show();
		}else{
			 Toast.makeText(this, "You have been checked in ! You will find this place in your Trip Journal !", Toast.LENGTH_LONG).show();
		}
	}
	
	public void map(View v){
		Intent i = new Intent(PorumbescuActivity.this, MapActivity.class);
		i.putExtra("KEY", 18);
	    startActivity(i);
	}
}

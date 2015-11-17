package menu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import org.example.discoversuceava.Attraction;
import org.example.discoversuceava.GraficActivity;
import org.example.discoversuceava.R;
import database.DatabaseWorker;
import drawer.SettingsActivity;
import adapters.JournalAdapter;
import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TripJournalActivity extends ActionBarActivity {
	List<Attraction> attractions = new ArrayList<Attraction>();
	List<String> names = new ArrayList<String>();
	List<String> dates = new ArrayList<String>();
	List<String> comments = new ArrayList<String>();
	
	private SQLiteDatabase db;
	private String userQuery, journalQuery, nameQuery;
	private Cursor cUser, cJournal, cName;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trip_journal);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.DarkGreen)));
		
		db = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(DatabaseWorker.DATABASE_NAME), null);
        
        userQuery = "select _id from users where email = ? AND password = ?";
        journalQuery = "select date, attraction_id, comments from journal where user_id=?";
        nameQuery = "select name from attractions where _id = ?";
        Bundle extras = getIntent().getExtras();
        String email = extras.getString("EMAIL");
        String password = extras.getString("PASSWORD");
        
        cUser = db.rawQuery( userQuery, new String[]{email, password});
        cUser.moveToFirst();
		cJournal = db.rawQuery(journalQuery, new String[]{Integer.toString(cUser.getInt(0))} );
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("EET"));
		TextView journalText = (TextView) findViewById(R.id.journalText);
		Button btn = (Button) findViewById(R.id.graficButton); 
		journalText.setVisibility(View.INVISIBLE);
		btn.setVisibility(View.VISIBLE);
		
		if(cJournal.getCount()>=1){
		 while(cJournal.moveToNext()){
			try{
			dates.add((dateFormat.parse(cJournal.getString(cJournal.getColumnIndex("date")))).toString());
			}catch(Exception e){
				e.printStackTrace();
			}
			if(cJournal.getString(2)!=null){
				comments.add(cJournal.getString(2));
			}else{
				comments.add("You have no memories yet!");
			}
			cName = db.rawQuery(nameQuery, new String[]{Integer.toString(cJournal.getInt(1))});
			cName.moveToFirst();
			names.add(cName.getString(0));
		}
		 
		 Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.journal_logo);
		 for(int i=0; i<names.size(); i++){
				Attraction a = new Attraction();
				a.setImage(image);
				a.setName(names.get(i));
				a.setDate(dates.get(i));
		        attractions.add(a);		
		 }
		 
		 ListView listView = ( ListView ) findViewById(R.id.lvJournal);
		 JournalAdapter adapter = new JournalAdapter(this, R.layout.lv_journal_layout, attractions); 
		 listView.setAdapter(adapter);
		 listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		     public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
		    	 new AlertDialog.Builder(TripJournalActivity.this)
				    .setTitle("Your memories!")
				    .setMessage(comments.get(position))
				    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				         public void onClick(DialogInterface dialog, int whichButton) {
				        	 
				         }
				    })
				    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				         public void onClick(DialogInterface dialog, int whichButton) {
				         }
				    }).show();
		         
		     }
		});
			
		}else{
			journalText.setVisibility(View.VISIBLE);
			 btn.setVisibility(View.INVISIBLE);
		}
	   
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trip_journal, menu);
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
	
	public void grafic(View v){
		Intent i = new Intent(TripJournalActivity.this, GraficActivity.class);
		startActivity(i);
	}
	
}

package org.example.discoversuceava;


import database.DatabaseWorker;
import drawer.AboutAppActivity;
import drawer.CurrencyActivity;
import drawer.SettingsActivity;
import drawer.WeatherActivity;
import menu.AttractionsActivity;
import menu.FoodDrinkActivity;
import menu.HotelsActivity;
import menu.MapActivity;
import menu.ShoppingActivity;
import menu.TripJournalActivity;

import adapters.DrawerAdapter;
import adapters.MyAdapter;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import android.widget.ListView;


public class WelcomeActivity extends ActionBarActivity {
	private String[] stringList;
	private ListView lv;
	private MyAdapter adapter;
	private DrawerAdapter dAdapter;
	private String[] drawerListViewItems;
    private ListView drawerListView;
    private DrawerLayout drawerLayout;
    private int user_id;
    private String screen_name, email,password, userQuery;
    private SQLiteDatabase db;
    private Cursor cUser;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		
		SharedPreferences setari= getSharedPreferences("UserSettings", Activity.MODE_PRIVATE);
	    email = setari.getString("email", "no_user");
		password = setari.getString("password", "no_user");		
		
		TextView tv = (TextView) findViewById(R.id.welcome2);
		Bundle extras = getIntent().getExtras();
		if(!email.equals("no_user") && !password.equals("no_user")) {
			if(extras!=null){
			 user_id = extras.getInt("KEY");
			}
			 SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(DatabaseWorker.DATABASE_NAME), null);
			 Cursor c = db.rawQuery("SELECT * FROM users WHERE _id = " + user_id, null);
			 c.moveToFirst();			
			 c.close();
			 userQuery = "select screen_name from users where email = ? AND password = ?";
			 cUser = db.rawQuery( userQuery, new String[]{email, password});
			 cUser.moveToFirst();
			 screen_name = cUser.getString(0);
			 System.out.println(email);
			 System.out.println(password);
			
			 tv.setText("Welcome, " + screen_name + "!");
		}else{
			tv.setText("Welcome!");
			user_id = -1;
		}
		
    	stringList = getResources().getStringArray(R.array.menu_items);
		int[] imageList = {R.drawable.map_logo, R.drawable.food_drink_logo, R.drawable.hotels_logo, R.drawable.attractions_logo, R.drawable.shopping_logo, R.drawable.trip_journal_logo};
	    adapter = new MyAdapter(stringList, this, imageList);
	    lv = (ListView) findViewById(R.id.listView1); 
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		     public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
		         
		         if(position == 1){
		        	 Intent i = new Intent(WelcomeActivity.this, FoodDrinkActivity.class);
		        	 i.putExtra("KEY", user_id);
	        	     startActivity(i);
		         }
		         if(position == 0){
		        	 Intent i = new Intent(WelcomeActivity.this, MapActivity.class);
		        	 i.putExtra("KEY", user_id);
	        	     startActivity(i);
		         }
		         if(position == 2){
		        	 Intent i = new Intent(WelcomeActivity.this, HotelsActivity.class);
		        	 i.putExtra("KEY", user_id);
	        	     startActivity(i);
		         }
		         if(position == 3){
		        	 Intent i = new Intent(WelcomeActivity.this, AttractionsActivity.class);
		        	 i.putExtra("KEY", user_id);
	        	     startActivity(i);
		         }
		         if(position == 5){
		        	 if(email.equals("no_user") && password.equals("no_user")){
		        		 Toast.makeText(WelcomeActivity.this, "In order to get access to your trip journal you must have an user and be logged in", Toast.LENGTH_LONG).show();
		        	 }else{
			        	 Intent i = new Intent(WelcomeActivity.this, TripJournalActivity.class);
			        	 i.putExtra("EMAIL", email);
			        	 i.putExtra("PASSWORD", password);
		        	     startActivity(i);
		        	 }
		         }
		         if(position == 4){
		        	 Intent i = new Intent(WelcomeActivity.this, ShoppingActivity.class);
		        	 i.putExtra("KEY", user_id);
	        	     startActivity(i);
		         }
		     }
		});

        drawerListViewItems = getResources().getStringArray(R.array.drawer_items);
        drawerListView = (ListView) findViewById(R.id.left_drawer);

        int[] drawerImageList = {R.drawable.about_app_logo, R.drawable.settings_logo2, R.drawable.feedback_logo, R.drawable.currency_logo, R.drawable.weather_logo2};
        dAdapter = new DrawerAdapter(drawerListViewItems, this, drawerImageList);
        drawerListView.setAdapter(dAdapter);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        int width = getResources().getDisplayMetrics().widthPixels / 2;
        int height = getResources().getDisplayMetrics().widthPixels / 3;
        DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) drawerListView.getLayoutParams();
        params.width = width;
        drawerListView.setLayoutParams(params);
        
        ImageView imageView = (ImageView)findViewById(R.id.welcome_image);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(width,height);
        imageView.setLayoutParams(layoutParams);
        
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		     public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
		         
		         if(position == 0){ 
		        	 Intent i = new Intent(WelcomeActivity.this, AboutAppActivity.class);
	        	     startActivity(i);
		         }
		         if(position == 1){
		        	 Intent i = new Intent(WelcomeActivity.this, SettingsActivity.class);
		        	 i.putExtra("KEY", screen_name);
	        	     startActivity(i);
		         }
		         if(position == 2){
		        	 /*Intent i = new Intent(WelcomeActivity.this, FeedbackActivity.class);
		        	 i.putExtra("KEY", user_id);
	        	     startActivity(i);*/
	        	     
		        	 Intent sendIntent = new Intent(Intent.ACTION_VIEW);
		        	 sendIntent.setType("plain/text");
		        	 sendIntent.setData(Uri.parse("discover.suceava@gmail.com"));
		        	 sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
		        	 sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "discover.suceava@gmail.com" });
		        	 startActivity(sendIntent);
		         }
		         if(position==3){
		        	 Intent i = new Intent(WelcomeActivity.this, CurrencyActivity.class);
	        	     startActivity(i);
		         }
		         if(position==4){
		        	 Intent i = new Intent(WelcomeActivity.this, WeatherActivity.class);
	        	     startActivity(i);
		         }
		     }
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		return super.onOptionsItemSelected(item);
	}
}

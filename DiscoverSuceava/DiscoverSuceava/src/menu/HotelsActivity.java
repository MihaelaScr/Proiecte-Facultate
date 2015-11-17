package menu;


import hotels.BucovinaActivity;
import hotels.ContinentalActivity;
import hotels.ImperiumActivity;
import hotels.SonnenhofActivity;
import hotels.ZamcaActivity;

import java.util.ArrayList;
import java.util.List;

import org.example.discoversuceava.Attraction;
import org.example.discoversuceava.R;

import drawer.SettingsActivity;




import adapters.MySimpleAdapter;
import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class HotelsActivity extends ActionBarActivity {
	
	List<Attraction> hotels = new ArrayList<Attraction>();
	String[] names = new String[] {
            "Sonnenhof Hotel", "Imperium Hotel", "Continental Hotel","Zamca Hotel", "Bucovina Hotel"
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotels);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.DarkGreen)));
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.hotels_logo);
		Bitmap awesome = BitmapFactory.decodeResource(getResources(), R.drawable.excelent_face_logo);
		Bitmap good = BitmapFactory.decodeResource(getResources(), R.drawable.good_face_logo);
		Bitmap meh = BitmapFactory.decodeResource(getResources(), R.drawable.bad_face_logo);
		for(int i=0; i<5;i++){
			Attraction a = new Attraction();
			a.setImage(image);
			a.setName(names[i]);
	        hotels.add(a);		
		}
		 hotels.get(0).setRate(awesome);
		 hotels.get(1).setRate(awesome);
		 hotels.get(2).setRate(good);
		 hotels.get(3).setRate(good);
		 hotels.get(4).setRate(meh);
		
		 ListView listView = ( ListView ) findViewById(R.id.lvHotels);
		 MySimpleAdapter adapter = new MySimpleAdapter(this, R.layout.lv_attraction_layout, hotels); 
		 listView.setAdapter(adapter);
		 
		 listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		     public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
		         if(position == 0){
		        	 Intent i = new Intent(HotelsActivity.this, SonnenhofActivity.class);
	        	     startActivity(i);
		         }
		         if(position == 1){
		        	 Intent i = new Intent(HotelsActivity.this, ImperiumActivity.class);
	        	     startActivity(i);
		         }
		         if(position == 2){
		        	 Intent i = new Intent(HotelsActivity.this, ContinentalActivity.class);
	        	     startActivity(i);
		         }
		         if(position == 3){
		        	 Intent i = new Intent(HotelsActivity.this, ZamcaActivity.class);
	        	     startActivity(i);
		         }
		         if(position == 4){
		        	 Intent i = new Intent(HotelsActivity.this, BucovinaActivity.class);
	        	     startActivity(i);
		         }
		     }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hotels, menu);
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
			startActivity(i);;
		}
		if(id == R.id.actions_exit){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}

package menu;

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
import attractions.CacicaActivity;
import attractions.CetateActivity;
import attractions.PorumbescuActivity;
import attractions.PutnaActivity;
import attractions.SucevitaActivity;
import attractions.VoronetActivity;

public class AttractionsActivity extends ActionBarActivity {
	
	List<Attraction> attractions = new ArrayList<Attraction>();
	String[] names = new String[] {
            "Cetatea de Scaun a Sucevei", "Sucevita Monastery", "Voronet Monastery","Putna Monastery",
            "The Salt Mine from Cacica","Ciprian Porumbescu's Memorial House"
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attractions);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.DarkGreen)));
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.attractions_logo);
		Bitmap awesome = BitmapFactory.decodeResource(getResources(), R.drawable.excelent_face_logo);
		Bitmap good = BitmapFactory.decodeResource(getResources(), R.drawable.good_face_logo);
		for(int i=0; i<6; i++){
			Attraction a = new Attraction();
			a.setImage(image);
			a.setName(names[i]);
	        attractions.add(a);		
		}
		 attractions.get(0).setRate(good);
		 attractions.get(1).setRate(good);
		 attractions.get(2).setRate(awesome);
		 attractions.get(3).setRate(awesome);
		 attractions.get(4).setRate(awesome);
		 attractions.get(5).setRate(good);
		
		 ListView listView = ( ListView ) findViewById(R.id.lvAttractions);
		 MySimpleAdapter adapter = new MySimpleAdapter(this, R.layout.lv_attraction_layout, attractions); 
		 listView.setAdapter(adapter);
		 
		 listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		     public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
		         if(position == 0){
		        	 Intent i = new Intent(AttractionsActivity.this, CetateActivity.class);
	        	     startActivity(i);
		         }
		         if(position == 1){
		        	 Intent i = new Intent(AttractionsActivity.this, SucevitaActivity.class);
	        	     startActivity(i);
		         }
		         if(position == 2){
		        	 Intent i = new Intent(AttractionsActivity.this, VoronetActivity.class);
	        	     startActivity(i);
		         }
		         if(position == 3){
		        	 Intent i = new Intent(AttractionsActivity.this, PutnaActivity.class);
	        	     startActivity(i);
		         }
		         if(position == 4){
		        	 Intent i = new Intent(AttractionsActivity.this, CacicaActivity.class);
	        	     startActivity(i);
		         }
		         if(position == 5){
		        	 Intent i = new Intent(AttractionsActivity.this, PorumbescuActivity.class);
	        	     startActivity(i);
		         }
		     }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.attractions, menu);
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
}

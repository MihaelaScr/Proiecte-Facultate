package menu;

import java.util.ArrayList;
import java.util.List;

import org.example.discoversuceava.Attraction;
import org.example.discoversuceava.R;

import drawer.SettingsActivity;

import shopping.CarrefourActivity;
import shopping.GaleriaActivity;
import shopping.IuliusActivity;
import shopping.MetroActivity;



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

public class ShoppingActivity extends ActionBarActivity {
	
	List<Attraction> shops = new ArrayList<Attraction>();
	String[] names = new String[] {
            "Iulius Mall", "Galleria Mall", "Metro", "Carrefour"
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.DarkGreen)));
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.shopping_logo);
		Bitmap awesome = BitmapFactory.decodeResource(getResources(), R.drawable.excelent_face_logo);
		Bitmap good = BitmapFactory.decodeResource(getResources(), R.drawable.good_face_logo);
		Bitmap meh = BitmapFactory.decodeResource(getResources(), R.drawable.bad_face_logo);
		for(int i=0; i<4;i++){
			Attraction a = new Attraction();
			a.setImage(image);
			a.setName(names[i]);
	        shops.add(a);		
		}
		 shops.get(0).setRate(awesome);
		 shops.get(1).setRate(meh);
		 shops.get(2).setRate(good);
		 shops.get(3).setRate(good);
		
		 ListView listView = ( ListView ) findViewById(R.id.lvShopping);
		 MySimpleAdapter adapter = new MySimpleAdapter(this, R.layout.lv_attraction_layout, shops);
		 listView.setAdapter(adapter);
		 
		 listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		     public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
		         if(position == 0){
		        	 Intent i = new Intent(ShoppingActivity.this, IuliusActivity.class);
	        	     startActivity(i);
		         }
		         if(position == 1){
		        	 Intent i = new Intent(ShoppingActivity.this, GaleriaActivity.class);
	        	     startActivity(i);
		         }
		         if(position == 2){
		        	 Intent i = new Intent(ShoppingActivity.this, MetroActivity.class);
	        	     startActivity(i);
		         }
		         if(position == 3){
		        	 Intent i = new Intent(ShoppingActivity.this, CarrefourActivity.class);
	        	     startActivity(i);
		         }
		     }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shopping, menu);
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

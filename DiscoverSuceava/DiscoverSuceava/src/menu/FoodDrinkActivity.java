package menu;

import java.util.ArrayList;
import java.util.List;

import org.example.discoversuceava.Attraction;

import org.example.discoversuceava.R;

import drawer.SettingsActivity;

import foodDrink.LatinoActivity;
import foodDrink.MosaikActivity;
import foodDrink.OscarWildeActivity;
import foodDrink.PadrinoActivity;
import foodDrink.TacoLocoActivity;
import foodDrink.VamaVecheActivity;



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


public class FoodDrinkActivity extends ActionBarActivity {
	
	List<Attraction> restaurants = new ArrayList<Attraction>();
	String[] names = new String[] {
            "Padrino Restaurant",
            "Taco Loco Restaurant",
            "Latino Restaurant",
            "Vama Veche Restaurant",
            "Oscar Wilde Pub",
            "Mosaik Restaurant"
    };
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_drink);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.DarkGreen)));
		
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.food_drink_logo);
		Bitmap awesome = BitmapFactory.decodeResource(getResources(), R.drawable.awesome_logo);
		Bitmap good = BitmapFactory.decodeResource(getResources(), R.drawable.good_logo);
		Bitmap meh = BitmapFactory.decodeResource(getResources(), R.drawable.meh_logo);
		for(int i=0; i<6;i++){
			Attraction a = new Attraction();
			a.setImage(image);
			a.setName(names[i]);
	        restaurants.add(a);		
		}
		 restaurants.get(0).setRate(awesome);
		 restaurants.get(2).setRate(awesome);
		 restaurants.get(5).setRate(awesome);
		 restaurants.get(1).setRate(good);
		 restaurants.get(4).setRate(good);
		 restaurants.get(3).setRate(meh);
		
		 ListView listView = ( ListView ) findViewById(R.id.lvFoodDrink);
		 MySimpleAdapter adapter = new MySimpleAdapter(this, R.layout.lv_attraction_layout, restaurants); 
		 listView.setAdapter(adapter);
		 
		 listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		     public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
		         
		    	 //TextView clickedView = (TextView) view;
		         if(position == 0){//(clickedView.getText().equals("Padrino Restaurant")){
		        	 Intent i = new Intent(FoodDrinkActivity.this, PadrinoActivity.class);
	        	     startActivity(i);
		         }
		         
		         //TextView clickedView = (TextView) view;
		         if(position == 1){//(clickedView.getText().equals("Taco Loco Restaurant")){
		        	 Intent i = new Intent(FoodDrinkActivity.this, TacoLocoActivity.class);
	        	     startActivity(i);
		         }
		         
		         //TextView clickedView = (TextView) view;
		         if(position == 2){//(clickedView.getText().equals("Latino Restaurant")){
		        	 Intent i = new Intent(FoodDrinkActivity.this, LatinoActivity.class);
	        	     startActivity(i);
		         }
		         
		       //TextView clickedView = (TextView) view;
		         if(position == 3){//(clickedView.getText().equals("Vama Veche Restaurant")){
		        	 Intent i = new Intent(FoodDrinkActivity.this, VamaVecheActivity.class);
	        	     startActivity(i);
		         }
		         
		       //TextView clickedView = (TextView) view;
		         if(position == 4){//(clickedView.getText().equals("Oscar Wilde Restaurant")){
		        	 Intent i = new Intent(FoodDrinkActivity.this, OscarWildeActivity.class);
	        	     startActivity(i);
		         }
		         
		         //TextView clickedView = (TextView) view;
		         if(position == 5){//(clickedView.getText().equals("Mosaik Restaurant")){
		        	 Intent i = new Intent(FoodDrinkActivity.this, MosaikActivity.class);
	        	     startActivity(i);
		         }
		     }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_drink, menu);
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

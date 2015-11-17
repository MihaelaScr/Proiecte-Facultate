package drawer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.example.discoversuceava.R;
import org.json.JSONObject;
import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.DarkGreen)));
		
		LoadCurrencyAsync loader = new LoadCurrencyAsync();
		ImageView warning = (ImageView)findViewById(R.id.warning);
		warning.setVisibility(View.INVISIBLE);
		if(isNetworkAvailable(this) == true){
			loader.execute("http://api.wunderground.com/api/1da02ad567a58cfd/conditions/q/CA/Suceava.json");
		}else{
			TextView tv1 = (TextView) findViewById(R.id.observationTime);
			TextView tv2 = (TextView) findViewById(R.id.weather);
			tv1.setText("You don't have a valid connection to the Internet. Please connect to the Internet first!");
			tv1.setTypeface(null, Typeface.BOLD);
			tv2.setText("");
			warning.setVisibility(View.VISIBLE);
		}
	}
	
	public boolean isNetworkAvailable(Context ctx){
	    ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()&& cm.getActiveNetworkInfo().isAvailable()&& cm.getActiveNetworkInfo().isConnected()) {
	        return true;
	    }
	    else{
	        return false;
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
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
	
	public class LoadCurrencyAsync extends AsyncTask<String, Integer, ArrayList<String>>{

        public boolean isNetworkAvailable(Context context) 
        {
            return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
        }

		@Override
		protected ArrayList<String> doInBackground(String... params) {
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(params[0]);
				try {
						ArrayList<String> res = new ArrayList<String>();
						HttpResponse response = client.execute(get);
						InputStream stream = response.getEntity().getContent();
						BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
						StringBuilder json_text = new StringBuilder();
						String line = null;
						while((line=reader.readLine())!=null){
							if(isCancelled()){
								break;
							}
							json_text.append(line);
						}			
						JSONObject obj = new JSONObject(json_text.toString());
						JSONObject currObs = obj.getJSONObject("current_observation");
						String obsTime = currObs.getString("observation_time_rfc822");
						String temperature = currObs.getString("temperature_string");
						//String iconUrl = currObs.getString("icon_url");
						
						StringBuilder el1 = new StringBuilder();
						StringBuilder el2 = new StringBuilder();
						//StringBuilder el3 = new StringBuilder();
						el1.append(obsTime);
						el2.append(temperature);
						//el3.append(iconUrl);
						res.add(el1.toString());
						res.add(el2.toString());
						//res.add(el3.toString());
						return res;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
		}

		@Override
		protected void onPostExecute(final ArrayList<String> result) {
			super.onPostExecute(result);
			
			TextView tv1 = (TextView) findViewById(R.id.observationTime);
			TextView tv2 = (TextView) findViewById(R.id.weather);
			tv1.setText("Observation Time:  " + result.get(0));
			tv2.setText("Temperature:  " + result.get(1));
//			Bitmap bmp;
//			try {
//				URL newurl = new URL("http://icons.wxug.com/i/c/k/clear.gif"); 
//				System.out.println(result.get(2));
//				HttpURLConnection con = (HttpURLConnection) newurl.openConnection();
//				con.setDoInput(true);
//				con.connect();
//				InputStream in = con.getInputStream();
//				bmp = BitmapFactory.decodeStream(in);
//				imageView.setImageBitmap(bmp);
//			} catch (Exception e) {
//				e.printStackTrace();
////			}
		}
	}
}

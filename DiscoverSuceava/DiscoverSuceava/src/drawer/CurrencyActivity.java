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
import org.json.JSONArray;
import org.json.JSONObject;
import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
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

public class CurrencyActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_currency);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.DarkGreen)));
		
		LoadCurrencyAsync loader = new LoadCurrencyAsync();
		if(isNetworkAvailable(this) == true){
			loader.execute("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22EURRON%22%2C%20%22USDRON%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
		}else{
			TextView euro = (TextView)findViewById(R.id.euro);
			TextView usd = (TextView) findViewById(R.id.usd);
			ImageView currencyImage = (ImageView)findViewById(R.id.currencyUSD);
			currencyImage.setImageResource(R.drawable.warning);
			euro.setTextSize(20);
			ImageView currencyImage2 = (ImageView)findViewById(R.id.currencyEuro);
			currencyImage2.setVisibility(View.INVISIBLE);
			euro.setText("You don't have a valid connection to the Internet. Please connect to the Internet first!");
			usd.setText("");
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
		getMenuInflater().inflate(R.menu.currency, menu);
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

		@Override
		protected ArrayList<String> doInBackground(String... params) {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(params[0]);
			try {
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
				JSONObject query = obj.getJSONObject("query");
				JSONObject results = query.getJSONObject("results");
				JSONArray rates = results.getJSONArray("rate");
				ArrayList<String> res = new ArrayList<String>();
				for(int i = 0; i<rates.length(); i++){
					JSONObject rate = rates.getJSONObject(i);
					String name = rate.getString("Name");
					String value = rate.getString("Rate");
					StringBuilder el = new StringBuilder();
					el.append(name);
					el.append("     ");
					el.append(value);
					res.add(el.toString());
				}
				return res;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}

		@Override
		protected void onPostExecute(ArrayList<String> result) {
			super.onPostExecute(result);
			TextView euro = (TextView)findViewById(R.id.euro);
			TextView usd = (TextView) findViewById(R.id.usd);
			euro.setText(result.get(0));
			usd.setText(result.get(1));
		}
	}
}

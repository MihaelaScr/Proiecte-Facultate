package menu;

import org.example.discoversuceava.R;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import drawer.SettingsActivity;
import android.support.v4.app.FragmentActivity;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MapActivity extends FragmentActivity {
	static final LatLng SUCEAVA = new LatLng(47.651389, 26.255556);
	static final LatLng PADRINO = new LatLng(47.638935, 26.247471);
	static final LatLng LATINO = new LatLng(47.625517,  26.233206);
	static final LatLng MOSAIK = new LatLng(47.626272, 26.234016);
	static final LatLng OSCARWILDE = new LatLng(47.644133, 26.259315);
	static final LatLng TACOLOCO = new LatLng(47.642174, 26.257258);
	static final LatLng VAMAVECHE = new LatLng(47.652439, 26.205621);
	static final LatLng BUCOVINA = new LatLng(47.641320, 26.258815);
	static final LatLng CONTINENTAL = new LatLng(47.645970, 26.255483);
	static final LatLng IMPERIUM = new LatLng(47.632173, 26.228885);
	static final LatLng SONNENHOF = new LatLng(47.626280, 26.234413);
	static final LatLng ZAMCA = new LatLng(47.651626, 26.245278);
	static final LatLng CARREFOUR = new LatLng(47.663025, 26.267603);
	static final LatLng GALERIA = new LatLng(47.632569, 26.229366);
	static final LatLng IULIUS = new LatLng(47.659518, 26.270401);
	static final LatLng METRO = new LatLng(47.634725, 26.235734);
	static final LatLng CACICA = new LatLng(47.635450, 25.897822);
	static final LatLng CETATE = new LatLng(47.645024, 26.270201);
	static final LatLng PORUMBESCU = new LatLng(47.568374, 26.053348);
	static final LatLng PUTNA = new LatLng(47.866111, 25.598543);
	static final LatLng SUCEVITA = new LatLng(47.778344, 25.711800);
	static final LatLng VORONET = new LatLng(47.517076, 25.864146);
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.DarkGreen)));
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		if(map != null){
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
			    int value = extras.getInt("KEY");
				    switch(value){
				    case 1:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(PADRINO).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(PADRINO, 20));
				    	break;
				    }
				    case 2:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(LATINO).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(LATINO, 20));
				    	break;
				    }
				    case 3:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(MOSAIK).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(MOSAIK, 20));
				    	break;
				    }
				    case 4:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(OSCARWILDE).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(OSCARWILDE, 20));
				    	break;
				    }
				    case 5:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(TACOLOCO).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(TACOLOCO, 20));
				    	break;
				    }
				    case 6:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(VAMAVECHE).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(VAMAVECHE, 20));
				    	break;
				    }
				    case 7:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(BUCOVINA).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(BUCOVINA, 20));
				    	break;
				    }
				    case 8:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(CONTINENTAL).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(CONTINENTAL, 20));
				    	break;
				    }
				    case 9:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(IMPERIUM).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(IMPERIUM, 20));
				    	break;
				    }
				    case 10:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(SONNENHOF).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(SONNENHOF, 20));
				    	break;
				    }
				    case 11:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(ZAMCA).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(ZAMCA, 20));
				    	break;
				    }
				    case 12:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(CARREFOUR).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(CARREFOUR, 20));
				    	break;
				    }
				    case 13:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(GALERIA).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(GALERIA, 20));
				    	break;
				    }
				    case 14:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(IULIUS).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(IULIUS, 20));
				    	break;
				    }
				    case 15:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(METRO).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(METRO, 20));
				    	break;
				    }
				    case 16:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(CACICA).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(CACICA, 20));
				    	break;
				    }
				    case 17:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(CETATE).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(CETATE, 20));
				    	break;
				    }
				    case 18:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(PORUMBESCU).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(PORUMBESCU, 20));
				    	break;
				    }
				    case 19:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(PUTNA).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(PUTNA, 20));
				    	break;
				    }
				    case 20:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(SUCEVITA).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(SUCEVITA, 20));
				    	break;
				    }
				    case 21:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(VORONET).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(VORONET, 20));
				    	break;
				    }
				    default:{
				    	Marker hamburg = map.addMarker(new MarkerOptions().position(SUCEAVA).title("Suceava"));
				    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(SUCEAVA, 15));   
				    	break;
				    }
			    }
			}
		}		     
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent i = new Intent(MapActivity.this, SettingsActivity.class);
			startActivity(i);
		}
		if(id == R.id.actions_exit){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}

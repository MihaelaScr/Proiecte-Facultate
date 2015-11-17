package org.example.discoversuceava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import database.DatabaseWorker;
import drawer.SettingsActivity;
import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GraficActivity extends ActionBarActivity {
	private SQLiteDatabase db;
	private String countQuery, nameQuery;
	private Cursor cCount, cName;
	private Map<String,Integer> maxims = new HashMap<String,Integer>();
	private Map<String,Integer> maximsSorted = new HashMap<String,Integer>();
	private List<String> nameList = new ArrayList<String>();
	private Set<String> names = new HashSet<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grafic);

		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.DarkGreen)));
		
		AttractionGrafic aGrafic = (AttractionGrafic)findViewById(R.id.graficA);
		db = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(DatabaseWorker.DATABASE_NAME), null);
		for(int i=1; i<=21; i++){
			countQuery = "select COUNT(check_in) from journal where attraction_id=?";
			cCount = db.rawQuery(countQuery, new String[]{Integer.toString(i)} );
			nameQuery = "select name from attractions where _id = ?";
			cName = db.rawQuery(nameQuery, new String[]{Integer.toString(i)} );
			if(cName.getCount()>=1 && cCount.getCount()>=1){
				cName.moveToFirst();
				cCount.moveToFirst();
				maxims.put(cName.getString(0), cCount.getInt(0));
			}
		}
		
		maximsSorted = sortByComparator(maxims);
		names = maximsSorted.keySet();
		Iterator iterator = names.iterator(); 
		int i=0;
	      while (iterator.hasNext() && i<5){
	    	 i++;
	         nameList.add(iterator.next().toString());
	      }
	    aGrafic.names = nameList;
		aGrafic.values = new int[]{maxims.get(nameList.get(0)),maxims.get(nameList.get(1)),maxims.get(nameList.get(2)),maxims.get(nameList.get(3)),maxims.get(nameList.get(4))};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grafic, menu);
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
	
	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {
		 
		// Convert Map to List
		List<Map.Entry<String, Integer>> list = 
			new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
 
		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
                                           Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
 
		// Convert sorted map back to a Map
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}

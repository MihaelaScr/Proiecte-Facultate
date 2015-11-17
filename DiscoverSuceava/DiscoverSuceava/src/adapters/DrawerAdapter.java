package adapters;

import org.example.discoversuceava.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends ArrayAdapter<String>{
	private String[] stringsList;
	private int[] imageList;
	private Context context;
	LayoutInflater inflater;
	 
	public DrawerAdapter(String[] stringsList, Context ctx, int[] imageList) {
	    super(ctx, R.layout.drawer_list_view_item, stringsList);
	    this.imageList = imageList;
	    this.stringsList = stringsList;
	    this.context = ctx;
	    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	 
	public View getView(int position, View convertView, ViewGroup parent) {
	    if (convertView == null) {
	        convertView = inflater.inflate(R.layout.drawer_list_view_item, parent, false);
	    }
	    ImageView imgView = (ImageView) convertView.findViewById(R.id.item_icon2);
	    imgView.setImageResource(imageList[position]);
	    TextView tv = (TextView) convertView.findViewById(R.id.item_text2);
	    tv.setText(stringsList[position]);
	    return convertView;
	}
}

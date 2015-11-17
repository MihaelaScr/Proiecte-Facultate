package adapters;

import java.util.ArrayList;
import java.util.List;

import org.example.discoversuceava.Attraction;
import org.example.discoversuceava.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class JournalAdapter extends BaseAdapter {
	private static class ViewHolder {
		public ImageView img;
		public TextView nume;
		public TextView data;
		}
		List<Attraction> attractions =new ArrayList<Attraction>();
		LayoutInflater layoutInflater;
		public JournalAdapter(Context context, int resursaLayout,List<Attraction> restaurants) {
		layoutInflater = LayoutInflater.from(context);
		this.attractions = restaurants;
		}
		
		@Override
		public int getCount() {
		return attractions.size();
		}
		
		@Override
		public Object getItem(int position) {
		return attractions.get(position);
		}
		
		@Override
		public long getItemId(int position) {
		return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			ViewHolder holder;
			if(convertView == null) {
			view = layoutInflater.inflate(R.layout.lv_journal_layout, parent, false);
			holder = new ViewHolder();
			holder.img = (ImageView)view.findViewById(R.id.journalImg);
			holder.nume = (TextView)view.findViewById(R.id.locationName);
			holder.data = (TextView)view.findViewById(R.id.locationDate);
			view.setTag(holder);
			}else{	
			view = convertView;
			holder = (ViewHolder)view.getTag(); 
			}
			
			Attraction atraction = attractions.get(position);
			holder.img.setImageBitmap(atraction.getImage());
			holder.nume.setText(atraction.getName());
			holder.data.setText(atraction.getDate());
			return view;

		}
}

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

public class MySimpleAdapter extends BaseAdapter{
	
	private static class ViewHolder {
		public ImageView img;
		public TextView nume;
		public ImageView rate;
		}
		List<Attraction> attractions =new ArrayList<Attraction>();
		LayoutInflater layoutInflater;
		
		public MySimpleAdapter(Context context, int resursaLayout,List<Attraction> restaurants) {
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
				view = layoutInflater.inflate(R.layout.lv_attraction_layout, parent, false);
				holder = new ViewHolder();
				holder.img = (ImageView)view.findViewById(R.id.img);
				holder.nume = (TextView)view.findViewById(R.id.attrName);
				holder.rate = (ImageView)view.findViewById(R.id.rateImg);
				view.setTag(holder);
			}else{	
				view = convertView;
				holder = (ViewHolder)view.getTag(); 
			}
			
			Attraction atraction = attractions.get(position);
			holder.img.setImageBitmap(atraction.getImage());
			holder.nume.setText(atraction.getName());
			holder.rate.setImageBitmap(atraction.getRate());
			return view;

		}
}

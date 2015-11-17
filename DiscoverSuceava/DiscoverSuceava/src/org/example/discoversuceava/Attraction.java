package org.example.discoversuceava;

import android.app.ListActivity;
import android.graphics.Bitmap;


public class Attraction extends ListActivity {
	String name;
	String date;
	Bitmap rate;
	Bitmap image;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	
	public Bitmap getRate() {
		return rate;
	}
	public void setRate(Bitmap rate) {
		this.rate = rate;
	}
	
	
	@Override
	public String toString() {
		return "Denumire: " + name;
	}
	
	
}

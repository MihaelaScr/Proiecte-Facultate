package database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.EditText;



public class ThoughtsMemo {
	public static  Context context;
	public boolean success ;
	public String attractionName, goodDate;
	public SimpleCursorAdapter cursorAdapter;
	public SQLiteDatabase db;
	public Cursor cJournal, cUser, cAttraction;
	public String email, password, userQuery, attractionQuery, journalQuery;
	public Date date;
	public SimpleDateFormat dateFormat;
	
	
	public ThoughtsMemo(Context context, String name){
		ThoughtsMemo.context = context;
		this.attractionName = name;
		SharedPreferences setari= context.getSharedPreferences("UserSettings", Activity.MODE_PRIVATE);
	    email = setari.getString("email", "no_user");
		password = setari.getString("password", "no_user");
		 if(email.equals("no_user") && password.equals("no_user")){
			 success = false;
		 }else{
			 success = true;
			 db = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath(DatabaseWorker.DATABASE_NAME), null);
	         
	         userQuery = "select _id from users where email = ? AND password = ?";
	         attractionQuery = "select _id from attractions where name = ?";
	         journalQuery = "select _id, comments, check_in from journal where user_id = ? and attraction_id = ?";
	         
	         cUser = db.rawQuery( userQuery, new String[]{email, password});
	         cAttraction = db.rawQuery( attractionQuery , new String[]{attractionName});
	         cUser.moveToFirst();
	         cAttraction.moveToFirst();
	 		 cJournal = db.rawQuery(journalQuery, new String[]{Integer.toString(cUser.getInt(0)), Integer.toString(cAttraction.getInt(0))});
	 		 
	 	     dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	         date = new Date();
	         dateFormat.setTimeZone(TimeZone.getTimeZone("EET"));
	         goodDate = dateFormat.format(date); 
		 }
		 
		 
		 
	}
	 
	public boolean addThoughts(){
		final EditText input = new EditText(context);

		new AlertDialog.Builder(context)
		    .setTitle("Make Memories !!!")
		    .setView(input)
		    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int whichButton) {
		     		 if(success == true){
		             String editable = input.getText().toString();
		             System.out.println(editable);
		             System.out.println(attractionName);
	     	        
		     		 if(cJournal.getCount()>=1 && cJournal.moveToFirst()){
		     			//daca userul mai are comentarii la atractia respectiva fac append la comentariu si apoi update
		     			System.out.println("User cu comentarii");
		     			ContentValues values = new ContentValues();
		     			values.put("date", goodDate);
		     			if(cJournal.getString(1) !=null){
		     				values.put("comments", cJournal.getString(1).concat(" ").concat(editable));
		     			}else{
		     				values.put("comments",editable);
		     			}
		     			long id = db.update("journal" , values , "_id=?",
		     					new String[] {String.valueOf(cJournal.getInt(0))});
		     		 }else{
		     			 //daca userul nu mai are comentarii la atractia respectiva fac insert in tabela
		     			System.out.println("User fara comentarii");
		     	        ContentValues values = new ContentValues();
		     	        values.put("date", goodDate);
		     	        values.put("check_in", false);
		     	        values.put("comments", editable);
		     	        values.put("user_id", cUser.getInt(0));
		     	        values.put("attraction_id", cAttraction.getInt(0));
		     	        long id = db.insert("journal", null, values);
		     		 }
		            
		     		 }
		         }
		    })
		    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int whichButton) {
		         }
		    }).show();
		return success;
	}
	
	public boolean checkIn(){
		if(success == true){
			if(cJournal.getCount()>=1 && cJournal.moveToFirst()){
     			//daca userul are deja o inregistrare facuta la locatia respectiva
     			System.out.println("User cu comentarii check-in");
     			ContentValues values = new ContentValues();
     			values.put("check_in", true);
     			long id = db.update("journal" , values , "_id=?",
     					new String[] {String.valueOf(cJournal.getInt(0))});
     		 }else{
     			 //daca nu am comentariu la locatia respectiva fac insert
     			System.out.println("User fara comentarii check-in");
     	        ContentValues values = new ContentValues();
     	        values.put("date", goodDate);
     	        values.put("check_in", true);
     	        values.put("user_id", cUser.getInt(0));
     	        values.put("attraction_id", cAttraction.getInt(0));
     	        long id = db.insert("journal", null, values);
     		 }
            
		}
		return success;
	}
}

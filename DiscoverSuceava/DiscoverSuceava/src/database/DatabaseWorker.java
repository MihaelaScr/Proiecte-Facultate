package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseWorker {
	public static final String DATABASE_NAME = "discover_suceava.db";
	private static final String DATABASE_USER = "users";
	private static final String DATABASE_JOURNAL = "journal";
	private static final String DATABASE_ATTRACTIONS= "attractions";
	private static final int DATABASE_VERSION = 4;
	
	private static final String USER_CREATE = "CREATE TABLE IF NOT EXISTS users (_id INTEGER PRIMARY KEY AUTOINCREMENT, email VARCHAR(60), password VARCHAR(30), screen_name VARCHAR(30))";
	private static final String JOURNAL_CREATE = "CREATE TABLE IF NOT EXISTS journal (_id INTEGER PRIMARY KEY AUTOINCREMENT, date DATE, check_in BOOLEAN, comments VARCHAR(8000), " +
				"user_id INTEGER, attraction_id INTEGER, FOREIGN KEY (user_id) REFERENCES users(_id), FOREIGN KEY (attraction_id) REFERENCES attractions(_id))" ;
	private static final String ATTRACTION_CREATE = "CREATE TABLE IF NOT EXISTS attractions (_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(60))";
	
	private final Context context;

	  private DatabaseHelper DBHelper;
	  private SQLiteDatabase db;

	  public DatabaseWorker(Context ctx) {
	    this.context = ctx;
	    DBHelper = new DatabaseHelper(context);
	  }

	  private static class DatabaseHelper extends SQLiteOpenHelper {
	    DatabaseHelper(Context context) {
	      super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {
	      try {
	        db.execSQL(USER_CREATE);
	        db.execSQL(JOURNAL_CREATE);
	        db.execSQL(ATTRACTION_CREATE);
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }

	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	      db.execSQL("DROP TABLE IF EXISTS users");
	      db.execSQL("DROP TABLE IF EXISTS journal");
	      db.execSQL("DROP TABLE IF EXISTS attractions");
	      onCreate(db);
	    }
	   
	  }

	  public SQLiteDatabase open() throws SQLException {
	    db = DBHelper.getWritableDatabase();
	    return db;
	  }

	  public void close() {
	    DBHelper.close();
	  }
	  
	  public long insertAttractions(String name) {
		    ContentValues initialValues = new ContentValues();
		    initialValues.put("name", name);
		    return db.insert( DATABASE_ATTRACTIONS, null, initialValues);
	  }

}

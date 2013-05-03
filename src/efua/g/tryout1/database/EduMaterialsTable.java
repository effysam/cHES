package efua.g.tryout1.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class EduMaterialsTable {
	
	//database table
	 public static final String EDUCATIONAL_MATERIALS = "educational_materials";
	 public static final String MATERIAL_ID = "_id";
	 public static final String TITLE = "title";
	 public static final String CONTENT = "content";	 
//	 public static final String IMAGE = "time";
//	 public static final String NURSE_ID = "nurse_id";
	 
	// Database creation SQL statement
	  private static final String DATABASE_CREATE = "CREATE TABLE " 
	      + EDUCATIONAL_MATERIALS
	      + "(" 
	      + MATERIAL_ID + " integer primary key autoincrement, " 
	      + TITLE + " text not null, " 
	      + CONTENT + " text not null "
	   //   + NURSE_ID + " text "
	      + ");";
	  
	  public static final String DATABASE_SELECT = "SELECT * FROM EDUCATIONAL_MATERIALS";
	  
	  public static void onCreate(SQLiteDatabase database) {
		    database.execSQL(DATABASE_CREATE);
		    database.execSQL(DATABASE_SELECT);
		    Log.d("database", DATABASE_CREATE);
		  }

		  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
		      int newVersion) {
		    Log.w(EduMaterialsTable.class.getName(), "Upgrading database from version "
		        + oldVersion + " to " + newVersion
		        + ", which will destroy all old data");
		   database.execSQL("DROP TABLE IF EXISTS " + EDUCATIONAL_MATERIALS);
		    onCreate(database);
		  }
	  
}

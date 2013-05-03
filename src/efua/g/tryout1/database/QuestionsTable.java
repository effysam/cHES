package efua.g.tryout1.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class QuestionsTable {
	
	//database table
	 public static final String QUESTIONS = "questions";
	 public static final String QUESTION_ID = "_id";
	 public static final String QUESTION = "question";	 
	 public static final String TIME = "time";
	 public static final String NURSE_ID = "nurse_id";
	 
	// Database creation SQL statement
	  private static final String DATABASE_CREATE = "CREATE TABLE " 
	      + QUESTIONS
	      + "(" 
	      + QUESTION_ID + " integer primary key autoincrement, " 
	      + QUESTION + " text not null, " 
	      + TIME + " datetime default current_timestamp, "
	      + NURSE_ID + " text "
	      + ");";
	  
	  public static final String DATABASE_SELECT = "SELECT * FROM QUESTIONS";
	  
	  public static void onCreate(SQLiteDatabase database) {
		    database.execSQL(DATABASE_CREATE);
		    database.execSQL(DATABASE_SELECT);
		    Log.d("database", DATABASE_CREATE);
		  }

		  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
		      int newVersion) {
		    Log.w(QuestionsTable.class.getName(), "Upgrading database from version "
		        + oldVersion + " to " + newVersion
		        + ", which will destroy all old data");
		   database.execSQL("DROP TABLE IF EXISTS " + QUESTIONS);
		    onCreate(database);
		  }
	  
}

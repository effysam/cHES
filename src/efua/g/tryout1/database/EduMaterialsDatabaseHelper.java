package efua.g.tryout1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class EduMaterialsDatabaseHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "communityhealthlocal.db";
  private static final int DATABASE_VERSION = 11;

  public EduMaterialsDatabaseHelper(Context context) {	
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  // Method is called during creation of the database
  @Override
  public void onCreate(SQLiteDatabase database) {
    EduMaterialsTable.onCreate(database);
  }

  // Method is called during an upgrade of the database,
  // e.g. if you increase the database version
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion,
      int newVersion) {
	  EduMaterialsTable.onUpgrade(database, oldVersion, newVersion);
  }
} 

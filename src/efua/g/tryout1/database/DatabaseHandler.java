package efua.g.tryout1.database;


import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

	public class DatabaseHandler extends SQLiteOpenHelper {
		 
	    // All Static variables
	    // Database Version
	    private static final int DATABASE_VERSION = 1;
	 
	    // Database Name
	    private static final String DATABASE_NAME = "communityhealth";
	 
	    // Questions table name
	    private static final String TABLE_QUESTIONS = "questions";
	    
	    //educational repository table name
	 //   private static final String TABLE_EDUCATIONAL_MATERIALS = "educational_materials";
	 
	    // Questions Table Columns names
	    private static final String KEY_ID = "id";
	    private static final String KEY_QUESTION = "question";
	 
	    public DatabaseHandler(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
	 
	    // Creating Tables
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
	                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_QUESTION + " TEXT" + ")";
	        db.execSQL(CREATE_QUESTIONS_TABLE);
	    }
	 
	    // Upgrading database
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        // Drop older table if existed
	        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
	 
	        // Create tables again
	        onCreate(db);
	    }
	    
	    /**
	     * All CRUD(Create, Read, Update, Delete) Operations
	     */
	 /*
	    // Adding new Questions
	    void addQuestion(Questions question) {
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues values = new ContentValues();
	        values.put(KEY_QUESTION, question.getQuestion()); // question Name
	       	 
	        // Inserting Row
	        db.insert(TABLE_QUESTIONS, null, values);
	        db.close(); // Closing database connection
	    }
	 
	    // Getting single question
	    Questions getquestion(int id) {
	        SQLiteDatabase db = this.getReadableDatabase();
	 
	        Cursor cursor = db.query(TABLE_QUESTIONS, new String[] { KEY_ID,
	                KEY_QUESTION}, KEY_ID + "=?",
	                new String[] { String.valueOf(id) }, null, null,null,null);
	        if (cursor != null)
	            cursor.moveToFirst();
	 
	        Questions question = new Questions(Integer.parseInt(cursor.getString(0)),
	                cursor.getString(1));
	        // return question
	        return question;
	    }
	 
	    
	    // Getting All Questionss
	    public List<Questions> getAllQuestions() {
	        List<Questions> questionList = new ArrayList<Questions>();
	        // Select All Query
	        String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS;
	 
	        SQLiteDatabase db = this.getWritableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	 
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	                Questions question = new Questions();
	                question.setID(Integer.parseInt(cursor.getString(0)));
	                question.setQuestion(cursor.getString(1));
	                // Adding question to list
	                questionList.add(question);
	            } while (cursor.moveToNext());
	        }
	 
	        // return question list
	        return questionList;
	    }
	 */
	    /*
	    // Updating single question
	    public int updateQuestions(Questions question) {
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues values = new ContentValues();
	        values.put(KEY_QUESTION, question.getQuestion());
	 
	        // updating row
	        return db.update(TABLE_QUESTIONS, values, KEY_ID + " = ?",
	                new String[] { String.valueOf(question.getID()) });
	    }
	 
	    // Deleting single question
	    public void deleteQuestion(Questions question) {
	        SQLiteDatabase db = this.getWritableDatabase();
	        db.delete(TABLE_QUESTIONS, KEY_ID + " = ?",
	                new String[] { String.valueOf(question.getID()) });
	        db.close();
	    }
	 
	    // Getting questions Count
	    public int getquestionsCount() {
	        String countQuery = "SELECT  * FROM " + TABLE_QUESTIONS;
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(countQuery, null);
	        cursor.close();
	 
	        // return count
	        return cursor.getCount();
	    }
	 */ 
	}
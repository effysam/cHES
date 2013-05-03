package efua.g.tryout1;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import efua.g.tryout1.contentprovider.EduMaterialsContentProvider;
import efua.g.tryout1.contentprovider.QuestionsContentProvider;
import efua.g.tryout1.database.EduMaterialsTable;
import efua.g.tryout1.database.QuestionsTable;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
 
@SuppressLint("NewApi")
public class MobileLearningHome extends Activity implements  LoaderManager.LoaderCallbacks<Cursor>{
    // All static variables
    static final String URL = "http://10.0.2.2/community/eduRepo.php?cmd=1";
    // JSON node keys
   // static final String TAG_ID = "id";
    public static String TAG_MATERIALDETAILS = "eduMaterials";
    static final String TAG_TITLE = "TITLE";
    static final String TAG_CONTENT = "CONTENT";
    static String KEY_THUMB_URL = "IMAGE";
    

//inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    
    ListView list;
    LazyAdapter adapter;
    SimpleCursorAdapter offLineAdapter;
    TextView txtcontent;
    
    ArrayList<HashMap<String, String>> eduMaterials;
    JSONArray materialDetails = null;
  //Creating Json Parser Instance
    ParseJSON jsonParser = new ParseJSON();
 
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_learning_home);
        list=(ListView)findViewById(R.id.list);
        fillData();
      //  registerForContextMenu(getListView());
           
       
        //materialDetails array
        new LoadAllMaterials().execute();
       
        list.setOnItemClickListener(new OnItemClickListener() { 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                       	
            	String title = ((TextView) view.findViewById(R.id.title)).getText().toString();
            	//View vi = inflater.inflate(R.layout.activity_ches_row, null);
            	String content = ((TextView) view.findViewById(R.id.content)).getText().toString();
               // Starting new intent
                Intent in = new Intent(getApplicationContext(), EduDetails.class);
                // sending answer, name and answer_time to next activity
                in.putExtra(TAG_TITLE, title);
                in.putExtra(TAG_CONTENT, content);
                 
                // starting new activity
                startActivityForResult(in, 100);
 
            }
        });
               
    }
    
     

   class LoadAllMaterials extends AsyncTask<String, String, String> {
    	   protected String doInBackground(String...args){
         try	{
        	 eduMaterials= new ArrayList<HashMap<String, String>>();
         
        	 //Getting Array of materialDetails
        	 JSONObject jsonObject = jsonParser.getJSONFromUrl(URL);
        	 
        	 materialDetails=jsonObject.getJSONArray(TAG_MATERIALDETAILS);
        	 
		 	//looping through all answerDetails
		 	for (int i=0;i<materialDetails.length();i++){
		 		JSONObject a = materialDetails.getJSONObject(i);
		 		
		 		//storing each json item in variable
		 		String title=a.getString(TAG_TITLE);
		 		String content= a.getString(TAG_CONTENT);
		     	String image=a.getString(KEY_THUMB_URL);
		     	
		     	//String default_image = "http://10.0.2.2/MobileWeb/ajFlow.php";
		     	HashMap<String, String> map = new HashMap<String, String>();
		         
		     	// adding each child node to HashMap key => value
		         map.put(TAG_TITLE, title);
		         map.put(TAG_CONTENT, content);
		      	 map.put(KEY_THUMB_URL, image); 
		
		    	 
		         // adding HashList to ArrayList
		         eduMaterials.add(map);
		     }
         } 
         catch (JSONException e) {
             e.printStackTrace();
         }
         return null;
       }
        
    	   protected void onPostExecute(String file_url){
    	   // Getting adapter by passing json data ArrayList
    		   adapter = new LazyAdapter(MobileLearningHome.this, eduMaterials);
    	        list.setAdapter(adapter);
    	        
    	   }
    }  
   
    
    private void fillData() {
		  list=(ListView)findViewById(R.id.list);
		    // Fields from the database (projection)
		    // Must include the _id column for the adapter to work
		    String[] from = new String[] {EduMaterialsTable.TITLE, EduMaterialsTable.CONTENT };
		    // Fields on the UI to which we map
		    int[] to = new int[] { R.id.title, R.id.content};

		    getLoaderManager().initLoader(0, null, this);
		    offLineAdapter = new SimpleCursorAdapter(this, R.layout.activity_ches_row, null, from,
		        to, 0);

		    list.setAdapter(offLineAdapter);
		  }
 
   
	    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		    String[] projection = { EduMaterialsTable.MATERIAL_ID, EduMaterialsTable.TITLE, EduMaterialsTable.CONTENT};
		    CursorLoader cursorLoader = new CursorLoader(this,
		        EduMaterialsContentProvider.CONTENT_URI, projection, null, null, null);
		    return cursorLoader;
		  }
	
	 // @Override
	 	  public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
	 		 offLineAdapter.swapCursor(data);
	 	  }
	
	 	  @Override
	 	  public void onLoaderReset(Loader<Cursor> loader) {
	 	    // data is not available anymore, delete reference
	 		 offLineAdapter.swapCursor(null);
	 	  }	  

 
}
    


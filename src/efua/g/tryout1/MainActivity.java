package efua.g.tryout1;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

    
    //url to make request
    private static String url = "";
    
    //JSON Node names
    
    public static String TAG_ANSWERDETAILS = "QnAs";
    public static final String TAG_QUESTION = "QUESTION";
    public static final String TAG_TIME = "TIME";
    public static final String TAG_ANSWER = "ANSWER";
    public static final String TAG_NAME = "SPEC_NAME";
    public static final String TAG_ANSWER_TIME = "ANSWER_TIME";
    
    ArrayList<HashMap<String, String>> arrayListAnswers;
  //answerDetails array
    JSONArray answerDetails = null;
    
    //Creating Json Parser Instance
    ParseJSON jsonParser = new ParseJSON();
    
   // TextView viewResult;
    ListView listAnswers;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listAnswers = (ListView)findViewById(R.id.listAnswers); 
        
        Intent i = getIntent();
        // getting attached intent data
        String option = i.getStringExtra(MyLibrary.SELECTED_OPTION);
        
        if ("View All Questions".equalsIgnoreCase(option)){
        	url = "http://10.0.2.2/community/fetchQnAs.php?cmd=2&username="+ NurseLogin.mUsername;
        }
        else if ("Recent Questions".equalsIgnoreCase(option)){
        	url = "http://10.0.2.2/community/fetchQnAs.php?cmd=6&username="+ NurseLogin.mUsername;
        }
        else if ("General Questions".equalsIgnoreCase(option)){
        	url = "http://10.0.2.2/community/fetchQnAs.php?cmd=3&username="+ NurseLogin.mUsername;
        }
        else if ("Nutrition Questions".equalsIgnoreCase(option)){
        	url = "http://10.0.2.2/community/fetchQnAs.php?cmd=4&username="+ NurseLogin.mUsername;
        }
        else if ("Pharmaceutical Questions".equalsIgnoreCase(option)){
        	url = "http://10.0.2.2/community/fetchQnAs.php?cmd=5&username="+ NurseLogin.mUsername;	
        }
        
      //loading answers in background thread
        new LoadAllAnswers().execute();
        

        listAnswers.setOnItemClickListener(new OnItemClickListener() { 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
            	String question = ((TextView) view.findViewById(R.id.question1)).getText().toString();
            	String time = ((TextView) view.findViewById(R.id.time1)).getText().toString();
                String answer = ((TextView) view.findViewById(R.id.answer)).getText().toString();
                String name =  ((TextView) view.findViewById(R.id.name)).getText().toString();
                String answer_time = ((TextView) view.findViewById(R.id.answer_time)).getText().toString();
                // Starting new intent
                Intent in = new Intent(getApplicationContext(), ViewAnswers.class);
                // sending answer, name and answer_time to next activity
                in.putExtra(TAG_QUESTION, question);
                in.putExtra(TAG_TIME, time);
                in.putExtra(TAG_ANSWER, answer);
                in.putExtra(TAG_NAME, name);
                in.putExtra(TAG_ANSWER_TIME, answer_time);
 
                // starting new activity
                startActivityForResult(in, 100);
            }
        });
 
    }
        
    
   class LoadAllAnswers extends AsyncTask<String, String, String> {
   protected String doInBackground(String...args){
    try	{
    	//Hashmap for ListView
        arrayListAnswers = new ArrayList<HashMap<String, String>>();
    	
    	JSONObject jsonObject = jsonParser.getJSONFromUrl(url);
    	//Getting array of answerDetails
    	answerDetails = jsonObject.getJSONArray(TAG_ANSWERDETAILS);
    	
    	//looping through all answerDetails
    	for (int i=0;i<answerDetails.length();i++){
    		JSONObject a = answerDetails.getJSONObject(i);
    		
    	//Storing each json item in variable
    	String question=a.getString(TAG_QUESTION);
    	String time=a.getString(TAG_TIME);
    	String answer = a.getString(TAG_ANSWER);
    	String spec_name = a.getString(TAG_NAME);
    	String answer_time = a.getString(TAG_ANSWER_TIME);
    	
    	 // creating new HashMap
        HashMap<String, String> map = new HashMap<String, String>();
        
     // adding each child node to HashMap key => value
        map.put(TAG_QUESTION, question);
        map.put(TAG_TIME, time);
        map.put(TAG_ANSWER, answer);
        map.put(TAG_NAME, spec_name);
        map.put(TAG_ANSWER_TIME, answer_time);
        
        
        // adding HashList to ArrayList
        arrayListAnswers.add(map);    	
    	}
    }
    catch (JSONException e) {
    	e.printStackTrace();
    }
    return null;
   }
  
   protected void onPostExecute(String file_url){
    /**
     * Updating parsed JSON data into ListView
     * */
    ListAdapter adapter = new SimpleAdapter(MainActivity.this, arrayListAnswers,
            R.layout.activity_main,
            new String[] {TAG_QUESTION, TAG_TIME, TAG_ANSWER, TAG_NAME, TAG_ANSWER_TIME},
            new int[] {R.id.question1, R.id.time1, R.id.answer, R.id.name,  R.id.answer_time});

    listAnswers.setAdapter(adapter);
   }
  }

    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}


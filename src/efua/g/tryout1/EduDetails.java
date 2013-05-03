package efua.g.tryout1;

import efua.g.tryout1.contentprovider.EduMaterialsContentProvider;
import efua.g.tryout1.database.EduMaterialsTable;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.TextView;
 
public class EduDetails extends Activity {
    
	String title;
	String content;
	private Uri todoUri;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edu_details);
          
     // getting attached intent data
     			        
        TextView txtTitle = ((TextView)findViewById(R.id.title1));
		TextView txtContent = ((TextView)findViewById(R.id.content1));
			       
		todoUri = (savedInstanceState == null) ? null : (Uri) savedInstanceState
		        .getParcelable(EduMaterialsContentProvider.CONTENT_ITEM_TYPE);
		
		Intent i = getIntent();
	        // getting attached intent data
			title = i.getStringExtra(MobileLearningHome.TAG_TITLE);
			content = i.getStringExtra(MobileLearningHome.TAG_CONTENT);
	       
	        
	        // displaying selected item details
	        txtTitle.setText(title);
	        txtContent.setText(content);
	        
	        storeInLocal();

    }
    
    //store articles in local database
    private void storeInLocal(){
		 //make provistion of if title or content is the same as in the database, do 
		  //not insert
    
		  ContentValues values = new ContentValues();
		  values.put(EduMaterialsTable.TITLE, title);
		  values.put(EduMaterialsTable.CONTENT, content);
		   		  
	        todoUri = getContentResolver().insert(EduMaterialsContentProvider.CONTENT_URI, values);
		  }
	  
    //LazyAdapter.material.get(MobileLearningHome.TAG_CONTENT)
    
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edu_details, menu);
		return true;
	}   
}
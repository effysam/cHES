package efua.g.tryout1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import efua.g.tryout1.contentprovider.QuestionsContentProvider;
import efua.g.tryout1.database.QuestionsTable;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import android.view.View;
import android.widget.EditText;
//import android.widget.TextView;

public class PoseQuestion extends Activity {
	
	public static final String EXTRA_QUESTION = "";
	public static final String EXTRA_ID = "2";
	EditText questionTxt, choIdTxt;
	public String question,question1;
	private Uri todoUri;
	private TextView it;
	boolean cancel = false;
	View focusView = null;
	

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_pose_question);
		
		it = (TextView)findViewById(R.id.textView1);
		//it.setText("username: "+NurseLogin.mUsername);
		final Button askButton=(Button)findViewById(R.id.submit);
		
		todoUri = (bundle == null) ? null : (Uri) bundle
		        .getParcelable(QuestionsContentProvider.CONTENT_ITEM_TYPE);
		
		askButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {	
				//forward questions to specialist
				questionTxt = (EditText)findViewById(R.id.question);
			   finish();
			}
		});
	}


	@Override
	public void finish(){
		question = questionTxt.getText().toString();
		try {		
			question1 = URLEncoder.encode(question,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Comment out storeInLocal() for questions till you figure out how to intergrate 
		 * multiple content providers. 
		 */
		//storeInLocal();
		
		DownloadWebPageTask task = new DownloadWebPageTask();
		if (question.length() == 0) {
			if (TextUtils.isEmpty(question)) {
				questionTxt.setError(getString(R.string.error_field_required));
				focusView = questionTxt;
				cancel = true;
			}
        }
		else{
			task.execute(new String[] {"http://10.0.2.2/community/askQuestions.php?cmd=1&question="
										+question1+"&username="+ NurseLogin.mUsername});	  	
			questionTxt.setText("");
			makeToast();
			}
		
		}
		
		private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
			@Override
			protected String doInBackground(String ... urls) {
				String response = "";
				for (String url : urls) {
					DefaultHttpClient client = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(url);
					try {
						HttpResponse execute = client.execute(httpGet);
						InputStream content = execute.getEntity().getContent();

						BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
						String s = "";
						while ((s = buffer.readLine()) != null) {
							response += s;
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return response;
			}
 
}

		
		  protected void onSaveInstanceState(Bundle outState) {
			    super.onSaveInstanceState(outState);
			    outState.putParcelable(QuestionsContentProvider.CONTENT_ITEM_TYPE, todoUri);
			  }
		  
		  //store data into local database on phone		  
		  private void storeInLocal(){
			 question = questionTxt.getText().toString();
			 
			  if (question.length() == 0) {
					if (TextUtils.isEmpty(question)) {
						questionTxt.setError(getString(R.string.error_field_required));
						focusView = questionTxt;
						cancel = true;
					}
		        }
			  else{
			  ContentValues values = new ContentValues();
		        values.put(QuestionsTable.QUESTION, question);
			    values.put(QuestionsTable.NURSE_ID, NurseLogin.mUsername);
			  
		        todoUri = getContentResolver().insert(QuestionsContentProvider.CONTENT_URI, values);
			  }
		  }

		  private void makeToast() {
			    Toast.makeText(PoseQuestion.this, "Question submitted",
			        Toast.LENGTH_SHORT).show();
			  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_pose_question, menu);
		return true;
	}

}


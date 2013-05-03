
package efua.g.tryout1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class ViewAnswers extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_answers);
		TextView txtQuestion = ((TextView)findViewById(R.id.question1));
		TextView txtTime = ((TextView)findViewById(R.id.time1));
		TextView txtAnswer = ((TextView)findViewById(R.id.answer));
		TextView txtName =  ((TextView)findViewById(R.id.name));
		TextView txtAnswer_Time = ((TextView)findViewById(R.id.answer_time));
	       
		Intent i = getIntent();
	        // getting attached intent data
			String question = i.getStringExtra(MainActivity.TAG_QUESTION);
			String time = i.getStringExtra(MainActivity.TAG_TIME);
	        String answer = i.getStringExtra(MainActivity.TAG_ANSWER);
	        String name = i.getStringExtra(MainActivity.TAG_NAME);
	        String answer_time = i.getStringExtra(MainActivity.TAG_ANSWER_TIME);
	        
	        // displaying selected item details
	        txtQuestion.setText(question);
	        txtTime.setText(time);
	        txtAnswer.setText(answer);
	        txtName.setText(name);
	        txtAnswer_Time.setText(answer_time);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_view_answers, menu);
		return true;
	}

}

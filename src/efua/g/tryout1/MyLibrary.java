	package efua.g.tryout1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MyLibrary extends ListActivity {
	public static final String SELECTED_OPTION = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        // storing string resources into Array
        String[] library_options = getResources().getStringArray(R.array.library_options);
 
        // Binding resources Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_my_library, R.id.label, library_options));
 
        ListView lv = getListView();
        
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
 
              // selected item
              String option = ((TextView) view).getText().toString();
              Log.d("MEGA","its working"+option);
              // Launching new Activity on selecting single List Item
              Intent i = new Intent(getApplicationContext(), MainActivity.class);
              // sending data to new activity
              i.putExtra(SELECTED_OPTION, option);
              startActivity(i);
 
          }
        });
    }
}

package efua.g.tryout1;

import java.util.ArrayList;
import java.util.HashMap;

import efua.g.tryout1.contentprovider.EduMaterialsContentProvider;
import efua.g.tryout1.contentprovider.QuestionsContentProvider;
import efua.g.tryout1.database.EduMaterialsTable;
import efua.g.tryout1.database.QuestionsTable;
 
import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
 
public class LazyAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    public static HashMap<String, String> material;
    private SimpleCursorAdapter adapter;
  //  public int position;
 
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
       

    }
    
    public void notifyDataSetChanged(){ 
    	
    }

	public int getCount() {
        if(data != null){
        	System.out.print(data.size());
            return data.size();
        }
        return 0;
    }
 
    public Object getItem(int position) {
    	System.out.print(position);
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {

        material = new HashMap<String, String>();
        material = data.get(position);
        
     	View vi=convertView;
        vi = inflater.inflate(R.layout.activity_ches_row, null);
        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView content = (TextView)vi.findViewById(R.id.content); // title
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
       
     // Setting all values in listview 
        title.setText(material.get(MobileLearningHome.TAG_TITLE));
        content.setText(material.get(MobileLearningHome.TAG_CONTENT));
        content .setVisibility(View.INVISIBLE);
        imageLoader.DisplayImage(material.get(MobileLearningHome.KEY_THUMB_URL), thumb_image);
      
        return vi;
    }
    
    
	  // Creates a new loader after the initLoader () call
	//  @Override

    
    

}
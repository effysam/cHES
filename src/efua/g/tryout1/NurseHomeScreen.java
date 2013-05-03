package efua.g.tryout1;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
 
public class NurseHomeScreen extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_home_screen);
 
        TabHost tabHost = getTabHost();
 
     // Tab for app home screen
        TabSpec homePage = tabHost.newTabSpec("Home");
        // setting Title and Icon for the Tab
       // askSpec.setIndicator("Ask", getResources().getDrawable(R.drawable.icon_photos_tab));
        homePage.setIndicator("Home");
        Intent homeIntent = new Intent(this, MobileLearningHome.class);
        homePage.setContent(homeIntent);
        
 
        // Tab for asking specialists
        TabSpec askSpec = tabHost.newTabSpec("Ask");
        // setting Title and Icon for the Tab
       // askSpec.setIndicator("Ask", getResources().getDrawable(R.drawable.icon_photos_tab));
        askSpec.setIndicator("Ask Specialist");
        Intent askIntent = new Intent(this, PoseQuestion.class);
        askSpec.setContent(askIntent);
        
        // Tab for Library
        TabSpec mLib = tabHost.newTabSpec("Library");
        //mLib.setIndicator("Library", getResources().getDrawable(R.drawable.icon_songs_tab));
        mLib.setIndicator("My Library");
        Intent libIntent = new Intent(this, MyLibrary.class);
        mLib.setContent(libIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(homePage);//Adding home page tab
        tabHost.addTab(askSpec); //Adding ask specialist tab
        tabHost.addTab(mLib); //Adding library tab

    }
}
package org.blacklight.android.flexibleprofiles.activity;

import org.blacklight.android.flexible_profiles.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class FlexibleProfilesActivity extends Activity {

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        TabSpec tabProfiles = tabHost.newTabSpec("Profiles");
        TabSpec tabRules = tabHost.newTabSpec("Rules");
        
        tabProfiles.setIndicator("Profiles");
        tabProfiles.setContent(new Intent(this, ProfilesActivity.class));
        
        tabRules.setIndicator("Rules");
        tabRules.setContent(new Intent(this, RulesActivity.class));
        
        tabHost.addTab(tabProfiles);
        tabHost.addTab(tabRules);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(org.blacklight.android.flexible_profiles.R.menu.main, menu);
	return true;
    }

}


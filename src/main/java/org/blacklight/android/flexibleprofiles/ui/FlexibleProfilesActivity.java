package org.blacklight.android.flexibleprofiles.ui;

import org.blacklight.android.flexible_profiles.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

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
        
        final Button fileLoadButton = (Button) findViewById(R.id.fileLoadButton);
        final TextView filePathTextView = (AutoCompleteTextView) findViewById(R.id.filePathTextView);
        fileLoadButton.setOnClickListener(new FileLoadButtonListener(filePathTextView));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(org.blacklight.android.flexible_profiles.R.menu.main, menu);
	return true;
    }
    
}


package org.blacklight.android.flexibleprofiles.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class FileLoadButtonListener implements OnClickListener {
	private TextView filePathTextView;
	
	public FileLoadButtonListener(final TextView filePathTextView) {
		this.filePathTextView = filePathTextView;
	}
	
	public void onClick(View v) {
		try {
			final File scriptPath = new File(filePathTextView.getText().toString());
			final Reader fileReader = new InputStreamReader(new FileInputStream(scriptPath));
			System.out.println(scriptPath);
		} catch (Exception e) {
			System.err.println("ERROR");
		}
	}

}

package org.blacklight.android.flexibleprofiles.environment;

import java.io.File;

import com.google.common.eventbus.EventBus;

public class AppEnvironment implements Environment {
	private static Environment instance;
	private final static EventBus eventBus = new EventBus();

	public static Environment getInstance() {
		if (instance == null) {
			instance = new AppEnvironment();
		}
		
		return instance;
	}
	
	public String getConfigurationFilePath() {
		return android.os.Environment.getExternalStorageDirectory() + File.separator + "flexibleprofiles.xml";
	}

	public static EventBus getEventBus() {
		return eventBus;
	}

}

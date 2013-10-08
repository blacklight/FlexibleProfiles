package org.blacklight.android.flexibleprofiles.environment;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import org.blacklight.android.flexibleprofiles.status.PowerConnectedStatus;
import org.blacklight.android.flexibleprofiles.status.Status;
import org.blacklight.android.flexibleprofiles.status.SyncEnabledStatus;
import org.blacklight.android.flexibleprofiles.status.WiFiConnectedStatus;

import com.google.common.eventbus.EventBus;

public class AppEnvironment implements Environment {
	private final static Environment instance = new AppEnvironment();
	private final static EventBus eventBus = new EventBus();
	private final static Collection<Class<? extends Status>> statusClasses = new HashSet<Class<? extends Status>>();
	
	static {
		statusClasses.add(PowerConnectedStatus.class);
		statusClasses.add(SyncEnabledStatus.class);
		statusClasses.add(WiFiConnectedStatus.class);
	}

	public static Environment getInstance() {
		return instance;
	}
	
	public String getConfigurationFilePath() {
		return android.os.Environment.getExternalStorageDirectory() + File.separator + "flexibleprofiles.xml";
	}

	public static EventBus getEventBus() {
		return eventBus;
	}

	public static Collection<Class<? extends Status>> getStatusClasses() {
		return statusClasses;
	}
	
}

package org.blacklight.android.flexibleprofiles.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;

public class FlexibleProfilesService extends IntentService {
	private static FlexibleProfilesService instance;
	private Intent intent;
	
	public static FlexibleProfilesService getInstance() {
		return instance;
	}
	
	public Intent getIntent() {
		return intent;
	}
	
	public FlexibleProfilesService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		instance = this;
		this.intent = intent;
		return Service.START_STICKY;
	}
}

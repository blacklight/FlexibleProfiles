package org.blacklight.android.flexibleprofiles.status;

import org.blacklight.android.flexibleprofiles.service.FlexibleProfilesService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class WiFiConnectedStatus extends BooleanStatus {

	public WiFiConnectedStatus() {
		super();
	}
	
	@Override
	public void refreshValue() {
		ConnectivityManager connManager = (ConnectivityManager) FlexibleProfilesService.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		setValue(mWifi.isConnected());
	}
	
}

package org.blacklight.android.flexibleprofiles.profiles.settings;

import org.blacklight.android.flexibleprofiles.ui.FlexibleProfilesActivity;

import android.content.Context;
import android.net.wifi.WifiManager;

public class WiFiSetting implements Setting {
	private final boolean value;
	
	public WiFiSetting(boolean value) {
		this.value = value;
	}

	@Override
	public void apply() {
		try {
			WifiManager wifiManager = (WifiManager)
				FlexibleProfilesActivity.getInstance().getBaseContext().getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(value);
		} catch (Exception e) {
			// TODO Manage exception here
		}
	}

	@Override
	public Object getValue() {
		return value;
	}

}

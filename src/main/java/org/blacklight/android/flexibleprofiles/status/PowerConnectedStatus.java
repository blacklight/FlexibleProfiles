package org.blacklight.android.flexibleprofiles.status;

import org.blacklight.android.flexibleprofiles.service.FlexibleProfilesService;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;


public class PowerConnectedStatus extends BooleanStatus {

	public PowerConnectedStatus() {
		super();
	}
	
	@Override
	public void refreshValue() {
		Intent intent = FlexibleProfilesService.getInstance().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		setValue(intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) > 0);
	}

}

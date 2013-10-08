package org.blacklight.android.flexibleprofiles.status;

import org.blacklight.android.flexibleprofiles.service.FlexibleProfilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;


public class PowerConnectedStatus extends BooleanStatus {
	private static final Logger log = LoggerFactory.getLogger(PowerConnectedStatus.class);

	public PowerConnectedStatus() {
		super();
	}
	
	@Override
	public void refreshValue() {
		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = FlexibleProfilesService.getInstance().registerReceiver(null, ifilter);
		int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
				|| status == BatteryManager.BATTERY_STATUS_FULL;
		setValue(isCharging);
		log.info(getClass() + " - status: [" + isCharging + "]");
	}

}

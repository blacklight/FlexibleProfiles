package org.blacklight.android.flexibleprofiles.receivers;

import org.blacklight.android.flexibleprofiles.environment.AppEnvironment;
import org.blacklight.android.flexibleprofiles.rules.events.Event;
import org.blacklight.android.flexibleprofiles.rules.events.PowerConnectedEvent;
import org.blacklight.android.flexibleprofiles.service.FlexibleProfilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class PowerConnectedReceiver extends EventReceiver {
	private static final Logger log = LoggerFactory.getLogger(PowerConnectedReceiver.class);
	
	public PowerConnectedReceiver() {
		eventClass = PowerConnectedEvent.class;
	}

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		int status = FlexibleProfilesService.getInstance().getIntent().getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
				|| status == BatteryManager.BATTERY_STATUS_FULL;
		log.info(getClass() + " - Event received - Value [" + isCharging + "]");
		Event event = createEvent(isCharging);
		AppEnvironment.getEventBus().post(event);
	}

}

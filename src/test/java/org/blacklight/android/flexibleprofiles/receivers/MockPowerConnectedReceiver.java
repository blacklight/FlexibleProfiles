package org.blacklight.android.flexibleprofiles.receivers;

import org.blacklight.android.flexibleprofiles.environment.AppEnvironment;
import org.blacklight.android.flexibleprofiles.rules.events.Event;
import org.blacklight.android.flexibleprofiles.rules.events.PowerConnectedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.content.Intent;

public class MockPowerConnectedReceiver extends EventReceiver {
	private static final Logger log = LoggerFactory.getLogger(PowerConnectedReceiver.class);
	
	public MockPowerConnectedReceiver() {
		eventClass = PowerConnectedEvent.class;
	}

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		boolean isCharging = Math.random() < 0.5;
		log.info(getClass() + " - Power connected value [" + isCharging + "]");
		Event event = createEvent(isCharging);
		AppEnvironment.getEventBus().post(event);
	}

}

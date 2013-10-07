package org.blacklight.android.flexibleprofiles.rules.events;

import org.blacklight.android.flexibleprofiles.status.Status;
import org.blacklight.android.flexibleprofiles.status.WiFiConnectedStatus;

public class WiFiConnectedEvent extends BooleanEvent {
	public WiFiConnectedEvent(boolean value) {
		super(value);
	}

	@Override
	public Class<? extends Status> getStatusClass() {
		return WiFiConnectedStatus.class;
	}

}

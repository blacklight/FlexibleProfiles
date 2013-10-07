package org.blacklight.android.flexibleprofiles.rules.events;

import org.blacklight.android.flexibleprofiles.status.Status;
import org.blacklight.android.flexibleprofiles.status.WiFiConnectedStatus;

public class WiFiConnectedEvent extends AbstractEvent {
	private final boolean wifiConnected;
	
	public WiFiConnectedEvent(final boolean wifiConnected) {
		this.wifiConnected = wifiConnected;
	}

	@Override
	public Object getValue() {
		return wifiConnected;
	}

	@Override
	public Class<? extends Status> getStatusClass() {
		return WiFiConnectedStatus.class;
	}

}

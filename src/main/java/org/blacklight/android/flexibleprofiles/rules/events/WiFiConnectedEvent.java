package org.blacklight.android.flexibleprofiles.rules.events;

public class WiFiConnectedEvent extends AbstractEvent {
	private final boolean wifiConnected;
	
	public WiFiConnectedEvent(final boolean wifiConnected) {
		this.wifiConnected = wifiConnected;
	}

	@Override
	public Object getValue() {
		return wifiConnected;
	}

}

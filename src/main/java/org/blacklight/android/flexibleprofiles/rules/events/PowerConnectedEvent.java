package org.blacklight.android.flexibleprofiles.rules.events;

public class PowerConnectedEvent extends AbstractEvent {
	private final boolean powerConnected;

	public PowerConnectedEvent(final boolean powerConnected) {
		this.powerConnected = powerConnected;
	}

	@Override
	public Object getValue() {
		return powerConnected;
	}
}

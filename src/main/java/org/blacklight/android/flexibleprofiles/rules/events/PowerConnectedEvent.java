package org.blacklight.android.flexibleprofiles.rules.events;

import org.blacklight.android.flexibleprofiles.status.PowerConnectedStatus;
import org.blacklight.android.flexibleprofiles.status.Status;

public class PowerConnectedEvent extends AbstractEvent {
	private final boolean powerConnected;

	public PowerConnectedEvent(final boolean powerConnected) {
		this.powerConnected = powerConnected;
	}

	@Override
	public Object getValue() {
		return powerConnected;
	}

	@Override
	public Class<? extends Status> getStatusClass() {
		return PowerConnectedStatus.class;
	}
	
}

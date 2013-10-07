package org.blacklight.android.flexibleprofiles.rules.events;

import org.blacklight.android.flexibleprofiles.status.PowerConnectedStatus;
import org.blacklight.android.flexibleprofiles.status.Status;

public class PowerConnectedEvent extends BooleanEvent {

	public PowerConnectedEvent(boolean value) {
		super(value);
	}

	@Override
	public Class<? extends Status> getStatusClass() {
		return PowerConnectedStatus.class;
	}
	
}

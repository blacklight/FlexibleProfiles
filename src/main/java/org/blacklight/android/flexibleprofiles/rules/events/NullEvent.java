package org.blacklight.android.flexibleprofiles.rules.events;

import org.blacklight.android.flexibleprofiles.status.Status;

public class NullEvent extends AbstractEvent {

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public Class<? extends Status> getStatusClass() {
		return null;
	}

}

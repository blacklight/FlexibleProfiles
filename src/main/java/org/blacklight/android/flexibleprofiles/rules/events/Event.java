package org.blacklight.android.flexibleprofiles.rules.events;

import org.blacklight.android.flexibleprofiles.status.Status;

public interface Event {
	
	public Object getValue();

	public boolean equals(final Event event);
	
	public Class<? extends Status> getStatusClass();
	
	public void fire();
	
}

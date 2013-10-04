package org.blacklight.android.flexibleprofiles.rules.events;

public interface Event {
	
	public Object getValue();

	public boolean equals(final Event event);
	
}

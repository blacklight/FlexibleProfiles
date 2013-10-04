package org.blacklight.android.flexibleprofiles.rules.events;

public abstract class AbstractEvent implements Event {

	@Override
	public boolean equals(final Event event) {
		return getClass().equals(event.getClass()) && getValue().equals(event.getValue());
	}
	
}

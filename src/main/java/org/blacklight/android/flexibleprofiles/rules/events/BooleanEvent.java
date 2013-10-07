package org.blacklight.android.flexibleprofiles.rules.events;

public abstract class BooleanEvent extends AbstractEvent {
	protected final boolean value;
	
	public BooleanEvent(final boolean value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

}

package org.blacklight.android.flexibleprofiles.exceptions;

@SuppressWarnings("serial")
public class FlexibleProfileException extends Exception {
	public FlexibleProfileException() {
		super();
	}

	public FlexibleProfileException(final String message) {
		super(message);
	}
	
	public FlexibleProfileException(final Throwable t) {
		super(t);
	}
	
	public FlexibleProfileException(final String message, final Throwable t) {
		super(message, t);
	}
	
}

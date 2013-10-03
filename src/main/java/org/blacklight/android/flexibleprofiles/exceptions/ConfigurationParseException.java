package org.blacklight.android.flexibleprofiles.exceptions;

@SuppressWarnings("serial")
public class ConfigurationParseException extends FlexibleProfileException {
	public ConfigurationParseException() {
		super();
	}

	public ConfigurationParseException(final String message) {
		super(message);
	}
	
	public ConfigurationParseException(final Throwable t) {
		super(t);
	}
	
	public ConfigurationParseException(final String message, final Throwable t) {
		super(message, t);
	}
	
}

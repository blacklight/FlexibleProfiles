package org.blacklight.android.flexibleprofiles.rules.events;

import org.blacklight.android.flexibleprofiles.exceptions.ConfigurationParseException;


public abstract class EventFactory {

	public static Event createEvent(String clazz, final String value) throws ConfigurationParseException {
		Event event = new NullEvent();
		clazz = clazz.toLowerCase().trim();
	
		if (clazz.equals("wifi connected")) {
			try {
				event = new WiFiConnectedEvent(Boolean.parseBoolean(value));
			} catch (final Exception e) {
				throw new ConfigurationParseException(e);
			}
		}
		else if (clazz.equals("power connected")) {
			try {
				event = new PowerConnectedEvent(Boolean.parseBoolean(value));
			} catch (final Exception e) {
				throw new ConfigurationParseException(e);
			}
		}
		
		return event;
	}

}

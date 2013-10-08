package org.blacklight.android.flexibleprofiles.receivers;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.blacklight.android.flexibleprofiles.rules.events.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.BroadcastReceiver;

public abstract class EventReceiver extends BroadcastReceiver {
	private static final Logger log = LoggerFactory.getLogger(EventReceiver.class);
	protected Class<? extends Event> eventClass;

	protected Event createEvent(final Object value) {
		try {
			return eventClass.getConstructor(value.getClass()).newInstance(value);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}
	
	public Class<? extends Event> getEventClass() {
		return eventClass;
	}

}

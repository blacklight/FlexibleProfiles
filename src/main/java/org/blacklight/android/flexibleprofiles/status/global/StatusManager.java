package org.blacklight.android.flexibleprofiles.status.global;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.blacklight.android.flexibleprofiles.configuration.Configuration;
import org.blacklight.android.flexibleprofiles.environment.AppEnvironment;
import org.blacklight.android.flexibleprofiles.exceptions.FlexibleProfileException;
import org.blacklight.android.flexibleprofiles.rules.Rule;
import org.blacklight.android.flexibleprofiles.rules.events.Event;
import org.blacklight.android.flexibleprofiles.status.PowerConnectedStatus;
import org.blacklight.android.flexibleprofiles.status.Status;
import org.blacklight.android.flexibleprofiles.status.StatusFactory;
import org.blacklight.android.flexibleprofiles.status.SyncEnabledStatus;
import org.blacklight.android.flexibleprofiles.status.WiFiConnectedStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;

public class StatusManager {
	private final Map<Class<? extends Status>, Status> globalStatus;
	private final static Logger log = LoggerFactory.getLogger(StatusManager.class);
	private static StatusManager instance;

	public synchronized static StatusManager getInstance() {
		if (instance == null) {
			instance = new StatusManager();
		}
		
		return instance;
	}
	
	public StatusManager() {
		AppEnvironment.getEventBus().register(this);
		globalStatus = new HashMap<Class<? extends Status>, Status>();
		
		try {
			globalStatus.put(PowerConnectedStatus.class, StatusFactory.fromClass(PowerConnectedStatus.class));
			globalStatus.put(SyncEnabledStatus.class, StatusFactory.fromClass(SyncEnabledStatus.class));
			globalStatus.put(WiFiConnectedStatus.class, StatusFactory.fromClass(WiFiConnectedStatus.class));
		} catch (final Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			// TODO Graphical error reporting
		}
	}
	
	public boolean statusEquals(final Class<? extends Status> statusClass, final Object value) {
		// We assume that if the passed status class is unknown, then its status is always verified
		if (!globalStatus.containsKey(statusClass)) {
			return true;
		}
		
		return globalStatus.get(statusClass).equals(value);
	}
	
	@Subscribe
	public synchronized void eventFired(final Event event) throws FlexibleProfileException {
		final Class<? extends Status> statusClass = event.getStatusClass();
		final Status status = globalStatus.get(statusClass);
		
		if (status != null) {
			log.info("Event fired [" + event.getClass() + "] value [" + event.getValue() + "]");
			status.setValue(event.getValue());
			int maximumPriority = Integer.MIN_VALUE;
			Rule appliedRule = null;
			
			for (final Rule rule : Configuration.getInstance().getRules()) {
				if (rule.isSatisfied() && rule.getPriority() > maximumPriority) {
					appliedRule = new Rule(rule);
					maximumPriority = appliedRule.getPriority();
				}
			}
			
			if (appliedRule != null) {
				appliedRule.apply();
			}
		}
	}
	
}

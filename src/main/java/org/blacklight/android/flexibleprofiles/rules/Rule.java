package org.blacklight.android.flexibleprofiles.rules;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.blacklight.android.flexibleprofiles.profiles.Profile;
import org.blacklight.android.flexibleprofiles.rules.events.Event;
import org.blacklight.android.flexibleprofiles.status.global.StatusManager;

public class Rule {
	public final static int AUTOMATIC_PRIORITY = -1;
	private final String name;
	private final Map<Class<? extends Event>, Event> events;

	private final int priority;

	private Profile profile;
	
	public Rule(final Rule rule) {
		this.name = rule.name;
		this.priority = rule.priority;
		this.events = new HashMap<Class<? extends Event>, Event>(rule.events);
		this.profile = rule.profile;
	}
	
	public Rule(final String name, final Collection<Event> events, final Profile profile, final int priority) {
		this.name = name;
		this.priority = priority;
		this.events = new HashMap<Class<? extends Event>, Event>();
		
		for (final Event event : events) {
			this.events.put(event.getClass(), event);
		}
		
		if (profile != null) {
			setProfile(profile);
		}
	}

	void setProfile(final Profile profile) {
		this.profile = profile;
	}
	
	public boolean isSatisfied() {
		StatusManager globalStatus = StatusManager.getInstance();
		boolean result = true;
		
		for (final Event event : events.values()) {
			if (!globalStatus.statusEquals(event.getStatusClass(), event.getValue())) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	public void apply() {
		profile.apply();
	}
	
	public String getName() {
		return name;
	}
	
	public int getPriority() {
		return priority;
	}
	
}

package org.blacklight.android.flexibleprofiles.rules;

import java.util.List;

import org.blacklight.android.flexibleprofiles.profiles.Profile;
import org.blacklight.android.flexibleprofiles.rules.events.Event;
import org.blacklight.android.flexibleprofiles.status.global.GlobalStatus;

public class Rule {
	public final static int AUTOMATIC_PRIORITY = -1;
	private final String name;
	private final List<Event> events;

	private final int priority;

	private Profile profile;
	
	public Rule(final String name, final List<Event> events, final Profile profile, final int priority) {
		this.name = name;
		this.events = events;
		this.priority = priority;
		
		if (profile != null) {
			setProfile(profile);
		}
	}

	public void setProfile(final Profile profile) {
		this.profile = profile;
	}
	
	public boolean isSatisfied() {
		GlobalStatus globalStatus = GlobalStatus.getInstance();
		boolean result = true;
		
		synchronized(globalStatus) {
			for (final Event event : events) {
				if (!globalStatus.statusEquals(event.getStatusClass(), event.getValue())) {
					result = false;
					break;
				}
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
	
	public List<Event> getEvents() {
		return events;
	}
	
	public Profile getProfile() {
		return profile;
	}

}

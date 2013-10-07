package org.blacklight.android.flexibleprofiles.configuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.blacklight.android.flexibleprofiles.profiles.Profile;
import org.blacklight.android.flexibleprofiles.rules.Rule;

public class Configuration {
	private final Map<String, Profile> profiles;
	private final Map<String, Rule> rules;
	
	public Configuration(final List<Profile> profiles, final List<Rule> rules) {
		this.profiles = new HashMap<String, Profile>();
		for (Profile profile : profiles) {
			this.profiles.put(profile.getName(), profile);
		}
		
		this.rules = new HashMap<String, Rule>();
		for (Rule rule : rules) {
			this.rules.put(rule.getName(), rule);
		}
	}

	public Collection<Profile> getProfiles() {
		return profiles.values();
	}
	
	public Collection<Rule> getRules() {
		return rules.values();
	}

}

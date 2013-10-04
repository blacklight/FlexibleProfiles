package org.blacklight.android.flexibleprofiles.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.blacklight.android.flexibleprofiles.profiles.Profile;
import org.blacklight.android.flexibleprofiles.rules.Rule;

public class Configuration {
	private final Map<String, Profile> profiles;
	
	public Configuration(final List<Profile> profiles, final List<Rule> rules) {
		this.profiles = new HashMap<String, Profile>();
		for (Profile profile : profiles) {
			this.profiles.put(profile.getName(), profile);
		}
	}

}

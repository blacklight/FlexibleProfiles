package org.blacklight.android.flexibleprofiles.configuration;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
	private final Map<String, Profile> profiles;
	
	public Configuration(final Profile[] profiles) {
		this.profiles = new HashMap<String, Profile>();
		for (Profile profile : profiles) {
			this.profiles.put(profile.getName(), profile);
		}
	}

}

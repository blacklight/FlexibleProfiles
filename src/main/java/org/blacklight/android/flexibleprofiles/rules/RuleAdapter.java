package org.blacklight.android.flexibleprofiles.rules;

import org.blacklight.android.flexibleprofiles.profiles.Profile;

public abstract class RuleAdapter {
	public static void setProfile(final Rule rule, final Profile profile) {
		rule.setProfile(profile);
	}

}

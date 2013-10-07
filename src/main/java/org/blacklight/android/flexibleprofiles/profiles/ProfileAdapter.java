package org.blacklight.android.flexibleprofiles.profiles;

import java.util.Collection;

import org.blacklight.android.flexibleprofiles.profiles.settings.Setting;

public abstract class ProfileAdapter {
	public static Collection<Setting> getSettings(final Profile profile) {
		return profile.getSettings();
	}

	public static void applyExtension(final Profile profile, final Profile extendz) {
		profile.applyExtension(extendz);
	}
}

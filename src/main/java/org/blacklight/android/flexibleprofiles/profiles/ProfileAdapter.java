package org.blacklight.android.flexibleprofiles.profiles;

import java.util.List;

import org.blacklight.android.flexibleprofiles.profiles.settings.Setting;

public abstract class ProfileAdapter {
	public static List<Setting> getSettings(final Profile profile) {
		return profile.getSettings();
	}

	public static void applyExtension(final Profile profile, final Profile extendz) {
		profile.applyExtension(extendz);
	}
}

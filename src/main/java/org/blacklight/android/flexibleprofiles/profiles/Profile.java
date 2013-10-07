package org.blacklight.android.flexibleprofiles.profiles;

import java.util.Collection;

import org.blacklight.android.flexibleprofiles.profiles.settings.Setting;

public class Profile {
	private final String name;
	private final Collection<Setting> settings;
	
	public Profile(final String name, final Collection<Setting> settings) {
		this.name = name;
		this.settings = settings;
	}
	
	public Profile(final String name, final Profile extendz, final Collection<Setting> settings) {
		this.name = name;
		this.settings = settings;
		
		if (extendz != null) {
			applyExtension(extendz);
		}
	}

	void applyExtension(final Profile extendz) {
		for (Setting extendedSetting : extendz.settings) {
			boolean instanceFound = false;
			for (Setting setting : settings) {
				if (setting.getClass().equals(extendedSetting.getClass())) {
					instanceFound = true;
					break;
				}
			}

			if (!instanceFound) {
				settings.add(extendedSetting);
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
	Collection<Setting> getSettings() {
		return settings;
	}
	
	public void apply() {
		for (Setting setting : settings) {
			setting.apply();
		}
	}
	
}

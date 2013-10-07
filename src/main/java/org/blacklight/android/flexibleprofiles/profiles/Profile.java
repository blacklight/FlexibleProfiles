package org.blacklight.android.flexibleprofiles.profiles;

import java.util.List;

import org.blacklight.android.flexibleprofiles.profiles.settings.Setting;

public class Profile {
	private final String name;
	private final List<Setting> settings;
	
	public Profile(final String name, final Profile extendz, final List<Setting> settings2) {
		this.name = name;
		this.settings = settings2;
		
		if (extendz != null) {
			applyExtension(extendz);
		}
	}

	public void applyExtension(final Profile extendz) {
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
	
	public List<Setting> getSettings() {
		return settings;
	}
	
	public void apply() {
		for (Setting setting : settings) {
			setting.apply();
		}
	}
	
}

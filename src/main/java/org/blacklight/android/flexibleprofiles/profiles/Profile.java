package org.blacklight.android.flexibleprofiles.profiles;

import java.util.List;

import org.blacklight.android.flexibleprofiles.profiles.settings.Setting;

public class Profile {
	private final String name;
	private final List<Setting> settings;
	
	public Profile(final String name, final Profile extend, final List<Setting> settings2) {
		this.name = name;
		this.settings = settings2;
		
		if (extend != null) {
			for (Setting extendedSetting : extend.settings) {
				boolean instanceFound = false;
				for (Setting setting : settings2) {
					if (setting.getClass().equals(extendedSetting.getClass())) {
						instanceFound = true;
						break;
					}
				}
				
				if (!instanceFound) {
					settings2.add(extendedSetting);
				}
			}
		}
		
	}

	public String getName() {
		return name;
	}
	
	public void apply() {
		for (Setting setting : settings) {
			setting.apply();
		}
	}
	
}

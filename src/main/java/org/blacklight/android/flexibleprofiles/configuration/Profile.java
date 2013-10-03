package org.blacklight.android.flexibleprofiles.configuration;

import java.util.List;

import org.blacklight.android.flexibleprofiles.configuration.settings.Setting;

public class Profile {
	private final String name;
	private final List<Setting> settings;
	
	public Profile(final String name, final Profile extend, final List<Setting> settings) {
		this.name = name;
		this.settings = settings;
		
		if (extend != null) {
			for (Setting extendedSetting : extend.settings) {
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

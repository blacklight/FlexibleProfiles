package org.blacklight.android.flexibleprofiles.profiles.settings;

public class NullSetting implements Setting {
	@Override
	public void apply() {
		// Does nothing
	}

	@Override
	public Object getValue() {
		return null;
	}

}

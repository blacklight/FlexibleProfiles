package org.blacklight.android.flexibleprofiles.profiles.settings;

public class SyncSetting implements Setting {
	private final boolean value;
	
	public SyncSetting(boolean value) {
		this.value = value;
	}

	@Override
	public void apply() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getValue() {
		return value;
	}

}

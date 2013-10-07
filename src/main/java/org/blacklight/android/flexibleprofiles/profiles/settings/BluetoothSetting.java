package org.blacklight.android.flexibleprofiles.profiles.settings;

public class BluetoothSetting implements Setting {
	private final boolean value;
	
	public BluetoothSetting(boolean value) {
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

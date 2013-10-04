package org.blacklight.android.flexibleprofiles.profiles.settings;

public class MobileDataSetting implements Setting {
	private final boolean value;
	
	public MobileDataSetting(final boolean value) {
		// TODO Auto-generated constructor stub
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

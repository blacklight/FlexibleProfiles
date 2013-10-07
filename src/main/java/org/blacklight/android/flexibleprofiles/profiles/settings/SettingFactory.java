package org.blacklight.android.flexibleprofiles.profiles.settings;

import org.blacklight.android.flexibleprofiles.exceptions.ConfigurationParseException;

public abstract class SettingFactory {

	public static Setting createSetting(String clazz, String value) throws ConfigurationParseException {
		Setting setting = new NullSetting();
		clazz = clazz.trim().toLowerCase();
		
		if (clazz.equals("wifi")) {
			try {
				setting = new WiFiSetting(Boolean.parseBoolean(value));
			} catch (final Exception e) {
				throw new ConfigurationParseException(e);
			}
		}
		else if (clazz.equals("mobile data")) {
			try {
				setting = new MobileDataSetting(Boolean.parseBoolean(value));
			} catch (final Exception e) {
				throw new ConfigurationParseException(e);
			}
		}
		else if (clazz.equals("bluetooth")) {
			try {
				setting = new BluetoothSetting(Boolean.parseBoolean(value));
			} catch (final Exception e) {
				throw new ConfigurationParseException(e);
			}
		}
		else if (clazz.equals("sync")) {
			try {
				setting = new SyncSetting(Boolean.parseBoolean(value));
			} catch (final Exception e) {
				throw new ConfigurationParseException(e);
			}
		}
		
		return setting;
	}

}

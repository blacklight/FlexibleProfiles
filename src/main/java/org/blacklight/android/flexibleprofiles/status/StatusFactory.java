package org.blacklight.android.flexibleprofiles.status;


public abstract class StatusFactory {
	public static Status fromClass(final Class<? extends Status> clazz) {
		if (clazz.equals(WiFiConnectedStatus.class)) {
			return new WiFiConnectedStatus();
		}
		else if (clazz.equals(PowerConnectedStatus.class)) {
			return new PowerConnectedStatus();
		}
		else if (clazz.equals(SyncEnabledStatus.class)) {
			return new SyncEnabledStatus();
		}
		
		return null;
	}
}

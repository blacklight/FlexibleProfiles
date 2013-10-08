package org.blacklight.android.flexibleprofiles.status;

import java.lang.reflect.InvocationTargetException;


public abstract class StatusFactory {
	public static Status fromClass(final Class<? extends Status> clazz)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return clazz.getConstructor().newInstance();
	}
	
}

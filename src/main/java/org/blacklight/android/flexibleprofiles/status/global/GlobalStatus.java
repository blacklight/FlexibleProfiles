package org.blacklight.android.flexibleprofiles.status.global;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.blacklight.android.flexibleprofiles.status.Status;
import org.blacklight.android.flexibleprofiles.status.StatusFactory;
import org.reflections.Reflections;

public class GlobalStatus {
	private final Map<Class<? extends Status>, Object> globalStatus;
	private static GlobalStatus instance;

	public synchronized static GlobalStatus getInstance() {
		if (instance == null) {
			instance = new GlobalStatus();
		}
		
		return instance;
	}
	
	public GlobalStatus() {
		globalStatus = new HashMap<Class<? extends Status>, Object>();
		Reflections reflections = new Reflections(Status.class.getPackage().getName());
		Set<Class<? extends Status>> statusClasses = reflections.getSubTypesOf(Status.class);
		
		for (final Class<? extends Status> statusClass : statusClasses) {
			Status status = StatusFactory.fromClass(statusClass);
			if (status != null) {
				globalStatus.put(statusClass, status);
			}
		}
	}
	
	public boolean statusEquals(final Class<? extends Status> statusClass, final Object value) {
		// We assume that if the passed status class is unknown, then its status is always verified
		if (!globalStatus.containsKey(statusClass)) {
			return true;
		}
		
		return globalStatus.get(statusClass).equals(value);
	}
}

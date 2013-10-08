package org.blacklight.android.flexibleprofiles.status;


public abstract class BooleanStatus implements Status {
	private boolean status;
	
	public BooleanStatus() {
		refreshValue();
	}

	@Override
	public Object getValue() {
		return status;
	}

	@Override
	public void setValue(Object value) {
		try {
			status = (Boolean) value;
		} catch (final Exception e) {
			throw new RuntimeException("Invalid value [" + value + " for class [" + getClass() + "]");
		}
	}

}

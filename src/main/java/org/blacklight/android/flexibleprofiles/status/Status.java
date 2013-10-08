package org.blacklight.android.flexibleprofiles.status;

public interface Status {
	
	public Object getValue();

	public void setValue(final Object value);
	
	public void refreshValue();
	
}

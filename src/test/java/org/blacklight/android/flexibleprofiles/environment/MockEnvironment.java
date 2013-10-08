package org.blacklight.android.flexibleprofiles.environment;


public class MockEnvironment implements Environment {
	private static Environment instance;

	public static Environment getInstance() {
		if (instance == null) {
			instance = new MockEnvironment();
		}
		
		return instance;
	}
	
	public String getConfigurationFilePath() {
		return "src/test/resources/profiles_script.xml";
	}

}

package org.blacklight.android.flexibleprofiles.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.blacklight.android.flexibleprofiles.exceptions.FlexibleProfileException;
import org.junit.Test;

// Add bin/classes to your run configuration classpath to run this!!

public class TestConfiguration extends TestCase {
	@Test
	public void testValidConfiguration() throws IOException, FlexibleProfileException {
		File configurationFile = new File("src/test/resources/sample_configuration.xml");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(configurationFile)));
		ConfigurationFactory.fromReader(reader);
	}

}

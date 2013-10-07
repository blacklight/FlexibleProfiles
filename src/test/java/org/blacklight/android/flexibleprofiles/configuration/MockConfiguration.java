package org.blacklight.android.flexibleprofiles.configuration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Collection;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.blacklight.android.flexibleprofiles.environment.Environment;
import org.blacklight.android.flexibleprofiles.exceptions.FlexibleProfileException;
import org.blacklight.android.flexibleprofiles.profiles.Profile;
import org.blacklight.android.flexibleprofiles.rules.Rule;

public class MockConfiguration extends Configuration {
	
	public static Configuration getInstance() throws FlexibleProfileException {
		if (instance == null) {
			try {
				Environment environment = mock(Environment.class);
				when(environment.getConfigurationFilePath()).thenReturn("src/test/resources/profiles_script.xml");
				
				instance = ConfigurationFactory.fromReader(
					new BufferedReader(
						new InputStreamReader(
							new FileInputStream(
								new File(environment.getConfigurationFilePath())))));
			} catch (final FileNotFoundException e) {
				log.error(ExceptionUtils.getStackTrace(e));
				throw new FlexibleProfileException(e);
			}
		}
		
		return instance;
	}
	
	public MockConfiguration(final Collection<Profile> profiles, final Collection<Rule> rules) {
		super(profiles, rules);
	}

}

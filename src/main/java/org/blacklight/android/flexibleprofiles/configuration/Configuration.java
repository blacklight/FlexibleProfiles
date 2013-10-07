package org.blacklight.android.flexibleprofiles.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.blacklight.android.flexibleprofiles.environment.AppEnvironment;
import org.blacklight.android.flexibleprofiles.exceptions.FlexibleProfileException;
import org.blacklight.android.flexibleprofiles.profiles.Profile;
import org.blacklight.android.flexibleprofiles.rules.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configuration {
	private final Map<String, Profile> profiles;
	private final Map<String, Rule> rules;
	protected static Configuration instance;
	protected static final Logger log = LoggerFactory.getLogger(Configuration.class);
	
	public static Configuration getInstance() throws FlexibleProfileException {
		if (instance == null) {
			try {
				instance = ConfigurationFactory.fromReader(
					new BufferedReader(
						new InputStreamReader(
							new FileInputStream(
								new File(AppEnvironment.getInstance().getConfigurationFilePath())))));
			} catch (final FileNotFoundException e) {
				log.error(ExceptionUtils.getStackTrace(e));
				throw new FlexibleProfileException(e);
			}
		}
		
		return instance;
	}
	
	public Configuration(final Collection<Profile> profiles, final Collection<Rule> rules) {
		this.profiles = new HashMap<String, Profile>();
		for (Profile profile : profiles) {
			this.profiles.put(profile.getName(), profile);
		}
		
		this.rules = new HashMap<String, Rule>();
		for (Rule rule : rules) {
			this.rules.put(rule.getName(), rule);
		}
	}

	public Collection<Profile> getProfiles() {
		return profiles.values();
	}
	
	public Collection<Rule> getRules() {
		return rules.values();
	}

}

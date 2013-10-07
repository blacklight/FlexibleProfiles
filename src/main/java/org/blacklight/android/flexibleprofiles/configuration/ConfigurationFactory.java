package org.blacklight.android.flexibleprofiles.configuration;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.blacklight.android.flexibleprofiles.exceptions.ConfigurationParseException;
import org.blacklight.android.flexibleprofiles.exceptions.FlexibleProfileException;
import org.blacklight.android.flexibleprofiles.profiles.Profile;
import org.blacklight.android.flexibleprofiles.profiles.ProfileAdapter;
import org.blacklight.android.flexibleprofiles.profiles.settings.Setting;
import org.blacklight.android.flexibleprofiles.profiles.settings.SettingFactory;
import org.blacklight.android.flexibleprofiles.rules.Rule;
import org.blacklight.android.flexibleprofiles.rules.RuleAdapter;
import org.blacklight.android.flexibleprofiles.rules.events.Event;
import org.blacklight.android.flexibleprofiles.rules.events.EventFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ConfigurationFactory {
	private static final Logger log = LoggerFactory.getLogger(ConfigurationFactory.class);
	
	private static class ConfigurationParseContext {
		private final Map<String, Profile> profiles;
		private final Map<String, String> extendz;
		private final Map<Rule, String> rulesProfiles;
		
		public ConfigurationParseContext() {
			profiles = new HashMap<String, Profile>();
			extendz = new HashMap<String, String>();
			rulesProfiles = new HashMap<Rule, String>();
		}
	}

	public static Configuration fromReader(final Reader inputReader) throws FlexibleProfileException {
		try {
			log.debug("Parsing configuration XML from " + inputReader.getClass());
			Document document = (Document) new SAXBuilder().build(inputReader);
			Element rootElement = document.getRootElement();
			if (rootElement == null || !rootElement.getName().toLowerCase().equals("flexible")) {
				throw new ConfigurationParseException("There must be one and only one root tag <flexible> in the provided configuration");
			}
			
			Element profilesNode = rootElement.getChild("profiles");
			Element rulesNode = rootElement.getChild("rules");
			ConfigurationParseContext context = new ConfigurationParseContext();
			
			log.debug("Parsing profiles");
			List<Profile> profiles = parseProfiles(profilesNode, context);
			
			log.debug("Parsing rules");
			List<Rule> rules = parseRules(rulesNode, context);
			
			log.debug("Binding rules to profiles");
			postProcess(context);
			
			return new Configuration(profiles, rules);
		} catch (JDOMException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new FlexibleProfileException(e);
		} catch (IOException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new FlexibleProfileException(e);
		}
	}

	private static void postProcess(final ConfigurationParseContext context) throws ConfigurationParseException {
		for (final String profileName : context.extendz.keySet()) {
			String extendsName = context.extendz.get(profileName);
			Profile profile = context.profiles.get(profileName);
			Profile extendz = context.profiles.get(extendsName);
			if (extendz == null) {
				final String msg = "Profile name [" + extendsName + "] not found";
				log.error(msg);
				throw new ConfigurationParseException(msg);
			}
			
			ProfileAdapter.applyExtension(profile, extendz);
		}
		
		for (final Rule rule : context.rulesProfiles.keySet()) {
			String profileName = context.rulesProfiles.get(rule);
			Profile profile = context.profiles.get(profileName);
			if (profile == null) {
				final String msg = "Profile name [" + profileName + "] not found";
				log.error(msg);
				throw new ConfigurationParseException(msg);
			}
			
			RuleAdapter.setProfile(rule, profile);
		}
	}

	private static List<Rule> parseRules(final Element rulesNode, final ConfigurationParseContext context) throws ConfigurationParseException {
		@SuppressWarnings("unchecked")
		List<Element> ruleElements = rulesNode.getChildren("rule");
		if (ruleElements.size() == 0) {
			throw new ConfigurationParseException("No rules specified");
		}
		List<Rule> rules = new ArrayList<Rule>();
			
		for (Element ruleElement : ruleElements) {
			String name = ruleElement.getAttributeValue("name");
			if (name != null) {
				name = name.trim();
			}
			if (name == null || name.equals("")) {
				throw new ConfigurationParseException("No [name] attribute associated to the rule");
			}
			
			String priorityStr = ruleElement.getAttributeValue("priority");
			int priority = Rule.AUTOMATIC_PRIORITY;
			if (priorityStr != null) {
				try {
					priority = Integer.parseInt(priorityStr.trim());
				} catch (final Exception e) {
					throw new ConfigurationParseException("Invalid priority value [" + priorityStr + "] - it should be a number");
				}
			}
			
			final Element whenElement = ruleElement.getChild("when");
			if (whenElement == null) {
				throw new ConfigurationParseException("The rule [" + name + "] has no events associated");
			}
			List<Event> events = parseEvents(whenElement);
			
			final Element thenElement = ruleElement.getChild("then");
			if (thenElement == null) {
				throw new ConfigurationParseException("The rule [" + name + "] has no profile associated");
			}
			
			final Element applyElement = thenElement.getChild("apply");
			if (applyElement == null) {
				throw new ConfigurationParseException("The rule [" + name + "] has no profile associated");
			}
			String profileName = parseApply(applyElement);
			
			Rule rule = new Rule(name, events, null, priority);
			rules.add(rule);
			context.rulesProfiles.put(rule, profileName);
		}
		
		return rules;
	}

	private static String parseApply(final Element applyElement) throws ConfigurationParseException {
		String profile = applyElement.getAttributeValue("profile");
		if (profile != null) {
			profile = profile.trim();
		}
		if (profile == null || profile.equals("")) {
			throw new ConfigurationParseException("No [profile] attribute associated to the <apply> tag");
		}

		return profile;
	}

	private static List<Event> parseEvents(final Element whenElement) throws ConfigurationParseException {
		@SuppressWarnings("unchecked")
		List<Element> eventElements = whenElement.getChildren("event");
		if (eventElements.size() == 0) {
			throw new ConfigurationParseException("No events specified");
		}
		List<Event> events = new ArrayList<Event>();
		
		for (final Element eventElement : eventElements) {
			String clazz = eventElement.getAttributeValue("class");
			if (clazz != null) {
				clazz = clazz.trim();
			}
			if (clazz == null || clazz.equals("")) {
				throw new ConfigurationParseException("No [class] attribute associated to the event");
			}
			
			String value = eventElement.getAttributeValue("value");
			if (value != null) {
				value = value.trim();
			}
			if (value == null || value.equals("")) {
				throw new ConfigurationParseException("No [value] attribute associated to the event");
			}
			
			Event event = EventFactory.createEvent(clazz, value);
			events.add(event);
		}
		
		return events;
	}

	private static List<Profile> parseProfiles(final Element profilesNode, final ConfigurationParseContext context) throws ConfigurationParseException {
		@SuppressWarnings("unchecked")
		List<Element> profileElements = profilesNode.getChildren("profile");
		if (profileElements.size() == 0) {
			throw new ConfigurationParseException("No profiles specified");
		}
		List<Profile> profiles = new ArrayList<Profile>();
		
		for (Element profileElement : profileElements) {
			String name = profileElement.getAttributeValue("name");
			if (name != null) {
				name = name.trim();
			}
			if (name == null || name.equals("")) {
				throw new ConfigurationParseException("No [name] attribute associated to the profile");
			}
			
			String extendz = profileElement.getAttributeValue("extends");
			if (extendz != null) {
				extendz = extendz.trim();
				context.extendz.put(name, extendz);
			}
			
			@SuppressWarnings("unchecked")
			List<Element> settingElements = profileElement.getChildren("setting");
			List<Setting> settings = parseSettings(settingElements);
			Profile profile = new Profile(name, null, settings);
			context.profiles.put(name, profile);
			profiles.add(profile);
		}

		return profiles;
	}

	private static List<Setting> parseSettings(final List<Element> settingElements) throws ConfigurationParseException {
		List<Setting> settings = new ArrayList<Setting>();
		
		for (Element settingElement : settingElements) {
			String clazz = settingElement.getAttributeValue("class");
			if (clazz != null) {
				clazz = clazz.trim();
			}
			if (clazz == null || clazz.equals("")) {
				throw new ConfigurationParseException("No [class] attribute associated to the setting");
			}
			
			String value = settingElement.getAttributeValue("value");
			if (value != null) {
				value = value.trim();
			}
			if (value == null || value.equals("")) {
				throw new ConfigurationParseException("No [class] attribute associated to the setting");
			}
			
			Setting setting = SettingFactory.createSetting(clazz, value);
			settings.add(setting);
		}
		
		return settings;
	}
	
}

package org.blacklight.android.flexibleprofiles.configuration;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.blacklight.android.flexibleprofiles.exceptions.ConfigurationParseException;
import org.blacklight.android.flexibleprofiles.exceptions.FlexibleProfileException;
import org.blacklight.android.flexibleprofiles.profiles.Profile;
import org.blacklight.android.flexibleprofiles.profiles.settings.Setting;
import org.blacklight.android.flexibleprofiles.profiles.settings.SettingFactory;
import org.blacklight.android.flexibleprofiles.rules.Rule;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public abstract class ConfigurationFactory {
	public static Configuration fromReader(final Reader inputReader) throws FlexibleProfileException {
		try {
			Document document = (Document) new SAXBuilder().build(inputReader);
			Element rootElement = document.getRootElement();
			if (rootElement == null || !rootElement.getName().toLowerCase().equals("flexible")) {
				throw new ConfigurationParseException("There must be one and only one root tag <flexible> in the provided configuration");
			}
			
			Element profilesNode = rootElement.getChild("profiles");
			Element rulesNode = rootElement.getChild("rules");
			
			List<Profile> profiles = parseProfiles(profilesNode);
			List<Rule> rules = parseRules(rulesNode);
			postProcessLogic();
			
			return new Configuration(profiles, rules);
		} catch (JDOMException e) {
			throw new FlexibleProfileException(e);
		} catch (IOException e) {
			throw new FlexibleProfileException(e);
		}
	}

	private static void postProcessLogic() {
		// TODO Auto-generated method stub
		
	}

	private static List<Rule> parseRules(Element rulesNode) throws ConfigurationParseException {
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
					priority = Integer.parseInt(name.trim());
				} catch (final Exception e) {
					throw new ConfigurationParseException("Invalid priority value [" + priorityStr + "] - it should be a number");
				}
			}
		}
		
		return rules;
	}

	private static List<Profile> parseProfiles(Element profilesNode) throws ConfigurationParseException {
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
			}
			
			@SuppressWarnings("unchecked")
			List<Element> settingElements = profileElement.getChildren("setting");
			List<Setting> settings = parseSettings(settingElements);
			Profile profile = new Profile(name, null, settings);
			profiles.add(profile);
		}

		return profiles;
	}

	private static List<Setting> parseSettings(List<Element> settingElements) throws ConfigurationParseException {
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

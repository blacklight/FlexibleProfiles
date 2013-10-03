package org.blacklight.android.flexibleprofiles.configuration;

import android.annotation.SuppressLint;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.blacklight.android.flexibleprofiles.exceptions.ConfigurationParseException;
import org.blacklight.android.flexibleprofiles.exceptions.FlexibleProfileException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ConfigurationFactory {
	@SuppressLint("DefaultLocale")
	public static Configuration fromReader(final Reader inputReader) throws FlexibleProfileException {
		try {
			Document document = (Document) new SAXBuilder().build(inputReader);
			Element rootElement = document.getRootElement();
			if (rootElement == null || !rootElement.getName().toLowerCase().equals("flexible")) {
				throw new ConfigurationParseException("There must be one and only one root tag <flexible> in the provided configuration");
			}
			
			Element profilesNode = rootElement.getChild("profiles");
			Element rulesNode = rootElement.getChild("rules");
			
			@SuppressWarnings("unchecked")
			List<Element> profiles = profilesNode.getChildren("profile");
			if (profiles.size() == 0) {
				throw new ConfigurationParseException("No profiles specified");
			}
			
			@SuppressWarnings("unchecked")
			List<Element> rules = rulesNode.getChildren("rule");
			if (rules.size() == 0) {
				throw new ConfigurationParseException("No rules specified");
			}
			
			for (int i=0; i < profiles.size(); i++) {
				Element profile = profiles.get(i);
				String name = profile.getAttributeValue("name").trim();
				if (name == null || name.equals("")) {
					throw new ConfigurationParseException("Profile [" + i + "] has no name");
				}
				
				@SuppressWarnings("unchecked")
				List<Element> settings = profile.getChildren("setting");
				if (settings.size() == 0) {
					throw new ConfigurationParseException("No settings specified for profile [" + name + "]");
				}
			}
		} catch (JDOMException e) {
			throw new FlexibleProfileException(e);
		} catch (IOException e) {
			throw new FlexibleProfileException(e);
		}
		
		return null;
	}
}

package org.blacklight.android.flexibleprofiles.tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

import org.blacklight.android.flexibleprofiles.configuration.Configuration;
import org.blacklight.android.flexibleprofiles.configuration.ConfigurationFactory;
import org.blacklight.android.flexibleprofiles.exceptions.FlexibleProfileException;
import org.blacklight.android.flexibleprofiles.profiles.Profile;
import org.blacklight.android.flexibleprofiles.profiles.settings.BluetoothSetting;
import org.blacklight.android.flexibleprofiles.profiles.settings.MobileDataSetting;
import org.blacklight.android.flexibleprofiles.profiles.settings.Setting;
import org.blacklight.android.flexibleprofiles.profiles.settings.SyncSetting;
import org.blacklight.android.flexibleprofiles.profiles.settings.WiFiSetting;
import org.blacklight.android.flexibleprofiles.rules.Rule;
import org.junit.Test;

public class TestConfiguration {
	@Test
	public void testConfigurationParse() throws FileNotFoundException, FlexibleProfileException {
		Configuration configuration = ConfigurationFactory.fromReader(
			new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/profiles_script.xml")))));
		
		Collection<Profile> profiles = configuration.getProfiles();
		assertEquals(4, profiles.size());
		boolean hasHomeProfile = true;
		boolean hasChargingProfile = true;
		boolean hasOutdoorProfile = true;
		boolean hasOfficeProfile = true;
		
		for (final Profile profile : profiles) { 
			final String profileName = profile.getName();
			final List<Setting> settings = profile.getSettings();
			
			if (profileName.toLowerCase().equals("home")) {
				assertEquals(2, settings.size());
				boolean hasWiFiSetting = false;
				boolean hasMobileDataSetting = false;
				hasHomeProfile = true;
				
				for (final Setting setting : settings) {
					if (setting instanceof WiFiSetting) {
						assertEquals(true, setting.getValue());
						hasWiFiSetting = true;
					}
					else if (setting instanceof MobileDataSetting) {
						assertEquals(false, setting.getValue());
						hasMobileDataSetting = true;
					}
				}
				assertTrue(hasWiFiSetting && hasMobileDataSetting);
			}
			
			else if (profileName.toLowerCase().equals("charging")) {
				assertEquals(3, settings.size());
				boolean hasWiFiSetting = false;
				boolean hasMobileDataSetting = false;
				boolean hasBluetoothSetting = false;
				hasChargingProfile = true;
				
				for (final Setting setting : settings) {
					if (setting instanceof WiFiSetting) {
						assertEquals(true, setting.getValue());
						hasWiFiSetting = true;
					}
					else if (setting instanceof MobileDataSetting) {
						assertEquals(false, setting.getValue());
						hasMobileDataSetting = true;
					}
					else if (setting instanceof BluetoothSetting) {
						assertEquals(true, setting.getValue());
						hasBluetoothSetting = true;
					}
				}
				assertTrue(hasWiFiSetting && hasMobileDataSetting && hasBluetoothSetting);
			}
			
			else if (profileName.toLowerCase().equals("outdoor")) {
				assertEquals(3, settings.size());
				boolean hasWiFiSetting = false;
				boolean hasMobileDataSetting = false;
				boolean hasSyncSetting = false;
				hasOutdoorProfile = true;
				
				for (final Setting setting : settings) {
					if (setting instanceof WiFiSetting) {
						assertEquals(false, setting.getValue());
						hasWiFiSetting = true;
					}
					else if (setting instanceof MobileDataSetting) {
						assertEquals(true, setting.getValue());
						hasMobileDataSetting = true;
					}
					else if (setting instanceof SyncSetting) {
						assertEquals(false, setting.getValue());
						hasSyncSetting = true;
					}
				}
				assertTrue(hasWiFiSetting && hasMobileDataSetting && hasSyncSetting);
			}
			
			else if (profileName.toLowerCase().equals("office")) {
				assertEquals(4, settings.size());
				boolean hasWiFiSetting = false;
				boolean hasMobileDataSetting = false;
				boolean hasSyncSetting = false;
				boolean hasBluetoothSetting = false;
				hasOfficeProfile = true;
				
				for (final Setting setting : settings) {
					if (setting instanceof WiFiSetting) {
						assertEquals(false, setting.getValue());
						hasWiFiSetting = true;
					}
					else if (setting instanceof MobileDataSetting) {
						assertEquals(false, setting.getValue());
						hasMobileDataSetting = true;
					}
					else if (setting instanceof SyncSetting) {
						assertEquals(false, setting.getValue());
						hasSyncSetting = true;
					}
					else if (setting instanceof BluetoothSetting) {
						assertEquals(false, setting.getValue());
						hasBluetoothSetting = true;
					}
				}
				assertTrue(hasWiFiSetting && hasMobileDataSetting && hasSyncSetting && hasBluetoothSetting);
			}
		}
		assertTrue(hasHomeProfile && hasChargingProfile && hasOfficeProfile && hasOutdoorProfile);
		
		Collection<Rule> rules = configuration.getRules();
		for (final Rule rule : rules) {
			System.out.println("================");
			System.out.println(rule.getName());
			System.out.println(rule.getPriority());
			System.out.println(rule.getEvents());
		}
	}

}

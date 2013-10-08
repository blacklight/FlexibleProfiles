package org.blacklight.android.flexibleprofiles.status.global;

import org.blacklight.android.flexibleprofiles.status.global.StatusManager;
import org.junit.Test;

public class TestGlobalStatus {
	@Test
	public void testGlobalStatus() {
		StatusManager globalStatus = StatusManager.getInstance();
		System.out.println(globalStatus);
	}

}

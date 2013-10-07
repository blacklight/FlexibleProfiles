package org.blacklight.android.flexibleprofiles.status.global;

import org.blacklight.android.flexibleprofiles.status.global.GlobalStatus;
import org.junit.Test;

public class TestGlobalStatus {
	@Test
	public void testGlobalStatus() {
		GlobalStatus globalStatus = GlobalStatus.getInstance();
		System.out.println(globalStatus);
	}

}

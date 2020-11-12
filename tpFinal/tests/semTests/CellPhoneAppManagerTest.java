package semTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;
import sem.CellPhoneAppManager;
import sem.CellPhoneApp;
import sem.SEM;

class CellPhoneAppManagerTest {

	private SEM server = mock(SEM.class);

	private CellPhoneAppManager sut = new CellPhoneAppManager(server);

	@BeforeEach
	public void setUp() {
		sut.registerApp(123456, "AA342FF");
	}

	@Test
	void testGetCellPhoneApp() {
		assertEquals(123456, sut.getCellPhoneApp(123456).getPhoneNumber());
		assertEquals("AA342FF", sut.getCellPhoneApp(123456).getDefaultLicensePlate());
	}

	@Test
	void testLoadBalance() {

		sut.loadBalance(123456, 70F);
		assertEquals(70F, sut.getCellPhoneApp(123456).checkBalance());
	}

	@Test
	void testRegisterApp() {

		sut.registerApp(123454, "AA234DD");
		assertEquals(sut.appsSize(), 2);
	}

	@Test
	void testRemoveApp() {
		CellPhoneApp appToRemove = sut.getCellPhoneApp(123456);
		sut.removeApp(appToRemove);
		assertEquals(sut.appsSize(), 0);
	}

	@Test
	void testAppsSize() {
		assertEquals(sut.appsSize(), 1);
	}
}

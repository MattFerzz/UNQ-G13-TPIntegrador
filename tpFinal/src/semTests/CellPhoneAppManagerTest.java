package semTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import sem.CellPhoneAppManager;
import sem.CellPhoneApp;
import sem.SEM;

class CellPhoneAppManagerTest {

	private SEM server       = mock(SEM.class);
	private CellPhoneApp app = mock(CellPhoneApp.class);

	private CellPhoneAppManager sut = new CellPhoneAppManager(server);
	
	@BeforeEach
	public void setUp() {
		sut.addApp(app);
	}
	
	@Test
	void testGetCellPhoneNumber() {
		when(app.getPhoneNumber()).thenReturn(123456);
		System.out.println(app);
		System.out.println(app.getPhoneNumber());
		System.out.println(sut.getCellPhoneApp(123456));
		assertEquals(sut.getCellPhoneApp(123456), app);
		assertNull(sut.getCellPhoneApp(234567));
	}

	@Test
	void testLoadBalance() {
		when(app.getPhoneNumber()).thenReturn(123456);
		sut.loadBalance(123456, 70F);
		verify(app).loadBalance(70F);
	}
	
	@Test
	void testAddApp() {
		CellPhoneApp app2 = mock(CellPhoneApp.class);
		sut.addApp(app2);
		assertEquals(sut.appsSize(), 2);
	}
	
	@Test
	void testRemoveApp() {
		sut.removeApp(app);
		assertEquals(sut.appsSize(), 0);
	}
	
	@Test
	void testAppsSize() {
		assertEquals(sut.appsSize(), 1);
	}
}

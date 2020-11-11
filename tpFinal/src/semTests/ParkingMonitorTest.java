package semTests;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sem.Observer;
import sem.ParkingMonitor;

public class ParkingMonitorTest {

	ParkingMonitor sut = new ParkingMonitor();
	
	Observer o = mock(Observer.class);
	
	@BeforeEach
	public void setUp() {
		sut.attach(o);		
	}
	
	@Test
	public void testAttatch() {
		assertEquals(sut.getObservers().size(), 1);
	}

	@Test
	public void testDetach() {
		sut.detach(o);
		assertEquals(sut.getObservers().size(), 0);
	}

	@Test
	public void testNotify() {
		sut.notify();
		verify(o).update();
	}
	
}

package semTests;
import org.junit.jupiter.api.Test;

import sem.Observer;
import sem.ParkingMonitor;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

public class ParkingMonitorTest {

	ParkingMonitor sut = new ParkingMonitor();
	
	Observer o = mock(Observer.class);
	
	@BeforeEach
	public void setUp() {
		sut.Attach(o);		
	}
	
	@Test
	public void testAttatch() {
		assertEquals(sut.getObservers().size(), 1);
	}

	@Test
	public void testDetach() {
		sut.Detach(o);
		assertEquals(sut.getObservers().size(), 0);
	}

	@Test
	public void testNotify() {
		sut.Notify();
		verify(o).Update();
	}
	
}

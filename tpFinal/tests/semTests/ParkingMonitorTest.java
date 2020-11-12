package semTests;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import sem.Observer;
import sem.Parking;
import sem.ParkingMonitor;

public class ParkingMonitorTest {

	ParkingMonitor sut = new ParkingMonitor();
	
	Observer observer = mock(Observer.class);
	
	@BeforeEach
	public void setUp() {
		sut.attach(observer);		
	}
	
	@Test
	public void testAttatch() {
		assertEquals(sut.getObservers().size(), 1);
	}

	@Test
	public void testDetach() {
		sut.detach(observer);
		assertEquals(sut.getObservers().size(), 0);
	}

	@Test
	public void testNotify() {
		Parking mockParking = Mockito.mock(Parking.class);
		sut.notify(mockParking);
		verify(observer).update(mockParking);
	}
	
}

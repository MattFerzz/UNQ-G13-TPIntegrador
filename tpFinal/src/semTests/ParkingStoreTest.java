package semTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import sem.Coordinate;
import sem.MeasuredParkingZone;
import sem.ParkingStore;
import sem.SEM;

class ParkingStoreTest {
	private MeasuredParkingZone mockParkingZone;
	private Coordinate mockCoordinate;
	private ParkingStore parkingStore;
	private SEM mockSystem;

	@BeforeEach
	void setUp() {
		mockCoordinate = Mockito.mock(Coordinate.class);
		mockParkingZone = Mockito.mock(MeasuredParkingZone.class);
		mockSystem = Mockito.mock(SEM.class);
		parkingStore = new ParkingStore(mockCoordinate, mockParkingZone, mockSystem);
	}

	@Test
	void testSellParkingCard() {
		parkingStore.sellParkingCard("AA123BB", 4);
		Mockito.verify(mockSystem, Mockito.times(1)).generateParkingPurchase(mockParkingZone, 4, "AA123BB");

	}

	@Test
	void testGetLocation() {
		assertEquals(mockCoordinate, parkingStore.getLocation());
	}

	@Test
	void testGetParkingZone() {
		assertEquals(mockParkingZone, parkingStore.getParkingZone());
	}

}

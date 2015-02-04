import static org.junit.Assert.*;
import ipv4.Address;

import org.junit.Test;


public class TestAddress {

	@Test
	public void testAddress_whenAddressValid_returnTrue() {
		assertTrue(Address.isValid("192.168.0.1"));
	}
	
	@Test
	public void testAddress_whenAddressHasExtraDots_returnFalse() {
		assertFalse(Address.isValid("192.168.0.1."));
	}

	@Test
	public void testAddress_whenAddressHasMissingDots_returnFalse() {
		assertFalse(Address.isValid("192.168.0"));
	}
	
	@Test
	public void testAddress_whenAddressValueOverAllowableRange_returnFalse() {
		assertFalse(Address.isValid("256.168.0.1"));
		assertFalse(Address.isValid("192.256.0.1"));
		assertFalse(Address.isValid("192.168.256.1"));
		assertFalse(Address.isValid("192.168.0.256"));
	}
}

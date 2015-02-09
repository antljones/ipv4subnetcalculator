package ipv4;

public class Subnet {
	
	private Subnet() {}
	
	public static long calcNetAddr( long address, long mask ) {
		return address & mask;
	}
	
	public static long calcBroadcastAddr( long address, long mask ) {
		//return the network address or'ed with the inverted mask
		return ( address & mask ) | ( 0xffffffffl - mask);
	}
	
	public static long calcAvailableAddressAmount( long address, long mask) {
		//return the broadcast address minus the network address - 1 (since 0 counts as an address)
		return calcBroadcastAddr( address, mask ) - calcNetAddr( address, mask ) - 1;
	}
}
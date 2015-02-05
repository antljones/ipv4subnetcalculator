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
}
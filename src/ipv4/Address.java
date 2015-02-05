package ipv4;

public class Address {
	
	private static final String IPV4_FORMAT = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
	
	private Address() {}
	
	public static boolean isValid(String dottedDecimalAddress ) {
		return dottedDecimalAddress.matches(IPV4_FORMAT);
	}
	  
	public static long toValue(String dottedDecimalAddress) {
		long intEndIP = 0;
		
	    String ipSplit[] = dottedDecimalAddress.split( "\\." );
			
		int k = 0;
			
		do {
			intEndIP |= Long.parseLong( ipSplit[ k ] );

			if ( k < 3 ) {
				intEndIP = intEndIP << 8;
			}
				
			k++;
		} while( k < 4 );
			
		intEndIP &= 0xffffffff;
		
		return intEndIP;
	}
	
	public static String toDottedDecimal( long value ) {
		return ( value >>> 24 ) + "." +
			   ( ( value & 0x0000000000ff0000L ) >>> 16 ) + "." +
			   ( ( value & 0x000000000000ff00L ) >>> 8 ) + "." +
			   ( value & 0x00000000000000ffL );
	}	
}
package ipv4;

public class Address {
	
	private String address;
	
	public Address() {}
	
	public Address( String address ) {
		setAddress( address );
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress( String address ) {	
		this.address = address;
	}
	
	public void setAddress( long addressValue ) {
		this.setAddress( toDottedDecimal( addressValue ) );
	}

	public void setAddress( Address address ) {
		this.address = address.getAddress();
	}
	  
	public long toValue() {
		long intEndIP = 0;
		
		if ( ! address.equals( "INVALID" ) ) {	
			String ipSplit[] = this.getAddress().split( "\\." );
			
			int k = 0;
			
			do {
				intEndIP |= Long.parseLong( ipSplit[ k ] );

				if ( k < 3 ) {
					intEndIP = intEndIP << 8;
				}
				
				k++;
			} while( k < 4 );
			
			intEndIP &= 0xffffffff;
			
		} else {
			intEndIP = Long.MAX_VALUE;
		}
		
		return intEndIP;
	}
	
	private String toDottedDecimal( long value ) {
		String host = "";
			
		host += ( value >>> 24 ) + "." +
				( ( value & 0x0000000000ff0000L ) >>> 16 ) + "." +
				( ( value & 0x000000000000ff00L ) >>> 8 ) + "." +
				( value & 0x00000000000000ffL );	
			
		return host;
	}	
}
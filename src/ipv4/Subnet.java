package ipv4;

public class Subnet {
	
	Address BAddr;
	Address NAddr;

	public Subnet( String addr , String mask ){
		BAddr = new Address();
		NAddr = new Address();
		
		Address tempAddr = new Address( addr );
		Address tempMask = new Address( mask );
		
		calcNetAddr( tempAddr , tempMask );
		calcBroad( tempMask );
	}
	
	private void calcNetAddr( Address addr , Address mask ){
		Long l = addr.toValue() & mask.toValue();
		
		l &= 0xffffffff;
		
		NAddr.setAddress( l );	
	}
	
	public Address getNetAddr(){
		return NAddr;
	}

	private void calcBroad( Address mask ){
		String tempAddress[] = getNetAddr().getAddress().split( "\\." );
		
		String tempMask[] = mask.getAddress().split( "\\." );
		
		String broadcast[] = new String[ 4 ];
		
		for ( int i = 0; i < tempAddress.length; i++ ){
			if ( Integer.parseInt( tempMask[ i ] ) == 255 ){
				broadcast[ i ] = tempAddress[ i ];
			}else{
				broadcast[ i ] = "" + ( Integer.parseInt( tempAddress[ i ] ) + ( 255 - Integer.parseInt( tempMask[ i ] ) ) );
			}
			
		}	
		
		String broadcastAddress = broadcast[ 0 ] + ".";
		broadcastAddress += broadcast[ 1 ] + ".";
		broadcastAddress += broadcast[ 2 ] + ".";
		broadcastAddress += broadcast[ 3 ];
		
		BAddr.setAddress( broadcastAddress );
	}
	
	public Address getBroadAddr(){
		return BAddr;
	}
}
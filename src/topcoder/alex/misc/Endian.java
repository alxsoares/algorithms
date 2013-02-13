package topcoder.alex.misc;

public class Endian {

	static byte[] toLittleEndian(int data)
	  {
	     byte[] result = new byte[4];
	     result[3]= (byte) ((data >> 24) & 0xff);
	     result[2]= (byte) ((data >> 16) & 0xff);
	     result[1]= (byte) ((data >> 8)  & 0xff);
	     result[0]=  (byte) (data        & 0xff); 
	     return result;
	  }
	static int fromLittleEndian(byte[] data)
	{
	  return ((data[3]&0x00ff) << 24 | (data[2]&0x00ff) << 16 |(data[1]&0x00ff) << 8 | data[0]&0x00ff);
	}
	static void printBytes(byte[] data){
		System.out.printf("%02x%02x%02x%02x\n",data[0],data[1],data[2],data[3]);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] littleEndian = toLittleEndian(666);
		printBytes(littleEndian);
		System.out.println(fromLittleEndian(littleEndian));
	}

}

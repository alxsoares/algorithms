package alex.algorithms;

import java.security.SecureRandom;
import java.util.HashSet;

public class RandomTest {
	public static void main(String[] args) {
		Long seed = System.nanoTime();
		SecureRandom sr = new SecureRandom(toLittleEndian(seed));
		
		byte[] token = sr.generateSeed(6);
		System.out.println(arrayAsHexString(token));
		HashSet<String> s = new HashSet<>();
		//Testing randomness
		int count =0;
		while(true){
			byte[] t = sr.generateSeed(6);
			String strToken = arrayAsHexString(t);
			System.out.println(strToken);
			if(s.contains(strToken)){
				break;
			}else{
				s.add(strToken);count++;
			}
		}
		System.out.printf("Collision after %d rounds.\n", count);
	}

	static byte[] toLittleEndian(long l) {
		return new byte[] { (byte) l, (byte) (l >> 8), (byte) (l >> 16),
				(byte) (l >> 24) };
	}

	final protected static char[] hexa = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String arrayAsHexString(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexa[v >>> 4];
			hexChars[j * 2 + 1] = hexa[v & 0x0F];
		}
		return new String(hexChars);
	}
}

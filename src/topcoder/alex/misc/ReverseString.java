package topcoder.alex.misc;

public class ReverseString {

	public static String reverse(String s){
		char[] chars = s.toCharArray();
		int len = chars.length;
		for(int i=0;i < len/2; i++ ){
			char aux = chars[i];
			chars[i] = chars[len-i-1];
			chars[len-i-1] = aux;
		}
		return new String(chars);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(reverse("1234"));
		System.out.printf("%02x %02x %02x", 1,16, 126);

	}

}

package alex.datastructures;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class BV {
	static byte[] bitField = new byte[0xFFFFFFF/8];
	public static void findNumber(String file) throws FileNotFoundException{
		Scanner scan = new Scanner(new FileReader(file));
		while(scan.hasNext()){
			int n = scan.nextInt();
			bitField[n/8]|=(1<<n%8);
		}
		scan.close();
		for(int i=0 ; i< bitField.length;i++){
			for(int j=0; j<8;j++){
				int val = bitField[i]&(1<<j);
				if(val==0){
					System.out.printf("%d is not present",i*8+j);
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

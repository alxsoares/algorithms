package alex.datastructures;

import java.util.Random;

public class BitSet {
	int [] bitSet;
	public BitSet(int size){
		bitSet = new int[size>>5];//divide por 32
	}
	public boolean get(int pos){
		int wordNumber = pos>>5;
		int bit = pos & 0X1F;//mod 32
		if((bitSet[wordNumber] & (1<<bit))==0) return false;
		return true;
	}
	public void set(int pos){
		int wordNumber = pos>>5;
		int bit = pos & 0X1F;//mod 32
		bitSet[wordNumber]|=(1<<bit);
	}
	
	public static void checkDuplicates(int array[]){
		BitSet bs = new BitSet(32000);
		for (int i = 0; i < array.length; i++) {
			int num = array[i]-1;
			if(bs.get(num)){
				System.out.printf("%d duplicado\n", array[i]);
			}else{
				bs.set(num);
			}
		}
	}
	
	public static void main(String[] args) {
		Random r = new Random(System.currentTimeMillis());
		int array[] = new int[3201];
		for(int i=0; i < array.length;i++){
			array[i] = r.nextInt(3200);
		}
		checkDuplicates(array);
	}

}

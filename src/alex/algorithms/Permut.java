package alex.algorithms;

public class Permut {
    public void permut(final char src[], final char dest[], final int pos, final int size) {
        if (pos < size) {
            for (int i = 0; i < src.length; i++) {
                dest[pos] = src[i];
                permut(src, dest, pos + 1, size);
            }
        } else {
            System.out.println(dest);
        }
    }

    public void permutString(final char str[], final int n) {
        final int len = str.length;
        if (n == len - 2) {
            System.out.println(str);
            char aux = str[len - 1];
            str[len - 1] = str[len - 2];
            str[len - 2] = aux;
            System.out.println(str);
            str[len - 2] = str[len - 1];
            str[len - 1] = aux;
        } else {
            for (int i = n; i < str.length; i++) {
                char aux = str[i];
                str[i] = str[n];
                str[n] = aux;
                permutString(str, n + 1);
                str[n] = str[i];
                str[i] = aux;
            }
        }
    }

    void print(final char[] s, final int[] perm, final int n) {
        for (int i = 0; i < n; i++)
            System.out.printf("%c", s[perm[i]]);
        System.out.printf("\n");
    }

    void perm(final char[] s, final int[] stack, final int n, final int level) {
        if (level == n) {
            print(s, stack, n);
            return;
        }
        for (int i = 0; i < n; i++) {
            stack[level] = i;
            boolean discard = false;
            for (int j = 0; j < level; j++) {
                if (stack[j] == i) {
                    discard = true;
                    break;
                }
            }
            if (!discard)
                perm(s, stack, n, level + 1);
        }
    }
    public static void printPermutations(String s){
    	printPermutations(s.toCharArray(),0);
    }
    private static void printPermutations(char [] s, int n) {
    	if(n == s.length){
    		System.out.printf("%s\n",new String(s));
    	}else{
    		for(int i=n;i <s.length;i++){
    			char aux = s[i];
    			s[i] = s[n];
    			s[n] = aux;
    			printPermutations(s,n+1);
    			s[n] = s[i];
    			s[i] = aux;
    		}
    	}
	}

	public static void main(final String[] args) {
        Permut permut = new Permut();
        String src = "0123456789ABC";
        // permut.permut(src.toCharArray(),new char[7],0,7);
        // permut.permutString(src.toCharArray(),0);
//        permut.perm(src.toCharArray(), new int[src.length()], src.length(), 0);
        printPermutations("ABC");
    }
}
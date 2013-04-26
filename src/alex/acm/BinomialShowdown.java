package alex.acm;

import java.io.IOException;
import java.util.StringTokenizer;

public class BinomialShowdown {
  long gcd(long a, long b) {
    if (a % b == 0)return b;
    else return gcd(b, a % b);
  }


  void Divbygcd(long  a, long  b) {
    long g = gcd(a, b);
    a /= g;
    b /= g;
  }

  long C(int n, int k) {
    long numerator = 1, denominator = 1, toMul, toDiv, i;
    if (k > n / 2) k = n - k; /* use smaller k */
    for (i = k; i>0; i--) {
      toMul = n - k + i;
      toDiv = i;
     // Divbygcd(toMul, toDiv);
      long g = gcd(toMul,toDiv);
      toMul /= g;
      toDiv /= g;
      //Divbygcd(numerator, toDiv);
      g = gcd(numerator,toDiv);
      numerator /= g;
      toDiv /=g;

      //Divbygcd(toMul, denominator);
      g = gcd(toMul,denominator);
      toMul/=g;
      denominator/=g;
      numerator *= toMul;
      denominator *= toDiv;
    }
    return numerator / denominator;
  }
  static String ReadLn(int maxLg) { // utility function to read from stdin

     byte lin[] = new byte[maxLg];

     int lg = 0, car = -1;

     try {

       while (lg < maxLg) {

         car = System.

             in.read();

         if ( (car < 0) || (car == '\n')) {
           break;
         }
         lin[lg++] += car;

       }

     }

     catch (IOException e) {

       return (null);
     }

     if ( (car < 0) && (lg == 0)) {
       return (null); // eof
     }

     return (new String(lin, 0, lg));
   }
  public static void main(String[] args) {
    BinomialShowdown main = new BinomialShowdown();
    String line ="";
    while((line = ReadLn(255))!= null){
      StringTokenizer tk = new StringTokenizer(line);
      int n = Integer.parseInt(tk.nextToken().trim());
      int k = Integer.parseInt(tk.nextToken().trim());
      if( n ==0 && k== 0) break;
      System.out.println(main.C(n,k));
    }

  }
}


package alex.algorithms;

import java.util.Stack;


public class TorresDeHanoi {
  public static void Hanoi(int n, int src, int dest, int aux)
  {
    if(n > 0){
      Hanoi(n-1, src, aux,dest);
      System.out.println("Mova "+src+" para "+dest);
      Hanoi(n-1,aux,dest,src);
    }
  }
  //ERRADO.
//  public static void Hanoi( Stack<Integer> src, Stack<Integer> dest, Stack<Integer> aux)
//  {
//    if(!src.isEmpty()){
//      Hanoi(src, aux,dest);
//      dest.push(src.pop());
//      Hanoi(aux,dest,src);
//    }
//  }
  public static void main(String[] args) {
    Hanoi(20,1,2,3);
  }
}

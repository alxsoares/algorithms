#include <stdio.h>
#include <stdlib.h>
#include <math.h>
  int primes[31624];
  int sieve[31624];
  int count =0;
  void precalcPrimes(){
    sieve[0] = 1;
    sieve[1] =1;
    sieve[2] = 0;
    for(int j=2; j< 31624;j++){
      if(sieve[j]==0){
        count++;
        for(int i=2*j; i < 31624;i+=j){
          sieve[i]++;
        }
      }
    }
    int j =0;
    for(int i=2;i <  31624;i++){
      if(sieve[i]==0){
        primes[j++] = i;
      }
    }
  }
  bool isPrime(long n){
    bool r = true;
    for(long i=2; i<=sqrt(n*1.0)+1;i++){
      if(n%i==0) return false;
    }
    return r;
  }
  void printResult(long l, long u) {
    long n = l;

    long max = 1;
    for (long i = l; i <= u; i++) {
      long number = 1;
      long aux = i;
         for (int j = 0; j < count && primes[j] <=sqrt(i * 1.0) + 1; j++) {
           int p = primes[j];
           int div = 0;
           while (aux % p == 0 && aux != 0) {
             div++;
             aux = aux / p;
           }
           number = number * (div + 1);
           if (aux == 0)break;
         }
      if (number > max) {
        max = number;
        n = i;
      }

    }
        if(n> 2 && max ==1) max++;



    printf("Between %ld and %ld, %ld has a maximum of %ld divisors.\n",l,u,n,max);

  }

int main(){
        long cases;
        long l,u;
        precalcPrimes();
        scanf("%ld\n",&cases);
        while(cases > 0){
                scanf("%ld %ld\n",&l,&u);
                printResult(l,u);
                cases--;
                /*printf("Atual %lld\n",cases);*/
                if(cases < 0) break;
                }
        return 0;
        }


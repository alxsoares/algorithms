#include <cstdlib>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>

using namespace std;
long long base[] = {0,1,1,1};
long long calcFibModular(long long n, long long m)
{
  if(n==0 || n==1)
  {
          return n;
  }
  int k = n/2;
  long long mod = (1<<m);
  long long fn = calcFibModular(k,m);
  long long fn_1 = calcFibModular(k-1,m);
  long long um = (k%2==0)?1:-1;
  long long b = (fn*fn +fn_1*fn_1)%mod;
  long long a = (4*fn*fn-fn_1*fn_1+2*um)%mod;
  if(n%2==0)
  {
     return (a - b)%mod;
  }else
  {
       return a;
  }

}
 long fibMod(long n, long m) {
    long i = 1;
    long j = 0;
    long k = 0;
    long h = 1;
    long t;
    long mod = (1<< m);
    while (n > 0) {
      if (n % 2 == 1) {
        t = (j * h)%mod;
        j = ((i * h)%mod + (j * k)%mod + t)%mod;
        i = ((i * k)%mod + t)%mod;
      }
      t =( h * h)%mod;
      h = ((2 * k * h)%mod + t)%mod;
      k = ((k * k)%mod + t)%mod;
      n = n / 2;

    }
    return j;

  }
 long long * multiMatriz(long long *m,long long * n,long long md){
     long long mat[4];
     long long a = n[0];
     long long b = n[1];
     long long c = n[2];
     long long d = n[3];

     long long e = m[0];
     long long f = m[1];
     long long g = m[2];
     long long h = m[3];

     long long mod = (1<<md);
     mat[0] = (a*e +b*g)%mod;
     mat[1] = (a*f+b*h)%mod;
     mat[2] = (c*e+d*g)%mod;
     mat[3] = (c*f+d*h)%mod;

     return mat;
}
long long * calfFibMatrix(int n,int m)
        {
        long long fact[] = {0,1,1,1};
        long long r[] = {1,0,0,1};
        while(n > 0 ){
                if(n &1){
                        long long * aux = multiMatriz(r,fact,m);
                        r[0] = aux[0];
                        r[1] = aux[1];
                        r[2] = aux[2];
                        r[3] = aux[3];
                        }
        long long *aux = multiMatriz(fact,fact,m);
        fact[0] = aux[0];
        fact[1] = aux[1];
        fact[2] = aux[2];
        fact[3] = aux[3];
        n = n>>1;

                }
return r;

}
long long calc(int n, int m){
if(n==0 || n==1)
{
        return n;
}
long long *r =  calfFibMatrix(n,m);
return r[1];
}

int main()
{
    long n,m;
    while((scanf("%ld %ld",&n,&m))==2)
    {
      long r = calc(n,m);


      printf("%ld\n",r);
    }
    return 0;
}

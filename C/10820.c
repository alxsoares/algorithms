#include <stdio.h>

#define MAXSIEVE 50000

char isPrime[MAXSIEVE+2];
int phi[MAXSIEVE+2];
int sum[MAXSIEVE+2];

void sieve() {
 int i,j,k=MAXSIEVE/2;
 for(i=1;i<=MAXSIEVE;i++) phi[i] = i;
 phi[1] = 0;
 for(i=2;i<=k;i++) if(!isPrime[i]) {
  for(j=i+i;j<=MAXSIEVE;j+=i) {
   isPrime[j] = 1;
   phi[j] = (phi[j] / i) * (i-1);
  }
 }
 for(i=2;i<=MAXSIEVE;i++) if(!isPrime[i]) phi[i] = i-1;
 for(i=1;i<=MAXSIEVE;i++) sum[i] = sum[i-1] + phi[i];
}

int main() {
 int n;
 sieve();
 while(scanf(" %d",&n)==1 && n) {
  printf("%d\n",2*sum[n]+1);
 }
 return 0;
}

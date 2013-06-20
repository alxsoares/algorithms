#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#define MAXSIEVE 316228
#define MM 199999999

long long *cache ;
char isPrime[MAXSIEVE+2];
int primes[27293];
void sieve() {
 int i,j;
 int count=0;
 for(i=2;i<=MAXSIEVE;i++) if(!isPrime[i]) {
  count++;
  for(j=i+i;j<=MAXSIEVE;j+=i) {
   isPrime[j] = 1;
  }
 }
 printf("%d\n",count);
 j=0;
 for(i=2;i<=MAXSIEVE;i++){
   if(isPrime[i]!=1){
	//printf("%d\n",j);
	primes[j++] = i;
   }
 }
 printf("Terminou %d\n",1);
}

long fi(long n) {
		if (n <= 0)
			return -1;
		if(end <MM && cache[n]!=0){
					return cache[n];
				}		
		int p = 1;
		int end=sqrt(n);
		long i;
		int j;
		for ( j = 0;j< 27293 && primes[j] <= end; j++) { // Trial
															// division
			int i = primes[j]; // Trial
															// division
			if (n % i == 0) { // Found a factor
				p *= i - 1;
				n /= i;
				while (n % i == 0) {
					p *= i;
					n /= i;
				}
				if(n < MM && cache[n]!=0){
				   return p*cache[n]; 
				}
				end = sqrt(n);
				if(end <MM && cache[end]!=0){
					return p*cache[end];
				}
			}
		}
		if (n != 1)
			p *= n - 1;
		return p;
	}

	
	long long fiEO(long m) {
		if (m < MM && cache[(int) m] != 0)
			return cache[(int) m];
		if (m % 4 == 0) {
			long result = 2 * fiEO(m / 2);
			if (m < MM)
				cache[(int) m] = result;
			return result;
		}
		if (m % 2 == 0 && m % 4 != 0) {

			long result = fiEO(m / 2);
			if (m < MM)
				cache[(int) m] = result;
			return result;
		}
		long result = fi(m);
		if (m < MM)
			cache[(int) m] = result;
		return result;
	}
 
long gcdLong(long u, long v) {
		if (u == v)
			return u;
		if (u == 0)
			return v;
		if (v == 0)
			return u;
		if ((~u & 1) > 0) {// u é par
			if ((v & 1) > 0) {// v impar
				return gcdLong(u >> 1, v);// v impar e u par
			}
			return gcdLong(u >> 1, v >> 1) << 1;// ambos são pares
		}
		if ((~v & 1) > 0)
			return gcdLong(u, v >> 1);// v par e u impar
		if (u > v) {
			return gcdLong((u - v) >> 1, v);
		} else {
			return gcdLong(u, (v - u) >> 1);
		}
	}
long long fiM(long m, long n) {
		long d = 1;
		if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0 || n % 11 == 0
				|| n % 13 == 0 || n % 17 == 0) {
			d = gcdLong(m, n);
			return 92160 * fiEO(n) * d / fiEO(d);
		}
		return 92160 * fiEO(n);

	}
 void listTotients() {
		int i;int j;
		for (i = 0; i < MM; i++)
			cache[i] = i;

		for (i = 2; i < MM; i++) {
			if (cache[i] == i) { // i is prime
				for (j = i; j < MM; j += i)
					cache[j] = cache[j] / i * (i - 1);
			}
		}
	}

int main(){
	cache = (long long *) calloc(MM, sizeof(long long));
	 memset(cache,0,MM);
	listTotients();
	sieve();
	FILE *ofp = fopen("euler.out", "w");
	long long result = 0;
		long mod = 1000000000;
		long long i;
		for (i = 1; i <= 100000000000L; i++) {
			
			if (i % 1000000 == 0)
				printf("%lld\n",i);
			result = ((result%mod) + fiM(510510, i)%mod)%mod;
		}
		free(cache);
		printf("%lld\n",result);
		fclose(ofp);
	
}


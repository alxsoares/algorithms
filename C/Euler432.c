#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#define MAXSIEVE 316228
#define MM 299999999

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
		if(n <MM && cache[n]!=0){
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
 static long fi2(long m, long n) {

        long d = 1;
        char two = 0, three = 0, five = 0, seven = 0, eleven = 0, thirteen = 0, seventeen = 0;
        while (n % 2 == 0) {
            n = n / 2;
            d *= 2;
            two = 1;
        }
        while (n % 3 == 0) {
            n = n / 3;
            d *= 3;
            three = 1;
        }
        while (n % 5 == 0) {
            n = n / 5;
            d *= 5;
            five = 1;
        }
        while (n % 7 == 0) {
            n = n / 7;
            d *= 7;
            seven = 1;
        }
        while (n % 11 == 0) {
            n = n / 11;
            d *= 11;
            eleven = 1;
        }
        while (n % 13 == 0) {
            n = n / 13;
            d *= 13;
            thirteen = 1;
        }
        while (n % 17 == 0) {
            n = n / 17;
            d *= 17;
            seventeen = 1;
        }

        long num = 1;
        long den = 1;
        if (two) {
            m = m / 2;
            d = d * 2;
            num *= 1;
            den *= 2;
        }
        if (three) {
            m = m / 3;
            d = d * 3;
            num *= 2;
            den *= 3;
        }
        if (five) {
            m = m / 5;
            d = d * 5;
            num *= 4;
            den *= 5;
        }
        if (seven) {
            m = m / 7;
            d = d * 7;
            num *= 6;
            den *= 7;
        }
        if (eleven) {
            m = m / 11;
            d = d * 11;
            num *= 10;
            den *= 11;
        }
        if (thirteen) {
            m = m / 13;
            d = d * 13;
            num *= 12;
            den *= 13;
        }
        if (seventeen) {
            m = m / 17;
            d = d * 17;
            num *= 16;
            den *= 17;
        }
        // if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0 || n % 11 ==
        // 0 || n % 13 == 0 || n % 17 == 0) {
        // d = gcd(m, n);
        // return 92160 * fiEO(n) * d / fiEO(d);
        // }
        return (fiEO(m) * fiEO(n) * d * num) / den;
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


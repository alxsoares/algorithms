#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <iostream>
#define MAX 47000


using namespace std;
int primes[MAX];
int numbers[MAX+1];
int nprimes=0;
void calcPrimes(){
        for(int i = 2; i <=MAX;i++){
                if(numbers[i]==0){
                        primes[nprimes++] = i;
                        for(int j=2*i;j<=MAX;j+=i){
                                numbers[j]++;
                        }
                }
        }
}
void printFactor(long long i){
        bool first = true;
        printf("%lld = ",i);
        if(i<0){
                printf("-1");
                i=-1*i;
                first = false;
        }
        int k =0;
        long long j = primes[k++];
        while( i > 1 && k <  nprimes){
                if(i%j==0){
                        while(i%j==0){
                                if(first){
                                        printf("%lld",j);
                                        first = false;
                                }else{
                                        printf(" x %lld",j);
                                }
                                i=i/j;
                        }
                }
                j = primes[k++];
        }
        if(i > 1){
                if(first){
                        printf("%lld",i);
                }else{
                        printf(" x %lld",i);
                }
        }
        printf("\n");
}
int main(){
        calcPrimes();
        long long i;
        cin >> i;
        while(i !=0){
                printFactor(i);
                cin >> i;
        }
        return 0;
}


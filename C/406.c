#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define MAX 1000
#define max(x,y) ((x>y?x:y));
int divs[MAX+1];
int primes[MAX+1];
int prim =1;
void calc(){
     primes[prim++]=1;
    for(int i=0; i <=MAX;i++) divs[i] = 0;
    for(int i=2; i <= MAX;i++){
        if(divs[i]==0){
        primes[prim++] =i;
        for(int j=2*i; j<=MAX;j+=i){
        divs[j]++;
        }
    }
    }
}



void printList(int n, int c){

     int j=0;
     printf("%d %d: ",n,c);
        for(int i=1; i <= n ;i++){
            if(divs[i]==0){
            j++;
        }

    }
    int lower=0,upper =0;
    if(j%2==0){
         int d  = 2*c;
         lower = max(1,(j-d)/2+1);
         upper = (j+d)/2;
               }
    else{
         int d  = 2*c-1;
         lower = max(1,(j-d)/2+1);
         upper = (j+d)/2;
         }

    for(int i=lower; i<=upper && i < prim && primes[i]<=n;i++)
    {
            printf("%d ",primes[i]);
    }

    printf("\n\n");


}

int main(){
calc();
int n, c;
while(scanf("%d %d\n",&n,&c)!=EOF){
printList(n,c);

}

}

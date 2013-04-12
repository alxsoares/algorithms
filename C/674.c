#include <stdio.h>
#define MAXTOTAL 10000

long long nway[MAXTOTAL+1];

int coin[5] = { 50,25,10,5,1 };

void calc() {


  int i,j,v,c;
  v = 5;
  nway[0] = 1;
  for (i=0; i<v; i++) {
    c = coin[i];
    for (j=c; j<=7489; j++)
      nway[j] += nway[j-c];
  }

}
int main(){
        int n;
        calc();
        while(scanf("%d",&n)!=EOF){
                if(n < 0) printf("%lld\n",0);
                else printf("%lld\n",nway[n]);
                }


        }


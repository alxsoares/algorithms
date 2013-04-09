#include<stdio.h>
#define max 1005
#define MAX 1005
#define MYMAX(x,y) ((x>y?x:y));
int main(){

int C[max][max],i,N,MW,w,Wi[MAX],Vi[MAX];
int tcase,T_cost=0,persons;
scanf("%d",&tcase);
while(tcase--){

scanf("%d",&N);
for(i=1;i<=N;++i)
scanf("%d %d\n",&Vi[i],&Wi[i]);

scanf("%d",&persons);
while(persons--){
scanf("%d",&MW);
for (i=0;i<=N ;i++) C[i][0] = 0;
for (w=0;w<=MW;w++) C[0][w] = 0;

for (i=1;i<=N;i++)
for (w=1;w<=MW;w++) {
if (Wi[i] > w)
C[i][w] = C[i-1][w];
else
C[i][w] = MYMAX(C[i-1][w] ,C[i-1][w-Wi[i]]+Vi[i]);
}

T_cost+=C[N][MW];
}

printf("%d\n",T_cost);
T_cost=0;

}
return 0;
}

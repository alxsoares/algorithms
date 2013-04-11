#include <stdio.h>
#include <stdlib.h>

#include <string.h>
/*10405*/
#define MAX 1000

int X[MAX],Y[MAX];
int i,j,m,n,c[MAX][MAX],b[MAX][MAX];

int LCSlength(int l1, int l2) {
  m=l1;
  n=l2;

  for (i=1;i<=m;i++) c[i][0]=0;
  for (j=0;j<=n;j++) c[0][j]=0;

  for (i=1;i<=m;i++)
    for (j=1;j<=n;j++) {
      if (X[i-1]==Y[j-1]) {
        c[i][j]=c[i-1][j-1]+1;
        b[i][j]=1; /* from north west */
      }
      else if (c[i-1][j]>=c[i][j-1]) {
        c[i][j]=c[i-1][j];
        b[i][j]=2; /* from north */
      }
      else {
        c[i][j]=c[i][j-1];
        b[i][j]=3; /* from west */
      }
    }

  return c[m][n];
}

void printLCS(int i,int j) {
  if (i==0 || j==0) return;

  if (b[i][j]==1) {
    printLCS(i-1,j-1);
    printf("%c",X[i-1]);
  }
  else if (b[i][j]==2)
    printLCS(i-1,j);
  else
    printLCS(i,j-1);
}

void main() {
        int l1,l2,i,tower=1,L1,L2;
        while(scanf("%d %d",&l1,&l2)==2){
                if(l1==0 && l2==0) break;
                L1 = l1;
                L2 = l2;
                i=0;
                while(l1>0){
                          scanf("%d",&X[i++]);l1--;
                        }
                i=0;
                while(l2>0){
                          scanf("%d",&Y[i++]);l2--;
                        }
                printf("Twin Towers #%d\n",tower++);
                printf("Number of Tiles : %d\n",LCSlength(L1,L2));
                printf("\n");

                }
}


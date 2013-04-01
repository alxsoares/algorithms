#include<stdio.h>
#define MAXK 1010
#define MAXL 5000

int r[MAXK][MAXL];
int l[MAXL];
int n;
int k;

int
resolve (int ini, int chop)
{
  int v1, v2, l1;
  if (r[ini][chop] != -1) return r[ini][chop];

  l1 = l[chop]-l[chop+1];
  v1 = l1*l1;
  if (ini<k-1) v1 += resolve(ini+1, chop+2);

  if (3*(k-ini) <= n-chop-1) {
    v2 =  resolve(ini, chop+1);
    if (v2 < v1) v1 = v2;
  }
  r[ini][chop]=v1;
  return v1;
}

int
main(){
  int c;
  int i, j;
  scanf(" %d ", &c);
  while (c){
    c--;
    scanf(" %d %d ", &k, &n);
    k+=8;
    for(i = 0; i < n; i++) {
      scanf(" %d ", &l[i]);
    }
    for(i = 0; i < k; i++){
      for(j = 0; n-j >= 3*(k-i) && j < n; j++){
      r[i][j] = -1;
      }
    }
    printf("%d\n", resolve(0, 0));
  }


  return 0;
}
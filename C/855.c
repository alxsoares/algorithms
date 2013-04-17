#include <stdio.h>
#include <stdlib.h>      

long STREETS[50002];
long AVENUES[50002];

int cmp(const void *ap, const void *bp)
{
  long a = *(long *)ap;
  long b = *(long *)bp;
  if (a < b) return -1;
  if (a > b) return 1;
  return 0;
}

int main(void)
{
  int nc, cases;
  int  streets;
  int  avenues;
  int nf, friends;
  long medst;
  long medav;
  scanf("%d",&cases);
  for (nc=0;nc<cases;nc++){
    scanf("%d %d %d",&streets,&avenues,&friends);
    for (nf=0;nf<friends;nf++)
      scanf("%ld %ld",&STREETS[nf],&AVENUES[nf]);

      qsort(STREETS,friends,sizeof(long),cmp);
      qsort(AVENUES,friends,sizeof(long),cmp);
     if ((friends % 2) == 0){
        medst = (STREETS[(friends/2)-1]);
        medav = (AVENUES[(friends/2)-1]);


      }
      else{
        medst = STREETS[friends/2];
        medav = AVENUES[friends/2];
      }

    printf("(Street: %ld, Avenue: %ld)\n",medst,medav);
  }
  return 0;
}

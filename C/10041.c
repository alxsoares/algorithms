#include <stdio.h>     
#include <stdlib.h>    
long STREETS[50002];

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
  int i,j,N,r,rel[500],t,opt,hasil;
        while(scanf("%d",&N)!=EOF)
        {
                for(j=0;j<N;j++)
                {
                opt=0;
                scanf("%d",&r);
                for(i=0;i<r;i++)
                scanf("%d",&rel[i]);
                qsort(rel,r,sizeof(rel[0]),cmp);
                if(r%2==0){
                        t = (r/2)-1;
                }
                else{
                        t = r/2;
                }
                for(i=0;i<r;i++)
                {
                        hasil=rel[i]-rel[t];
                   if(hasil<0) hasil=-hasil;
                        opt+=hasil;
                }
                printf("%d\n",opt);
                }
        }

}

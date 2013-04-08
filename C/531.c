#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/*531*/
#define MAX 100
typedef struct Word
        {
          char seq[31];
        } Word;

Word X[MAX],Y[MAX];
int i,j,m,n,c[MAX][MAX],b[MAX][MAX];

int LCSlength(int l1, int l2) {
  m=l1;
  n=l2;

  for (i=1;i<=m;i++) c[i][0]=0;
  for (j=0;j<=n;j++) c[0][j]=0;

  for (i=1;i<=m;i++)
    for (j=1;j<=n;j++) {
                if (strcmp(X[i-1].seq,Y[j-1].seq)==0) {
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
        if(i-1 ==0|| j-1 ==0)


                {
                printf("%s",X[i-1].seq);
                }
        else
                {
                printf(" %s",X[i-1].seq);
                }

  }
  else if (b[i][j]==2)
    printLCS(i-1,j);
  else
    printLCS(i,j-1);
}
void main() {
        while(true){
                char c;
                int i=0;
                int word1 =0;
                int word2 =0;
                while((c = getchar())!=EOF)
                        {
                        if(c>='a' && c<='z')
                                {
                                X[word1].seq[i++] = c;
                                }
                        else if(c == ' '|| c=='\n' && i!=0)
                                {
                                X[word1].seq[i]='\0';word1++;i=0;
                                }
                        else if(c =='#'){
                                X[word1].seq[i]='\0';i=0;
                                break;
                                }

                        }
                if(c == EOF) break;
                while((c = getchar())!=EOF)
                        {
                        if(c>='a' && c<='z')
                                {
                                Y[word2].seq[i++] = c;
                                }
                        else if(c == ' '|| c=='\n' && i!=0)
                                {
                                Y[word2].seq[i]='\0';word2++;i=0;
                                }
                        else if(c =='#'){
                                Y[word2].seq[i]='\0';i=0;
                                break;
                                }

                        }
                LCSlength(word1,word2);
                printLCS(word1,word2);
                printf("\n");
                if(c == EOF) break;
                }

}

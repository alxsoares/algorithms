#define MM 100000000000LL
#define MS (MM/(5*5*5*13*13*17))
#define MC (MM/(5*5*5*13*13))
int C[MC+1], P[MC+1], np;
long long S[MS+1];
int main() {
   int n,i,j,k;
   long long p,q,X,Y,Z,A=0;

   for(q=1;q<=MS;q<<=1) S[q]=1;
   for(p=3;p<=MC;p+=2) if(!C[p]) {
      for(q=p*p;q<=MC;q+=2*p) C[q]=1;
      if((p&3)==1) P[np++]=p;
      else if(p<=MS) for(n=MS/p; n; n--) if(S[n])
         for(X=n; X<=MS; X*=p) S[X]=1;
   }
   for(n=1;n<=MS;n++) S[n]=n*S[n]+S[n-1];

   for(i=0;i<3;i++) {
      X=P[i]; X=X*X*X*X*X*X*X;
      for(j=0;j<np;j++) if(j!=i) {
         Y=X*P[j]*P[j]*P[j];
         if(Y>=MM) break;
         A+=Y*S[MM/Y];
      }
   }
   X=5*5*5*5*5; X*=X;
   for(i=1;i<np;i++) {
      Y=X*P[i]*P[i];
      if(Y>=MM) break;
      A+=Y*S[MM/Y];
   }
   for(i=0;P[i]<676;i++) {
      X=P[i]*P[i]*P[i];
      for(j=0;j<np;j++) if(j!=i) {
         Y=X*P[j]*P[j];
         if(Y>=MM) break;
         for(k=0;k<np;k++) if(k!=j && k!=i) {
            Z=Y*P[k];
            if(Z>=MM) break;
            A+=Z*S[MM/Z];
         }
      }
   }
   printf("%lli\n",A);
}

#include <stdio.h>
#include <string.h>
#define MAX_SIZE 2000
#define MAX_ELEM 1000

    typedef struct BigNum
    {


    char dig[MAX_SIZE];

    int len;
    } Bignum;

    Bignum sum(Bignum a, Bignum b)

    {

    Bignum r;r.len=0;r.dig[0] = 0;


    int carry =0;

    int i;

    for(i=0; i <a.len && i< b.len;i++)
    {


    int a1 = a.dig[i];

    int b1 = b.dig[i];

    int s = a1+ b1 + carry;
    r.dig[i] = s%10;

    r.len++;

    carry = s/10;

    }


    for(; i < a.len; i++)
    {


    int a1 = a.dig[i];

    int s = a1 + carry;
    r.dig[i] = s%10;

    r.len++;

    carry = s/10;

    }


    for(; i < b.len; i++)
    {


    int b1 = b.dig[i];

    int s = b1 + carry;
    r.dig[i] = s%10;

    r.len++;

    carry = s/10;

    }


    if(carry > 0)
    {

    r.dig[i] = carry;

    r.len++;

    }


    return r;
}
 Bignum dobra(Bignum nb)
    {
      Bignum r;r.len=0;r.dig[0] = 0;
      int carry =0;
      int i;
      for( i =0; i < nb.len;i++)
      {
         int s = 2*nb.dig[i]+carry;
         r.dig[i] = s%10;
         r.len++;
         carry = s/10;
      }
      if(carry > 0)
      {
          r.dig[i] = carry;
          r.len++;
          }
      return r;

}


   Bignum f[MAX_ELEM+1];
   void   calcFib()
    {
    Bignum one; one.dig[0] =1; one.len=1;
    Bignum two ; two.dig[0] = 2; two.len =1;
    Bignum three; three.dig[0] = 3; three.len =1;
    f[0] = one;

    f[1] = two;

    f[2] = three;

    for(int i=3; i <= MAX_ELEM; i++)
    {

    f[i] = sum((f[i-3]),dobra(f[i-2]));
    }

    }


    void  getNth(int n)
    {

    Bignum r = f[n];


    char str[MAX_SIZE+1];

    int j=0;

    for(int i = r.len-1; i >=0;i--)
    {

    sprintf(&(str[j++]),"%d",r.dig[i]);

    }

    str[j] = 0x00;


    printf("%s\n",str);
    }

 main()
{
     int j;
    calcFib() ;
    while(scanf("%d\n",&j)==1)
    {

    getNth(j);

    }
}

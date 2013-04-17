#include <stdio.h>
    #include <string.h>
    #define MAX_SIZE 2000

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

    BigNum f[5001];

    void

    calcFib()
    {
    Bignum zero; zero.dig[0] =0; zero.len=1;
    Bignum one; one.dig[0] =1; one.len=1;

    f[0] = zero;

    f[1] = one;


    for(int i=2; i <= 5000; i++)
    {

    f[i] = sum(f[i-1],f[i-2]);
    if(f[i].len>1000) break;

    }

    }

    void getNth(int n)
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

    calcFib();


    int j;

    while(scanf("%d\n",&j)==1)

    //for(int j =1; j <=5000;j++)
    {

    getNth(j);

    }

    }

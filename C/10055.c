#include <stdio.h>
#include <stdlib.h>

int main ()
{
long long  a, b,r;
while(scanf("%lld %lld", &a, &b)==2) {
        r = a -b;
        if(r < 0) r = -1*r;
printf("%lld\n", r);
}
return 0;
}

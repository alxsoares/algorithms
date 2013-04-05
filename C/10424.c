#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <cstdlib>
#include <iostream>
using namespace std;
char * StringToUpper(char * strToConvert)
{//change each element of the string to upper case
   for(unsigned int i=0;i<strlen(strToConvert);i++)
   {
      strToConvert[i] = toupper(strToConvert[i]);
   }
   return strToConvert;//return the converted string
}

int calcNumber(char *name){
       StringToUpper(name);
       int len = strlen(name);
       int sum=0;
       for(int i=0; i < len;i++){
               int value = name[i] -'A'+1;
               if(value >=1 && value <=26){
                       sum+=value;
               }
       }
       int result = 0;
       int q = sum;
       do{
               result =0;
       while(q != 0){
               result += q%10;
               q = q/10;
       }
               q = result;
       }while(result >= 10);
       return result;
}

int main(){
       char name1[30];
       char name2[30];

           while(gets(name1) && gets(name2)) {
               int sum1 = calcNumber(name1);
               int sum2 = calcNumber(name2);
                           if(sum1 == 0 && sum2 ==0){
                                        printf("%.2f",0);cout<<" %"<<endl;
                           }else
               if(sum1 > sum2){
                       float f = ((float)sum2/sum1);
                       printf("%.2f",100*f);cout<<" %"<<endl;
               }else{
                       float f = ((float)sum1/sum2);
                       printf("%.2f",100*f);cout<<" %"<<endl;
               }

       }

       return 0;
}

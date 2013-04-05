#include <stdio.h>
#include <stdlib.h>

typedef struct DNA{
        char s[50];
        int d;
        } DNA;
void calcD(DNA  &d, int n)
        {
        int count =0;
        for(int i=0; i <= n-1;i++){
                char p = d.s[i];
                for(int j =i+1; j <= n-1;j++)
                        {
                        if(p > d.s[j]){
                                count++;
                                }
                        }
                }
d.d = count;
        }
void calcDesordem(DNA *arr, int size, int n)
        {
        for(int i=0; i < size;i++)
                {
                calcD(arr[i],n);
                }

        }
void bubleSort(DNA *arr, int size)
        {
        for(int i = size-1;i>=0;i--)
                {
                for(int j=0; j < i;j++)
                        {
                        if(arr[j].d > arr[j+1].d)
                                {
                                DNA aux = arr[j+1];
                                arr[j+1] = arr[j];
                                arr[j] = aux;
                                }
                        }
                }
        for(int i=0; i < size;i++){
                printf("%s\n",arr[i].s);
                }
        }
int main(){
        int M, n , size;
        DNA arr[100];
        char blank[10];
        scanf("%d",&M);
        while(M > 0){
                M--;
                scanf("%d %d\n",&n,&size);
                int s = size;
                int i =0;
                while(s > 0){
                        s--;
                        scanf("%s\n",(arr[i++].s));
                        }
        calcDesordem(arr,size,n);
        bubleSort(arr,size);
        scanf("\n");
                }



        }

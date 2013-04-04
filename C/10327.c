#include <stdio.h>
#include <stdlib.h>

int bubleSort(int *arr, int n)
       {
       int count = 0;
       for(int i = n-1;i>=0;i--)
               {
               for(int j=0; j < i;j++)
                       {
                       if(arr[j] > arr[j+1])
                               {
                                       int aux = arr[j+1];
                                       arr[j+1] = arr[j];
                                       arr[j] = aux;
                                       count++;
                               }
                       }
               }
                   return count;
       }
        void reverse(int *arr, int n)
                {
                        for(int i=0; i < n/2;i++)
                                {
                                int aux = arr[i];
                                arr[i] = arr[n-1-i];
                                arr[n-1-i] = aux;
                                }
                }
   void solve(int *arr, int n)
           {
           int count1, count2;
                count1 = bubleSort(arr,n);
                reverse(arr,n);
                count2 = bubleSort(arr,n);
                if(count1 < count2){
                        printf("Minimum exchange operations : %d\n",count1);
                        }
                else{
                        printf("Minimum exchange operations : %d\n",count2);
                        }
           }
int  main()
       {
       int arr[1000];


       int size;
           while(scanf("%d\n",&size)!=EOF){
               int i=0;
               int n = size;
               while(size > 0)
                       {
                               size--;
                               scanf("%d",&arr[i++]);
                       }
               solve(arr,n);
                   }

       return 0;
       }

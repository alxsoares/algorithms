#include <stdio.h>
#include <stdlib.h>

void bubleSort(int *arr, int n)
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
                printf("Optimal train swapping takes %d swaps.\n",count);
        }
int  main()
        {
        int arr[50];
        int cases, size;
        scanf("%d\n",&cases);
        while(cases > 0)
                {
                cases--;
                scanf("%d\n",&size);
                int i=0;
                int n = size;
                while(size > 0)
                        {
                                size--;
                                scanf("%d",&arr[i++]);
                        }
                bubleSort(arr,n);
                }
        return 0;
        }

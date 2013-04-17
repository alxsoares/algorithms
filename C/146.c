#include <algorithm>
#include <iostream>
#include <string.h>
#include <stdio.h>


using namespace std;

int main() {
    char line[50];
    while(gets(line) && strcmp(line,"#")) {
    if(std::next_permutation(line,line+strlen(line)))
    printf("%s\n",line);
    else printf("No Successor\n");
    }
}

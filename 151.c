#include <iostream>

using namespace std;

int main(void) {

int *cityOnNet, *cityOffNet;
int citiesOff, citiesOn, nextCity, numCities, m;

while(cin >> numCities && numCities !=0) {
cityOnNet = new int[numCities];
cityOffNet = new int[numCities];

m = 0;

do{
citiesOn = numCities;

for (int i = 0; i < numCities; i++){
cityOnNet[i] = i+1;
cityOffNet[i] = 0;
}

cityOffNet[0] = 1;
citiesOn--;
citiesOff=1;
nextCity=0;

for (int i = 1; i < numCities; i++){
nextCity+=m;
while(nextCity >= citiesOn){
nextCity -= citiesOn;
}
for (int j = 0; j < citiesOn; j++){
if (cityOffNet[i-1] == cityOnNet[j]){
for (int l = j; l < citiesOn; l++){
cityOnNet[l] = cityOnNet[l+1];
}
}
}
cityOffNet[i] = cityOnNet[nextCity];
citiesOff++;
citiesOn--;
}
m++;
}while (cityOffNet[numCities-1] != 13);
cout << m <<endl;
delete [] cityOnNet;
delete [] cityOffNet;
}
return 0;
}

	#include <stdio.h>
	#include <vector>
	#include <algorithm>
	#include <math.h>
	#include <limits.h>
	#include <float.h>


	using namespace std;

	double df(double a,double b)
	{
		return sqrt(a*a+b*b);
	}

	int main()
	{
		int i,j,N,z;
		vector<vector<double> > dist;
		vector<pair<int,int> > points;
		vector<int> enumeration,ShortestPath;
		double Shortest,Current;

		z=0;
		scanf("%d",&N);
		while(N>0)
		{
		z++;
		dist=vector<vector<double> >(N,vector<double>(N,0));
		points.resize(N);
		enumeration.clear();
		Shortest=DBL_MAX;

	for(i=0;i<N;i++)
	{
	scanf("%d %d",&points[i].first,&points[i].second);
	}
	for(i=0;i<N;i++)
	{
	for(j=0;j<N;j++)
	{
	if(i!=j)
	{
	dist[i][j]=df(points[i].first-points[j].first,points[i].second-points[j].second);
	}
	}
	}
	for(i=0;i<N;i++)
	{
	enumeration.push_back(i);
	}
	do
	{
	Current=0;
	for(i=1;i<N;i++)
	{
	Current+=dist[enumeration[i-1]][enumeration[i]]+16;
	if(Current >= Shortest) break;
	}
	if(Current<Shortest)
	{
	Shortest=Current;
	ShortestPath=enumeration;
	}
	}
	while(next_permutation(enumeration.begin(),enumeration.end()));
	printf("**********************************************************\n");
	printf("Network #%d\n",z);
	for(i=1;i<N;i++)
	{
	printf("Cable requirement to connect (%d,%d) to (%d,%d) is %3.2lf feet.\n",points[ShortestPath[i-1]].first,points[ShortestPath[i-1]].second,points[ShortestPath[i]].first,points[ShortestPath[i]].second,dist[ShortestPath[i-1]][ShortestPath[i]]+16);
	}
	printf("Number of feet of cable required is %3.2lf.\n",Shortest);
	scanf("%d",&N);
	}
	return 0;
	}


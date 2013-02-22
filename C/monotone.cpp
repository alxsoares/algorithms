#include <stdio.h>
#include <vector>
#include <stack>
#include <cstdlib>

using namespace std;

#define Epsilon 0.000001

#define XOR(a,b) ((a||b) &&!(a&&b))

#define VecOpVec2(v1,op,v2)   \
((v1).X op (v2).X, \
 (v1).Y op (v2).Y  \
)

/* tipo ponto inteiro */
typedef struct Point {
	int X;
	int Y;
} Point;

typedef struct Segment {
	Point p1;
	Point p2;
} Segment;
#define PMAX    1000            /* Max # of pts in polygon */
typedef vector<Point> Polygon;/* type double polygon */

int Area2(Point a, Point b, Point c) {
	return (a.X-c.X)*(b.Y-c.Y)-(a.Y-c.Y)*(b.X-c.X);
}

bool Left(Point a, Point b, Point c) {
	return (Area2(a, b, c) > 0);
}

bool LeftOn(Point a, Point b, Point c) {
	return (Area2(a, b, c) >= 0);
}

bool Collinear(Point a, Point b, Point c) {
	return (Area2(a, b, c) == 0);
}

bool IntersectProp(Point a, Point b, Point c, Point d) {
	if (Collinear(a, b, c) || Collinear(a, b, d)|| Collinear(c, d, a)
			|| Collinear(c, d, b)) {
		return false;
	}

	return XOR(Left(a,b,c),Left(a,b,d)) && XOR(Left(c,d,a),Left(c,d,b));
}

bool Between(Point a, Point b, Point c) {
	if (!Collinear(a, b, c)) {
		return false;
	}
	if (a.X!= b.X)/*AB nao e vertical*/
	{
		return ( (a.X<=c.X&& c.X<=b.X) || (b.X<=c.X&& c.X <=a.X));
	}
	return ( (a.Y<=c.Y&& c.Y<=b.Y) || (b.Y<=c.Y&& c.Y<=a.Y));
}

bool Intersect(Point a, Point b, Point c, Point d) {
	if (IntersectProp(a, b, c, d)) {
		return true;
	}
	if (Between(a, b, c)) {

		return true;
	} else if (Between(a, b, d)) {

		return true;
	} else if (Between(c, d, a)) {
		return true;
	} else if (Between(c, d, b)) {
		return true;
	}

	return false;
}

template<class T> T fabs(T value) {
	if (value >=0)
		return value;
	return -value;
}

bool Diagonalie(int i, int j, int n, Polygon P)

{
	int k;
	int k1;

	for (k = 0; k < n; k++) {
		k1 = (k+1) % n;
		if ( ! ((k == i ) || (k1 == i )|| (k == j )|| (k1 == j )))
			if (Intersect(P[i], P[j], P[k], P[k1]) )
				return false;
	}
	return true;
}

bool InCone(int i, int j, int n, Polygon P)

{
	int i1;
	int in1;

	i1 = (i + 1) % n;
	in1 = (i + n - 1) % n;

	if (LeftOn(P[in1], P[i], P[i1]) )
		return Left(P[i], P[j], P[in1])&& Left(P[j], P[i], P[i1]);

	/* Assume (i-1,i,i+1) not collinear. */
	/* else P[i] is reflex. */
	else
		return !(LeftOn(P[i], P[j], P[i1])&& LeftOn(P[j], P[i], P[in1]) );

}

bool Diagonal(int i, int j, int n, Polygon P)

{
	return InCone(i, j, n, P ) && Diagonalie(i, j, n, P );
}

int Read_Points(Polygon P, int argc, char *argv[], char out_fname[])

{
	int n = 0, i;
	int temp1, temp2;
	FILE *in_fp, *out_fp;

	if (argc != 2) {
		printf("Usage: monotone.exe data_file !\n");
		exit(-1);
	}

	/* open the file for reading */
	in_fp = fopen(argv[1], "r");

	/* open the file for writing */
	strcpy(out_fname, argv[1]);
	strcat(out_fname, ".wrl");
	out_fp = fopen(out_fname, "w");

	/* create the VRML file */
	fprintf(out_fp, "#VRML V1.0 ascii\n\n");
	fprintf(out_fp, "DEF BackgroundColor Info{ string \"1.0 1.0 1.0\" }\n\n");

	fprintf(out_fp, "Separator {\n");
	fprintf(out_fp, "\tMaterial {diffuseColor 1.0 0.0 0.0}\n");
	fprintf(out_fp, "\tCoordinate3 {\n");
	fprintf(out_fp, "\t\tpoint [\n");

	/* begin reading from file */
	while ((fscanf(in_fp, "%d %d", &temp1, &temp2) != EOF)) {
		Point p;
		p.X = temp1;
		p.Y = temp2;
		P.push_back(p);
		n++;
		fprintf(out_fp, "\t\t\t%d %d 0.0,\n", temp1, temp2);
	}

	printf("n = %3d vertices read\n", n);
	putchar('\n');

	fprintf(out_fp, "\t\t]\n\t}\n");

	/* print the points */
	fprintf(out_fp, "\tPointSet {\n");
	fprintf(out_fp, "\t\tstartIndex 0\n");
	fprintf(out_fp, "\t\tnumPoint %d\n", n - 1);
	fprintf(out_fp, "\t}\n");

	fprintf(out_fp, "\tMaterial {diffuseColor 0.0 1.0 0.0}\n");
	fprintf(out_fp, "\tIndexedLineSet {\n");
	fprintf(out_fp, "\t\tcoordIndex [\n");

	for (i = 0; i < n - 1; i++)
		fprintf(out_fp, "\t\t\t%d, %d, -1,\n", i, i+1);

	fprintf(out_fp, "\t\t\t%d, %d, -1,\n", n - 1, 0);

	fprintf(out_fp, "\t\t]\n\t}\n");
	fprintf(out_fp, "\tMaterial {diffuseColor 0.0 0.0 1.0}\n");
	fprintf(out_fp, "\tIndexedLineSet {\n");
	fprintf(out_fp, "\t\tcoordIndex [\n");

	fclose(in_fp);
	fclose(out_fp);

	return n;
}

//Ordenação burra
void Sort_Points(int n, Polygon P, Polygon SortedP, vector<int> labels) {
	int i, j, k, l;
	Point temp1, temp2;

	for (i = 0; i < n; i++) {
		VecOpVec2(SortedP[i], =, P[i]);
		labels[i] = i;
	}

	/* we are looking for the largest y value, with the smallest x value */
	/* labels[] keeps track of the original indices of the points */
	for (i = 0; i < n; i++) {
		VecOpVec2(temp1, =, SortedP[i]);
		l = k = i;

		for (j = i + 1; j < n; j++) {

			/* pick out the larger y value */
			if ((SortedP[j].Y > temp1.Y)) {
				VecOpVec2(temp2, =, temp1);
				l = k;
				VecOpVec2(temp1, =, SortedP[j]);
				k = j;
			}

			if (fabs(temp2.Y - temp1.Y) <= Epsilon) {
				if ((temp2.X < temp1.X)) {
					VecOpVec2(temp1, =, temp2);
					k = l;
				}
			}
		}

		VecOpVec2(SortedP[k], =, SortedP[i]);
		VecOpVec2(SortedP[i], =, temp1);

		l = labels[i];
		labels[i] = labels[k];
		labels[k] = l;

	}
}

bool Adjacente(int s1, int s2) {
	if (fabs(s1 - s2) ==1)
		return true;
	return false;
}
void MonotoneTriangulation(int n, Polygon P, char out_fname[], vector<int> labels) {
	vector<int> pilha;
	vector<Segment> D;

	pilha.push_back(0);
	pilha.push_back(1);

	for (int i=2; i<n; i++) {
		int ui = labels[i];
		int st = labels[pilha.back()];
		int indexSt = pilha.back();
		int s1 = labels[pilha.front()];
		//Caso (a): ui adjacente a st mas não a s1
		if (Adjacente(ui, st) && !Adjacente(ui, s1)) {
			while (pilha.size() > 1 && Area2( P[st],P[ui],P[labels[pilha[pilha.size()-2]]])< 0) {

				Segment s;
				s.p1 = P[ui];
				s.p2 = P[st];
				D.push_back(s);
				pilha.pop_back();
				st = labels[pilha.back()];
			}
			pilha.push_back(i);
		}
		//Caso (b): ui  adjacente a s1 mas não a st
		else if (Adjacente(ui, s1) && !Adjacente(ui, st)) {
			int aux = indexSt;
			while (pilha.size()>1) {
				Segment s;
				s.p1 = P[ui];
				s.p2 = P[st];
				D.push_back(s);
				pilha.pop_back();
				st = labels[pilha.back()];

			}
			pilha.pop_back();//desempilha s1
			pilha.push_back(aux);
			pilha.push_back(i);

		}
		//Caso (c): ui adjacente a s1 e a st: ui = un

		else if (Adjacente(ui, s1) && Adjacente(ui, st)) {
			pilha.pop_back();
			while (pilha.size()>2) {
				Segment s;
				st = labels[pilha.back()];
				s.p1 = P[ui];
				s.p2 = P[st];
				D.push_back(s);
				pilha.pop_back();

			}
		}

	}
	//Imprimir diagonais.

}

int main(int argc, char *argv[]) {
	int n;
	char out_fname[256];
	Polygon P, SortedP;

	n = Read_Points (P, argc, argv, out_fname );
	vector<int> labels(n);
	
	Sort_Points (n, P, SortedP, labels );

	MonotoneTriangulation (n, P, out_fname, labels);

	exit(-1);
}

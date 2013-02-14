#include <stdio.h>
#include <stdlib.h>
#include <vector>
#include <cstdlib>


#define DIM 2 /* dimensão do espaço */
#define XOR(a,b) ((a||b) &&!(a&&b))
/* tipo ponto inteiro */
typedef struct Point
{
        int X;
        int Y;
}Point;

/*
 *
 */
using namespace std;

int Area2(Point a,Point b,Point c)
{
    return  (a.X-c.X)*(b.Y-c.Y)-
            (a.Y-c.Y)*(b.X-c.X);
}

bool Left(Point a, Point b, Point c)
{
    return  (Area2(a, b, c) > 0);
}

bool LeftOn(Point a, Point b, Point c)
{
    return  (Area2(a, b, c) >= 0);
}

bool Collinear(Point a, Point b, Point c)
{
    return  (Area2(a, b, c) == 0);
}

bool IntersectProp(Point a,Point b,Point c,Point d)
{
    if(Collinear(a,b,c) || Collinear(a,b,d)|| Collinear(c,d,a) || Collinear(c,d,b))
    {
        return false;
    }

    return XOR(Left(a,b,c),Left(a,b,d)) && XOR(Left(c,d,a),Left(c,d,b));
}

bool Between(Point a,Point b,Point c)
{
    if(!Collinear(a,b,c))
    {
        return false;
    }
    if(a.X!= b.X)/*AB nao e vertical*/
    {
        return ( (a.X<=c.X && c.X<=b.X) || (b.X<=c.X && c.X <=a.X));
    }
    return ( (a.Y<=c.Y&& c.Y<=b.Y) || (b.Y<=c.Y && c.Y<=a.Y));
}

int Intersect(Point a,Point b,Point c,Point d)
{
    if(IntersectProp(a,b,c,d))
    {
        /* Somo as interseccoes*/
        return 2;
    }
    if(Between(a,b,c))
    {

        return 1;
    }else if(Between(a,b,d))
    {

        return 1;
    }else if(Between(c,d,a))
    {
        return 1;
    }else if(Between(c,d,b))
    {
        return 1;
    }

    return 0;
}

int main() {
        int numTests;
        int numLines;
        scanf("%d", &numTests);
        while(numTests > 0)
        {
                numTests--;
                scanf("%d",&numLines);
                std::vector<Point> pts;


                int segmentos = numLines;
                int k = numLines;
                while(k>0)
                {
                        k--;
                        Point a,b;
                        scanf("%d %d %d %d",&a.X,&a.Y,&b.X,&b.Y);
                        pts.push_back(a);
                        pts.push_back(b);
                        }

                for(int i=0;i < 2*numLines;i+=2)
                {
                        Point a = pts[i];
                        Point b = pts[i+1];
                        for(int j =i+2;j <2*numLines;j+=2)
                        {
                                Point c = pts[j];
                                Point d = pts[j+1];
                                segmentos+=Intersect(a,b,c,d);
                        }
                }
                printf("%d\n",segmentos);
                if(numTests > 0) printf("\n");

        }



    return 0;
}

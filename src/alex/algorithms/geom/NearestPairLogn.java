//package alex.algorithms.geom;
//
//public class NearestPairLogn {
//     static final int PONTOS = 10000;
//    double  X[] = new double[PONTOS];
//    double Y[] = new double[PONTOS];
//    int fY[] = new int[PONTOS];
//    boolean pontosIguais = false;
//    
//    void divide(final int px[],final int py[],final int px_esq[],final int py_esq[],
//            int &n_esq,int px_dir[], int py_dir[],int &n_dir,int n)
//{
//n_esq = n/2;
//n_dir = n - n_esq;
//int i;
//for(i=0; i < n_esq;i++)
//{
//    px_esq[i] = px[i];
//}
//for(int j=0; j < n_dir;j++,i++)
//{
//    px_dir[j] = px[i];
//}
//double XC = X[px[n_esq]];
//double YC = Y[px[n_esq]];
//int j =0, k=0;
//
//for (int i=0; i<n; i++)
//if (X[py[i]] < XC)
//{
//py_esq[j] = py[i];
//j++;
//}
//else if(X[py[i]] > XC)
//{
//py_dir[k] = py[i];
//k++;
//}else
//{
//    if(Y[py[i]] < YC)
//    {
//            py_esq[j] = py[i];
//            j++;
//    }else
//    {
//             py_dir[k] = py[i];
//            k++;
//    }
//}
//
//}
//long double distQuadratica(final int a, final int b)
//{
//double  deltaX = X[a] - X[b];
//double  deltaY = Y[a] - Y[b];
//
//return (deltaX*deltaX + deltaY*deltaY);
//}
//void combina(final int px[],final int py[],final int i_esq, final int j_esq, final int i_dir, final int j_dir,
//   final int n, int &ponto1, int &ponto2)
//{
//double distEsquerda = distQuadratica(i_esq,j_esq);
//double distDireita = distQuadratica(i_dir,j_dir);
//double delta;
//if(distEsquerda <= distDireita)
//{
//    delta = distEsquerda;
//    ponto1 = i_esq;
//    ponto2 = j_esq;
//}
//else
//{
//    delta = distDireita;
//    ponto1 = i_dir;
//    ponto2 = j_dir;
//}
//
//double XC = X[px[n/2]];
//int t=0;
//for(int i=0; i < n;i++)
//{
//    double dX =X[py[i]]-XC;
//dX = (dX < 0)? -dX: dX;
//    if(dX*dX <= delta)
//    {
//            fY[t]=py[i];
//            t++;
//    }
//}
//
//for (int i=0; i<t-1; i++)
//{
//    for (int j=i+1; j<=i+7 && j<t; j++)
//    {
//            long double d = distQuadratica(fY[i],fY[j]);
//            if(d < delta)
//            {
//                    delta= d;
//                    ponto1 = fY[i];
//                    ponto2 = fY[j];
//            }
//    }
//}
//}
//
//void parMaisProxRec (final int px[],final int py[],final int n, int &ponto1, int &ponto2)
//{
//if(n==2)
//{
//    ponto1 = px[0];
//    ponto2 = px[1];
//}else if(n==3)
//{
//    double p0p1 = distQuadratica(px[0], px[1]);
//    double p1p2 = distQuadratica(px[1],px[2]);
//    double p0p2 = distQuadratica(px[0],px[2]);
//    double minimo = min(p0p1,min(p1p2,p0p2));
//    if(minimo == p0p1)
//    {
//            ponto1 = px[0];
//            ponto2 = px[1];
//
//    }else if(minimo ==p1p2){
//            ponto1 = px[1];
//            ponto2 = px[2];
//    }else
//    {
//            ponto1 = px[0];
//            ponto2 = px[2];
//    }
//}else
//{
//    int px_esq[PONTOS], py_esq[PONTOS], n_esq;
//    int px_dir[PONTOS], py_dir[PONTOS], n_dir;
//    int i_esq, j_esq, i_dir, j_dir;
//
//    divide(px,py,px_esq,py_esq,n_esq,px_dir,py_dir,n_dir,n);
//
//    parMaisProxRec(px_esq,py_esq,n_esq,i_esq,j_esq);
//
//    parMaisProxRec(px_dir,py_dir,n_dir,i_dir,j_dir);
//
//    combina(px,py,i_esq, j_esq, i_dir,  j_dir, n, ponto1, ponto2);
//
//}
//
//}
//
//int ordenaX(const void *a, const void *b)
//{
//int *pa = (int *)a;
//int *pb = (int *)b;
//if(X[*pa] > X[*pb])
//{
//    return 1;
//}
//if(X[*pa] < X[*pb])
//{
//    return -1;
//}
//if(Y[*pa] > Y[*pb])
//{
//    return 1;
//}
//if(Y[*pa] < Y[*pb])
//{
//    return -1;
//}
//pontosIguais = true;
//return 0;
//
//}
//
//int ordenaY(const void *a, const void *b)
//{
//int *pa = (int *)a;
//int *pb = (int *)b;
//if(Y[*pa] > Y[*pb])
//{
//    return 1;
//}
//if(Y[*pa] < Y[*pb])
//{
//    return -1;
//}
//return 0;
//
//}
//
//    /**
//     * @param args
//     */
//    public static void main(final String[] args) {
//        // TODO Auto-generated method stub
//
//    }
//
// }

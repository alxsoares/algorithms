#include <queue> // for priority_queue
#include <string> // for string
#include <vector>
#include <cstdio>
#include <iostream>
#include <cstring>
#include <set>
#include <list> // list class library
#include <algorithm>
#define MAX_DEPTH 10
using namespace std;
/*vector<string> states; */
char board[6][6];
int sr[5][5];
int sx,sy;
int min_moves = -1;
int cut=1;
int goal[5][5]={
        1,1,1,1,1,
        0,1,1,1,1,
        0,0,-1,1,1,
        0,0,0,0,1,
        0,0,0,0,0,
};

/*string create_state(){
        string result ="";
        for(int i=0; i <5;i++){
                for(int j=0;j<5;j++){
                        if(sr[i][j] !=-1){
                                result.append(1,sr[i][j]+'0');
                        }
                        else{
                                result.append("-1");
                        }
                }
        }
        return result;
}
*/
bool check()
{
        int i,j;
        for(i=0;i<5;i++){
                for(j=0;j<5;j++){
                        if(sr[i][j]!=goal[i][j]) return false ;
                }
        }
        return true;
}

bool IDS(int steps,int px, int py, int pxx, int pyy){
        if(px < 0 || px > 4) return false ;
        if(py < 0 || py > 4) return false ;
        if(steps > MAX_DEPTH || steps > cut) return false ;
        sr[pxx][pyy] = sr[px][py];
        sr[px][py] = -1;
        /*string state_id = create_state();
        vector<string>::iterator nums_iter = find(states.begin(),states.end(),state_id);
        if (nums_iter != states.end()){
        //já visitado.
        sr[px][py] = sr[pxx][pyy];
        sr[pxx][pyy] = -1;
        return false;

        }*/
        if(check()){
                min_moves = steps;
                return true;
        }
        //states.push_back(state_id);
        //move 1
        if(IDS(steps+1,px+1,py+2,px,py)){
                return true ;
        }
        //move 2
        if(IDS(steps+1,px-1,py+2,px,py)){
                return true ;
        }
        //move 3
        if(IDS(steps+1,px+1,py-2,px,py)){
                return true ;
        }
        //move 4
        if(IDS(steps+1,px-1,py-2,px,py)){
                return true ;
        }
        //move 5
        if(IDS(steps+1,px+2,py+1,px,py)){
                return true ;
        }
        //move 6
        if(IDS(steps+1,px-2,py+1,px,py)){
                return true ;
        }
        //move 7
        if(IDS(steps+1,px+2,py-1,px,py)){
                return true ;
        }
        //move 8
        if(IDS(steps+1,px-2,py-1,px,py)){
                return true ;
        }
        sr[px][py] = sr[pxx][pyy];
        sr[pxx][pyy] = -1;
        //states.pop_back();
        return false;
}

int solve(){
        min_moves = -1;
        /*states.clear();*/


        for(cut=1;cut <=MAX_DEPTH;cut++){
                if(IDS(0,sx,sy,sx,sy)){
                        break;
                }
        }
        return min_moves;
}
int main()
{
        int tst;
        scanf("%d",&tst);
        while(tst--){
                for(int i=0;i<5;){
                        char line[6];
                        gets(line);
                        if(line[0]=='\0'){
                                continue;
                        }else{
                                strcpy(board[i++],line);
                        }
                }
                for(int i=0;i<5;i++){
                        for(int j=0;j<5;j++){
                                if(board[i][j]=='1') sr[i][j]=1;
                                else if(board[i][j]=='0') sr[i][j]=0;
                                else if(board[i][j]==' '){sr[i][j]=-1;sx=i;sy=j;}
                        }
                }
                int moves = solve();
                if(moves < 0){
                        printf("Unsolvable in less than 11 move(s).\n");
                }else{
                        printf("Solvable in %d move(s).\n",moves);
                }
        }
        return 0;
}

/*
미로 탈출 명령어 
1. 문제 재정의 및 추상화 
n x m 격자 미로. DP 알고리즘 사용해서 문제 풀이. 1부터 k까지 이동 거리 바꿔가며 DP 배열 초기화. 
dp[old][r][m] = (r,m) 위치까지 도달하는데 드는 직전 최적 경로 
dp[new][r][m] = (r,m) 위치까지 도달하는데 드는 최신 최적 경로 (old보다 이동 거리 1 증가)
점화식
dp[new][r][m]=max(dp[old][r-1][m]+"d",dp[old][r][m-1]+"r",...)

2. 풀이과정 상세히 적기
String이 아니라 StringBuilder를 쓰자. 자체 정의 class. (Comparable 구현)

3. 시간복잡도 계산
O((N*M)^2)
최대 연산 수 : 2500*2500*4=6250000*4=2500_0000=2500만 

4. 코드 작성
*/
import java.util.*;
import java.io.*;

class Solution {
    
    static class Board{
        
        int rowN, colN;
        String[][] oldS;
        String[][] newS;
        
        // 상하좌우
        final int[] driS=new int[]{-1,1,0,0};
        final int[] dciS=new int[]{0,0,-1,1};
        final char[] dirS=new char[]{'d','u','r','l'};
        
        Board(int rowN, int colN, int stRi, int stCi){
            this.rowN=rowN;
            this.colN=colN;
            oldS=new String[rowN][colN];
            newS=new String[rowN][colN];
            for(int ri=0;ri<rowN;ri++) {
                for(int ci=0;ci<colN;ci++){
                    oldS[ri][ci]="";
                    newS[ri][ci]="";
                }
            }
            if(stRi>=1) oldS[stRi-1][stCi]="u";
            if(stRi<=rowN-2) oldS[stRi+1][stCi]="d";
            if(stCi>=1) oldS[stRi][stCi-1]="l";
            if(stCi<=colN-2) oldS[stRi][stCi+1]="r";
        }
        
        void run(){
            for(int ri=0;ri<rowN;ri++){
                for(int ci=0;ci<colN;ci++){
                    
                    String min="";
                    
                    for(int i=0;i<4;i++){
                        int nri=ri+driS[i], nci=ci+dciS[i];
                        if(nri<0||nri>=rowN||nci<0||nci>=colN)  continue;
                        
                        String old=oldS[nri][nci];
                        if(old.length()==0)  continue;
                        char dir=dirS[i];
                        if(min.length()==0){
                            min=old+String.valueOf(dir);
                            continue;
                        }
                        if(min.compareTo(old)>0){
                            min=old+String.valueOf(dir);
                            continue;
                        }
                    }
                    newS[ri][ci]=min;
                }
            }
            String [][] tmp=oldS;
            oldS=newS;
            newS=tmp;
            
        }
        
        void debug(){
            for(int ri=0;ri<rowN;ri++){
                for(int ci=0;ci<colN;ci++){
                    System.out.printf("| %s ",oldS[ri][ci]);
                }
                System.out.println("|");
            }
        }
    }
    
    public String solution(int rowN, int colN, int stRi, int stCi, int dstRi, int dstCi, int dist) {
        String answer = "";
        // Board(int rowN, int colN, int stRi, int stCi){
        Board board = new Board(rowN, colN, stRi-1,stCi-1);
        for(int i=2;i<=dist;i++){
            board.run();
        }
        // board.debug();
        answer=board.oldS[dstRi-1][dstCi-1];
        if(answer.length()==0)    answer="impossible";
        return answer;
    }
}
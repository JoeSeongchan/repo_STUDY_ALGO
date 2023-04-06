package 조성찬;
/*
활주로 건설
1. 문제 재정의 및 추상화 
N*N 절벽지대 활주로
가로-세로 방향으로 건설
높이가 동일한 구간에 활주로 건설 가능
높이가 다른 구간에는 경사로 건설. 경사로 길이 x, 높이 1
높이 차이가 1이고 낮은 지형이 경사로의 길이만큼 연속되는 곳에 설치 가능 
경사로의 길이 fixed? Yes. 입력으로 주어진다. 
경사로는 무한하게 주어진다. 활주로를 건설할 수 있는 모든 경우의 수를 구하여라.
가로-세로 방향으로 건설 가능하기에 그렇게 경우의 수가 많이 나오진 않을 것이다. 
333221은 낮은 지형(1)의 길이가 활주로의 길이보다 짧기 때문에 활주로 설치 불가!
하나의 행, 열이 활주로가 되려면 전체가 활주로 조건을 만족해야 한다. (그것이 문제 조건)
주의: 경사로는 세워서 사용할 수 없다. 
모든 행, 모든 열 검사해야 한다. -> 경우의 수: 2N
모든 행 안에서 높이 값을 조사한다. 높이 값과 그 높이값이 연속되는 개수를 구한다.
그리고 높이값이 연속되는 개수가 경사로의 길이보다 크거나 같은지 확인한다. 
더 작으면 활주로를 설치할 수 없다고 판단한다.
완전탐색!

2. 풀이과정 상세히 적기 
생략

3. 시간복잡도 계산
O(N*N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class B021_SWEA4014_활주로 건설{
    
    int width, slopeLen;
    int[][] map;
    int[] maxOfRowS;
    int[] maxOfColS;
    List<int[]>[] rowInfoS;
    List<int[]>[] colInfoS;
    
    int solution(int width, int slopeLen, int[][] map){
        int answer=0;
        init(width,slopeLen,map);
        answer=count();
        return answer;
    }
    
    void init(int width, int slopeLen, int[][]map){
        this.width=width;
        this.slopeLen=slopeLen;
        this.map=map;
        // maxOfRowS=new int[width];
        // maxOfColS=new int[width];
        // Arrays.fill(maxOfRowS,Integer.MIN_VALUE);
        // Arrays.fill(maxOfColS,Integer.MIN_VALUE);
        rowInfoS=new ArrayList[width];
        colInfoS=new ArrayList[width];
        for(int i=0;i<width;i++){
            rowInfoS[i]=new ArrayList<>();
            colInfoS[i]=new ArrayList<>();
        }
        
        for(int i=0;i<width;i++){
            int rowPrevVal=-1;
            int colPrevVal=-1;  
            for(int k=0;k<width;k++){
                int idx=i;
                int rowVal=map[idx][k];
                int colVal=map[k][idx];
                // maxOfRowS[idx]=Math.max(maxOfRowS[idx],rowVal);
                // maxOfColS[idx]=Math.max(maxOfColS[idx],colVal);
                if(rowPrevVal!=rowVal){
                    rowPrevVal=rowVal;
                    rowInfoS[idx].add(new int[]{rowVal,1});
                } else {
                    rowInfoS[idx].get(rowInfoS[idx].size()-1)[1]+=1;
                }
                if(colPrevVal!=colVal) {
                    colPrevVal=colVal;
                    colInfoS[idx].add(new int[]{colVal,1});
                } else {
                    colInfoS[idx].get(colInfoS[idx].size()-1)[1]+=1;
                }
            }
        }
    }
    
    int count(){
        int sum=0;
        for(int i=0;i<width;i++){
            List<int[]> rowInfo=rowInfoS[i];
            List<int[]> colInfo=colInfoS[i];
            // System.out.printf("%d번째 행: %s\n",i,listToString(rowInfo));
            boolean isRowOk=true;
            boolean isColOk=true;
            for(int k=0;k<rowInfo.size();k++){
                int[] cur=rowInfo.get(k);
                int blockCnt=0;
                if(k<rowInfo.size()-1){
                    int[] next=rowInfo.get(k+1);
                    if(next[0]>cur[0]+1){
                        isRowOk=false;
                        break;
                    }
                    if(next[0]>cur[0]){
                        blockCnt+=1;
                    }
                }
                if(k>0){
                    int[] prev=rowInfo.get(k-1);
                    if(prev[0]>cur[0]+1){
                        isRowOk=false;
                        break;
                    }
                    if(prev[0]>cur[0]){
                        blockCnt+=1;
                    }
                }
                if(cur[1]/slopeLen<blockCnt){
                    isRowOk=false;
                }
            }
            if(isRowOk){
                // System.out.printf("%d 행 OK\n",i);
                sum+=1;
            }
            // System.out.printf("%d번째 열: %s\n",i,listToString(colInfo));
            for(int k=0;k<colInfo.size();k++){
                int[] cur=colInfo.get(k);
                int blockCnt=0;
                if(k<colInfo.size()-1){
                    int[] next=colInfo.get(k+1);
                    if(next[0]>cur[0]+1){
                        isColOk=false;
                        break;
                    }
                    if(next[0]>cur[0]){
                        blockCnt+=1;
                    }
                    
                }
                if(k>0){
                    int[] prev=colInfo.get(k-1);
                    if(prev[0]>cur[0]+1){
                        isColOk=false;
                        break;
                    }
                    if(prev[0]>cur[0]){
                        blockCnt+=1;
                    }
                }
                if(cur[1]/slopeLen<blockCnt){
                    isColOk=false;
                }
            }
            if(isColOk){
                // System.out.printf("%d 열 OK\n",i);
                sum+=1;
            }
        }
        return sum;
    }
    
    String listToString(List<int[]> list){
        StringBuilder sb=new StringBuilder();
        for(int[] v: list){
            sb.append(Arrays.toString(v)).append(' ');
        }
        sb.append('\n');
        return sb.toString();
    }
    
    public static void main(String[] args) throws Exception{
        B021_SWEA4014_활주로 건설 T=new B021_SWEA4014_활주로 건설();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int tcN=Integer.parseInt(kb.readLine());
        StringBuilder sb=new StringBuilder();
        for(int tci=1;tci<=tcN;tci++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            int width = Integer.parseInt(stk.nextToken());
            int slopeLen = Integer.parseInt(stk.nextToken());
            int[][] map=new int[width][width];
            for(int ri=0;ri<width;ri++){
                stk=new StringTokenizer(kb.readLine());
                for(int ci=0;ci<width;ci++){
                    map[ri][ci]=Integer.parseInt(stk.nextToken());
                }
            }
            sb.append('#').append(tci).append(' ')
                .append(T.solution(width,slopeLen,map)).append('\n');
        }
        System.out.print(sb);
    }
}


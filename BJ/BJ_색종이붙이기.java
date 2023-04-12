/*
색종이 붙이기
1. 문제 재정의 및 추상화 
다섯종류의 색종이, 각 종류마다 최대 5장씩 붙일 수 있다. 
1이 적힌 칸을 만나면 다섯 종류의 색종이 모두를 Try해본다.
그런 다음, 해당 색종이를 사용하기로 마음 먹었으면 그 색종이로 종이를 채운다.
케이스 탐색이 끝나면 색종이를 다시 뗀다. 

2. 문제 풀이과정 상세히 적기
DFS, 완전탐색, 백트래킹 

3. 시간복잡도 계산
O(N*N): 색종이 붙일 때 필요한 시간
O(M!*N): 색종이 붙이는 모든 경우의 수 
... 매우 크다... 백트래킹을 잘하자!

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_색종이붙이기{
    int[][] map;
    final int width=10;
    int minUsedCnt;
    int oneCnt;
    
    // 작은 사각형부터 Try
    int solution(int[][] map){
        int answer=0;
        init(map);
        int[] rectNumS=new int[5+1];
        Arrays.fill(rectNumS,5);
        dfs(0,0,rectNumS,oneCnt,0);
        answer=minUsedCnt;
        if(answer==Integer.MAX_VALUE)   answer=-1;
        return answer;
    }
    
    void init(int[][] map){
        this.map=map;
        minUsedCnt=Integer.MAX_VALUE;
        oneCnt=0;
        for(int ri=0;ri<width;ri++){
            for(int ci=0;ci<width;ci++){
                if(map[ri][ci]==1)  oneCnt+=1;
            }
        }
    }
    
    void dfs(int ri, int ci, int[] rectNumS, int remCnt, int usedCnt){
        // System.out.printf("ri: %d, ci: %d, rectNumS: %s, remCnt: %d, usedCnt: %d\n",ri,ci,Arrays.toString(rectNumS),remCnt,usedCnt);
        if(usedCnt>=minUsedCnt){
            return;
        }
        if(remCnt==0){
            minUsedCnt=Math.min(minUsedCnt,usedCnt);
            return;
        }
        if(ci==width){
            dfs(ri+1,0,rectNumS,remCnt,usedCnt);
            return;
        }
        if(ri==width){
            return;
        }
        if(map[ri][ci]==0){
            dfs(ri,ci+1,rectNumS,remCnt,usedCnt);
            return;
        }
        for(int i=5;i>=1;i--){
            if(rectNumS[i]==0)  continue;
            if(!canFill(ri,ci,i))   continue;
            fill(ri,ci,i);
            rectNumS[i]-=1;
            dfs(ri,ci+1,rectNumS,remCnt-i*i,usedCnt+1);
            rectNumS[i]+=1;
            remove(ri,ci,i);
        }
    }
    
    boolean canFill(int ri, int ci, int rectWidth){
        if(ri+rectWidth>width || ci+rectWidth>width)  return false;
        for(int i=ri;i<ri+rectWidth;i++){
            for(int k=ci;k<ci+rectWidth;k++){
                if(map[i][k]==0)    return false;
            }
        }
        return true;
    }
    
    void fill(int ri,int ci,int rectWidth){
        for(int i=ri;i<ri+rectWidth;i++){
            for(int k=ci;k<ci+rectWidth;k++){
                map[i][k]=0;
            }
        }
    }
    
    void remove(int ri,int ci,int rectWidth){
        for(int i=ri;i<ri+rectWidth;i++){
            for(int k=ci;k<ci+rectWidth;k++){
                map[i][k]=1;
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_색종이붙이기 T=new BJ_색종이붙이기();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int width=10;
        int[][] map=new int[width][width];
        for(int ri=0;ri<width;ri++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            for(int ci=0;ci<width;ci++){
                map[ri][ci]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(map));
    }
}
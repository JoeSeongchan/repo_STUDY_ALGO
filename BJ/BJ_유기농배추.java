/*
유기농 배추
1. 문제 재정의 및 추상화 
전형적인 BFS 문제 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N*M)
N: 가로 길이
M: 세로 길이

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_유기농배추{
    int rowN,colN;
    int[][] map;
    
    int solution(int rowN, int colN, int bN, int[][] bS){
        int answer=0;
        init(rowN,colN,bN,bS);
        boolean[][] visited=new boolean[rowN][colN];
        Queue<int[]> tskS=new ArrayDeque<>();
        final int[] dri=new int[]{1,0,-1,0};
        final int[] dci=new int[]{0,1,0,-1};
        int cnt=0;
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                if(map[ri][ci]==0)  continue;
                if(visited[ri][ci])   continue;
                visited[ri][ci]=true;
                tskS.offer(new int[]{ri,ci});
                cnt+=1;
                while(!tskS.isEmpty()){
                    int[] p=tskS.poll();
                    int tri=p[0],tci=p[1];
                    for(int i=0;i<4;i++){
                        int nri=tri+dri[i];
                        int nci=tci+dci[i];
                        if(nri<0||nri>=rowN)    continue;
                        if(nci<0||nci>=colN)    continue;
                        if(map[nri][nci]==0)    continue;
                        if(visited[nri][nci])   continue;
                        visited[nri][nci]=true;
                        tskS.offer(new int[]{nri,nci});
                    }
                }
            }
        }
        answer=cnt;
        return answer;
    }
    
    void init(int rowN, int colN, int bN, int[][] bS){
        this.rowN=rowN;
        this.colN=colN;
        map=new int[rowN][colN];
        for(int[] b:bS){
            int ri=b[0],ci=b[1];
            map[ri][ci]=1;
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_유기농배추 T=new BJ_유기농배추();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int tcN=Integer.parseInt(kb.readLine());
        for(int tci=1;tci<=tcN;tci++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            int rowN=Integer.parseInt(stk.nextToken());
            int colN=Integer.parseInt(stk.nextToken());
            int bN=Integer.parseInt(stk.nextToken());
            int[][] bS=new int[bN][2];
            for(int i=0;i<bN;i++){
                stk=new StringTokenizer(kb.readLine());
                for(int k=0;k<2;k++){
                    bS[i][k]=Integer.parseInt(stk.nextToken());
                }
            }
            System.out.println(T.solution(rowN,colN,bN,bS));
        }
    }
}
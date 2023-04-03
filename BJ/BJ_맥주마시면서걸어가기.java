package 조성찬;
/*
맥주 마시면서 걸어가기
1. 문제 재정의 및 추상화 
경로 안 각 지점 사이의 거리가 1000m 이내여야 한다. 
그래야 목마르지 않고 목적지에 도착할 수 있다. 
다익스트라로 출발지 - 도착지 사이의 최단 경로를 구하면서 중간 부분 경로가 1000m 인 것은 계산에서 제외한다.

2. 풀이과정 상세히 적기
생략

** 다익스트라의 시간복잡도가 왜 O(VlogV) 또는 O(ElogE)인지 증명할 수 있어야 한다.
증명 과정을 보고 이해하며 외우자! (라이브코딩 면접 대비)

3. 시간복잡도 계산
O(T*ElogE)
T: 테스트케이스 개수
E: 간선 개수 = V^2

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class Solution{
    
    int cN;
    int stPointIdx;
    int edPointIdx;
    int[][] graph;
    
    String solution(int cN, int[][] pS){
        String answer="";
        init(cN,pS);
        if(dijkstra()==Integer.MAX_VALUE){
            answer="sad";
        } else{
            answer="happy";
        }
        return answer;
    }
    
    void init(int cN, int[][] pS){
        this.cN=cN;
        stPointIdx=0;
        edPointIdx=cN+1;
        graph=new int[cN+2][cN+2];
        for(int ri=0;ri<cN+2;ri++){
            for(int ci=0;ci<cN+2;ci++){
                if(ri==ci)  continue;
                graph[ri][ci]=Math.abs(pS[ri][0]-pS[ci][0])+Math.abs(pS[ri][1]-pS[ci][1]);
            }
        }
    }
    
    int dijkstra(){
        int[] distS=new int[cN+2];
        Arrays.fill(distS,Integer.MAX_VALUE);
        distS[stPointIdx]=0;
        
        PriorityQueue<int[]> tskS=new PriorityQueue<>((p1,p2)->Integer.compare(p1[1],p2[1]));
        tskS.offer(new int[]{stPointIdx,0});
        while(!tskS.isEmpty()){
            int[] p=tskS.poll();
            int tp=p[0];
            int dist=p[1];
            if(distS[tp]<dist)  continue;
            for(int i=0;i<cN+2;i++){
                if(tp==i)   continue;
                if(distS[tp]+graph[tp][i]>=distS[i])    continue;
                if(graph[tp][i]>1000) continue;
                distS[i]=distS[tp]+graph[tp][i];
                tskS.offer(new int[]{i,distS[i]});
            }
        }
        // System.out.println(Arrays.toString(distS));
        return distS[edPointIdx];
    }
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int tcN=Integer.parseInt(kb.readLine());
        StringBuilder sb=new StringBuilder();
        for(int tci=1;tci<=tcN;tci++){
            int cN=Integer.parseInt(kb.readLine());
            int[][] pS=new int[cN+2][2];
            for(int i=0;i<cN+2;i++){
                StringTokenizer stk=new StringTokenizer(kb.readLine());
                for(int k=0;k<2;k++){
                    pS[i][k]=Integer.parseInt(stk.nextToken());
                }
            }
            sb.append(T.solution(cN,pS)).append('\n');
        }
        System.out.print(sb);
    }
}
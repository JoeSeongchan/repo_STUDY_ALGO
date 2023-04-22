/*
바이러스
1. 문제 재정의 및 추상화 
BFS 문제.. 간선 리스트를 인접 정점 리스트로 바꾼다. 
그리고 문제를 푼다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(V+E)
V: 컴퓨터의 수
E: 컴퓨터가 네트워크 상에서 서로 연결되어 있는 정보의 수 (간선의 수)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_바이러스{
    int cN;
    List<Integer>[] adjS;
    
    int solution(int cN, int pN, int[][] pS){
        int answer=0;
        init(cN,pN,pS);
        Queue<Integer> tskS=new ArrayDeque<>();
        tskS.offer(1);
        int infectedCnt=0;
        boolean[] infected=new boolean[cN+1];
        infected[1]=true;
        while(!tskS.isEmpty()){
            int c=tskS.poll();
            infectedCnt+=1;
            for(int adj:adjS[c]){
                if(infected[adj])   continue;
                infected[adj]=true;
                tskS.offer(adj);
            }
        }
        answer=infectedCnt-1;
        return answer;
    }
    
    void init(int cN, int pN, int[][] pS){
        this.cN=cN;
        adjS=new ArrayList[cN+1];
        for(int i=1;i<=cN;i++){
            adjS[i]=new ArrayList<Integer>();
        }
        for(int[] p:pS){
            adjS[p[0]].add(p[1]);
            adjS[p[1]].add(p[0]);
        }
    }

    
    public static void main(String[] args) throws Exception{
        BJ_바이러스 T=new BJ_바이러스();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int cN=Integer.parseInt(kb.readLine());
        int pN=Integer.parseInt(kb.readLine());
        int[][] pS=new int[pN][2];
        for(int i=0;i<pN;i++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<2;k++){
                pS[i][k]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(cN,pN,pS));
    }
}
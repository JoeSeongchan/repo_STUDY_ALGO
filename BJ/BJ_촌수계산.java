/*
촌수계산
1. 문제 재정의 및 추상화 
BFS 문제이다. 무방향 그래프로 만든다. 어느 한 사람에서 시작하여 다른 사람으로 가기까지 거쳐야 하는 간선의 수를 구한다. 그 값을 리턴한다. (촌수 )

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N+M)

4. 코드 작성
*/
import java.util.*;
import java.io.*;
public class BJ_촌수계산{
    int pN,tg1,tg2;
    List<Integer>[] adjS;
    
    int solution(int pN, int tg1, int tg2, int rN, int[][] rS){
        int answer=0;
        init(pN,tg1,tg2,rN,rS);
        
        int[] distS=new int[pN+1];
        Arrays.fill(distS,-1);
        Queue<Integer> tskS=new ArrayDeque<>();
        tskS.offer(tg1);
        distS[tg1]=0;
        
        while(!tskS.isEmpty()){
            int p=tskS.poll();
            int dist=distS[p];
            for(int adj:adjS[p]){
                if(distS[adj]!=-1)  continue;
                distS[adj]=dist+1;
                tskS.offer(adj);
            }
        }
        
        answer=distS[tg2];
        return answer;
    }
    
    void init(int pN, int tg1, int tg2, int rN, int[][] rS){
        this.pN=pN;
        this.tg1=tg1;
        this.tg2=tg2;
        
        adjS=new ArrayList[pN+1];
        for(int i=1;i<=pN;i++){
            adjS[i]=new ArrayList<Integer>();
        }
        
        for(int[] r:rS){
            int v1=r[0],v2=r[1];
            adjS[v1].add(v2);
            adjS[v2].add(v1);
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_촌수계산 T=new BJ_촌수계산();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int pN=Integer.parseInt(kb.readLine());
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int tg1=Integer.parseInt(stk.nextToken());
        int tg2=Integer.parseInt(stk.nextToken());
        int rN=Integer.parseInt(kb.readLine());
        int[][] rS=new int[rN][2];
        for(int i=0;i<rN;i++){
            stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<2;k++){
                rS[i][k]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(pN,tg1,tg2,rN,rS));
    }
}
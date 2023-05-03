/*
회사 문화 1 
1. 문제 재정의 및 추상화 
트리를 구성한다. 칭찬 점수 배열에 칭찬 점수를 기록한다. 
그리고나서 루트노드에서 시작하여 트리를 BFS 순회한다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(V+E)
V: 직원의 수 
E: 상사, 부하 사이 관계의 수 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_회사문화1{
    
    int pN;
    int[] wInfoS;
    List<Integer>[] adjS;
    
    String solution(int pN, int wN, int[] rS, int[][] wS){
        String answer="";
        init(pN,wN,rS,wS);
        Queue<Integer> tskS=new ArrayDeque<>();
        tskS.offer(1);
        while(!tskS.isEmpty()){
            int p=tskS.poll();
            int w=wInfoS[p];
            for(int adj:adjS[p]){
                wInfoS[adj]+=w;
                tskS.offer(adj);
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=pN;i++){
            int w=wInfoS[i];
            sb.append(w).append('\n');
        }
        answer=sb.toString();
        return answer;
    }
    
    void init(int pN, int wN, int[] rS, int[][] wS){
        this.pN=pN;
        // 1~pN
        wInfoS=new int[pN+1];
        for(int[] w: wS){
            int p=w[0];
            int wGrade=w[1];
            wInfoS[p]+=wGrade;
        }
        // 1~pN
        adjS=new ArrayList[pN+1];
        for(int i=1;i<=pN;i++){
            adjS[i]=new ArrayList<Integer>();
        }
        for(int i=1;i<=pN;i++){
            int lowerIdx=i;
            int upperIdx=rS[i-1];
            if(upperIdx==-1)    continue;
            adjS[upperIdx].add(lowerIdx);
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_회사문화1 T=new BJ_회사문화1();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        
        int pN=Integer.parseInt(stk.nextToken());
        int wN=Integer.parseInt(stk.nextToken());
        int[] rS=new int[pN];
        
        stk=new StringTokenizer(kb.readLine());
        for(int i=0;i<pN;i++){
            rS[i]=Integer.parseInt(stk.nextToken());
        }
        
        int[][] wS=new int[wN][2];
        for(int i=0;i<wN;i++){
            stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<2;k++){
                wS[i][k]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(pN,wN,rS,wS));
    }
}
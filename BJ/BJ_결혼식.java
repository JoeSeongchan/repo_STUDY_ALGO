/*
결혼식
1. 문제 재정의 및 추상화 
친구와 친구의 친구를 초대한다. 최대 길이 2 
전형적인 BFS 문제이다. 너무 쉬운 문제이다.

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(n+m)
n: 동기의 수
m: 친구 관계의 수

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_결혼식{
    int pN;
    List<Integer>[] adjS;
    
    int solution(int pN, int rN, int[][] rS){
        int answer=0;
        init(pN,rN,rS);
        
        Queue<Integer> tskS=new ArrayDeque<>();
        tskS.offer(1);
        
        int[] distS=new int[pN+1];
        Arrays.fill(distS,-1);
        distS[1]=0;
        int cnt=0;
        
        while(!tskS.isEmpty()){
            int p=tskS.poll();
            int dist=distS[p];
            if(dist>=3) continue;
            cnt+=1;
            for(int adj: adjS[p]){
                if(distS[adj]!=-1)  continue;
                distS[adj]=dist+1;
                tskS.offer(adj);
            }
        }
        answer=cnt-1;
        return answer;
    }
    
    void init(int pN, int rN, int[][] rS){
        this.pN=pN;
        adjS=new ArrayList[pN+1];
        for(int i=1;i<=pN;i++){
            adjS[i]=new ArrayList<Integer>();
        }
        for(int[] r:rS){
            int p1=r[0],p2=r[1];
            adjS[p1].add(p2);
            adjS[p2].add(p1);
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_결혼식 T=new BJ_결혼식();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int pN=Integer.parseInt(kb.readLine());
        int rN=Integer.parseInt(kb.readLine());
        int[][] rS=new int[rN][2];
        for(int i=0;i<rN;i++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<2;k++){
                rS[i][k]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(pN,rN,rS));
    }
}
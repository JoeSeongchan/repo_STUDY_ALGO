/*
줄 세우기
1. 문제 재정의 및 추상화 
위상정렬. 순서에 맞게 정렬하고 싶음. 의존관계가 있음. 의존관계를 먼저 만족시켜야 다음 단계로 넘어갈 수 있음. 
진입 차수, 진출 차수를 구한다. A가 B보다 작을 때, B의 진입 차수가 1 증가하고, A의 진출 차수가 1 증가한다. 
진입 차수가 0인 정점은 가장 키가 작은 사람이다. 그 사람부터 해결한다. 전형적인 위상정렬 알고리즘을 
사용하는 문제이다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N+M) 
N: 학생의 수 
M: 두 학생의 키를 비교한 횟수

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_줄세우기{
    
    List<Integer>[] adjS;
    int[] inEdgeInfoS; // 1~pN
    Queue<Integer> zeroInEdgeS;
    int pN;
    
    String solution(int pN, int cN, int[][] cS){
        String answer="";
        init(pN,cN,cS);
        StringBuilder sb=new StringBuilder();
        while(!zeroInEdgeS.isEmpty()){
            int zeroInEdgeIdx=zeroInEdgeS.poll();
            sb.append(zeroInEdgeIdx).append(' ');
            for(int adj: adjS[zeroInEdgeIdx]){
                inEdgeInfoS[adj]-=1;
                if(inEdgeInfoS[adj]==0){
                    zeroInEdgeS.offer(adj);
                }
            }
        }
        answer=sb.toString();
        return answer;
    }
    
    void init(int pN, int cN, int[][] cS){
        this.pN=pN;
        adjS=new ArrayList[pN+1];
        for(int i=1;i<=pN;i++){
            adjS[i]=new ArrayList<Integer>();
        }
        inEdgeInfoS=new int[pN+1];
        zeroInEdgeS=new ArrayDeque<>();
        for(int[] c:cS){
            int p1=c[0], p2=c[1];
            adjS[p1].add(p2);
            inEdgeInfoS[p2]+=1;
        }
        for(int i=1;i<=pN;i++){
            if(inEdgeInfoS[i]==0){
                zeroInEdgeS.offer(i);
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_줄세우기 T=new BJ_줄세우기();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int pN=Integer.parseInt(stk.nextToken());
        int cN=Integer.parseInt(stk.nextToken());
        int[][] cS=new int[cN][2];
        for(int i=0;i<cN;i++){
            stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<2;k++){
                cS[i][k]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(pN,cN,cS));
    }
}
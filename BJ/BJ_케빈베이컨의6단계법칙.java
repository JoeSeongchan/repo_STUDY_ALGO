/*
케빈 베이컨의 6단계 법칙
1. 문제 재정의 및 추상화 
백준 유저 중에서 케빈 베이컨의 수가 가장 작은 사람을 찾는다.
케빈 베이컨의 수 = 모든 사람과 케빈 베이컨 게임을 했을 때 나오는 단계의 합
친구 관계는 중복되어 들어올 수 있다. 모든 사람은 친구관계로 연결되어져 있다. 사람의 번호는 1부터 N까지이다. 두 사람이 같은 번호를 갖는 경우는 없다.
케빈 베이컨의 수가 가장 작은 사람이 여럿일 경우 번호가 가장 작은 사람을 출력한다.

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O((N+M)*N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_케빈베이컨의6단계법칙{
    int pN;
    Set<Integer>[] adjS;
    
    int solution(int pN, int rN, int[][] rS){
        int answer=0;
        init(pN,rN,rS);
        
        int minDistSum=Integer.MAX_VALUE;
        int bakenPer=-1;
        Queue<Integer> tskS=new ArrayDeque<>();
        
        for(int stp=1;stp<=pN;stp++){
            tskS.offer(stp);
            int[] distS=new int[pN+1];
            Arrays.fill(distS,-1);
            distS[stp]=0;
            while(!tskS.isEmpty()){
                int p=tskS.poll();
                int dist=distS[p];
                for(int adj: adjS[p]){
                    if(distS[adj]!=-1)  continue;
                    distS[adj]=dist+1;
                    tskS.offer(adj);
                }
            }
            
            int distSum=0;
            for(int i=1;i<=pN;i++){
                distSum+=distS[i];
            }
            // System.out.printf("dist: %d, per: %d\n",distSum,stp);
            if(minDistSum>distSum){
                minDistSum=distSum;
                bakenPer=stp;
            }
        }
        answer=bakenPer;
        return answer;
    }
    
    void init(int pN,int rN,int[][] rS){
        this.pN=pN;
        adjS=new HashSet[pN+1];
        for(int i=1;i<=pN;i++){
            adjS[i]=new HashSet<Integer>();
        }
        for(int[] r: rS){
            int p1=r[0],p2=r[1];
            adjS[p1].add(p2);
            adjS[p2].add(p1);
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_케빈베이컨의6단계법칙 T=new BJ_케빈베이컨의6단계법칙();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int pN=Integer.parseInt(stk.nextToken());
        int rN=Integer.parseInt(stk.nextToken());
        int[][] rS=new int[rN][2];
        for(int i=0;i<rN;i++){
            stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<2;k++){
                rS[i][k]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(pN,rN,rS));
    }
}
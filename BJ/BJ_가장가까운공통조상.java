/*
가장 가까운 공통조상 
1. 문제 재정의 및 추상화 
간선 리스트로 문제를 풀 수 있을 것 같다. 정확히는 간선 리스트는 아니다.
간선 리스트를 {자식 노드: 부모 노드} map으로 변형한다. A, B의 가장 가까운 공통 조상을 구하고 싶으면
map을 통해 A의 루트 노드까지의 경로를 Stack에 저장한다. B도 마찬가지이다. 
A의 Stack과 B의 Stack 을 Top부터 하나씩 원소를 제거해나가며 같은 원소들을 계속 제거해나간다.
그러다가 Top에 있는 원소가 서로 다르면 마지막으로 제거한 원소 값을 답으로 삼는다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(logN)

4. 코드 작성
*/
import java.util.*;
import java.io.*;

public class BJ_가장가까운공통조상{
    
    Map<Integer, Integer> parentInfoS;
    int vN;
    int a,b;
    
    int solution(int vN, int eN, int[][] eS, int a, int b){
        int answer=0;
        init(vN,eN,eS,a,b);
        answer=find();    
        return answer;
    }
    
    void init(int vN, int eN, int[][] eS, int a, int b){
        this.vN=vN;
        parentInfoS=new HashMap<>();
        for(int[] e:eS){
            parentInfoS.put(e[1],e[0]);
        }
        this.a=a;
        this.b=b;
    }
    
    int find(){
        Stack<Integer> aSt=getParentS(a);
        Stack<Integer> bSt=getParentS(b);
        int lastSame=-1;
        while(!aSt.isEmpty() && !bSt.isEmpty()){
            int aTop=aSt.pop();
            int bTop=bSt.pop();
            if(aTop!=bTop){
                break;
            }
            lastSame=aTop;
        }
        return lastSame;
    }
    
    Stack<Integer> getParentS(int v){
        Stack<Integer> st=new Stack<>();
        int tmp=v;
        while(true){
            st.push(tmp);
            int parent=parentInfoS.getOrDefault(tmp,0);
            if(parent==0){
                break;
            }
            tmp=parent;
        }
        return st;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_가장가까운공통조상 T=new BJ_가장가까운공통조상();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int tcN=Integer.parseInt(kb.readLine());
        StringBuilder sb=new StringBuilder();
        for(int tci=1;tci<=tcN;tci++){
            int vN=Integer.parseInt(kb.readLine());
            int eN=vN-1;
            int[][] eS=new int[eN][2];
            for(int i=0;i<eN;i++){
                StringTokenizer stk=new StringTokenizer(kb.readLine()); 
                for(int k=0;k<2;k++){
                    eS[i][k]=Integer.parseInt(stk.nextToken());
                }
            }
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            int a=Integer.parseInt(stk.nextToken());
            int b=Integer.parseInt(stk.nextToken());
            sb.append(T.solution(vN,eN,eS,a,b)).append('\n');
        }
        System.out.println(sb);
    }
}
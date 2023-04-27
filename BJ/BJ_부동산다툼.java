/*
부동산 다툼 
1. 문제 재정의 및 추상화 
완전이진트리 문제. 배열을 타고 가면서 값이 들어있는지 확인

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(QlogN)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_부동산다툼{
    String solution(int landN, int duckN, int[] duckLandS){
        String answer="";
        // 100_0000 = 100만 
        // 1_000_000 = 1MB
        
        Stack<Integer> path=new Stack<>();
        boolean[] owned=new boolean[(1<<20)+1];
        StringBuilder sb=new StringBuilder();
        
        for(int i=0;i<duckN;i++){
            int duckLand=duckLandS[i];
            int tmp=duckLand;
            while(tmp!=1){
                path.push(tmp);
                tmp/=2;
            }
            // System.out.println(duckLand);
            // System.out.println(path);
            boolean passed=true;
            while(!path.isEmpty()){
                int v=path.pop();
                // System.out.println("  "+v);
                if(owned[v]){
                    // System.out.println("owned!");
                    passed=false;
                    sb.append(v);
                    path.clear();
                    break;
                }   
            }
            // System.out.println();
            if(passed){
                sb.append(0);
                owned[duckLand]=true;
            }
            sb.append('\n');
        }
        
        answer=sb.toString();
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_부동산다툼 T=new BJ_부동산다툼();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int landN=Integer.parseInt(stk.nextToken());
        int duckN=Integer.parseInt(stk.nextToken());
        int[] duckLandS=new int[duckN];
        for(int i=0;i<duckN;i++){
            duckLandS[i]=Integer.parseInt(kb.readLine());
        }
        System.out.println(T.solution(landN,duckN,duckLandS));
    }
}
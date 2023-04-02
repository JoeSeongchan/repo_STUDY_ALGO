package 조성찬;
/*
최장 증가 부분 수열 
1. 문제 재정의 및 추상화 
LIS 알고리즘으로 문제 해결 
시간복잡도: O(N^2)

2. 풀이과정 상세히 적기
생략 

3. 시간복잡도 계산
O(N^2)
N: 수열 길이

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class B013_SWEA3307_최장증가부분수열{
    int solution(int vN, int[] vS){
        int answer=0;
        int[] dp=new int[vN];
        dp[0]=1;
        for(int i=1;i<vN;i++){
            int v=vS[i];
            int max=0;
            for(int k=0;k<i;k++){
                if(vS[k]>v) continue; // 더 큰 값은 제외
                max=Math.max(max,dp[k]);
            }
            dp[i]=max+1;
        }
        for(int i=0;i<vN;i++){
            answer=Math.max(answer,dp[i]);   
        }
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        B013_SWEA3307_최장증가부분수열 T=new B013_SWEA3307_최장증가부분수열();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int tcN=Integer.parseInt(kb.readLine());
        StringBuilder sb=new StringBuilder();
        for(int tci=1;tci<=tcN;tci++){
            int vN=Integer.parseInt(kb.readLine());
            int[] vS=new int[vN];
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            for(int i=0;i<vN;i++){
                vS[i]=Integer.parseInt(stk.nextToken());
            }
            sb.append('#').append(tci).append(' ')
                .append(T.solution(vN,vS)).append('\n');
        }
        System.out.print(sb);
    }
}

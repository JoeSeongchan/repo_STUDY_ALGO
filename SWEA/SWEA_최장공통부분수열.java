/*
No. 13 최장 공통 부분 수열

1. 문제 재정의 및 추상화 
최대 공통 부분 수열의 길이를 구하여라. 
연속되지 않아도 된다. 
DP로 푼다. 
DP로 풀 때 거쳐야 하는 단계
    1. 테이블을 정의한다. 
    2. 테이블을 가지고 답을 어떻게 구할 것인지 생각한다. 
    3. 초기값을 어떻게 설정할 것인가? 
    4. 점화식을 찾는다. 

1. 테이블을 정의한다. 
    dp[i][j]: 첫번째 문자열의 [0~i], 두번째 문자열의 [0~j]의 최대 공통 부분 수열의 길이
2. 테이블을 가지고 어떻게 답을 구할 것인가?
    최대 공통 부분 수열의 길이: dp[첫번째 문자열의 길이][두번째 문자열의 길이]
3. 초기값을 어떻게 설정할 것인가?
    dp[0][0~두번째 문자열의 길이]=0
4. 점화식을 찾는다.
    if str1[i]==str2[j], dp[i][j]=dp[i-1][j-1]+1
    if str1[i]!=str2[j], dp[i][j]=max(dp[i-1][j],dp[i][j-1])

2. 풀이과정 상세히 적기
생략 

3. 시간복잡도 계산 
O(N^2)
N: 문자열의 길이

4. 코드 작성 
*/

import java.util.*;
import java.io.*;
public class Solution{
    int solution(String str1, String str2){
        int answer=0;
        int[][] dp=new int[str1.length()+1][str2.length()+1];
        for(int i=1;i<=str1.length();i++){
            for(int k=1;k<=str2.length();k++){
                if(str1.charAt(i-1)==str2.charAt(k-1)){
                    dp[i][k]=dp[i-1][k-1]+1;
                    continue;
                }
                dp[i][k]=Math.max(dp[i-1][k],dp[i][k-1]);
            }
        }
        // for(int i=1;i<=str1.length();i++){
        //     for(int k=1;k<=str2.length();k++){
        //         System.out.print(dp[i][k]+" ");
        //     }
        //     System.out.println();
        // }
        answer=dp[str1.length()][str2.length()];
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int tcN=Integer.parseInt(kb.readLine());
        StringBuilder sb=new StringBuilder();
        for(int tci=1;tci<=tcN;tci++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            sb.append('#').append(tci).append(' ')
            .append(T.solution(stk.nextToken(),stk.nextToken())).append('\n');
        }
        System.out.print(sb);
    }
}


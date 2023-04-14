/*
고냥이
1. 문제 재정의 및 추상화 
전형적인 두 포인터 문제. 두 포인터는 왼쪽 끝에서 시작한다. 
그리고 두 포인터 중 앞으로 오른쪽에 위치할 포인터를 차례대로 오른쪽으로 이동시킨다.
그리고 종류의 개수가 N을 넘어가지 않을 때까지 계속 이동시킨다. 그러다가 최대 종류의 개수가 N을 넘어가면 그 순간 맨 왼쪽 포인터를 오른쪽으로 이동시킨다. 언제까지? 종류의 개수가 N이 될 때까지! 오른쪽 포인터가 문자열의 오른쪽 끝에 다다를 때까지 위 작업을 반복한다. 매 순간 최대 길이를 갱신한다. (종류의 개수가 N을 넘어가는 경우 제외)
2. 풀이과정 상세히 적기
생략 

3. 시간복잡도 계산 
O(N)
4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_고냥이{
    int solution(int maxTypeN, char[] cS){
        int answer=0;
        int maxLen=Integer.MIN_VALUE;
        Map<Character, Integer> charInfoS=new HashMap<>();
        int lp=0,rp=0;
        while(rp<cS.length){
            charInfoS.putIfAbsent(cS[rp],0);
            charInfoS.put(cS[rp],charInfoS.get(cS[rp])+1);
            // 종류의 개수 <= N 만족할 때까지 계속해서 왼쪽 포인터 오른쪽으로 이동
            while(charInfoS.keySet().size()>maxTypeN){
                charInfoS.put(cS[lp],charInfoS.get(cS[lp])-1);
                if(charInfoS.get(cS[lp])==0){
                    charInfoS.remove(cS[lp]);
                }
                lp+=1;
            }
            int len=rp-lp+1;
            maxLen=Math.max(maxLen,len);
            rp+=1;
        }
        answer=maxLen;
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_고냥이 T=new BJ_고냥이();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int maxTypeN=Integer.parseInt(kb.readLine());
        char[] cS=kb.readLine().toCharArray();
        System.out.println(T.solution(maxTypeN,cS));
    }
}
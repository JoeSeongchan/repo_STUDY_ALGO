/*
귀여운 라이언
1. 문제 재정의 및 추상화
라이언이 최소 K개 이상이 있는 연속된 인형들의 집합 중 가장 작은 집합의 크기를 구하여라.
전형적인 두 포인터 문제.

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_귀여운라이언{
    
    int solution(int dollN, int necLionN, int[] dollS){
        int answer=Integer.MAX_VALUE;
        
        int lp=0,rp=0;
        int lionCnt=0;
        if(dollS[rp]==1){
            lionCnt+=1;
        }
        while(rp<dollN){
            // System.out.printf("lp: %d, rp: %d, lionCnt: %d\n",lp,rp,lionCnt);
            if(lionCnt<necLionN){
                rp+=1;
                if(rp>=dollN)   break;
                if(dollS[rp]==1){
                    lionCnt+=1;
                }
            } else if(lionCnt==necLionN){
                answer=Math.min(answer,rp-lp+1);
                if(dollS[lp]==1){
                    lionCnt-=1;
                }
                lp+=1;
            } else if(lionCnt>necLionN){
                if(dollS[lp]==1){
                    lionCnt-=1;
                }
                lp+=1;
            }
        }
        if(answer==Integer.MAX_VALUE){
            answer=-1;
        }
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_귀여운라이언 T=new BJ_귀여운라이언();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int dollN=Integer.parseInt(stk.nextToken());
        int necLionN=Integer.parseInt(stk.nextToken());
        stk=new StringTokenizer(kb.readLine());
        int[] dollS=new int[dollN];
        for(int i=0;i<dollN;i++){
            dollS[i]=Integer.parseInt(stk.nextToken());
        }
        System.out.println(T.solution(dollN,necLionN,dollS));
    }
}
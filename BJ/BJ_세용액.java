/*
세 용액 
1. 문제 재정의 및 추상화 
세 용액을 합친다. 두 값을 합쳤던 이전 유형들과는 다르다. 이런 문제를 어떻게 풀 수 있는가? 두 개의 포인터가 아니라 세 개의 포인터를 둔다? Yes!
용액을 오름차순으로 정렬한다.
용액을 하나 고른다. 그리고 나머지 두 용액이 있다. 나머지 두 용액을 차례대로 두 포인터를 통해서 세 용액을 합했을 때 0에 가까운 값이 나오도록 해야 한다. 
왼쪽 포인터, 오른쪽 포인터. 두 포인터가 가리키는 값의 합 + 다른 하나의 포인터가 가리키는 값 = 총합 S
S>0 -> 오른쪽 포인터 왼쪽으로
S<=0 -> 왼쪽 포인터 오른쪽으로
** 오답노트: int값끼리 더하면 결과값도 int이다. 
(long) vS[lp]+vS[mp]+vS[rp] 같은 방식으로 코드를 적어야지
결과값이 long이 나온다. 전체 범위가 -10억~10억이기 때문에
총합이 21억을 넘어가는 경우도 생각해야 한다! 
2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N^2)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_세용액{
    String solution(int vN, int[] vS){
        
        String answer="";
        int ansLp=-1,ansRp=-1,ansMp=-1;
        Arrays.sort(vS);
        long minAbsSum=Long.MAX_VALUE;
        for(int mp=0;mp<vN;mp++){
            int lp=0,rp=vN-1;
            while(lp<rp){
                if(lp==mp){
                    lp+=1;
                    continue;
                } else if(rp==mp){
                    rp-=1;
                    continue;
                }
                
                long sum=(long)vS[mp]+vS[lp]+vS[rp];
                // System.out.println(sum);
                if(Math.abs(sum)<minAbsSum){
                    ansLp=lp;
                    ansRp=rp;
                    ansMp=mp;
                    minAbsSum=Math.abs(sum);
                }
                if(sum<0){
                    lp+=1;
                } else {
                    rp-=1;
                }
            }
        }
        int[] ans=new int[]{vS[ansLp],vS[ansMp],vS[ansRp]};
        Arrays.sort(ans);
        StringBuilder sb=new StringBuilder();
        for(int v: ans){
            sb.append(v).append(' ');
        }
        answer=sb.toString();
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_세용액 T=new BJ_세용액();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int vN=Integer.parseInt(kb.readLine());
        int[] vS=new int[vN];
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        for(int i=0;i<vN;i++){
            vS[i]=Integer.parseInt(stk.nextToken());
        }
        System.out.println(T.solution(vN,vS));
    }
}
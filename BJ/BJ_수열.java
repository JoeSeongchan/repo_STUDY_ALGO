/*
수열 
1. 문제 재정의 및 추상화 
연속적인 며칠 동안의 온도의 합이 가장 큰 값을 계산하여라
전형적인 두 포인터 문제. 아주 쉽다.

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_수열{
    int solution(int vN, int contN, int[] vS){
        int answer=0;
        int lp=0,rp=0+contN-1;
        int sum=0;
        for(int i=lp;i<=rp;i++){
            sum+=vS[i];
        }
        int bgSum=sum;
        while(rp<vN-1){
            sum-=vS[lp];
            lp+=1;
            rp+=1;
            sum+=vS[rp];
            bgSum=Math.max(bgSum,sum);
        }
        answer=bgSum;
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_수열 T=new BJ_수열();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int vN=Integer.parseInt(stk.nextToken());
        int contN=Integer.parseInt(stk.nextToken());
        int[] vS=new int[vN];
        stk=new StringTokenizer(kb.readLine());
        for(int i=0;i<vN;i++){
            vS[i]=Integer.parseInt(stk.nextToken());
        }
        System.out.println(T.solution(vN,contN,vS));
    }
}
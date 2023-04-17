/*
수 고르기 
1. 문제 재정의 및 추상화 
전형적인 두 포인터 문제이다. 왼쪽 포인터와 오른쪽 포인터를 둔다. 
왼쪽 포인터가 가리키는 수를 lv라 하고 오른쪽 포인터가 가리키는 수를 rv라 하자. lv와 rv의 차이값이 M 이상일 때 왼쪽 포인터를 이동한다. M 미만일 때 오른쪽 포인터를 이동한다. 오른쪽 포인터가 범위를 넘어서는 순간 위 반복 작업을 멈춘다. 
2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_수고르기{
    int solution(int vN, int minDiff, int[] vS){
        int answer=Integer.MAX_VALUE;
        int lp=0,rp=0;
        Arrays.sort(vS);
        while(rp<vN && lp<vN){
            // System.out.printf("lp: %d, rp: %d\n",lp,rp);
            int lv=vS[lp];
            int rv=vS[rp];
            int diff=rv-lv;
            if(diff<minDiff){
                rp+=1;
            } else{
                answer=Math.min(answer,diff);
                lp+=1;
            }
        }
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_수고르기 T=new BJ_수고르기();
       // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int vN=Integer.parseInt(stk.nextToken());
        int minDiff=Integer.parseInt(stk.nextToken());
        int[] vS=new int[vN];
        for(int i=0;i<vN;i++){
            vS[i]=Integer.parseInt(kb.readLine());
        }
        System.out.println(T.solution(vN,minDiff,vS));
    }
}
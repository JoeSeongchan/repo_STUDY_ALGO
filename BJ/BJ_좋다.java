/*
좋다
1. 문제 재정의 및 추상화
두 포인터: 정렬된 리스트를 두 포인터가 순차 탐색하면서 두 포인터 구간의 합이 타겟 값과 같을 때까지 두 포인터를 조작하는 알고리즘 
리스트(배열)을 정렬한다. 그리고 두 포인터를 왼쪽 끝, 오른쪽 끝에 둔다. 두 포인터가 가리키는 값의 합이 타겟값보다 크면 오른쪽 포인터를 왼쪽으로 옮기고 합이 타겟값보다 작으면 왼쪽 포인터를 오른쪽으로 옮긴다. 합==타겟값인 경우 바로 break한다. 이 작업을 리스트 안 모든 값에 대해서 반복하여 수행한다.
** 오답노트
자기 자신이 두 수에 포함되면 안 된다. 문제가 애매해서 실수했었다. 
2. 풀이과정 상세히 적기
생략
3. 시간복잡도 계산
O(N^2)
4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_좋다{
    int solution(int vN, int[] vS){
        int answer=0;
        Arrays.sort(vS);
        for(int i=0;i<vN;i++){
            int targetValue=vS[i];
            int lp=0, rp=vN-1;
            while(lp!=rp){
                if(lp==i){
                    lp+=1;
                    continue;
                } else if(rp==i){
                    rp-=1;
                    continue;
                }
                int sum=vS[lp]+vS[rp];
                if(sum==targetValue){
                    answer+=1;
                    break;
                } else if(sum<targetValue){
                    lp+=1;
                } else{
                    rp-=1;
                }
            }
        }
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_좋다 T=new BJ_좋다();
        System.setIn(new FileInputStream("input.txt"));
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
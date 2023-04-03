/*
List of Unique Numbers
1. 문제 재정의 및 추상화 

2. 풀이과정 상세히 적기

1 2 3 4 5 6 4

1 2 3 2 4
1 1
1 2
1 3 => 3+2+1=6
3 2 
3 4 => 2+1=3 => 3-1=2

중복된 것 발견할 때까지 end 뒤로 1보 진보
중복된 것 발견하면 st=중복된 원소가 처음 나온 위치의 다음 위치

** 오답노트
내 힘으로 푼 것이 아니다! 시간이 지난 후에 다시 풀어보자!
출처: https://velog.io/@pppp0722/%EB%B0%B1%EC%A4%80-%EA%B3%A8%EB%93%9C4-13144-List-of-Unique-Numbers-Java
3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class Solution{
    long solution(int vN, int[] vS){
        long answer=0;
        int[] ct=new int[100_001]; // 수열 안에서 [index]의 개수
        int l=0; // 왼쪽 끝 index (left index)
        int r=-1;  // 오른쪽 끝 index (right index)
        while(l<vN){ // 왼쪽 끝 index가 오른쪽 끝에 다다를 때까지 반복 
            while(r+1<vN && ct[vS[r+1]]==0){ // 다음 값이 중복되지 않는 경우,
                r+=1; // right index를 다음 칸으로 이동 
                ct[vS[r]]++; // 오른쪽 끝 값 개수 1 증가
            }
            // r+1이 vN이거나 r+1 위치의 값이 중복된 값인 경우,
            answer+=(r-l+1); // l~r 까지의 수열 개수 count
            ct[vS[l++]]--; // left index를 오른쪽으로 이동 (이동하면서 left index 위치의 값 개수를 1 감소시킴)
        }
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int vN=Integer.parseInt(kb.readLine());
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int[] vS=new int[vN];
        
        for(int i=0;i<vN;i++){
            vS[i]=Integer.parseInt(stk.nextToken());
        }
        System.out.println(T.solution(vN,vS));
    }
}

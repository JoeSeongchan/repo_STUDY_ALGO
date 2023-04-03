/*
두 용액
1. 문제 재정의 및 추상화 
두 포인터 알고리즘 사용
배열을 오름차순으로 정렬한다. 양쪽 끝에서 시작. 
왼쪽 끝은 가장 작은 값을 가리키고 오른쪽 끝은 가장 큰 값을 가리킨다. 
왼쪽 끝과 오른쪽 끝 값을 더한다. 합이 0보다 작으면(마이너스 값이면) 왼쪽 끝을 오른쪽으로 이동시킨다.
합이 0보다 크면 (플러스 값이면) 오른쪽 끝은 왼쪽으로 이동시킨다. 
왼쪽 끝과 오른쪽 끝이 만날 때 (같은 값을 가리킬 때)까지 위 작업을 반복한다.
위 작업을 수행하면서 양쪽 끝의 합의 0에 가장 가까운 값을 갱신한다.

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(N)
N: 배열의 길이

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class Main{
    int vN;
    int[] vS;
    int p1,p2;
    
    String solution(int vN, int[] vS){
        String answer="";
        init(vN,vS);  
        twoPointer();
        answer=p1+" "+p2;
        return answer;
    }
    
    void init(int vN, int[] vS){
        this.vN=vN;
        this.vS=vS;
        Arrays.sort(vS);
        p1=0;
        p2=0;
    }
    
    void twoPointer(){
        int stIdx=0;
        int edIdx=vS.length-1;
        int closestToZero=Integer.MAX_VALUE;
        // System.out.println(Arrays.toString(vS));
        while(stIdx<edIdx){
            int sum=vS[stIdx]+vS[edIdx];
            // System.out.printf("stIdx: %d, edIdx: %d, sum: %d\n",stIdx,edIdx,sum);
            if(Math.abs(closestToZero)>Math.abs(sum)){
                closestToZero=sum;
                p1=vS[stIdx];
                p2=vS[edIdx];
            }
            if(sum<0){
                stIdx+=1;
            } else{
                edIdx-=1;
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        Main T=new Main();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int vN=Integer.parseInt(kb.readLine());
        int[] vS=new int[vN];
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        for(int i=0;i<vN;i++){
            vS[i]=Integer.parseInt(stk.nextToken());
        }
        System.out.println(T.solution(vN, vS));
    }
}
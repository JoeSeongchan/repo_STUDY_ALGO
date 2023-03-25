/*
먹을 것인가 먹힐 것인가
1. 문제 재정의 및 추상화
B 집합에 대해서 전처리 작업한다.
    - B를 크기에 따라 오름차순으로 정렬한다.
    - 그런 다음 배열을 하나 만든다. 배열의 길이는 2만이다.
        arr[idx]=idx값보다 작은 원소의 개수 (B 안에서)
    A의 각 원소에 대해서 해당 원소보다 작은 B 원소의 개수를 구할 수 있다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(M+N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_먹을것인가먹힐것인가{
    int aN, bN;
    int[] aS, bS;
    int targetIdx;
    
    int solution(int aN, int bN, int[] aS, int[] bS){
        int answer=0;
        init(aN,bN,aS,bS);
        for(int a:aS){
            targetIdx=-1;
            bSearch(a,bS,0,bN-1);
            if(targetIdx==-1)   continue;
            answer+=(targetIdx+1);
        }
        return answer;
    }
    
    void init(int aN, int bN, int[] aS, int[] bS){
        this.aN=aN;
        this.bN=bN;
        this.aS=aS;
        this.bS=bS;
        Arrays.sort(aS);
        Arrays.sort(bS);
    }
    
    void bSearch(int target, int[] vS, int st, int ed){
        if(st>ed)   return;
        int mid=(st+ed)/2;
        if(vS[mid]<target){
            targetIdx=mid;
            bSearch(target,vS,mid+1,ed);
        } else {
            bSearch(target,vS,st,mid-1);
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_먹을것인가먹힐것인가 T=new BJ_먹을것인가먹힐것인가();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int tcN=Integer.parseInt(kb.readLine());
        StringBuilder sb=new StringBuilder();
        for(int tci=1;tci<=tcN;tci++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            int aN=Integer.parseInt(stk.nextToken());
            int bN=Integer.parseInt(stk.nextToken());
            int[] aS=new int[aN];
            int[] bS=new int[bN];
            stk=new StringTokenizer(kb.readLine());
            for(int i=0;i<aN;i++){
                aS[i]=Integer.parseInt(stk.nextToken());
            }
            stk=new StringTokenizer(kb.readLine());
            for(int i=0;i<bN;i++){
                bS[i]=Integer.parseInt(stk.nextToken());
            }
            sb.append(T.solution(aN,bN,aS,bS)).append('\n');
        }
        System.out.println(sb);
    }
}
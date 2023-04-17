/*
배열 합치기
1. 문제 재정의 및 추상화 
정렬되어 있는 두 배열 A, B를 합치고, 정렬하고, 출력하라.
사실 그냥 합친 다음에 바로 정렬해도 된다. 
시간복잡도: O(nlogn) 무난히 통과.
공간복잡도: n+m 100만+100만 = 2_000_000=2MB

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O((N+M)log(N+M))

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_배열합치기{
    String solution(int aN, int bN, int[] aS, int[] bS){
        String answer="";
        List<Integer> total=new ArrayList<>();
        for(int v: aS){
            total.add(v);
        }
        for(int v: bS){
            total.add(v);
        }
        Collections.sort(total);
        StringBuilder sb=new StringBuilder();
        for(int v: total){
            sb.append(v).append(' ');
        }
        answer=sb.toString();
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_배열합치기 T=new BJ_배열합치기();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
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
        System.out.println(T.solution(aN,bN,aS,bS));
    }
}
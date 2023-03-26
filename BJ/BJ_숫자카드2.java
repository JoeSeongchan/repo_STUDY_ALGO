/*
숫자 카드 2
1. 문제 재정의 및 추상화 
해시맵을 써서 숫자의 개수를 센다. 

2. 풀이과정 상세히 적기
생략 

3. 시간복잡도 계산
O(N+M)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_숫자카드2{
    int[] solution(int aN, int[] aS, int bN, int[] bS){
        int[] answer;
        Map<Integer,Integer> aInfoS=new HashMap<>();
        for(int a:aS){
            aInfoS.putIfAbsent(a,0);
            aInfoS.put(a,aInfoS.get(a)+1);
        }
        answer=new int[bN];
        for(int i=0;i<bN;i++){
            int b=bS[i];
            answer[i]=aInfoS.getOrDefault(b,0);
        }
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_숫자카드2 T=new BJ_숫자카드2();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int aN=Integer.parseInt(kb.readLine());
        int[] aS=new int[aN];
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        for(int i=0;i<aN;i++){
            aS[i]=Integer.parseInt(stk.nextToken());
        }
        int bN=Integer.parseInt(kb.readLine());
        int[] bS=new int[bN];
        stk=new StringTokenizer(kb.readLine());
        for(int i=0;i<bN;i++){
            bS[i]=Integer.parseInt(stk.nextToken());
        }
        int[] answer=T.solution(aN,aS,bN,bS);
        StringBuilder sb=new StringBuilder();
        for(int v: answer){
            sb.append(v).append(' ');
        }
        System.out.println(sb);
    }
}
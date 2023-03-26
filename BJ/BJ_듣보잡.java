/*
듣보잡
1. 문제 재정의 및 추상화
{듣} 교집합 {보}
HashSet 사용

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N+M)
N: {듣} 집합의 크기
M: {보} 집합의 크기

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_듣보잡{
    
    static class Result{
        int num;
        String[] strS;
        Result(int num,String[] strS){
            this.num=num;
            this.strS=strS;
        }
    }
    
    Result solution(int aN, int bN, String[] aS, String[] bS){
        Result answer;
        Set<String> aSet=new HashSet<>();
        for(String a: aS){
            aSet.add(a);
        }
        Set<String> bSet=new HashSet<>();
        for(String b: bS){
            bSet.add(b);
        }
        Set<String> rSet=new HashSet<>(aSet);
        rSet.retainAll(bSet);
        int num=rSet.size();
        String[] strS=new String[num];
        int idx=0;
        for(String r: rSet){
            strS[idx]=r;
            idx+=1;
        }
        Arrays.sort(strS);
        answer=new Result(num,strS);
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_듣보잡 T=new BJ_듣보잡();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int aN=Integer.parseInt(stk.nextToken());
        int bN=Integer.parseInt(stk.nextToken());
        String[] aS=new String[aN];
        for(int i=0;i<aN;i++){
            aS[i]=kb.readLine();
        }
        String[] bS=new String[bN];
        for(int i=0;i<bN;i++){
            bS[i]=kb.readLine();
        }
        Result r=T.solution(aN,bN,aS,bS);
        StringBuilder sb=new StringBuilder();
        sb.append(r.num).append('\n');
        for(String str: r.strS){
            sb.append(str).append('\n');
        }
        System.out.println(sb);
    }
}
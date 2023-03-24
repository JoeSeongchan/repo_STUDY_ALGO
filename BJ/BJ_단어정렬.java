/*
단어 정렬
1. 문제 재정의 및 추상화 
단순 정렬 문제! 중복된 원소는 제거한다는 조건이 추가되었다. 
길이가 짧은 것부터, 길이가 같으면 사전 순으로 정렬...
중복 제거는 정렬된 배열을 순회하면서 이전 값과 같으면 StringBuilder에 append하지 않는 식으로 수행한다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(NlogN)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_단어정렬{
    String solution(int strN, String[] strS){
        String answer="";
        Arrays.sort(strS,(s1,s2)->{
            if(s1.length()<s2.length()) return -1;
            if(s1.length()>s2.length()) return 1;
            return s1.compareTo(s2);
        });
        StringBuilder sb=new StringBuilder();
        sb.append(strS[0]).append('\n');
        String prev=strS[0];
        for(int i=1;i<strN;i++){
            if(prev.equals(strS[i])){
                continue;
            }
            sb.append(strS[i]).append('\n');
            prev=strS[i];
        }
        answer=sb.toString();
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_파일정리 T=new BJ_파일정리();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int strN=Integer.parseInt(kb.readLine());
        String[] strS=new String[strN];
        for(int i=0;i<strN;i++){
            strS[i]=kb.readLine();
        }
        System.out.println(T.solution(strN,strS));
    }
}
/*
파일 정리
1. 문제 재정의 및 추상화
확장자별로 파일 수를 센다. 확장자는 어떻게 알아낼 수 있는가? 
String의 split을 사용한다. (. 을 기준으로 자른다.)
해시맵에 (확장자, 개수)를 저장한다. 그리고 나서 Entry를 가져와서 그 Entry들을 정렬한다. 정렬하는 기준은 문제의 기준을 따른다. 
확장자 이름 - 파일의 개수를 콘솔에 출력한다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(NlogN)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_단어정렬{
    String solution(int fN, String[] fS){
        String answer="";
        Map<String, Integer> infoS=new HashMap<>();
        for(String f: fS){
            infoS.putIfAbsent(f,0);
            infoS.put(f,infoS.get(f)+1);
        }
        List<Map.Entry<String,Integer>> eS=new ArrayList<>(infoS.entrySet());
        Collections.sort(eS,(e1,e2)->{
            return e1.getKey().compareTo(e2.getKey());
        });
        StringBuilder sb=new StringBuilder();
        for(Map.Entry<String,Integer> e: eS){
            sb.append(e.getKey()).append(' ').append(e.getValue()).append('\n');
        }
        answer=sb.toString();
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_단어정렬 T=new BJ_단어정렬();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int fN=Integer.parseInt(kb.readLine());
        String[] fS=new String[fN];
        for(int i=0;i<fN;i++){
            StringTokenizer stk=new StringTokenizer(kb.readLine(),".");
            stk.nextToken();
            fS[i]=stk.nextToken();
        }
        System.out.println(T.solution(fN,fS));
    }
}

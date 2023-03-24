/*
수열 정렬
1. 문제 재정의 및 추상화 
받은 배열을 정렬한다. 그런 다음 배열의 원소에 대해서 HashMap을 만든다. HashMap의 key는 배열의 원소값이다. value는 원소가 나오는 index Queue이다. 
HashMap을 만들고 나서 원래 배열을 순회하면서 HashMap에 접근하여 index Queue에서 index를 하나 꺼내어 StringBuilder에 append한다. 

내가 만든 논리가 잘 적용되는가? 
2 3 1 => 1 2 0
1 2 3 => 1:0 2:1 3:2

2 1 3 1 => 2 0 3 1 
1 1 2 3 => 1:0,1 2:2 3:3
적용된다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(NlogN)

** 배운 점
    - Arrays.copyOf(원본배열,복사할 원소의 개수) -> 복제된 배열 리턴 
4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_수열정렬{
    int vN;
    int[] vS;
    Map<Integer,Queue<Integer>> infoS;
    int[] sortedS;
    
    String solution(int vN, int[] vS){
        String answer="";
        init(vN,vS);
        StringBuilder sb=new StringBuilder();
        for(int v:vS){
            sb.append(infoS.get(v).poll()).append(' ');
        }
        answer=sb.toString();
        return answer;
    }
    
    void init(int vN, int[] vS){
        this.vN=vN;
        this.vS=vS;
        infoS=new HashMap<>();
        sortedS=Arrays.copyOf(vS,vS.length);
        Arrays.sort(sortedS);
        for(int i=0;i<vN;i++){
            int v=sortedS[i];
            infoS.putIfAbsent(v,new ArrayDeque<>());
            infoS.get(v).offer(i);
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_수열정렬 T=new BJ_수열정렬();
        // System.setIn(new FileInputStream("input.txt"));
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
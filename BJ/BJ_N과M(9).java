/*
N과 M (9)
1. 문제 재정의 및 추상화 
N개의 자연수 중에서 M개를 고른 수열
수열을 만들어서 문자열로 만든다. 공백으로 구분
그런 다음 그 문자열을 Set에 넣는다. 
중복된 문자열은 알아서 걸러진다. 
    우선 배열을 정렬한다. 오름차순으로 정렬!
    그래야 중복되는 조합을 만들지 않을 수 있다. 
    
2. 풀이과정 상세히 적기
생략

3. 시간복잡도
O(2^N)

** 오답노트 
  ** StringBuilder에 숫자를 append한 다음 수열을 String으로 바꾸어 Set에 넣어보는 식으로 구현했다.
    그런데 StringBuilder에 숫자를 append한 다음에 다시 그것을 원상복구할 때 setLength(length()-2) 식으로 구현했다. 
    그런데 숫자가 항상 한 자리수는 아니다! 문제를 제대로 읽어보지 않고 풀었기 때문에 출력초과와 같은 오류가 났던 것이다. 
    문제를 제대로 읽어야 한다! 확실하게 제대로 풀었는데도 오류가 발생한다면 최대값, 최솟값으로 엣지 케이스 테스트케이스를 만들어서 돌려보자!
    그러면 내가 제대로 읽지 못한 부분이 드러난다!
4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class Solution{
    Set<String> rS;
    int vN, pickN;
    int[] vS;
    StringBuilder sb;
    
    String solution(int vN, int pickN, int[] vS){
        init(vN,pickN,vS);
        permutation(0,new boolean[vN], new int[pickN]);
        return sb.toString();
    }
    
    void init(int vN, int pickN, int[] vS){
        this.vN=vN;
        this.pickN=pickN;
        Arrays.sort(vS);
        this.vS=vS;
        rS=new HashSet<>();
        sb=new StringBuilder();
    }
    
    void permutation(int cnt, boolean[] isSelected, int[] selected){
        if(cnt>=pickN){
            StringBuilder tmp=new StringBuilder();
            for(int i=0;i<selected.length;i++){
                tmp.append(selected[i]).append(' ');
            }
            if(rS.contains(tmp.toString())) return;
            rS.add(tmp.toString());
            sb.append(tmp.toString());
            sb.append('\n');
            return;
        }
        for(int i=0;i<vN;i++){
            if(isSelected[i]) continue;
            isSelected[i]=true;
            selected[cnt]=vS[i];
            permutation(cnt+1,isSelected,selected);
            isSelected[i]=false;
        }
    }
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int vN=Integer.parseInt(stk.nextToken());
        int pickN=Integer.parseInt(stk.nextToken());
        int[] vS=new int[vN];
        stk=new StringTokenizer(kb.readLine());
        for(int i=0;i<vN;i++){
            vS[i]=Integer.parseInt(stk.nextToken());
        }
        System.out.println(T.solution(vN,pickN,vS));
    }
}
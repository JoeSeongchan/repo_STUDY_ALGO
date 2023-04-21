/*
물통 
1. 문제 재정의 및 추상화 
한 물통 안에 든 물을 다른 물통으로 옮긴다. 
상-공-트... 모든 경우의 수를 탐색한다. A,B,C 조합을 저장한다.
이미 확인한 조합은 더 이상 거기서 파생되는 케이스를 따지지 않는다. (생략)
백트래킹을 써야 하지 않을까? 시간복잡도는? ...

** 오답노트
    Objects.hash(...)를 써서 방문 확인하려 했는데 해시 충돌이 나서 원하는 답이 나오지 않았다.
    Objects.hash(...) 0~21억 범위의 값으로 모든 케이스를 다 유일식별할 수 없다.
    해시를 쓰려 하기 전에 배열로 방문 확인할 수 있는 방법이 있는지 확인하자!

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
생략

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_물통{
    
    int vN;
    int[] vS;
    int[] cS;
    boolean[][] used;
    Set<Integer> resultS;
    
    String solution(int vN, int[] vS){
        String answer="";
        init(vN,vS);
        dfs();
        List<Integer> resultList=new ArrayList<>(resultS);
        Collections.sort(resultList);
        StringBuilder sb=new StringBuilder();
        for(int result: resultList){
            sb.append(result).append(' ');
        }
        // System.out.println(Arrays.toString(cS));
        answer=sb.toString();
        return answer;
    }
    
    void init(int vN, int[] vS){
        this.vN=vN;
        this.vS=vS;
        cS=new int[vN];
        cS[2]=vS[2];
        used=new boolean[201][201];
        resultS=new HashSet<>();
    }
    
    void dfs(){
        if(used[cS[0]][cS[1]]){
            return;
        }
        used[cS[0]][cS[1]]=true;
        if(cS[0]==0){
            //   System.out.println(Arrays.toString(cS));
            resultS.add(cS[2]);
        }
        for(int i=0;i<3;i++){
            for(int k=0;k<3;k++){
                // i->k
                if(i==k)    continue;
                if(cS[i]==0 || cS[k]==vS[k])    continue;
                int[] backup=Arrays.copyOf(cS,3);
                if(vS[k]-cS[k]>=cS[i]){
                    cS[k]=cS[k]+cS[i];
                    cS[i]=0;
                    dfs();
                } else{
                    cS[i]-=(vS[k]-cS[k]);
                    cS[k]=vS[k];
                    dfs();
                }
                cS=backup;
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_물통 T=new BJ_물통();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int vN=3;
        int[] vS=new int[vN];
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        for(int i=0;i<vN;i++){
            vS[i]=Integer.parseInt(stk.nextToken());
        }
        System.out.println(T.solution(vN,vS));
    }
}
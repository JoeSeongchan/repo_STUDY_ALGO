/*
경로 찾기
1. 문제 재정의 및 추상화 
인접 행렬 -> 인접 정점 리스트. BFS 사용
각 정점에 대해서 BFS 돌림. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N^2)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_경로찾기{
    int vN;
    List<Integer>[] adjS;
    
    String solution(int vN, int[][] adjMatrix){
        String answer="";
        init(vN,adjMatrix);
        
        int[][] result=new int[vN][vN];
        Queue<Integer> tskS=new ArrayDeque<>();
        
        for(int stv=0;stv<vN;stv++){
            tskS.offer(stv);
            while(!tskS.isEmpty()){
                int edv=tskS.poll();
                for(int newEdv: adjS[edv]){
                    if(result[stv][newEdv]==1)  continue;
                    result[stv][newEdv]=1;
                    tskS.offer(newEdv);
                }
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int ri=0;ri<vN;ri++){
            for(int ci=0;ci<vN;ci++){
                sb.append(result[ri][ci]).append(' ');
            }
            sb.append('\n');
        }
        answer=sb.toString();
        return answer;
    }
    
    void init(int vN, int[][] adjMatrix){
        this.vN=vN;
        adjS=new ArrayList[vN];
        for(int i=0;i<vN;i++){
            adjS[i]=new ArrayList<Integer>();
        }
        for(int ri=0;ri<vN;ri++){
            for(int ci=0;ci<vN;ci++){
                if(adjMatrix[ri][ci]==0)    continue;
                adjS[ri].add(ci);
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_경로찾기 T=new BJ_경로찾기();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int vN=Integer.parseInt(kb.readLine());
        int[][] adjMatrix=new int[vN][vN];
        for(int ri=0;ri<vN;ri++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            for(int ci=0;ci<vN;ci++){
                adjMatrix[ri][ci]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(vN,adjMatrix));
    }
}
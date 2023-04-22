/*
섬의 개수
1. 문제 재정의 및 추상화 
전형적인 BFS 문제

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(W*H)
W: 너비
H: 높이

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_섬의개수{
    int solution(int rowN, int colN, int[][] map){
        int answer=0;
        Queue<int[]> tskS=new ArrayDeque<>();
        boolean[][] visited=new boolean[rowN][colN];
        final int[] dri=new int[]{1,0,-1,0,1,1,-1,-1};
        final int[] dci=new int[]{0,1,0,-1,1,-1,1,-1};
        int cnt=0;
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                if(map[ri][ci]==0)  continue;
                if(visited[ri][ci]) continue;
                visited[ri][ci]=true;
                tskS.offer(new int[]{ri,ci});
                while(!tskS.isEmpty()){
                    int[] p=tskS.poll();
                    int tri=p[0],tci=p[1];
                    for(int i=0;i<8;i++){
                        int nri=tri+dri[i];
                        int nci=tci+dci[i];
                        if(nri<0||nri>=rowN)    continue;
                        if(nci<0||nci>=colN)    continue;
                        if(map[nri][nci]==0)    continue;
                        if(visited[nri][nci])   continue;
                        visited[nri][nci]=true;
                        tskS.offer(new int[]{nri,nci});
                    }
                }
                cnt+=1;
            }
        }
        answer=cnt;
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_섬의개수 T=new BJ_섬의개수();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            int colN=Integer.parseInt(stk.nextToken());
            int rowN=Integer.parseInt(stk.nextToken());
            if(rowN==0 && colN==0){
                return;
            }
            int[][] map=new int[rowN][colN];
            for(int ri=0;ri<rowN;ri++){
                stk=new StringTokenizer(kb.readLine());
                for(int ci=0;ci<colN;ci++){
                    map[ri][ci]=Integer.parseInt(stk.nextToken());
                }
            }
            System.out.println(T.solution(rowN,colN,map));
        }
    }
}
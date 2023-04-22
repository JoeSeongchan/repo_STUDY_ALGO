/*
양
1. 문제 재정의 및 추상화 
전형적인 BFS 문제

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(W*H)
W: 열의 수
H: 행의 수

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_양{
    String solution(int rowN, int colN, char[][] map){
        String answer="";
        final int[] dri=new int[]{1,0,-1,0};
        final int[] dci=new int[]{0,1,0,-1};
        
        Queue<int[]> tskS=new ArrayDeque<>();
        boolean[][] visited=new boolean[rowN][colN];

        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                if(visited[ri][ci]) continue;
                if(map[ri][ci]=='#' || map[ri][ci]=='.') continue;
                if(map[ri][ci]=='v'){
                    int wolfCnt=1;
                    int sheepCnt=0;
                    tskS.offer(new int[]{ri,ci});
                    visited[ri][ci]=true;
                    List<int[]> sheepS=new ArrayList<>();
                    List<int[]> wolfS=new ArrayList<>();
                    wolfS.add(new int[]{ri,ci});
                    while(!tskS.isEmpty()){
                        int[] p=tskS.poll();
                        int tri=p[0],tci=p[1];
                        for(int i=0;i<4;i++){
                            int nri=tri+dri[i];
                            int nci=tci+dci[i];
                            if(nri<0||nri>=rowN)    continue;
                            if(nci<0||nci>=colN)    continue;
                            if(map[nri][nci]=='#')  continue;
                            if(visited[nri][nci])   continue;
                            if(map[nri][nci]=='o'){
                                sheepCnt+=1;
                                sheepS.add(new int[]{nri,nci});
                            }
                            if(map[nri][nci]=='v'){
                                wolfCnt+=1;
                                wolfS.add(new int[]{nri,nci});
                            }
                            visited[nri][nci]=true;
                            tskS.offer(new int[]{nri,nci});
                        }
                    }
                    // System.out.printf("sheep: %d, wolf: %d\n",sheepCnt,wolfCnt);
                    if(sheepCnt<=wolfCnt){
                        for(int[] sheep:sheepS){
                            int sri=sheep[0],sci=sheep[1];
                            map[sri][sci]='.';
                        }
                    } else{
                        for(int[] wolf: wolfS){
                            int wri=wolf[0],wci=wolf[1];
                            map[wri][wci]='.';
                        }
                    }
                }
            }
        }
        int remWolfCnt=0,remSheepCnt=0;
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                if(map[ri][ci]=='.')    continue;
                if(map[ri][ci]=='v')    remWolfCnt+=1;
                if(map[ri][ci]=='o')    remSheepCnt+=1;
            }
        }
        answer=remSheepCnt+" "+remWolfCnt;
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_양 T=new BJ_양();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int rowN=Integer.parseInt(stk.nextToken());
        int colN=Integer.parseInt(stk.nextToken());
        char[][] map=new char[rowN][colN];
        for(int ri=0;ri<rowN;ri++){
            char[] ol=kb.readLine().toCharArray();
            for(int ci=0;ci<colN;ci++){
                map[ri][ci]=ol[ci];
            }
        }
        System.out.println(T.solution(rowN,colN,map));
    }
}
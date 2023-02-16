/*
1861. 정사각형 방
 
1. 문제 재정의 및 추상화 
N*N 방 배열. 방 값 주어진다. 상하좌우로 이동 가능. 단 값이 1만큼 커야 함. 
BFS를 사용한다. 최대 이동 값을 구한다. 
 
2. 풀이과정 상세히 적기 
생략 
 
3. 시간 복잡도 계산 
O(N^2)
N: 최대 1000
100_0000 능히 시간 안에 풀 수 있다. 
 
4. 코드 작성 
*/
 
import java.util.*;
import java.io.*;
public class Solution{
    static class Elem{
        int ri;
        int ci;
        Elem(int ri, int ci){
            this.ri=ri;
            this.ci=ci;
        }
    }
     
    int[] solution(int wd, int[][] map){
        int[] answer;
        Elem[] elemS=new Elem[wd*wd];
         
        for(int ri=0;ri<wd;ri++){
            for(int ci=0;ci<wd;ci++){
                int v=map[ri][ci];
                elemS[v-1]=new Elem(ri,ci);
            }
        }
         
        boolean[][] isVisited=new boolean[wd][wd];
        Queue<Elem> tskS=new ArrayDeque<>();
        int[] dri=new int[]{1,0,-1,0};
        int[] dci=new int[]{0,1,0,-1};
        int biggestCnt=Integer.MIN_VALUE;
        int biggestCntStartElemValue=-1;
        for(Elem startElem: elemS){
            if(isVisited[startElem.ri][startElem.ci])   continue;
            tskS.offer(startElem);
            int cnt=1;
            while(!tskS.isEmpty()){
                Elem elem=tskS.poll();
                for(int i=0;i<4;i++){
                    int nri=elem.ri+dri[i];
                    int nci=elem.ci+dci[i];
                    if(nri<0||nri>=wd)  continue;
                    if(nci<0||nci>=wd)  continue;
                    if(isVisited[nri][nci])    continue;
                    if(map[elem.ri][elem.ci]+1!=map[nri][nci])   continue;
                    isVisited[nri][nci]=true;
                    tskS.offer(new Elem(nri,nci));
                    cnt+=1;
                }
            }
            if(cnt>biggestCnt){
                biggestCnt=cnt;
                biggestCntStartElemValue=map[startElem.ri][startElem.ci];
            }
        }
        answer=new int[]{biggestCnt,biggestCntStartElemValue};
        return answer;
    }
     
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
         
        StringBuilder sb=new StringBuilder();
        int tcN=Integer.parseInt(kb.readLine());
         
        for(int tci=1;tci<=tcN;tci++){
            int wd=Integer.parseInt(kb.readLine());
            int[][] map=new int[wd][wd];
            for(int i=0;i<wd;i++){
                StringTokenizer stk=new StringTokenizer(kb.readLine());
                for(int k=0;k<wd;k++){
                    map[i][k]=Integer.parseInt(stk.nextToken());
                }
            }
            int[] answer= T.solution(wd,map);
            sb.append('#').append(tci).append(' ').append(answer[1]).append(' ').append(answer[0]).append('\n');
        }
        System.out.print(sb);
    }   
}

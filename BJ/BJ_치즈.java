/*
치즈
1. 문제 재정의 및 추상화
(0,0)에서 시작. (0,0)가 치즈에서 빈 칸으로 바뀐 날을 0일로 설정
---
큐 안에 담긴 모든 칸 정보를 꺼낸 다음 사방으로 이동하며 빈 칸 탐색
    빈 칸의 날짜를 큐 안에 담겼던 칸 정보의 날짜와 동일하게 설정 
    빈 칸을 탐색하면서 빈 칸과 맞닿은 치즈 위치를 리스트에 저장
빈 칸을 모두 다 찾았으면 이전에 리스트에 저장해둔 치즈를 빈 칸으로 바꾼다.
찾은 치즈를 빈 칸으로 만들면서 몇번째 날에 치즈를 삭제했는지 기록한다.
    치즈가 담겼던 칸의 날짜를 큐 안에 담겼던 칸 정보의 날짜보다 1 증가한 값으로 설정
이전에 치즈였지만 이번에 빈 칸으로 바뀐 칸 정보를 큐에 넣는다. 
---
모든 치즈가 사라질 때까지 반복 
    큐가 빌 때까지 반복
전형적인 BFS 문제

2. 풀이과정 상세히 적기
1번 단계에서 이미 상세히 적었음

3. 시간복잡도 계산
O(V*(V+E))
최적화 되어 있기 때문에 무리 없이 문제 해결할 수 있을 것이라고 생각

** 오답노트
당연하다고 여기는 생각 흐름에서 오답이 나온다.
    - 잘못된 이해의 예: "맨 마지막에 체크된 칸의 개수를 센다."
           올바른 이해 -> 맨 마지막에 체크된 '치즈'의 개수를 센다. 
            분명 다른 내용이다. 오답이 나오면 내가 '당연'하다고 여겼던 부분을 먼저 의심한다.
            엣지 케이스를 생각해본다. 위 케이스를 찾기 위해선 치즈가 하나도 없는 맵을 테스트케이스로 만들어 돌려보면 된다.

4. 코드 작성 
*/

import java.util.*;
import java.io.*;
public class Solution{
    int rowN,colN;
    String solution(int rowN, int colN, int[][] map){
        this.rowN=rowN;
        this.colN=colN;
        int[][] copied=new int[rowN][colN];
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                copied[ri][ci]=map[ri][ci];
            }
        }
        final int[] dri=new int[]{1,0,-1,0};
        final int[] dci=new int[]{0,1,0,-1};
        String answer="";
        int[][] dayS=new int[rowN][colN];
        for(int[] row:dayS){
            Arrays.fill(row,Integer.MAX_VALUE);
        }
        
        Queue<int[]> tskS=new ArrayDeque<>();
        tskS.offer(new int[]{0,0});
        dayS[0][0]=0;
        while(!tskS.isEmpty()){
            List<int[]> cheezeS=new ArrayList<>();
            // 새로운 빈 칸 찾기
            while(!tskS.isEmpty()){
                int[] p=tskS.poll();
                int ri=p[0];
                int ci=p[1];
                int day=dayS[ri][ci];
                // 사방 탐색
                for(int i=0;i<4;i++){
                    int nri=ri+dri[i];
                    int nci=ci+dci[i];
                    if(!isValid(nri,nci))  continue;
                    if(dayS[nri][nci]!=Integer.MAX_VALUE)   continue;
                    // 인접해 있는 치즈 기록!
                    if(map[nri][nci]==1){
                        map[nri][nci]=2;
                        cheezeS.add(new int[]{nri,nci});
                        dayS[nri][nci]=day+1;
                        continue;
                    }
                    // 이미 기록한 치즈는 skip!
                    if(map[nri][nci]==2)    continue;
                    dayS[nri][nci]=day;
                    tskS.offer(new int[]{nri,nci});
                }
            }
            for(int[] cheeze:cheezeS){
                // 이미 빈 칸으로 바꾼 치즈는 skip!
                if(map[cheeze[0]][cheeze[1]]==0){
                    continue;
                }
                map[cheeze[0]][cheeze[1]]=0;
                tskS.offer(cheeze); // 치즈를 빈 칸으로 바꾸면서 큐에 넣음
                // 이유: 치즈가 사라지면서 빈 공간이 새로 생기는 경우, 그 빈 공간을 찾아야 하기 때문
            }
        }
        int maxDay=0;
        int maxDayN=0;
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                if(copied[ri][ci]!=1)   continue;
                int day=dayS[ri][ci];
                if(day==maxDay){
                    maxDayN+=1;
                } else if(day>maxDay){
                    maxDay=day;
                    maxDayN=1;
                }
            }
        }
        answer=""+maxDay+"\n"+maxDayN;
        return answer;
    }
    
    boolean isValid(int ri,int ci){
        if(ri<0||ri>=rowN)  return false;
        if(ci<0||ci>=colN)  return false;
        return true;
    }
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int rowN=Integer.parseInt(stk.nextToken());
        int colN=Integer.parseInt(stk.nextToken());
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
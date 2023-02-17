/*
움직이는 미로 탈출 (BJ 16954)

1. 문제 재정의 및 추상화 
벽이 아래로 움직인다. 사람이 한 칸 움직이고 나서 벽이 아래로 한 칸 내려간다. 
사람은 출발점에서 도착점으로 이동할 수 있는가? 있으면 1을, 없으면 0을 리턴하라. 
벽이 밑으로 이동하면, 그 자리로 이동할 수 있다. 이동 후에 벽을 아래로 내린다. 

2. 풀이과정 상세히 적기 
1번 단계에서 이미 상세히 적었다. 생략 

3. 시간 복잡도 계산 
O((N*M)^2) 
N*M=8*8=64
64^2=3600

4. 코드 작성 
*/

import java.util.*;
import java.io.*;

public class Solution{
    
    static class Point{
        int ri,ci,moveCount;
        Point(int ri, int ci, int moveCount){
            this.ri=ri;
            this.ci=ci;
            this.moveCount=moveCount;
        }
    }
    
    char[][] map;
    int wd;
    
    int solution(char[][] map){
        int[] dri=new int[]{1,0,-1,0,   1,-1,1,-1,   0};
        int[] dci=new int[]{0,1,0,-1,   1,1,-1,-1,   0};
        
        int answer=0;
        init(map);
        boolean[][] isVisited=new boolean[wd][wd];
        Queue<Point> tskS=new ArrayDeque<>();
        tskS.offer(new Point(wd-1,0,0));
        isVisited[wd-1][0]=true;
        
        int moveCount=0;
        while(!tskS.isEmpty()){
            Point p=tskS.poll();
            if(moveCount+1==p.moveCount){
                moveCount+=1;
                moveBlock();
            }
            if(map[p.ri][p.ci]=='#'){
                continue;
            }
            if(p.ri==0 && p.ci==wd-1){
                answer=1;
                break;
            }
            for(int i=0;i<9;i++){
                int nri=p.ri+dri[i];
                int nci=p.ci+dci[i];
                if(nri<0||nri>=wd)   continue;
                if(nci<0||nci>=wd)   continue;
                if(map[nri][nci]=='#')  continue;
                tskS.offer(new Point(nri,nci,p.moveCount+1));
            }
        }
        return answer;
    }
    
    void init(char[][] map){
        this.map=map;
        this.wd=8;
    }
    
    void moveBlock(){
        for(int ri=wd-2;ri>=0;ri--){
            map[ri+1]=map[ri];
        }
        map[0]=new char[wd];
        for(int i=0;i<wd;i++){
            map[0][i]='.';
        }
    }
    
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        
        int wd=8;
        char[][] map=new char[wd][wd];
        for(int ri=0;ri<wd;ri++){
            map[ri]=kb.readLine().toCharArray();
        }
        System.out.println(T.solution(map));
    }
}

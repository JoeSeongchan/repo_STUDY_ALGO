/*
No. 9 파핑파핑 지뢰찾기

1. 문제 재정의 및 추상화
전형적인 BFS 문제. 인접(대각선 포함)한 모든 자리에 지뢰가 없는 경우, 
해당 자리를 눌렀을 때 인접한 모든 자리가 눌려진다. (점점 퍼진다. 눌린 영역이 점점 넓어진다.)
한 번 눌렀을 때 확장되는 모든 영역을 구하고, 나머지 영역 개수에서 지뢰 개수를 뺀 영역은
하나씩 눌러줘야 하기 때문에 그냥 count값에 그 개수를 더한다. 

2. 풀이과정 자세히 적기
배열을 순회한다. 이전에 눌린 적이 없으면서, 인접한 자리에 지뢰가 없는 영역을 찾는다. 
해당 영역을 찾으면 Queue에 넣는다. count를 1 증가시킨다. 
Queue에 넣은 원소를 하나씩 가져온다. 
해당 원소의 인접한 자리를 체크한다. (이전에 클릭한 적이 없으면서 인접한 자리에 지뢰가 없는 경우에만 Queue에 넣는다. 넣고 나서 visited 배열에 클릭 사실을 기록한다.)

visited 배열을 순회하며 클릭되지 않은 영역의 개수를 구한다. 클릭되지 않은 영역의 개수에서 지뢰 개수를 뺀 만큼의 영역은
하나씩 눌러줘야 한다. 클릭되지 않은 영역의 개수에서 지뢰 개수를 뺀 값을 count값에 더한다.

3. 시간복잡도 계산 
O(N*N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class Solution{
    
    static class Elem{
        int ri,ci;
        Elem(int ri,int ci){
            this.ri=ri;
            this.ci=ci;
        }
    }
    
    final int[] dri=new int[]{-1,0,1,0,1,1,-1,-1};
    final int[] dci=new int[]{0,1,0,-1,1,-1,1,-1};
    boolean[][] clicked;
    int count;
    int width;
    char[][] map;
    
    int solution(int width,char[][] map){
        int answer=0;
        init(width,map);
        findOneClickArea();
        findOtherArea();
        answer=count;
        return answer;
    }
    
    void init(int width,char[][] map){
        this.width=width;
        this.map=map;
        clicked=new boolean[width][width];
        count=0;
    }
    
    void findOneClickArea(){
        Queue<Elem> queue=new ArrayDeque<>();
        for(int ri=0;ri<width;ri++){
            for(int ci=0;ci<width;ci++){
                if(map[ri][ci]=='*')    continue;
                if(clicked[ri][ci])     continue;
                if(isThereBombAround(ri,ci))  continue;
                clicked[ri][ci]=true;
                queue.offer(new Elem(ri,ci));
                count+=1;
                while(!queue.isEmpty()){
                    Elem elem=queue.poll();
                    for(int i=0;i<8;i++){
                        int nri=elem.ri+dri[i];
                        int nci=elem.ci+dci[i];
                        if(!isInArea(nri,nci))  continue;
                        if(clicked[nri][nci])   continue;
                        clicked[nri][nci]=true; // 주변에 폭탄이 있어도 클릭된다.
                        if(isThereBombAround(nri,nci))  continue;
                        queue.offer(new Elem(nri,nci));
                    }
                }
            }
        }
    }
    
    boolean isThereBombAround(int ri, int ci){
        boolean answer=false;
        for(int i=0;i<8;i++){
            int nri=ri+dri[i];
            int nci=ci+dci[i];
            if(!isInArea(nri,nci))  continue;
            if(map[nri][nci]=='*'){
                answer=true;
                break;
            }
        }
        return answer;
    }
    
    boolean isInArea(int ri, int ci){
        if(ri<0||ri>=width)   return false;
        if(ci<0||ci>=width)   return false;
        return true;
    }
    
    void findOtherArea(){
        int allCnt=width*width;
        int clickedCnt=0;
        int bombCnt=0;
        for(int ri=0;ri<width;ri++){
            for(int ci=0;ci<width;ci++){
                if(map[ri][ci]=='*'){
                    bombCnt+=1;
                    continue;
                } 
                if(clicked[ri][ci]){
                    clickedCnt+=1;
                }
            }
        }
        count+=(allCnt-clickedCnt-bombCnt);
    }
    
    
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int tcN=Integer.parseInt(kb.readLine());
        StringBuilder sb=new StringBuilder();
        for(int tci=1;tci<=tcN;tci++){
            int width=Integer.parseInt(kb.readLine());
            char[][] map=new char[width][width];
            for(int ri=0;ri<width;ri++){
                map[ri]=kb.readLine().toCharArray();
            }
            sb.append('#').append(tci).append(' ')
            .append(T.solution(width,map)).append('\n');
        }
        System.out.print(sb);
    } 
}


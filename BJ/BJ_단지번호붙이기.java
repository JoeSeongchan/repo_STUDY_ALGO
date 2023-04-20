/*
단지 번호 붙이기
1. 문제 재정의 및 추상화 
전형적인 BFS 문제! 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(V+E)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_단지번호붙이기{
    int width;
    int[][] map;
    int[][] flagS;
    final int[] dri=new int[]{1,0,-1,0};
    final int[] dci=new int[]{0,1,0,-1};
    
    String solution(int width, int[][] map){
        String answer="";
        int flagNo=1;
        List<Integer> flagCntS=new ArrayList<>();
        init(width,map);
        
        Queue<int[]> tskS=new ArrayDeque<>();
        for(int ri=0;ri<width;ri++){
            for(int ci=0;ci<width;ci++){
                if(map[ri][ci]==0) continue;
                if(flagS[ri][ci]!=-1)   continue;
                tskS.offer(new int[]{ri,ci});
                flagS[ri][ci]=flagNo;
                int cnt=0;
                while(!tskS.isEmpty()){
                    int[] p=tskS.poll();
                    int tri=p[0],tci=p[1];
                    cnt+=1;
                    for(int i=0;i<4;i++){
                        int nri=tri+dri[i];
                        int nci=tci+dci[i];
                        if(nri<0||nri>=width)   continue;
                        if(nci<0||nci>=width)   continue;
                        if(map[nri][nci]==0)    continue;
                        if(flagS[nri][nci]!=-1) continue;
                        flagS[nri][nci]=flagNo;
                        tskS.offer(new int[]{nri,nci});
                    }
                }
                flagCntS.add(cnt);
                flagNo+=1;
            }
        }
        StringBuilder sb=new StringBuilder();
        sb.append(flagCntS.size()).append('\n');
        Collections.sort(flagCntS);
        for(int v: flagCntS){
            sb.append(v).append('\n');
        }
        answer=sb.toString();
        return answer;
    }
    
    void init(int width, int[][] map){
        this.width=width;
        this.map=map;
        flagS=new int[width][width];
        for(int ri=0;ri<width;ri++){
            Arrays.fill(flagS[ri],-1);
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_단지번호붙이기 T=new BJ_단지번호붙이기();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int width=Integer.parseInt(kb.readLine());
        int[][] map=new int[width][width];
        for(int ri=0;ri<width;ri++){
            char[] ol=kb.readLine().toCharArray();
            for(int ci=0;ci<width;ci++){
                map[ri][ci]=Character.getNumericValue(ol[ci]);
            }
        }
        System.out.println(T.solution(width,map));
    }
}
/*
미로 탐색
1. 문제 재정의 및 추상화 
전형적인 BFS 문제 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N^2)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_미로탐색{
    int solution(int rowN, int colN, int[][] map){
        int answer=0;
        final int[] dri=new int[]{1,0,-1,0};
        final int[] dci=new int[]{0,1,0,-1};
        
        Queue<int[]> tskS=new ArrayDeque<>();
        int[][] distS=new int[rowN][colN];
        for(int ri=0;ri<rowN;ri++){
            Arrays.fill(distS[ri],-1);
        }
        distS[0][0]=1;
        tskS.offer(new int[]{0,0});
        
        while(!tskS.isEmpty()){
            int[] p=tskS.poll();
            int ri=p[0],ci=p[1];
            int dist=distS[ri][ci];
            for(int i=0;i<4;i++){
                int nri=ri+dri[i];
                int nci=ci+dci[i];
                if(nri<0||nri>=rowN)    continue;
                if(nci<0||nci>=colN)    continue;
                if(map[nri][nci]==0)    continue;
                if(distS[nri][nci]!=-1) continue;
                distS[nri][nci]=dist+1;
                tskS.offer(new int[]{nri,nci});
            }
        }
        answer=distS[rowN-1][colN-1];
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_미로탐색 T=new BJ_미로탐색();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int rowN=Integer.parseInt(stk.nextToken());
        int colN=Integer.parseInt(stk.nextToken());
        int[][] map=new int[rowN][colN];
        for(int ri=0;ri<rowN;ri++){
            char[] ol=kb.readLine().toCharArray();
            for(int ci=0;ci<colN;ci++){
                map[ri][ci]=Character.getNumericValue(ol[ci]);
            }
        }
        System.out.println(T.solution(rowN,colN,map));
    }
}
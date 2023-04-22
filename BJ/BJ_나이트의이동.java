/*
나이트의 이동 
1. 문제 재정의 및 추상화 
체스판 위에 나이트가 있음. 나이트가 이동하려고 하는 칸 주어짐.
나이트가 몇 번 움직이면 이 칸으로 이동할 수 있는지 구하라.
전형적인 BFS 문제이다.

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N^2)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_나이트의이동{
    int solution(int width, int[] kn, int[] tg){
        int answer=-1;
        
        final int[] dri=new int[]{-1,-2,-2,-1,1,2,2,1};
        final int[] dci=new int[]{2,1,-1,-2,-2,-1,1,2};
        
        Queue<int[]>tskS=new ArrayDeque<>();
        int[][] distS=new int[width][width];
        for(int[] row: distS)   Arrays.fill(row,-1);
        distS[kn[0]][kn[1]]=0;
        tskS.offer(kn);
        
        while(!tskS.isEmpty()){
            int[] p=tskS.poll();
            int ri=p[0],ci=p[1];
            int dist=distS[ri][ci];
            if(ri==tg[0] && ci==tg[1]){
                answer=dist;
                break;
            }
            for(int i=0;i<8;i++){
                int nri=ri+dri[i],nci=ci+dci[i];
                if(nri<0||nri>=width)   continue;
                if(nci<0||nci>=width)   continue;
                if(distS[nri][nci]!=-1) continue;
                distS[nri][nci]=dist+1;
                tskS.offer(new int[]{nri,nci});
            }
        }
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_나이트의이동 T=new BJ_나이트의이동();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int tcN=Integer.parseInt(kb.readLine());
        for(int tci=1;tci<=tcN;tci++){
            int width=Integer.parseInt(kb.readLine());
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            int[] kn=new int[2];
            for(int i=0;i<2;i++){
                kn[i]=Integer.parseInt(stk.nextToken());
            }
            stk=new StringTokenizer(kb.readLine());
            int[] tg=new int[2];
            for(int i=0;i<2;i++){
                tg[i]=Integer.parseInt(stk.nextToken());
            }
            sb.append(T.solution(width,kn,tg)).append('\n');
        }
        System.out.println(sb);
    }
}
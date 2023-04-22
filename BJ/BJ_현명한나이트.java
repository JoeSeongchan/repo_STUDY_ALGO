/*
현명한 나이트
1. 문제 재정의 및 추상화
이전에 풀었던 '나이트' 문제와 동일하다. 여러 개의 타겟이 있다는 점만 다르다. 
2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N*N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_현명한나이트{
    String solution(int width, int tgN, int[] kn, int[][] tgS){
        String answer="";
        final int[] dri=new int[]{-1,-2,-2,-1,1,2,2,1};
        final int[] dci=new int[]{2,1,-1,-2,-2,-1,1,2};
        Queue<int[]>tskS=new ArrayDeque<>();
        tskS.offer(kn);
        int[][] distS=new int[width][width];
        for(int[] row: distS)   Arrays.fill(row,-1);
        distS[kn[0]][kn[1]]=0;
        while(!tskS.isEmpty()){
            int[] p=tskS.poll();
            int ri=p[0],ci=p[1];
            int dist=distS[ri][ci];
            for(int i=0;i<8;i++){
                int nri=ri+dri[i],nci=ci+dci[i];
                if(nri<0||nri>=width)   continue;
                if(nci<0||nci>=width)   continue;
                if(distS[nri][nci]!=-1) continue;
                distS[nri][nci]=dist+1;
                tskS.offer(new int[]{nri,nci});
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int[] tg:tgS){
            sb.append(distS[tg[0]][tg[1]]).append(' ');
        }
        sb.append('\n');
        answer=sb.toString();
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_현명한나이트 T=new BJ_현명한나이트();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int width=Integer.parseInt(stk.nextToken());
        int tgN=Integer.parseInt(stk.nextToken());
        int[] kn=new int[2];
        stk=new StringTokenizer(kb.readLine());
        for(int i=0;i<2;i++){
            kn[i]=Integer.parseInt(stk.nextToken())-1;
        }
        int[][] tgS=new int[tgN][2];
        for(int i=0;i<tgN;i++){
            stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<2;k++){
                tgS[i][k]=Integer.parseInt(stk.nextToken())-1;
            }
        }
        System.out.println(T.solution(width,tgN,kn,tgS));
    }
}

/*
봄버맨2
큐에 폭파할 셀 정보를 입력 
*/
import java.util.*;
import java.io.*;
public class BJ_봄버맨2{
    
    static class Cell{
        int ri, ci;
        char type;
        int elapsedTime;
        
        public Cell(int ri, int ci, char type){
            this.ri=ri;
            this.ci=ci;
            this.type=type;
            elapsedTime=0;
        }
        
        // 터질 예정인 셀이면 true 리턴
        public boolean timeGo(){
            if(type!='b'){
                return false;
            }
            if(elapsedTime==2){
                return true;
            }
            elapsedTime+=1;
            return false;
        }
        
        public void clear(){
            type='n';
            elapsedTime=0;
        }
        
        public void putBomb(){
            if(type=='n'){
                type='b';
                elapsedTime=0;
            }
        }
        
        @Override
        public String toString(){
            if(type=='b'){
                return "O";
            }
            return ".";
        }
    }
    
    int rowN, colN, time;
    Cell[][] cellS;
    String[] outputS;
    
    String solution(int rowN, int colN, int time, char[][] map){
        String answer="";
        init(rowN, colN, time, map);
        simulate();
        if(time==1){
            answer=outputS[1];
        } else{
            answer=outputS[(time-2)%4+2];
        }
        return answer;
    }
    
    void init(int rowN,int colN,int time,char[][] map){
        this.rowN=rowN;
        this.colN=colN;
        this.time=time;
        cellS=new Cell[rowN][colN];
        outputS=new String[6];
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                Cell cell=null;
                if(map[ri][ci]=='O'){
                    cell=new Cell(ri,ci,'b');
                } else{
                    cell=new Cell(ri,ci,'n');
                }
                cellS[ri][ci]=cell;
            }
        }
    }
    
    void simulate(){
        final int[] dri=new int[]{1,0,-1,0};
        final int[] dci=new int[]{0,1,0,-1};
        Queue<Cell> goingToBombS=new ArrayDeque<>();
        for(int i=0;i<=5;i++){
            // 짝수 초에 폭탄 설치 
            if(i!=0 && i%2==0){
                for(int ri=0;ri<rowN;ri++){
                    for(int ci=0;ci<colN;ci++){
                        cellS[ri][ci].putBomb();
                    }
                }
            } 
            // 홀수 초에 폭탄 터짐
            else if(i!=1 && i%2==1){
                while(!goingToBombS.isEmpty()){
                    Cell goingToBomb = goingToBombS.poll();
                    int ri=goingToBomb.ri, ci=goingToBomb.ci;
                    goingToBomb.clear();
                    for(int k=0;k<4;k++){
                        int nri=ri+dri[k];
                        int nci=ci+dci[k];
                        if(!isValid(nri,nci))   continue;
                        cellS[nri][nci].clear();
                    }
                }
            }
            
            for(int ri=0;ri<rowN;ri++){
                for(int ci=0;ci<colN;ci++){
                    if(cellS[ri][ci].timeGo()){
                        goingToBombS.offer(cellS[ri][ci]);
                    }
                }
            }
            // System.out.println(goingToBombS);
            StringBuilder sb=new StringBuilder();
            for(int ri=0;ri<rowN;ri++){
                for(int ci=0;ci<colN;ci++){
                    sb.append(cellS[ri][ci].toString());
                    // System.out.printf("%d",cellS[ri][ci].elapsedTime);
                }
                // System.out.println();
                sb.append('\n');
            }
            // System.out.println();
            outputS[i]=sb.toString();
        }
    }
    
    boolean isValid(int ri, int ci){
        if(ri<0||ri>=rowN)  return false;
        if(ci<0||ci>=colN)  return false;
        return true;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_봄버맨2 T=new BJ_봄버맨2();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int rowN=Integer.parseInt(stk.nextToken()); 
        int colN=Integer.parseInt(stk.nextToken());
        int time=Integer.parseInt(stk.nextToken());
        char[][] map=new char[rowN][colN];
        for(int ri=0;ri<rowN;ri++){
            map[ri]=kb.readLine().toCharArray();
        }
        System.out.println(T.solution(rowN,colN,time,map));
    }
}
/*
표 병합
1. 문제 재정의 및 추상화 
Union-Find 알고리즘 적용 문제 
int[][] map
UPDATE r c value => map 값 초기화 
UPDATE value1, value2 => map에서 value1을 찾아서 전부 교체. 
MERGE r1 c1 r2 c2 => union-find로 부모 병합. r1 c1의 부모가 루트가 됨. 
UNMERGE r c => map[r][c] 값은 그대로 유지. r,c를 부모로 가지는 노드를 전부 찾아서 값을 blank로 초기화 
PRINT r c => 부모노드의 값을 출력 

2. 풀이과정 상세히 적기
생략
예시 적용도 필요 없음 

3. 시간복잡도 계산 
O(R*C*COMMAND) 
최대 연산 횟수 : 2500*1000=250_0000=250만 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

class Solution {
    static class Position{
        int r,c;
        Position(int r,int c){
            this.r=r;
            this.c=c;
        }
        @Override
        public boolean equals(Object obj){
            Position other = (Position)obj;
            if(r==other.r && c==other.c)    return true;
            return false;
        }
        @Override
        public String toString(){
            return String.format("(%d, %d)",r,c);
        }
    }
    enum CommandType{
        UPDATE_CELL, UPDATE_VAL, MERGE, UNMERGE, PRINT
    }
    static class Command{
        CommandType type;
        Position p1,p2;
        String value1,value2;
        Command(CommandType type, Position p1, Position p2, String value1, String value2){
            this.type=type;
            this.p1=p1;
            this.p2=p2;
            this.value1=value1;
            this.value2=value2;
        }
        @Override
        public String toString(){
            return String.format("명령 타입 : %s | 위치1 : %s | 위치2 : %s | 값1 : %s | 값2 : %s",
                                type, p1, p2, value1, value2);
        }
    }
    String[][] map;
    final int WIDTH=51;
    Position[][] parentS;
    Command[] commandS;
    int commandN;
    
    public String[] solution(String[] rawCommandS) {
        String[] answer = {};
        init(rawCommandS);
        int printIdx=0;
        List<String> printResultS=new ArrayList<>();
        for(Command command : commandS){
            System.out.println(command);
            String result=run(command);
            debug();
            if(result==null)    continue;
            printResultS.add(result);
        }
        answer=printResultS.toArray(new String[printResultS.size()]);
        return answer;
    }
    
    void init(String[] rawCommandS){
        commandN=rawCommandS.length;
        commandS=new Command[commandN];
        for(int i=0;i<commandN;i++){
            String rawCommand=rawCommandS[i];
            Command command = parse(rawCommand);
            commandS[i]=command;
        }
        map=new String[WIDTH][WIDTH];
        makeSet();
    }
    
    Command parse(String rawCommand){
        String[] partS=rawCommand.split(" ");
        String rawType=partS[0];
        CommandType type=null;
        int r1,c1,r2,c2;
        String value1, value2;
        r1=c1=r2=c2=0;
        value1=value2=null;
        
        switch(rawType){
            case "UPDATE":default:
                if(partS.length==4){
                    type=CommandType.UPDATE_CELL;
                    r1=stoi(partS[1]);
                    c1=stoi(partS[2]);
                    value1=partS[3];
                } else{
                    type=CommandType.UPDATE_VAL;
                    value1=partS[1];
                    value2=partS[2];
                }
                break;
            case "MERGE":
                type=CommandType.MERGE;
                r1=stoi(partS[1]);
                c1=stoi(partS[2]);
                r2=stoi(partS[3]);
                c2=stoi(partS[4]);
                break;
            case "UNMERGE":
                type=CommandType.UNMERGE;
                r1=stoi(partS[1]);
                c1=stoi(partS[2]);
                break;
            case "PRINT":
                type=CommandType.PRINT;
                r1=stoi(partS[1]);
                c1=stoi(partS[2]);
                break;
        }
        // CommandType type, Position p1, Position p2, int value1, int value2
        Position p1=new Position(r1,c1), p2=new Position(r2,c2);
        Command command = new Command(type,p1,p2,value1,value2);
        // System.out.println(command);
        return command;
    }
    
    int stoi(String str){
        return Integer.parseInt(str);
    }
    
    void makeSet(){
        parentS=new Position[WIDTH][WIDTH];
        for(int ri=1;ri<WIDTH;ri++){
            for(int ci=1;ci<WIDTH;ci++){
                parentS[ri][ci]=new Position(ri,ci);
            }
        }
    }
    
    String run(Command command){
        String answer=null;
        switch(command.type){
            case UPDATE_CELL: updateCell(command); break;
            case UPDATE_VAL: updateVal(command); break;
            case MERGE: merge(command); break;
            case UNMERGE: unmerge(command); break;
            case PRINT: answer=print(command); break;
        }
        return answer;
    }
    
    // ok
    void updateCell(Command command){
        Position root=findRoot(command.p1);
        map[root.r][root.c]=command.value1;
    }
    
    // ok
    void updateVal(Command command){
        for(int ri=1;ri<WIDTH; ri++){
            for(int ci=1;ci<WIDTH;ci++){
                Position p=new Position(ri,ci);
                Position root=findRoot(p);
                if(command.value1.equals(map[root.r][root.c])){
                    map[root.r][root.c]=command.value2;
                }
            }
        }
    }
    
    // ok
    Position findRoot(Position p){
        if(!parentS[p.r][p.c].equals(p)){
            parentS[p.r][p.c]=findRoot(parentS[p.r][p.c]);
        }
        return parentS[p.r][p.c];
    }
    
    // ok
    void merge(Command command){
        union(command.p1,command.p2);
    }
    
    // ok
    void union(Position base, Position part){
        Position r1=findRoot(base);
        Position r2=findRoot(part);
        if(r1.equals(r2))   return;
        if(map[r1.r][r1.c]==null && map[r2.r][r2.c]!=null){
            map[r1.r][r1.c]=map[r2.r][r2.c];  
        }
        parentS[r2.r][r2.c]=r1;
    }
    
    void unmerge(Command command){
        updateRoot();
        Position target=command.p1;
        Position targetRoot=findRoot(target);
        
        map[target.r][target.c]=map[targetRoot.r][targetRoot.c];
        parentS[target.r][target.c]=target;
        
        for(int ri=1;ri<WIDTH; ri++){
            for(int ci=1;ci<WIDTH;ci++){
                Position p=new Position(ri,ci);
                if(p.equals(target))    continue;
                Position root=findRoot(p);
                if(root.equals(targetRoot)){
                    parentS[p.r][p.c]=p;
                    map[p.r][p.c]=null;
                }
            }
        }
    }
    
    void updateRoot(){
        for(int ri=1;ri<WIDTH;ri++){
            for(int ci=1;ci<WIDTH;ci++){
                findRoot(new Position(ri,ci));
            }
        }
    }
    
    String print(Command command){
        Position root=findRoot(command.p1);
        String answer=map[root.r][root.c];
        if(answer==null)    answer="EMPTY";
        return answer;
    }
    
    void debug(){
        for(int ri=1;ri<5;ri++){
            System.out.print("| ");
            for(int ci=1;ci<5;ci++){
                Position p=new Position(ri,ci);
                Position root=findRoot(p);
                if(p.equals(root)){
                    System.out.printf("%s | ",map[ri][ci]);
                } else{
                    System.out.printf("%s | ",root);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
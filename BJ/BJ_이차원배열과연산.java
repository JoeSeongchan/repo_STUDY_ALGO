package codingTest;
/*
이차원 배열과 연산
1. 문제 재정의 및 추상화 
수의 등장 횟수, 수의 값 커지는 순서대로 정렬
배열에 수의 값과 수의 등장 횟수를 모두 넣는다. 
배열이 길어진다. 
수를 정렬할 때는 0은 무시해야 한다. 
행과 열의 크기가 100을 넘어가는 경우에는 처음 100개만 따진다. 
제일 긴 행과 열을 기준으로 0을 채운다. 
배열 A에 들어있는 수가 주어질 때, A[r][c]에 들어 있는 값이 k가 되기 위한 최소 시간을 구하자. 
전형적인 시뮬레이션 문제인 듯 보인다. 하지만 시뮬레이션으로 문제를 풀었을 때 시간 초과가 나지 않는지 체크해야 한다. 
한 번 정렬 최대 연산 수 = 100*100=1만 
최대 100초 => 최대 연산 수 = 100만

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(최대 시간 * 최대 열 수 * 최대 행 수)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class Solution{

    int rowIdx, colIdx, targetValue, rowN, colN;
    int[][] map;
    
    int solution(int rowIdx, int colIdx, int targetValue, int rowN, int colN, int[][] map){
        int answer=0;
        init(rowIdx,colIdx,targetValue,rowN,colN,map);
        if(check()) return 0;
        for(int time=1;time<=100;time++){
            if(run()){
                return time;
            }
            // debug();
        }
        return -1;
    }

    void debug(){
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                System.out.printf("%d ",map[ri][ci]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    void init(int rowIdx, int colIdx, int targetValue, int rowN, int colN, int[][] map){
        this.rowIdx=rowIdx-1;
        this.colIdx=colIdx-1;
        this.targetValue=targetValue;
        this.rowN=rowN;
        this.colN=colN;
        this.map=map;
    }
    
    boolean run(){
        if(rowN>=colN){
            runCommandR();
        } else{
            runCommandC();
        }
        return check();
    }
    
    void runCommandR(){
        List<List<Integer>> dynamicArrS=new ArrayList<>();
        for(int[] row: map){
            List<Integer> dynamicArr=runCommand(row);
            dynamicArrS.add(dynamicArr);
        }
        int maxColN=Integer.MIN_VALUE;
        for(List<Integer> arr: dynamicArrS){
            maxColN=Math.max(maxColN,arr.size());
        }
        colN=Math.min(maxColN,100);
        map=new int[rowN][colN];
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                if(dynamicArrS.get(ri).size()<=ci)  continue;
                map[ri][ci]=dynamicArrS.get(ri).get(ci);
            }
        }
    }
    
    void runCommandC(){
        List<List<Integer>> dynamicArrS=new ArrayList<>();
        int[] arr=new int[rowN];
        for(int ci=0;ci<colN;ci++){
            for(int ri=0;ri<rowN;ri++){
                arr[ri]=map[ri][ci];
            }
            List<Integer> dynamicArr=runCommand(arr);
            dynamicArrS.add(dynamicArr);
        }
        int maxRowN=Integer.MIN_VALUE;
        for(List<Integer> dynamicArr: dynamicArrS){
            maxRowN=Math.max(maxRowN,dynamicArr.size());
        }
        rowN=Math.min(maxRowN,100);
        map=new int[rowN][colN];
        for(int ci=0;ci<colN;ci++){
            for(int ri=0;ri<rowN;ri++){
                if(dynamicArrS.get(ci).size()<=ri)  continue;
                map[ri][ci]=dynamicArrS.get(ci).get(ri);
            }
        }
    }

    List<Integer> runCommand(int[] arr){
        List<Integer> dynamicArr=new ArrayList<>();
        Map<Integer,Integer> info=new HashMap<>();
        for(int v: arr){
            if(v==0)    continue;
            info.putIfAbsent(v,0);
            info.put(v,info.get(v)+1);
        }
        List<Map.Entry<Integer,Integer>> entrySet=new ArrayList<>(info.entrySet());
        Collections.sort(entrySet,(e1,e2)->{
            int v1=e1.getKey(), v2=e2.getKey();
            int n1=e1.getValue(), n2=e2.getValue();
            if(n1<n2) return -1;
            if(n1>n2) return 1;
            if(v1<v2) return -1;
            if(v1>v2) return 1;
            return 0;
        });
        for(Map.Entry<Integer,Integer> e: entrySet){
            int v=e.getKey();
            int n=e.getValue();
            dynamicArr.add(v);
            dynamicArr.add(n);
        }
        return dynamicArr;
    }
    
    boolean check(){
        if(rowIdx>=rowN)    return false;
        if(colIdx>=colN)    return false;
        return map[rowIdx][colIdx]==targetValue;
    }
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int rowIdx=Integer.parseInt(stk.nextToken());
        int colIdx=Integer.parseInt(stk.nextToken());
        int targetValue=Integer.parseInt(stk.nextToken());
        int rowN=3, colN=3;
        int[][] map=new int[rowN][colN];
        for(int ri=0;ri<rowN;ri++){
            stk=new StringTokenizer(kb.readLine());
            for(int ci=0;ci<colN;ci++){
                map[ri][ci]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(rowIdx,colIdx,targetValue,rowN,colN,map));
    }
}
/*
카드
1. 문제 재정의 및 추상화 
단순 정렬 문제? 왜 정답률이 낮을까?
일단 값은 long 타입 안에 넣을 수 있는 범위 안에 있다.
HashMap에 데이터를 저장한다. 
공간복잡도 체크... 10만 개의 int형 데이터 크기: 400_000 => 400kb
400kb*3=1200kb... 1200kb+800kb=2000kb 충분히 수용할 만한 데이터 크기이다..
다 한다음에 HashMap의 value 리스트를 정렬해서 문제를 해결한다. 
정렬 기준: 
    1순위: 가장 개수가 많은 원소 
    2순위: 가장 작은 원소
실제로 테스트케이스에 적용해보자. OK!

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(NlogN)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_카드{
    static class Entry implements Comparable<Entry>{
        int num;
        long v;
        
        Entry(long v){
            this.v=v;
            num=0;
        }
        
        void countUp(){
            num+=1;
        }
        
        @Override
        public int compareTo(Entry other){
            if(num>other.num)    return -1;
            if(num<other.num)    return 1;
            if(v<other.v)   return -1;
            if(v>other.v)   return 1;
            return 0;
        }
    }
    
    int vN;
    long[] vS;
    Map<Long,Entry> eS;
    
    long solution(int vN, long[] vS){
        long answer=0;
        init(vN,vS);
        List<Entry> entryS=new ArrayList<>(eS.values());
        Collections.sort(entryS);
        answer=entryS.get(0).v;
        return answer;
    }
    
    void init(int vN, long[] vS){
        this.vN=vN;
        this.vS=vS;
        eS=new HashMap<>();
        for(long v: vS){
            eS.putIfAbsent(v,new Entry(v));
            eS.get(v).countUp();
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_카드 T=new BJ_카드();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int vN=Integer.parseInt(kb.readLine());
        long[] vS=new long[vN];
        for(int i=0;i<vN;i++){
            vS[i]=Long.parseLong(kb.readLine());
        }
        System.out.println(T.solution(vN,vS));
    }
}
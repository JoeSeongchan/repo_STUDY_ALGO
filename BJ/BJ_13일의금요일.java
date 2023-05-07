/*
13일의 금요일
1. 문제 재정의 및 추상화 
2019년 1월 1일부터 N년 12월 31일까지 '13일의 금요일'의 개수를 세어라. 
N은 최대 10만년까지 가능하다. 
조건 
    - 2019년 1월 1일은 화요일
    - 2월이 29일까지인 년도 = 윤년
    - 윤년 조건 주어짐
단순 구현 문제 

2. 풀이과정 상세히 적기 
생략

3. 시간복잡도 계산
O(N)
N: 끝 년도
최대 연산 횟수: 356*10만 = 3650만회..
시간 안에 풀 수 있다!

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_13일의금요일{
    
    static class Calendar {
        
        int year, month, day;
        Calendar(int year, int month, int day){
            this.year=year;
            this.month=month;
            this.day=day;
        }
        
        boolean isYunYear(){
            if(year%400==0) return true;
            if(year%100==0) return false;
            if(year%4==0)   return true;
            return false;
        }
        
        int endDay(){
            switch(month){
                case 1:case 3:case 5:case 7:case 8:case 10:case 12:
                    return 31;
                case 2:
                    if(isYunYear()) return 29;
                    return 28;
                default: return 30;
            }
        }
        
        boolean isOverMonth(){
            int endDay=endDay();
            if(day>endDay)  return true;
            return false;
        }
        
        boolean isOverYear(){
            int endMonth=12;
            if(month>endMonth)  return true;
            return false;
        }
        
        void nextWeek(){
            day+=7;
            if(isOverMonth()){
                day-=endDay();
                month+=1;
            }
            if(isOverYear()){
                month=1;
                year+=1;
            }
        }
        
        boolean isBefore(Calendar other){
            if(year<=other.year &&
                month<=other.month &&
                day<=other.day){
                    return true;
                }
            return false;
        }
        
        @Override
        public String toString(){
            return String.format("%d-%d-%d",year,month,day);
        }
    }
    
    int solution(int endYear){
        int answer=0;
        // 금요일을 기준으로 7일씩 앞으로 이동한다. 
        // 금요일인데 13일인 날의 개수를 센다. 
        int year=2019;
        int month=1;
        int day=4;
        Calendar c=new Calendar(year,month,day);
        Calendar deadline=new Calendar(endYear,12,31);
        int cnt=0;
        while(c.isBefore(deadline)){
            // System.out.println(c);
            if(c.day==13){
                cnt+=1;
            }
            c.nextWeek();
        }
        answer=cnt;
        return answer;
    }
    
    
    
    public static void main(String[] args) throws Exception{
        BJ_13일의금요일 T=new BJ_13일의금요일();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int endYear=Integer.parseInt(kb.readLine());
        System.out.println(T.solution(endYear));
    }
}
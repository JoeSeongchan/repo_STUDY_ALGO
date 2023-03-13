package PROGRMMERS;

/*
1. 문제 재정의 및 추상화
최소한의 카메라를 설치하여 모든 차량을 감시하는 방법을 구하여라.
그리디 알고리즘으로 문제를 푼다. 

2. 풀이과정 상세히 적기
(차량 번호, 시간, 시작/종료시간 여부)를 정보로 가진 클래스를 선언한다.
그 클래스의 인스턴스를 여럿 만든다. 인스턴스 배열을 시간에 따라 오름차순으로 정렬한다.
시간이 동일하면 시작 시간이 종료 시간보다 우선하게 만든다. 최대한 많은 차량을 카메라로 촬영하기 위해서다.
스택에 해당 인스턴스 배열을 하나씩 쌓아둔다. 그러다가 종료시간을 하나 발견하면
그 때까지 스택에 쌓여있던 모든 시작 시간을 꺼낸다. 이 모든 차량을 카메라로 촬영했음을 
boolean 배열에 기록한다. 

3. 시간 복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.*;

class PROGRAMMERS_단속카메라 {
    static class Time implements Comparable<Time> {
        int carIdx;
        int time;
        boolean isStart;

        Time(int carIdx, int time, boolean isStart) {
            this.carIdx = carIdx;
            this.time = time;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Time other) {
            if (time < other.time) {
                return -1;
            } else if (time > other.time) {
                return 1;
            } else if (isStart && !other.isStart) {
                return -1;
            } else if (!isStart && other.isStart) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public int solution(int[][] routes) {
        int answer = 0;
        Time[] tS = new Time[routes.length * 2];
        for (int i = 0; i < routes.length; i++) {
            tS[i * 2] = new Time(i, routes[i][0], true);
            tS[i * 2 + 1] = new Time(i, routes[i][1], false);
        }
        Arrays.sort(tS);
        Stack<Time> stack = new Stack<>();
        boolean[] isShooted = new boolean[routes.length];
        int cameraCnt = 0;
        for (Time t : tS) {
            if (isShooted[t.carIdx])
                continue;
            if (t.isStart) {
                stack.push(t);
                continue;
            }
            cameraCnt += 1;
            while (!stack.isEmpty()) {
                Time other = stack.pop();
                isShooted[other.carIdx] = true;
            }
        }
        answer = cameraCnt;
        return answer;
    }
}
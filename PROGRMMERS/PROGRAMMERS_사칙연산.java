package PROGRMMERS;

/*
1. 문제 재정의 및 추상화 
빼기 연산은 결합법칙이 적용되지 않는다. 모든 경우의 결과값을 계산한다.
모든 결과값을 하나로 모아 크기에 따라 오름차순으로 정렬한다. 마지막 값(최댓값)을 구한다.
이 문제를 DP로 어떻게 풀 수 있을까? 작은 문제의 답을 활용해서 큰 문제의 답을 구한다. 
1
1-3
    1-(3)
1-3+5
    1-(3)+5
    1-(3+5)
1-3+5-8
    1-(3)+5-8
        1-(3)+5-(8)
    1-(3+5)-8
        1-(3+5)-(8)
    1-(3+5-8)
        1-(3+5-(8))
하나의 연산자가 나오면 그 연산자가 뺄셈 연산자인지 확인한다.
뺄셈 연산자이면 바로 뒤에 (를 두고, 그 다음 숫자부터 )를 차례대로 배치한다.
이것은 어떻게 구현할 수 있는가? 1개 숫자 음수로, 2개 숫자 음수로, 3개 숫자 음수로, ...
이렇게 연산자를 정반대로 바꾼다. 모든 경우를 탐색해야 한다. 
200C100... 모든 경우를 탐색할 수 없다. 시간복잡도가 너무 크다. 

뒤에서부터 탐색한다. 최솟값과 최댓값을 미리 저장해두고 있는다. 연산자가 나오면 이 최솟값, 최댓값을 활용한다.
+는 최댓값을 쓴다. -는 최솟값을 쓴다. 최댓값=숫자+최댓값, 최솟값=숫자+최솟값, 최댓값=숫자-최솟값, 최솟값=숫자-최댓값
1-3+5-8

** 배운 점
    - 최댓값 - 최솟값 = 최댓값
    최솟값 - 최댓값 = 최솟값
    최댓값 + 최댓값 = 최댓값
    최솟값 + 최솟값 = 최솟값
        경우 나누어서 특정 범위 내 부분 표현식의 최댓값, 최솟값을 찾는다.
    - for 문의 반복에 사용되는 변수는 2개 이상 두지 않는다.
        버그가 많이 난다. 하나를 두고 나머지 하나는 for문 body 안에서 초기화하는 식으로 반복문 로직을 짠다.
*/
import java.util.*;
import java.io.*;

class PROGRAMMERS_사칙연산 {
    int[] opdS;
    char[] oprS;
    int opdN;
    int oprN;
    int[][] bgS;
    int[][] smS;

    public int solution(String arr[]) {
        int answer = 0;
        init(arr);
        // 점점 범위를 늘려간다. (0-1), ... (0-opdN-1)
        for (int diff = 0; diff <= opdN - 1; diff++) {
            for (int st = 0; (st + diff) < opdN; st++) {
                int ed = st + diff;
                if (diff == 0) {
                    bgS[st][st] = opdS[st];
                    smS[st][st] = opdS[st];
                    continue;
                }
                initBgSm(st, st + diff);
            }
        }
        answer = bgS[0][opdN - 1];
        return answer;
    }

    void init(String[] arr) {
        int totalN = arr.length;
        // 피연산자가 1개 더 많다.
        opdS = new int[totalN / 2 + 1];
        oprS = new char[totalN / 2];
        opdN = opdS.length;
        oprN = oprS.length;
        int opdIdx = 0;
        int oprIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            String v = arr[i];
            switch (v) {
                case "+":
                case "-":
                    oprS[oprIdx] = v.charAt(0);
                    oprIdx += 1;
                    break;
                default:
                    opdS[opdIdx] = Integer.parseInt(v);
                    opdIdx += 1;
                    break;
            }
        }
        bgS = new int[opdN][opdN];
        smS = new int[opdN][opdN];
    }

    // st~ed 범위의 최댓값, 최솟값 찾기
    void initBgSm(int st, int ed) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        // (st~mid), (mid+1,ed)
        for (int mid = st; mid < ed; mid++) {
            char op = oprS[mid];
            switch (op) {
                case '+':
                    max = Math.max(max, bgS[st][mid] + bgS[mid + 1][ed]);
                    min = Math.min(min, smS[st][mid] + smS[mid + 1][ed]);
                    break;
                case '-':
                    max = Math.max(max, bgS[st][mid] - smS[mid + 1][ed]);
                    min = Math.min(min, smS[st][mid] - bgS[mid + 1][ed]);
                    break;
            }
        }
        bgS[st][ed] = max;
        smS[st][ed] = min;
    }
}
package PROGRMMERS;

/*
1. 문제 재정의 및 추상화 
Npk: p줄 k번째 수으로 가는 경로 안 최대 수
N11, ... , 차례대로 채워나간다.
Npk 2차원 배열 만든다.

2. 풀이과정 상세히 적기
N(p+1)(k+1)=max(N(p)(k),N(p)(k+1))
생략

3. 시간복잡도 계산
O(p*k)
p: 줄 수, k: 하나의 줄 안에서 수의 순서

4. 코드 작성
*/
class PROGRAMMERS_정수삼각형 {
    int[][] maxValueS;

    public int solution(int[][] triangle) {
        int answer = 0;
        init(triangle);
        for (int ri = 0; ri < triangle.length; ri++) {
            if (ri == 0)
                continue;
            int[] row = maxValueS[ri];
            for (int ci = 0; ci < row.length; ci++) {
                int left = Integer.MIN_VALUE;
                int right = Integer.MIN_VALUE;
                if (ci >= 1)
                    left = maxValueS[ri - 1][ci - 1];
                if (ci <= row.length - 1)
                    right = maxValueS[ri - 1][ci];
                maxValueS[ri][ci] = Math.max(left, right) + maxValueS[ri][ci];
            }
        }
        int ri = triangle.length - 1;
        int max = Integer.MIN_VALUE;
        for (int ci = 0; ci < maxValueS[ri].length; ci++) {
            max = Math.max(max, maxValueS[ri][ci]);
        }
        answer = max;
        return answer;
    }

    void init(int[][] triangle) {
        maxValueS = new int[500][500];
        for (int ri = 0; ri < triangle.length; ri++) {
            int[] row = triangle[ri];
            for (int ci = 0; ci < row.length; ci++) {
                maxValueS[ri][ci] = triangle[ri][ci];
            }
        }
    }
}

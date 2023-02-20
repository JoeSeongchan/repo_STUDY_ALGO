package 조성찬;

/*
Z (BJ 1074)

1. 문제 재정의 및 추상화 
N이 주어질 때, r행 c열을 몇 번째로 방문하는가? 
배열 사이즈: (2^N)*(2^N)
2^30=10억... 배열안 요소를 하나하나 계산하여 넣어서는 문제에서 주어진 
제한 시간 안에 문제를 풀 수 없다. 그렇다면 어떻게 해야 하는가? ... 

2. 풀이과정 상세히 적기

2차원 배열을 네 등분한다. 
네 등분하고 나서 value에 4를 곱한다. (value의 초기값은 0이다)
r행 c열을 찾는다. 
배열을 네 등분하여 r행 c열이 들어갈 sub 배열을 찾는다. 
0 1 
2 3
sub 배열이 왼쪽 위면 value에 0을 더한다. 
sub 배열이 오른쪽 위면 value에 1을 더한다. 
sub 배열이 왼쪽 아래면 value에 2을 더한다. 
sub 배열이 오른쪽 아래면 value에 3을 더한다. 

위 작업을 r행 c열 정사각형을 찾을 때까지 반복한다. 
r행 c열 정사각형을 찾았을 때 value값이 r행 c열 정사각형에 들어갈 값이다. 
(전형적인 Divide and Conquer 문제)

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.StringTokenizer; // 입력 받은 문자열을 공백을 기준으로 자르기 위해 StringTokenizer 사용
import java.io.InputStreamReader; // 입력 단위를 Byte에서 Char로 바꾸기 위해 InputStreamReader 사용
import java.io.BufferedReader; // Buffer를 사용하여 빠르게 입력 받기 위해 BufferedReader 사용 

public class A049_BJ1074_Z { // Z (BJ 1074) 문제 해결 클래스
	int N; // 배열 사이즈가 2^N * 2^N 일 때, N 값
	int width; // 배열의 폭 (2^N)
	int tgRowIdx; // 값을 구하고자 하는 요소(target element)의 row 위치(=index)
	int tgColIdx; // 값을 구하고자 하는 요소(target element)의 column 위치(=index)
	int value; // 값을 구하고자 하는 요소(target element)의 값

	int solution(int N, int tgRowIdx, int tgColIdx) { // 파라미터로 입력 값을 받아 문제에서 원하는 출력값을 리턴한다.
		int answer = 0; // 답
		init(N, tgRowIdx, tgColIdx); // 멤버 변수 초기화
		divideAndConquer(0, 0, width); // Divide and conquer 방식으로 문제 해결
		answer = value; // 답 초기화
		return answer; // 답 리턴
	}

	void init(int N, int tgRowIdx, int tgColIdx) { // 멤버 변수 초기화
		this.tgRowIdx = tgRowIdx; // 값을 구하고자 하는 요소(target element)의 row 위치(=index) 초기화
		this.tgColIdx = tgColIdx; // 값을 구하고자 하는 요소(target element)의 column 위치(=index) 초기화
		this.N = N; // 배열 사이즈가 2^N * 2^N 일 때, N 값 초기화
		width = 1 << N; // 배열의 폭 (2^N) 초기화
		value = 0; // 값을 구하고자 하는 요소(target element)의 값을 0으로 초기화
	}

	// Divide and conquer 방식으로 특정 위치의 요소(target element)의 값을 구한다.
	void divideAndConquer(int startRowIdx, int startColIdx, int size) {
		if (size == 1) { // 특정 위치의 요소(target element)를 찾은 경우, (기저조건)
			return; // 재귀 종료
		}
		int newSize = size / 2; // 네 칸으로 나누기 때문에 size 값을 이등분한다.
		int midRowIdx = startRowIdx + newSize; // 중간에 위치한 row 위치(=index)
		int midColIdx = startColIdx + newSize; // 중간에 위치한 column 위치(=index)
		value *= 4; // 네 등분하면서 value에 4를 곱한다.
		if (tgRowIdx < midRowIdx) { // 값을 구하고자 하는 요소(target element)가 위쪽에 위치한 경우,
			if (tgColIdx < midColIdx) { // 값을 구하고자 하는 요소(target element)가 왼쪽 위에 위치한 경우,
				value += 0; // value에 0을 더한다.
				divideAndConquer(startRowIdx, startColIdx, newSize); // 범위 좁혀서 재귀 호출
			} else { // 값을 구하고자 하는 요소(target element)가 오른쪽 위에 위치한 경우,
				value += 1; // value에 1을 더한다.
				divideAndConquer(startRowIdx, midColIdx, newSize); // 범위 좁혀서 재귀 호출
			}
		} else { // 값을 구하고자 하는 요소(target element)가 아래쪽에 위치한 경우,
			if (tgColIdx < midColIdx) { // 값을 구하고자 하는 요소(target element)가 왼쪽 아래에 위치한 경우,
				value += 2; // value에 2을 더한다.
				divideAndConquer(midRowIdx, startColIdx, newSize); // 범위 좁혀서 재귀 호출
			} else { // 값을 구하고자 하는 요소(target element)가 오른쪽 아래에 위치한 경우,
				value += 3; // value에 3을 더한다.
				divideAndConquer(midRowIdx, midColIdx, newSize); // 범위 좁혀서 재귀 호출
			}
		}
	}

	public static void main(String[] args) throws Exception { // 프로그램 시작점
		A049_BJ1074_Z T = new A049_BJ1074_Z(); // 문제 해결 클래스의 인스턴스 생성 (이 인스턴스의 solution 메소드 호출로 결과값 받아올 것이다.)
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader로 빠르게 입력 받는다.
		StringTokenizer stk = new StringTokenizer(kb.readLine()); // 한 줄을 입력 받아 공백을 기준으로 자른다.
		int N = Integer.parseInt(stk.nextToken()); // 첫번째 토큰은 '배열 사이즈가 2^N * 2^N 일 때, N 값'이다.
		int tgRowIdx = Integer.parseInt(stk.nextToken()); // 두번째 토큰은 값을 구하고자 하는 요소(target element)의 row 위치(=index) 값이다.
		int tgCoIdx = Integer.parseInt(stk.nextToken()); // 세번째 토큰은 값을 구하고자 하는 요소(target element)의 column 위치(=index)
															// 값이다.
		System.out.println(T.solution(N, tgRowIdx, tgCoIdx)); // 문제 해결 클래스의 인스턴스의 solution 메소드 호출로 결과값 구한 후 콘솔에 출력한다.
	}
}

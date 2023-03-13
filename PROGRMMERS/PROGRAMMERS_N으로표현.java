package PROGRMMERS;

/*
1. 문제 재정의 및 추상화 
N을 k번 써서 만들 수 있는 수 = Nk
사칙연산 = *
N1: {N}
N2: {N1*N1} 
N3: {N1*N2, N2*N1}
N4: {N1*N3, N2*N2, N3*N1}
...
N8까지 구한다.
number가 N1, ..., N8 집합 안에 속하는 지 확인. 
찾자마자 리턴. (제일 앞에 있는 집합 고른다.)

2. 풀이과정 상세히 적기
Set의 List
생략

3. 시간복잡도 계산 
O(8*4^8)=O(k*4^k)
k=최솟값의 최댓값

4. 코드 작성 
*/
import java.util.*;

class PROGRAMMERS_N으로표현 {
    List<Set<Integer>> NkSetS;
    final int maxK = 8;
    int value, targetValue;

    public int solution(int N, int number) {
        init(N, number);
        int answer = 0;
        NkSetS.set(1, new HashSet<>(Arrays.asList(value)));
        for (int k = 2; k <= maxK; k++) {
            NkSetS.set(k, makeSet(k));
        }
        for (int k = 1; k <= maxK; k++) {
            // System.out.println(NkSetS.get(k));
            if (NkSetS.get(k).contains(targetValue)) {
                return k;
            }
        }
        return -1;
    }

    void init(int value, int targetValue) {
        this.value = value;
        this.targetValue = targetValue;
        NkSetS = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            NkSetS.add(new HashSet<>());
        }
    }

    Set<Integer> makeSet(int k) {
        Set<Integer> set = new HashSet<>();
        int def = 0;
        for (int i = 1; i <= k; i++) {
            def *= 10;
            def += value;
        }
        set.add(def);
        for (int i = 1; i < k; i++) {
            int p = k - i;
            Set<Integer> Ni = NkSetS.get(i);
            Set<Integer> Np = NkSetS.get(p);
            for (int vi : Ni) {
                for (int vp : Np) {
                    set.add(vi + vp);
                    set.add(vi - vp);
                    set.add(vi * vp);
                    if (vp != 0) {
                        set.add(vi / vp);
                    }
                }
            }
        }
        return set;
    }
}

/*
영우는 사기꾼?
1. 문제 재정의 및 추상화 
영우가 치트키를 사용하지 않고 게임을 진행했는지 확인해보자! 
**제약조건 
1. 한 건물은 최대 3개의 건물에만 영향을 미칠 수 있음. 
2. 치트키를 쓰면 건물을 자유롭게 지을 수 있다. 
3. 치트키로 지은 건물은 건설 정보에 들어가지 않는다. 
4. 모든 건물은 중복 건설이 가능하다. 
5. 2단계 이전 건물은 부숴도 된다. (이미 1단계 이전 건물 건설 완료 조건 만족했기 때문)
6. 이전 단계의 건물을 모두 지은 후에야 다음 단계 건물을 지을 수 있다. 

위상정렬 알고리즘을 사용한다. 진입 차수가 0이 아님에도 건설을 진행한 건물이 있는지 확인한다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(V+E)
V: 건물의 수 
E: 건물과 건물 사이의 관계 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_영우는사기꾼{
	int vN,eN,gN;
	List<Integer>[] adjS;
	int[] inEdgeS;
	int[] bS;
	
	String solution(int vN, int eN, int gN, int[][] eS, int[][] gS){
		init(vN,eN,gN,eS,gS);
		for(int[] g: gS){
			int op=g[0],v=g[1];
			switch(op){
				case 1:
				//System.out.printf("%d번 건물 건설 시도\n",v);
				// 건물 건설
				// 건물을 건설할 때, 해당 건물 노드의 진입 차수가 0인지 확인한다. 0이 아니면 치트키를 쓴 것이다. 
				if(inEdgeS[v]!=0){
					////System.out.println("건설 불가! [테크트리 불만족]");
					return "Lier!";
				}
				if(bS[v]==0){
					// [해당 건물] 처음 건물 건설 => 해당 건물 노드가 영향을 주는 노드의 진입 차수를 1씩 감소시킨다. 
					for(int adj: adjS[v]){
						inEdgeS[adj]-=1;
					}
				}
				// 건물 개수 1 증가
				bS[v]+=1;
				//System.out.println("건설 성공!");
				break;
				
				case 2:
				//System.out.printf("%d번 건물 파괴 시도\n",v);
				// 건물 파괴 
				// 건물이 없는데도 파괴하려고 할 때,
				if(bS[v]==0){
					////System.out.println("파괴 불가! [건물이 없음]");
					return "Lier!";
				}
				if(bS[v]==1){
					// [해당 건물] 마지막 건물 파괴 => 해당 건물 노드가 영향을 주는 노드의 진입 차수를 1씩 증가시킨다. 
					for(int adj: adjS[v]){
						//System.out.println(inEdgeS[adj]);
						inEdgeS[adj]+=1;
					}
					//System.out.println("마지막 건물이기 때문에 테크트리 중간에 깨짐");
				}
				//System.out.println("파괴 성공!");
				bS[v]-=1;
				break;
			}
		}
		String answer="King-God-Emperor";
		return answer;
	}
	
	void init(int vN,int eN,int gN,int[][] eS,int[][] gS){
		this.vN=vN;
		this.gN=gN;
		adjS=new ArrayList[vN+1];
		for(int i=1;i<=vN;i++){
			adjS[i]=new ArrayList<Integer>();
		}
		inEdgeS=new int[vN+1];
		for(int[] e:eS){
			int v1=e[0],v2=e[1];
			adjS[v1].add(v2);
			inEdgeS[v2]+=1;
		}
		//System.out.println(Arrays.toString(inEdgeS));
		bS=new int[vN+1];
	}
	
	public static void main(String[] args) throws Exception{
		BJ_영우는사기꾼 T=new BJ_영우는사기꾼();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		int vN=Integer.parseInt(stk.nextToken());
		int eN=Integer.parseInt(stk.nextToken());
		int gN=Integer.parseInt(stk.nextToken());
		int[][] eS=new int[eN][2];
		for(int i=0;i<eN;i++){
			stk=new StringTokenizer(kb.readLine());
			for(int k=0;k<2;k++){
				eS[i][k]=Integer.parseInt(stk.nextToken());
			}
		}
		int[][] gS=new int[gN][2];
		for(int i=0;i<gN;i++){
			stk=new StringTokenizer(kb.readLine());
			for(int k=0;k<2;k++){
				gS[i][k]=Integer.parseInt(stk.nextToken());
			}
		}
		System.out.println(T.solution(vN,eN,gN,eS,gS));
	}
}
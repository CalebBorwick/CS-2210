public class Asn1 {
	public static void main(String[] args) {
		int[] A = {6,8,1,7,5};
		int[] B = {3,9,0,8,2};
		boolean bool = false;
		int i = 0;
		int j = 0;
		while (i < A.length) {
			while (j < B.length) {
				System.out.println(A[i]);
				if (A[i] == B[j]) {
					System.out.println(A[i]);
					bool=true;
					break;
				}
				j+=1;
			}
			if (bool == true) {
				break;
			}
			j=0;
			i+=1;
		}
		System.out.println(bool);
		//return bool;
	}
}
package myProblemList.twoPoints;

public class _977_Squares_of_a_Sorted_Array {
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int res[] = new int[n];
        int i = 0, j = n - 1, p = n - 1;
        while(i <= j) {
            if(Math.abs(A[i]) >= Math.abs(A[j])) {
                res[p] = A[i] * A[i];
                i++;
            }
            else {
                res[p] = A[j] * A[j];
                j--;
            }
            p--;
        }
        return res;
    }
}

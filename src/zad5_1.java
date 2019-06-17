import com.sun.org.apache.xpath.internal.operations.Bool;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class zad5_1 {

    static SecureRandom generator = new SecureRandom();

    public static void gnp(int n, float p, int[][] A) {
        int i,j;
        for (i=0;i<n;i++) {
            A[i][i] = 0;
        }

        for (i=0;i<n-1;i++){
            for (j=i+1;j<n;j++) {
                int pom = (generator.nextDouble() <= p) ? 1 : 0;
                A[i][j] = A[j][i] = pom;
            }
        }
    }

    public static void printA(int n, int[][] A) {
        int i,j;
        for (i=0;i<n;i++) {
            for (j=0;j<n;j++){
                if (A[i][j]==1) System.out.print("1");
                else System.out.print("0");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

        public static void copyA(int n, int[][] A, int[][] C) {
            int i,j;
            for (i=0;i<n;i++){
                for (j=0;j<n;j++){
                    C[i][j] = A[i][j];
                }
            }
        }

        public static int greaterThanA(int n, int[][] A, int[][] B) {
        int i,j;
        for (i=0;i<n-1;i++){
            for (j=i+1;j<n;j++) {
                if (B[i][j] < A[i][j]) return 1;
                else if (B[i][j] > A[i][j]) return -1;
            }
        }
        return 0;
    }

    public static  void induceA(int n, int[] p, int[][] A, int[][] B) {
        int i,j;
        for (i=0;i<n;i++) {
            for (j=0;j<n;j++) {
                B[i][j] = A[p[i]][p[j]];
            }
        }
    }

    public static void permInit(int n, int[] p) {
        int i;
        for (i=0;i<n;i++) {
            p[i]=i;
        }
    }

    public static int permNext(int n,  int[] p) {
        int i,j,x,b;
        b=0;
        i=n-1;

        while (i>0) {
            if (p[i]>p[i-1]) {
                j=n-1;
                while (p[j] < p[i-1]) j--;
                x=p[j]; p[j]=p[i-1]; p[i-1]=x;
                while (i<n) {
                    x=p[i]; p[i]=p[n-1]; p[n-1]=x;
                    i++; n--;
                };
                b=1;
                break;
            }
            i--;
        }
        return b;
    }

    private static boolean IzoTest(int arrSize, float p){
        boolean ifIzo = false;
        int[][] A = new int[arrSize][arrSize];
        int[][] B = new int[arrSize][arrSize];
        int[][] C = new int[arrSize][arrSize];
        int[] perm = new int[arrSize];

        gnp(arrSize,p,A);
        printA(arrSize, A);
        gnp(arrSize,p,B);
        printA(arrSize, B);

        copyA(arrSize, B, C);
        permInit(arrSize, perm);

        do{
            induceA(arrSize, perm, B, C);
            if(greaterThanA(arrSize, A, C)==0){
                ifIzo = true;
                break;
            }
        } while(permNext(arrSize, perm) == 1);

        return ifIzo;
    }

    public static void main(String[] args){
        if(IzoTest(15, 0.5f)==true){
            System.out.print("YES");
        }
        else{
            System.out.print("NO");
        }
    }
}
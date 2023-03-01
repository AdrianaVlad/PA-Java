/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bonus;

/**
 *
 * @author Vlad Adriana
 */
public class Bonus {

    public static void main(String[] args) {
        if(checkInt(args[0])){
            System.out.println("Invalid 1st argument: expected positive integer");
            return;
        }
        int n=getInt(args[0]);
        if(n<3){
            System.out.println("Invalid 1st argument: a cycle graph has at least 3 vertices");
            return;
        }
        int A[][]=createCycleGraph(n);
        System.out.println("The adjacency matrix A of the generated cycle graph is:");
        displayMatrix(A);
        powerMatrix(A,n);
        
        if(checkInt(args[1])){
            System.out.println("Invalid 2nd argument: expected positive integer");
            return;
        }
        n=getInt(args[1]);
        if(checkInt(args[2])){
            System.out.println("Invalid 3nd argument: expected positive integer");
            return;
        }
        int degree=getInt(args[2]);
        if(degree>=n || degree*n%2==1){
            System.out.println("Invalid 2nd or 3rd argument: such a graph cannot exist (degree should be < number of vertices and the number of edges should be even)");
            return;
        }
        A=createRegularGraph(n,degree);
        System.out.println("\nThe adjacency matrix A of the generated regular graph is:");
        displayMatrix(A);
    }
    public static boolean checkInt(String val){
        int i=0;
        if(val.charAt(i)=='-')
            return false;
        for(;i<val.length();i++)
            if(val.charAt(i)<'0' || val.charAt(i)>'9')
                return true;
        return false;
    }
    public static int getInt(String val){
        int i=0,x=0;
        for(;i<val.length();i++)
            x=x*10+val.charAt(i)-'0';
        return x;
    }
    public static int[][] createCycleGraph(int n){
        int C[][]=new int[n][n], i;
        for(i=0;i<n;i++){
            C[i][(i+1)%n]=1;
            C[i][(n+i-1)%n]=1;
        }
        return C;
    }
    public static void displayMatrix(int M[][]){
        int i,j,len=M.length;
        for(i=0;i<len;i++){
            for(j=0;j<len;j++)
                System.out.print(M[i][j] + " ");
            System.out.print('\n');
        }
    }
    public static void powerMatrix(int M[][],int n){
        int i, P[][]=M;
        for(i=2;i<=n;i++){
            P=multiplyMatrix(P,M);
            System.out.println("A^" + i);
            displayMatrix(P);
        }
    }
    public static int[][] multiplyMatrix(int A[][],int B[][]){
        int result[][]=new int[A.length][B[0].length];
        int i,j,k;
        for(i=0;i<A.length;i++){
            for(j=0;j<B[0].length;j++){
                for(k=0;k<B.length;k++)
                    result[i][j]=result[i][j]+A[i][k]*B[k][j];
            }
        }
        return result;
    }
    public static int[][] createRegularGraph(int n, int degree){
        int R[][]=new int[n][n], i, j;
        if(degree%2==1)
            for(i=0;i<n;i++){
                R[i][(i+n/2)%n]=1;
            }
        for(i=0;i<n;i++){
            for(j=1;j<=degree/2;j++){
                R[i][(i+j)%n]=1;
                R[i][(i-j+n)%n]=1;
            }
        }
        return R;
    }
}

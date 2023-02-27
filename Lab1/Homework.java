/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.homework;
//import java.util.Scanner;
/**
 *
 * @author Vlad Adriana
 */
public class Homework {

    public static void main(String[] args) {
        //Scanner console = new Scanner (System.in);
        //int n = console.nextInt();
        long t1=System.currentTimeMillis();
        if(checkInt(args[0])){
            System.out.println("Invalid argument");
            return;
        }
        int n=getInt(args[0]);
        int M[][]=createLatinSquare(n);
        long t2=System.currentTimeMillis();
        if(n>=10000)
            System.out.println("runtime: " + (t2-t1) + "ms");
        else{
            System.out.println("The lines are:");
            displayLines(M);
            System.out.println("The columns are:");
            displayColumns(M);
        }
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
    public static int[][] createLatinSquare(int n){
        int M[][]=new int[n][n], i, k;
        for(k=1;k<=n;k++){
            for(i=0;i<n;i++){
                M[i][(i+k-1)%n]=k;
            }
        }
        return M;
    }
    public static void displayLines(int M[][]){
        int i,j,len=M.length;
        String line = "";
        for(i=0;i<len;i++){
            for(j=0;j<len;j++)
                line=line+Integer.toString(M[i][j]);
            System.out.println(line);
            line="";
        }
            
    }
    public static void displayColumns(int M[][]){
        int i,j,len=M.length;
        String column = "";
        for(i=0;i<len;i++){
            for(j=0;j<len;j++)
                column=column+Integer.toString(M[j][i]);
            System.out.println(column);
            column="";
        }
    }
}
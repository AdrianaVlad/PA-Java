/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.compulsory;

/**
 *
 * @author Vlad Adriana
 */
public class Compulsory {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String[] languages={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random()*1_000_000);
        n=newValue(n);
        n=getDigit(n);
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }
    public static int newValue(int n){
        n=n*3;
        n=n+0b10101;
        n=n+0xFF;
        n=n*6;
        return n;
    }
    public static int getDigit(int n){
        int x;
        while(n>=10){
            x=0;
            while(n!=0){
            x+=n%10;
            n/=10;
        }
            n=x;
        }
        return n;
    }
}

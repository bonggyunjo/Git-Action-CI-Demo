/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ci.demo;

/**
 *
 * @author Admin
 */
public class CiDemo {
    

    public static void main(String[] args) {
        measureStringPerformance();
        measureStringBuilderPerformance();
    }
        private static void measureStringPerformance() {
        long startTime = System.nanoTime();
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str += "a";
        }
        long endTime = System.nanoTime();
        System.out.println("String 연결 시간: " + (endTime - startTime) + " ns");
    }

    // StringBuilder 사용 시간 측정
    private static void measureStringBuilderPerformance() {
        long startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("a");
        }
        long endTime = System.nanoTime();
        System.out.println("StringBuilder 연결 시간: " + (endTime - startTime) + " ns");
    }
}


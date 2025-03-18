package com.mycompany.ci.demo;

public class CiDemo {

    public static void main(String[] args) {
        // String 연결 성능 측정
        long stringTime = measureStringPerformance();
        // StringBuilder 연결 성능 측정
        long stringBuilderTime = measureStringBuilderPerformance();

        // 성능 비교 출력
        System.out.println("String 연결 시간: " + stringTime + " ns");
        System.out.println("StringBuilder 연결 시간: " + stringBuilderTime + " ns");

        if (stringTime > stringBuilderTime) {
            System.out.println("StringBuilder가 더 빠릅니다.");
        } else if (stringTime < stringBuilderTime) {
            System.out.println("String이 더 빠릅니다.");
        } else {
            System.out.println("두 방법의 속도가 같습니다.");
        }
    }

    private static long measureStringPerformance() {
        long startTime = System.nanoTime();
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str += "a";  // String 연결
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureStringBuilderPerformance() {
        long startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("a");  // StringBuilder 연결
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}

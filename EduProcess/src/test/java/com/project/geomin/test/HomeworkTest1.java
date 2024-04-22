package com.project.geomin.test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class HomeworkTest1 {

	
	@Autowired
	private MockMvc mvc;
	
	@Test
	 public void givenArray_whenFindMinimum_thenCorrect() { // 검증하기 위한 테스트 케이스들
        assertAll(
                () -> test(new int[]{3, 4, 5, 1, 2}, 1),
                () -> test(new int[]{4, 5, 6, 7, 0, 1, 2}, 0),
                () -> test(new int[]{3, 1, 2}, 1)
        );

    }

    private void test(int[] given, int expected) {
        // when
        FindMinimumInRotatedSortedArray findMinimumInRotatedSortedArray = new FindMinimumInRotatedSortedArray();
        int actual = findMinimumInRotatedSortedArray.findMin(given);
        // then
        assertEquals(expected, actual);
        
    	printTestInfo(given, expected);
    }
   
   private void printTestInfo(int[] given, int expected) {
	   // 메모리 사용량 확인용
       Runtime runtime = Runtime.getRuntime();
       long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
       System.out.println("테스트 케이스 통과");
       System.out.printf("memory usage: %d KB\n", usedMemoryBefore / 1024);
       System.out.println("주어진 값 : " + arrayToString(given) + "  result : " + expected +"\n");
   }
   
   private String arrayToString(int[] arr) {
       StringBuilder sb = new StringBuilder("[");
       for (int i = 0; i < arr.length; i++) {
           sb.append(arr[i]);
           if (i != arr.length - 1) {
               sb.append(", ");
           }
       }
       sb.append("]");
       return sb.toString();
   }
}



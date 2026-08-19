package CS3L_lab1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CS3L_lab1_class {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.printf("How many tickets would you like to purchase: ");
		int kkk = sc.nextInt();
		System.out.printf("Whats the maximum number of neg :");
		int neg = sc.nextInt();
		System.out.printf("how many in column?");
		int cotton = sc.nextInt();
		System.out.println("5"
				+ "C(49,6): " + a(neg, cotton));
	        for (int i = 0; i < kkk; i++) {
	            List<Integer> ticket = sample(neg, cotton);
	          
	            StringBuilder sb = new StringBuilder();
	            	for (int j = 0; j < ticket.size(); j++) {
	            		sb.append(ticket.get(j));
	            		if (j < ticket.size() - 1) sb.append(" ");
	            	}
	            System.out.println(sb.toString());
	        }
	       sc.close();
	    }
			public static long a(int v, int k) {
		        if (k < 0 || k > v) return 0;
		        if (k > v - k) k = v - k;
		        double r = 1;
		        for (int i = 1; i <= k; i++) {
		            r = r * (v - k + i) / i;
		        }
		        return Math.round(r);
		    }

		    public static List<Integer> sample(int n, int k) {
		        List<Integer> pool = new ArrayList<>();
		        for (int i = 1; i <= n; i++) {
		            pool.add(i);
		        }
		        Collections.shuffle(pool);
		        List<Integer> ticket = pool.subList(0, k);
		        Collections.sort(ticket);
		        return ticket;
		
	}

}

//
//package a;
//import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//public class Discretemath {
//	public static void main(String[] args) {
//        System.out.println("C(49,6): " + a(49, 6));
//        for (int i = 0; i < 5; i++) {
//            List<Integer> ticket = sample(49, 6);
//          
//            StringBuilder sb = new StringBuilder();
//            	for (int j = 0; j < ticket.size(); j++) {
//            		sb.append(ticket.get(j));
//            		if (j < ticket.size() - 1) sb.append(" ");
//            	}
//            System.out.println(sb.toString());
//        }
//    }
//		public static long a(int v, int k) {
//	        if (k < 0 || k > v) return 0;
//	        if (k > v - k) k = v - k;
//	        double r = 1.0;
//	        for (int i = 1; i <= k; i++) {
//	            r = r * (v - k + i) / i;
//	        }
//	        return Math.round(r);
//	    }
//
//	    public static List<Integer> sample(int n, int k) {
//	        List<Integer> pool = new ArrayList<>();
//	        for (int i = 1; i <= n; i++) {
//	            pool.add(i);
//	        }
//	        Collections.shuffle(pool);
//	        List<Integer> ticket = pool.subList(0, k);
//	        Collections.sort(ticket);
//	        return ticket;
//	    }
//}
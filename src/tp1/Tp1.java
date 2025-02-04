package tp1;

import java.util.function.Function;

public class Tp1 {

	public interface T1 {
		int apply(int x);
	}

	public interface T2 {
		int apply(T1 f);
	}
	
	public static <T> Function<T, T> f3(Function<T, T> f) {
		return x -> f.apply(f.apply(x));
	}
	
	
	public static void main(String[] args) {
		//a.
		System.out.println("Question 1.a:");
		T1 f1 = x -> x+1;
		System.out.println(f1.apply(3));
		
		//b
		System.out.println("Question 1.b:");
		T2 f2 = f -> f.apply(f.apply(0));
		System.out.println(f2.apply(x -> x+1));
		
		
		//c
		Function<Integer, Integer> f4 = x -> x + 1;
		
		System.out.println("Question 1.c:");
		System.out.println(f3(f4).apply(2));
		
		
		
		//QUESTION 2
		
	}
}

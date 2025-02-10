package tp2;

import java.util.function.Function;

public class Tp2 {
	
	
	public interface Integrable {
		
		double apply(double x);
		
		default double integr(double a, double b, int n) {
			
			double h = (b-a)/n;
			double somme = h * (apply(a)+apply(a+h))/2;
			
			
					
			for (int i=1; i<n; i++) {
				double aTemp = a+(i*(h));
				double bTemp = aTemp + h;
				
				somme += h * (apply(aTemp)+apply(bTemp))/2; //calcul d'un trapeze
				
			}
			
			return somme;
		}
	}
		
	public interface Expr<V> {
		int eval(Function<V, Integer> env);
		
		static Expr Var(V v) {
			
		}
	}
		
	public class Main {
	    public static void main(String[] args) {
	       //EXERCICE 1
	        Integrable f = x -> x*x;

	        double result = f.integr(0, 10, 1000);
	        System.out.println("Approximation de l'intégrale de x^2 sur [0,10] : " + result);
	        
	        Integrable f2 = x -> Math.sin(x);
	        double result2 = f2.integr(0, Math.PI, 1000);
	        System.out.println("Approximation de l'intégrale de sin(x) sur [0,pi] : " + result2);
	        
	        
	        //EXERCICE 2
	        
	        
	    }
	}
	

}

package tp2;

import java.util.HashMap;
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
	
	
	
	//EX2
		
	public interface Expr<V> {
		int eval(Function<V, Integer> env);
		
		static <V> Expr<V> Var(V v) {
			return env -> env.apply(v);
		}
		
		default Expr<V> add(Expr<V> exp) {
			return env -> this.eval(env) + exp.eval(env);
		}
		
		default Expr<V> mul(Expr<V> exp) {
			return env -> this.eval(env) * exp.eval(env);
		}
		
	}
	
	
	
	//EX3
	
	interface HasSucc<N extends HasSucc<N>> {
		N succ(); //renvoie le successeur de l’objet courant
		<T> T cases(T x, Function<N,T> f); // renvoie x si this n’est 
	// pas un successeur, f(p) si this est le successeur de p
		
		default Boolean isSucc() {
			return cases(false, p -> true);
		}
		
		default HasSucc<N> isPred() {
			return cases(this, p -> p);
		}
		

		
	}
	
	public interface HasPlus<N extends HasPlus<N>> extends HasSucc<N> {

	    default N add(N m) {
	        return cases(m, p -> (N) p.add(m).succ());
	    }
	}
	
	public static class MyNat implements HasPlus<MyNat> {
	    private final int value; 

	    public MyNat(int value) {
	        if (value < 0) throw new IllegalArgumentException("x < 0");
	        this.value = value;
	    }

	    @Override
	    public MyNat succ() {
	        return new MyNat(value + 1);
	    }

	    @Override
	    public <T> T cases(T x, Function<MyNat, T> f) {
	        return (value == 0) ? x : f.apply(new MyNat(value - 1));
	    }
	    
	    public int getValue() {
	        return value;
	    }

	    @Override
	    public String toString() {
	        return String.valueOf(value);
	    }
	}

		
	public class Main {
	    public static void main(String[] args) {
	       //EXERCICE 1
	        Integrable f = x -> x*x;

	        double result = f.integr(0, 10, 1000);
	        System.out.println("Approximation de l'integrale de x^2 sur [0,10] : " + result);
	        
	        Integrable f2 = x -> Math.sin(x);
	        double result2 = f2.integr(0, Math.PI, 1000);
	        System.out.println("Approximation de l'integrale de sin(x) sur [0,pi] : " + result2);
	        
	        
	        //EXERCICE 2
	        
	        HashMap<String, Integer> envMap = new HashMap<>();
	        envMap.put("x", 2);
	        envMap.put("y", 5);
	        
	        Function<String, Integer> env = envMap::get;
	        
	        Expr<String> exprX = Expr.Var("x");
	        Expr<String> exprY = Expr.Var("y");
	        
	        Expr<String> exprResult = exprX.mul(exprX).add(exprY);

	      
	        System.out.println("2.d: résultat = " + exprResult.eval(env)); //9
	        
	        
	        //EXERCICE 3
	        
	        MyNat n1 = new MyNat(2);
	    	MyNat n2 = new MyNat(3);
	    	System.out.println("3.d: somme = " + n1.add(n2)); // affiche 5

	        
	    }
	}
	

}

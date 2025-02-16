package tp3;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tp3 {

	
	//EX1
	static Integer fact(int n) {
		return (n == 0) ? 0 : Stream.iterate(1, x -> x+1).limit(n).reduce(1, (x,y) -> x*y);
		
	}
	
	static List<Integer> dixPremFacts() {
		return Stream.iterate(0, x->x+1).limit(10).map(Tp3::fact).toList();
	}
	
	static void affichDixPremFacts() {
		Stream.iterate(0, x->x+1).limit(10).map(Tp3::fact).forEach(e -> System.out.print(e + " "));
		System.out.println();
	}
	
	static Boolean predNbChiffresPair(Integer n) {
		if (n<10) { return false;}
		
		Boolean result = true;
		
		while (n!=0) {
			n = n/10;
			result = !result;
		}
		return result;
	}
	
	static void affichQuatrePremFactsNbChiffresPairs() {
		Stream.iterate(0, x->x+1).map(Tp3::fact).filter(Tp3::predNbChiffresPair).limit(4).forEach(e -> System.out.print(e + " "));
		System.out.println();
	}
	
	public static void main(String[] args) {
		System.out.println("1.a: test de fact(6): " + fact(6));
		
		System.out.println("1.b: Liste des 10 premières factorielles: " + dixPremFacts());
		
		System.out.println("1.c: Afficher 10 premières facts sans construire de liste: ");
		affichDixPremFacts();
		
		System.out.println("1.d: ");
		affichQuatrePremFactsNbChiffresPairs();
		
	}
	

}

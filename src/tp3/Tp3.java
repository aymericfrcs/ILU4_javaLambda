package tp3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import tp3.Tp3.Ville;

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
	
	
	public class Pair {
		public Integer x;
		public Integer fact;
		
		
	}
	
	//EX2
	
	interface Produit {
		int getPrix();
	}

	public class Prod implements Produit {
		private int prix;
		public Prod(int prix) {
			this.prix = prix;
		}
		public int getPrix() {return this.prix;}
	}
	
	/*public int sommeProduitsSum(Stream<Prod> s) {
		s.
	}*/
	
	public static int sommeProduitsReduce(Stream<Prod> s) {
		return s.map(e -> e.getPrix()).reduce(0, (x,y) -> x+y);
	}
	
	
	//EX3
	
	interface IPays {
		  List<Ville> getVilles();
		  String getContinent();
		  Ville getCapitale();
		  int getPopulation();
		}

	interface IVille {
		  Pays getPays();
		  int getPopulation();
		  String toString();
		}

	public record Pays(String nom, List<Ville> villes, String continent, Ville capitale, int population) {}
	public record Ville(String nom, Pays pays, int population) {
		//void setPays(Pays pays) {this.pays = pays;}
	}
	
	public static Optional<Ville> capitaleLaPlusPeuplee(Stream<Pays> p) {
		return p.map(Pays::capitale).max(Comparator.comparing(Ville::population));
	}
	
	public static List<Pays> triCPc(Stream<Pays> p) {
		return p.sorted(Comparator.comparing(Pays::continent).thenComparing(Pays::population)).collect(Collectors.toList());
	}
	
	public static List<Pays> triCPd(Stream<Pays> p) {
		return p.sorted(Comparator.comparing(Pays::continent).thenComparing(Pays::population).reversed()).collect(Collectors.toList());
	}
	
	public static void main(String[] args) {
		System.out.println("1.a: test de fact(6): " + fact(6));
		
		System.out.println("1.b: Liste des 10 premieres factorielles: " + dixPremFacts());
		
		System.out.println("1.c: Afficher 10 premieres facts sans construire de liste: ");
		affichDixPremFacts();
		
		System.out.println("1.d: ");
		affichQuatrePremFactsNbChiffresPairs();
		
		//j'ai skip la question 1.e
		
		//EX2
		Tp3 tp3 = new Tp3();
		
		List<Prod> prodList = Arrays.asList(
				tp3.new Prod(2),
				tp3.new Prod(4),
				tp3.new Prod(3)
				);
		
		//2.a
		
		//2.b
		System.out.println(sommeProduitsReduce(prodList.stream()));
		
		
		//EX3
		
		Ville paris = new Ville("Paris", null, 2103000);
		Ville madrid = new Ville("Madrid", null, 3277000);
		Ville london = new Ville("London", null, 8866000);
		
		List<Ville> villesFrance = Arrays.asList(paris);
		List<Ville> villesEspana = Arrays.asList(madrid);
		List<Ville> villesUK = Arrays.asList(london);
		
		Pays france = new Pays("France", villesFrance, "Europe", paris, 68170000);
		Pays espana = new Pays("Espana", villesEspana, "Europe", madrid, 48000000);
		Pays uk = new Pays("United Kingdom", villesUK, "Europe", london, 68350000);
		
		/*paris.setPays(france);
		madrid.setPays(espana);
		london.setPays(uk);*/
		
		List<Pays> paysList = Arrays.asList(france, espana, uk);
		Stream<Pays> p = paysList.stream();
		
		//3.b
		Optional<Ville> cap = capitaleLaPlusPeuplee(p);
		System.out.println(cap.toString());
		
		
		//3.c
		Stream<Pays> p2 = paysList.stream();
		System.out.println(triCPc(p2));
				
		//3.d
		Stream<Pays> p3 = paysList.stream();
		System.out.println(triCPd(p3));
		
		//3.e
				
		
	}
	

}

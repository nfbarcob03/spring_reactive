package clase2;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public
class JavaUtilExercies {

    public static
    void main(String[] args) {
        // Punto 1
        System.out.println("-------------------Punto 1 ----------------");
        BiFunction<Integer,Integer, Integer> sumaDosInt= (numero1,numero2) -> numero1+numero2;
        System.out.println("suam  de 1  y  2  es " + sumaDosInt.apply(1,2));

        System.out.println("-------------------Punto 2 ----------------");
        Function<List<String>, List<String>> ordenarLista = (List<String> lista) ->{
            lista.sort((s1,s2) -> s1.compareTo(s2));
            return lista;
        };
        var lista = Arrays.asList("pera", "manzana", "uva", "banana", "kiwi");
        System.out.println("Lista original: " + lista);
        System.out.println("Lista ordenada: " + ordenarLista.apply(lista));

        System.out.println("-------------------Punto 3 ----------------");

        Predicate<Integer> isPrimo = (numero) ->{
            return numero >1 && java.util.stream.IntStream.range(2, numero).noneMatch(i -> numero % i == 0);
        };

        System.out.println("El numero " + 5 + " Es primo?: " + isPrimo.test(5));
        System.out.println("El numero " + 8 + " Es primo?: " + isPrimo.test(8));
        System.out.println("El numero " + 13 + " Es primo?: " + isPrimo.test(13));

        System.out.println("-------------------Punto 4 ----------------");
        Predicate<String> isPalindromo = (palabra) ->{
            return palabra.equalsIgnoreCase(new StringBuilder(palabra).reverse().toString());
        };

        System.out.println("La palbra acurruca es palindromo ? " + isPalindromo.test("acurruca"));
        System.out.println("La palbra acurruca es Otorringolaringolo ? " + isPalindromo.test("Otorringolaringolo"));

        System.out.println("-------------------Punto 5 ----------------");

        BiFunction<String,StringCase,String> cast = (palabra,enumeracion) -> {
             return switch (enumeracion){
                case LOWER -> palabra.toUpperCase();
                case UPPER -> palabra.toLowerCase();
            };
        };
        System.out.println(cast.apply("aRrOzZ", StringCase.LOWER));
        System.out.println(cast.apply("hola", StringCase.UPPER));
        System.out.println(cast.apply("nada", StringCase.UPPER));



    }
    enum StringCase {
        UPPER,LOWER
    }
}

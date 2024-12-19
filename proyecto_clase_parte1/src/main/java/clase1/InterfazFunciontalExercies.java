package clase1;


import java.util.*;
import java.util.stream.Collectors;

public
class InterfazFunciontalExercies {
    public static
    void main(String[] args) {
        //System.out.println("Hola, mundo!");

        System.out.println("------ 1. Suma dos numeros con lambda------");
        System.out.println(suma(10,2));

        System.out.println("------ 1.1 Suma dos numeros con lambda ------");
        System.out.println(sumaLista(Arrays.asList(1, 2, 3, 4, 5)));

        System.out.println("------ 2. Ordenar string ------");
        System.out.println(ordenarStrings(Arrays.asList("zorro", "loro", "arbol", "0","torre","ave", "carro",
                                                   "1", "beta")));

        System.out.println("------3. Numero Primo ------");
        System.out.println(isPrimo(13));
        System.out.println(isPrimo(7));
        System.out.println(isPrimo(5));

        System.out.println("------ 3.1 Numero no primo ------");
        System.out.println(isPrimo(1));
        System.out.println(isPrimo(8));
        System.out.println(isPrimo(9));

        System.out.println("------4. palabra  palindromo  ------");
        System.out.println(isPalindromo("acurruca"));
        System.out.println(isPalindromo("malayalam"));
        System.out.println(isPalindromo("aerea"));

        System.out.println("------ 3.1 palabra no palindromo  ------");
        System.out.println(isPalindromo("Otorringolaringolo"));
        System.out.println(isPalindromo("casa "));
        System.out.println(isPalindromo("zapato"));



    }

    public static String isPalindromo (String palabra){
        CambiadorClase<String, Boolean> validadorPalindromo = (String x)->{
            return x.equalsIgnoreCase(new StringBuilder(x).reverse().toString());
        };
        return  validadorPalindromo.validacion(palabra) ? "La palabra " + palabra + " ES palindromo" :
                "La palabra " + palabra + " NO ES palindromo";
    }

    public static int suma (int a,int b){
        OperacionMismaClase<Integer> sumar = (x,y) -> x+y;
        return sumar.operacionDosArgumentos(a,b);
    }

    public static
    String isPrimo (int numero){
        CambiadorClase<Integer,Boolean> validador = x -> {
            return x >1 && java.util.stream.IntStream.range(2, x).noneMatch(i -> x % i == 0);
        };
        return validador.validacion(numero)? "El numero " + numero + " es primo" : "El numero " + numero + " NO es primo";

    }

    public static int sumaLista (List<Integer> listaNumeros){
        return listaNumeros.stream().collect(Collectors.summingInt(Integer::intValue));
    }

    public static List<String> ordenarStrings(List<String> lista){
         lista.sort((string1,string2) -> string1.compareTo(string2));
        return lista;
    }


    // Interfaces funcionales para las funciones lambda
    interface OperacionMismaClase<V> {
        V operacionDosArgumentos(V x, V y);
    }

    interface CambiadorClase<V,R> {
        R validacion (V x);
    }

}

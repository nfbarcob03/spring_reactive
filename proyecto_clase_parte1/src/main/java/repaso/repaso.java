package repaso;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public
class repaso {
    public static
    void main(String[] args) {
        //System.out.println("Hola, mundo!");

        List<Item> inventory = Arrays.asList(
                new Item("apple", 9.99),
                new Item("orange", 7.99),
                new Item("watermelon", 11.99),
                new Item("apple", 9.99),
                new Item("apple", 9.99),
                new Item("orange", 7.99),
                new Item("lemon", 6.99),
                new Item("apple", 9.99),
                new Item("watermelon", 11.99),
                new Item("papaya", 11.99),
                new Item("banana", 9.99)
        );
        System.out.println("----------------------Secuencial--------------------");
        System.out.println(groupByPriceSequenceCompleteObject(inventory));

        System.out.println(groupByPriceSequenceNameObject(inventory));

        System.out.println("----------------------Lambda--------------------");
        var resultadoWithGroupByComplete = inventory.stream().collect(groupingBy(Item::getPrice));

        System.out.println(resultadoWithGroupByComplete);

        var resultadoWithGroupByOnlyName = inventory.stream().collect(groupingBy(Item::getPrice,
                                                                                 Collectors.mapping(Item::getName, Collectors.toList())));
        System.out.println(resultadoWithGroupByOnlyName);
    }

    public static
    Map<Double, List<Item>> groupByPriceSequenceCompleteObject(List<Item> listaItems){

        Map<Double,List<Item>> resultado = new HashMap<>();

        for (Item item:listaItems){
            if (!resultado.containsKey(item.getPrice())){
                var lista = new  ArrayList<Item>();
                lista.add(item);
                resultado.put(item.getPrice(),lista);
            }else{
                resultado.get(item.getPrice()).add(item);
            }
        }
        return resultado;
    }

    public static
    Map<Double, List<String>> groupByPriceSequenceNameObject(List<Item> listaItems){

        Map<Double,List<String>> resultado = new HashMap<>();

        for (Item item:listaItems){
            if (!resultado.containsKey(item.getPrice())){
                var lista = new  ArrayList<String>();
                lista.add(item.getName());
                resultado.put(item.getPrice(),lista);
            }else{
                resultado.get(item.getPrice()).add(item.getName());
            }
        }
        return resultado;
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 * @author Przemo
 */
public class RankerHelper {
    
    /**
     * Produces a sum of the cards value if all provided have equal value
     * @param pair
     * @param n
     * @return
     * @throws Exception 
     */
    public static int getSingleMulti(Set<Integer> pair, int n) throws Exception{
        if(pair.size()==n){
            int p = pair.stream().mapToInt(i->i).sum();
            return p%n==0 ? p : 0;
        } else {
            throw new Exception("A "+ n + " element set must be provided to establish a pair.");
        }
    }
    
    /**
     * Check if all cards in the set are of the same suit
     * @param cards
     * @return 
     */
    public static boolean sameColour(Set<Integer> cards){
        return cards.stream().mapToInt(i->i/13).distinct().count()==1;
    }
    
    public static boolean cardsInSequence(Set<Integer> cards) throws Exception{
        if(cards.size()==5){
            TreeSet<Integer> sint = new TreeSet(cards);
            int sum;
            sum = cards.stream().collect(Collectors.summingInt(i->i));
            return (sum==sint.size()*(sint.first()+sint.last())/2);
        } else {
            throw new Exception("More than 5 cards examined for a sequence.");
        }
    }
}

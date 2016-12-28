/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Provide a set of cards on the table and the hand
 * @author pnowicki
 */
public class Ranker {
    
    private final Set<Integer> cardsInt;
    
    public Ranker(final Set<Card> cards){
        cardsInt= new TreeSet<>();
        cards.stream().forEach(c -> cardsInt.add(CardCoder.code(c)));
    }
    
    public int rankCardSet(){
        return 0;
    }
    
    protected int isRoyalFlush(){
        //of all those cards, best set will have to be picked. This will be done from the top of the hierarchy
        //is there a poker? (13, 14, 12, 11, 1)
        int i=0;
        while(i<4 && !cardsInt.containsAll(Arrays.asList(i*13+13,i*13+14,i*13+12,i*13+11,i*13+1))){
            i++;
        }
        return i<4? 9: 0;
    }
    
    protected int isStraightFlush(){
        return sameColour(cardsInt) && buildPermutations(cardsInt).stream().anyMatch(s -> {
            try {
                cardsInSequence(s);
            } catch (Exception ex) {
                Logger.getLogger(Ranker.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }) ? 8:0;
    } 
    
    protected int isFourOfAKind(){
        
        return 7;
    }
    
    protected int isFullHouse(){
        return 6;
    }
    
    protected int isFlush(){
        return 5;
    }
    
    protected int isStraight(){
        return 4;
    }
    
    protected int isThreeOfAKind(){
        return 3;
    }
    
    protected int isTwoPair(){
        return 2;
    }
    
    protected int isPair(){
        return 1;
    }
    
    private boolean sameColour(Set<Integer> cards){
        Iterator<Integer> p = cardsInt.iterator();
        int pint = p.next()/13;
        int v = 0;
        while(p.hasNext() && p.next()/13==pint){
            v++;
        }
        return v==cards.size();
    }
    
    private boolean cardsInSequence(Set<Integer> cards) throws Exception{
        if(cards.size()==5){
            TreeSet<Integer> sint = new TreeSet(cards);
            int sum;
            sum = cards.stream().collect(Collectors.summingInt(i->i));
            return (sum==sint.size()*(sint.first()+sint.last())/2);
        } else {
            throw new Exception("More than 5 cards examined for a sequence.");
        }
    }
    
    private List<Set<Integer>> buildPermutations(Set<Integer> cards){
        List<Set<Integer>> ret = new ArrayList<>();
        for(int i=0; i< (1 << cards.size()); i++){
            Set processed = processSubset(cardsInt, i);
            if(processed.size()==5){
                ret.add(processed);
            }    
        }
        return ret;
    }
    
    private Set<Integer> processSubset(Set<Integer> set, int signature){
        Set<Integer> ret = new TreeSet<>();
        int mask = 1;
        int[] pit = new int[set.size()-1];
        Iterator<Integer> piter = set.iterator();
        int v=0;
        while(v!=pit.length && piter.hasNext()){
            pit[v]=piter.next();
        }
        for(int i=0; i<set.size(); i++){
            if((mask & signature) > 0){
                ret.add(pit[i]);
            }
            mask = mask << 1;
        }
        return ret;
    }
}

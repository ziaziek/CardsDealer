/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Provide a set of cards on the table and the hand
 *
 * @author pnowicki
 */
public class Ranker {

    private final Set<Integer> cardsInt; //this set should always have size 7
    private final Set<Integer> kickers;
    private final Set<Integer> deck;
    private final List<Set<Integer>> fiveCardPermutations;

    public Ranker(final Set<Card> deckCards, final Set<Card> handCards) throws Exception {
        if (deckCards == null || handCards == null || deckCards.size() != 5 || handCards.size() != 2) {
            throw new Exception("The size of the deck cards stack should be 5 and the hand stack 2.");
        } else {
            cardsInt = new TreeSet<>();
            deck = new TreeSet<>();
            kickers = new TreeSet<>();
            deckCards.stream().forEach(c -> deck.add(CardCoder.code(c)));
            handCards.stream().forEach(c -> kickers.add(CardCoder.code(c)));
            cardsInt.addAll(deck);
            cardsInt.addAll(kickers);
            fiveCardPermutations = buildPermutations(cardsInt, 5);
        }

    }

    /**
     * Ranks the entire card set, picking the highest possible hand. Includes the kickers from hand
     * @return points for the best possible set
     */
    public int rankCardSet() throws Exception {
        //Ranking starts from the highest possible rank to test against
        //This could be done as well with delegates - interface and classes implementing, put them all in an array or list and do the check in a while loop until p!=0
        //However, as this is a limited set of checks and there are not so many, as of now, the nested if's is a good choice.
        //Should the ranker rank hands for other games, an abstract class or interface shold be created for it and then the above described solution should be implemented.
        int kicker = kickers.stream().mapToInt(c->c%13).max().orElse(0);
        int p = isRoyalFlush();
        if(p==0){
            p=isStraightFlush();
            if(p==0){
                p=isFourOfAKind();
                if(p==0){
                    p=isFullHouse();
                    if(p==0){
                        p=isFlush();
                        if(p==0){
                            p=isStraight();
                            if(p==0){
                                p=isThreeOfAKind();
                                if(p==0){
                                    p=isTwoPair();
                                    if(p==0){
                                        p=isPair();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return p+kicker;
    }

        protected int isRoyalFlush() throws Exception {
        //of all those cards, best set will have to be picked. This will be done from the top of the hierarchy
        //is there a poker? (13, 14, 12, 11, 1)
        for(Set<Integer> s : fiveCardPermutations){
            if(RankerHelper.cardsInSequence(s) && RankerHelper.sameColour(s) && s.stream().mapToInt(c->c%13).distinct().boxed().collect(Collectors.toList()).contains(12)){
                return 1000;
            }
        }
        return 0;
    }

    protected int isStraightFlush() throws Exception {
        for(Set<Integer> s: fiveCardPermutations){
            if(RankerHelper.cardsInSequence(s) && RankerHelper.sameColour(s)){
                return 900+s.stream().mapToInt(c->c%13).sum();
            }
        }
        return 0;
    }

    protected int isFourOfAKind() {
        List<Set<Integer>> fours = buildPermutations(cardsInt, 4);
        int value = 0;
        boolean isFour = false;
        for (Set<Integer> set : fours) {
            int s0 = 0, s1 = 0;
            int p = 0;
            for (Integer n : set) {
                p = n % 13;
                if (s0 == 0) {
                    s0 = set.size() * p;
                }
                s1 += p;
            }
            if (s1 == s0) {
                value = p;
                isFour = true;
            }
            if (isFour) {
                break;
            }
        }
        return value == 0 ? value : 800 + 4 * value;
    }

    protected int isFullHouse() {
        //for each 5-card combination check that there are only 2-ranked sets
        for (Set<Integer> s : fiveCardPermutations) {
            List<Integer> strans = new ArrayList<>(s.stream().mapToInt(c -> c % 13).boxed().collect(Collectors.toList()));
            TreeSet<Integer> p = new TreeSet<>(strans);
            if (p.stream().distinct().count() == 2) {
                //See if the cards frequencies are 2, 3 or 3,2
                if ((Collections.frequency(strans, p.first()) == 3 && Collections.frequency(strans, p.last()) == 2) || (Collections.frequency(strans, p.first()) == 2 && Collections.frequency(strans, p.last()) == 3)) {
                    return 700 + strans.stream().mapToInt(c -> c).sum();
                }
            }
        }
        return 0;
    }

    //same suit
    protected int isFlush() {
        for (Set<Integer> s : fiveCardPermutations) {
            if (RankerHelper.sameColour(s)) {
                return 600 + s.stream().mapToInt(c -> c%13).sum();
            }
        }
        return 0;
    }

    protected int isStraight() throws Exception {
        for (Set<Integer> s : fiveCardPermutations) {
            if (RankerHelper.cardsInSequence(s) && !RankerHelper.sameColour(s)) {
                return 500 + s.stream().mapToInt(c -> c%13).sum();
            }
        }
        return 0;
    }

    protected int isThreeOfAKind() {

        try {
            for(Set<Integer> cs : buildPermutations(cardsInt, 3)){
                int v = RankerHelper.getSingleMulti(cs, 3);
                if(v>0){
                    return 400 + v;
                }
            }
            return 0;
        } catch (Exception ex) {
            Logger.getLogger(Ranker.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    protected int isTwoPair() {
        List<Integer> cardsRanks = new ArrayList<>();
        cardsInt.forEach((p) -> {
            cardsRanks.add(p % 13);
        });
        cardsRanks.stream().forEach(x -> System.out.print(x+","));
        Set<Integer> p = new TreeSet<>(cardsRanks.stream().distinct().collect(Collectors.toSet()));
        if (p.stream().count() == 5) {//We've got two pairs
            int np = 0;
            np = cardsRanks.stream().filter((i) -> (Collections.frequency(cardsRanks, i)==2)).map((i) ->  i).reduce(np, Integer::sum);
            return 300 + np;
        } else {
            return 0;
        }
    }

    protected int isPair() {
        try {
            for (Set<Integer> s : buildPermutations(cardsInt, 2)) {
                s.stream().forEach(x -> System.out.print(x + ","));
                int sint = RankerHelper.getSingleMulti(s, 2);
                if (sint > 0) {
                    return 200 + sint;
                }
            }
            return 0;
        } catch (Exception ex) {
            Logger.getLogger(Ranker.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    private List<Set<Integer>> buildPermutations(Set<Integer> cards, int setSize) {
        cards.stream().forEach(x -> System.out.print(x + ","));
        List<Set<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < (1 << cards.size()); i++) {
            Set processed = processSubset(cardsInt, i);
            if (processed.size() == setSize) {
                ret.add(processed);
            }
        }
        System.out.println();
        return ret;
    }

    private Set<Integer> processSubset(Set<Integer> set, int signature) {
        Set<Integer> ret = new TreeSet<>();
        int mask = 1;
        int[] pit = new int[set.size()];
        Iterator<Integer> piter = set.iterator();
        int v = 0;
        while (v != pit.length && piter.hasNext()) {
            pit[v] = piter.next();
            v++;
        }
        for (int i = 0; i < set.size(); i++) {
            if ((mask & signature) > 0) {
                ret.add(pit[i]);
            }
            mask = mask << 1;
        }
        return ret;
    }
}

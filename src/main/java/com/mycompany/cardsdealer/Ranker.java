/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.ArrayList;
import java.util.Arrays;
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

    public int rankCardSet() {
        //Ranking starts from the highest possible rank to test against
        
        return 0;
    }

    protected int isRoyalFlush() {
        //of all those cards, best set will have to be picked. This will be done from the top of the hierarchy
        //is there a poker? (13, 14, 12, 11, 1)
        int i = 0;
        while (i < 4 && !cardsInt.containsAll(Arrays.asList(i * 13 + 13, i * 13 + 14, i * 13 + 12, i * 13 + 11, i * 13 + 1))) {
            i++;
        }
        return i < 4 ? 100 : 0;
    }

    protected int isStraightFlush() {
        return RankerHelper.sameColour(cardsInt) && fiveCardPermutations.stream().anyMatch(s -> {
            try {
                RankerHelper.cardsInSequence(s);
            } catch (Exception ex) {
                Logger.getLogger(Ranker.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }) ? 90 : 0;
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
        return value == 0 ? value : 4 * (10 + value); //min = 44 max = 80
    }

    protected int isFullHouse() {
        //for each 5-card combination check that there are only 2-ranked sets
        for (Set<Integer> s : fiveCardPermutations) {
            TreeSet<Integer> p = new TreeSet<>(s.stream().mapToInt(c -> c % 13).boxed().collect(Collectors.toSet()));
            if (p.stream().distinct().count() == 2) {
                //See if the cards frequencies are 2, 3 or 3,2
                if ((Collections.frequency(p, p.first()) == 3 && Collections.frequency(p, p.last()) == 2) || (Collections.frequency(p, p.first()) == 2 && Collections.frequency(p, p.last()) == 2)) {
                    return 6 * p.stream().mapToInt(c -> c).sum();
                }
            }
        }
        return 0;
    }

    //same suit
    protected int isFlush() {
        for (Set<Integer> s : fiveCardPermutations) {
            if (RankerHelper.sameColour(s)) {
                return 5 * s.stream().mapToInt(c -> c).sum();
            }
        }
        return 0;
    }

    protected int isStraight() throws Exception {
        for (Set<Integer> s : fiveCardPermutations) {
            if (RankerHelper.cardsInSequence(s) && !RankerHelper.sameColour(s)) {
                return 4 * s.stream().mapToInt(c -> c).sum();
            }
        }
        return 0;
    }

    protected int isThreeOfAKind() {

        try {
            for(Set<Integer> cs : buildPermutations(cardsInt, 3)){
                int v = RankerHelper.getSingleMulti(cs, 3);
                if(v>0){
                    return v;
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
            np = cardsRanks.stream().filter((i) -> (Collections.frequency(cardsRanks, i)==2)).map((i) -> 2 * i).reduce(np, Integer::sum);
            return np;
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
                    return sint;
                }
                System.out.println(" Sint=" + sint);
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

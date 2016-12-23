/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

/**
 *
 * @author pnowicki
 */
public class RankedPlayer extends Player implements Comparable<RankedPlayer>{
    
    int rank = 0;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(RankedPlayer o) {
        return Integer.compare(this.getRank(), o.getRank());
    }
    
}

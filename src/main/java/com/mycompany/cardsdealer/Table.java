/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pnowicki
 */
public abstract class Table {
    
    protected CardStack stack;
    protected List<Card> cardsOnTable;

    public List<Card> getCardsOnTable() {
        return cardsOnTable;
    }
    
    protected List<Player> players;
    
    public Table(){
        cardsOnTable = new ArrayList<>();
    };
    
    public Table(CardStack stack, List<Player> players){
        this();
        this.stack = stack;
        this.players=players;
    }
    
    public abstract void giveCardsToPlayers();
    
    public abstract void giveCardsToTableStack(int numberOfCards);
}

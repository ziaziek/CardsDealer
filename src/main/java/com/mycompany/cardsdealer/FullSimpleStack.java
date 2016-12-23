/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardsdealer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


/**
 *
 * @author pnowicki
 */
public class FullSimpleStack implements CardStack{
    
    private final int maxCards = 52;
    private final int shuffleIteration = 100;
    
    private Queue<Card> cards;

    public Queue<Card> getCards() {
        return cards;
    }

    public FullSimpleStack() {
        this.cards = new LinkedList<>();
        freshenStack();
    }

    @Override
    public Card[] draw(int numberOfCards) {
        int finalDraw = numberOfCards;
        if(cards.size()<numberOfCards){
            finalDraw = cards.size();
        }
        Card[] r = new Card[finalDraw];
        for(int i=0; i<finalDraw; i++){
            r[i]=cards.poll();
        }
        return r;
    }

    @Override
    public void shuffle() {
        Random r = new Random();
        
        if(cards.isEmpty()){
            freshenStack();
        }
        Queue<Card> q = cards;
        Card[] cardsArray = q.toArray(new Card[q.size()-1]);
        
        for(int i=0; i<shuffleIteration;i++){
            int p = r.nextInt(q.size());
            int qp = r.nextInt(q.size());
            Card crd = cardsArray[p];
            cardsArray[p]=cardsArray[qp];
            cardsArray[qp]=crd;       
        }
        
        cards=new LinkedList<>(Arrays.asList(cardsArray));
    }

    @Override
    public void returnCard(Card c) {
        if(cards.size()<maxCards){
            cards.offer(c);
        }
    }

    private void freshenStack() {
        for(int i=0; i<maxCards; i++){
            cards.offer(CardCoder.decode(i));
        }
    }
    
    
}

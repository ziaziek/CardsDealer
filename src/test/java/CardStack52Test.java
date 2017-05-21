/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.cardsdealer.Card;
import com.mycompany.cardsdealer.Card52Stack;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Przemo
 */
public class CardStack52Test {
    

    
    public CardStack52Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void initTest(){
        Card52Stack st = new Card52Stack();
        Assert.assertNotNull(st.getCards());
        Assert.assertEquals(52, st.getCards().size());
        Assert.assertNotNull(st.getCards().peek());
    }
    
    @Test
    public void shuffleTest(){
        Card52Stack st = new Card52Stack();
        Card c = st.getCards().peek();
        int i=0;
        while(i<5 && st.getCards().peek().equals(c)){
            i++;
            st.shuffle();
        }
        Assert.assertTrue(i<5);
    }
    
    @Test
    public void drawTest(){
        Card52Stack st = new Card52Stack();
        Card[] c = st.draw(1);
        Assert.assertNotNull(c);
        Assert.assertNotNull(c[0]);
        Assert.assertEquals(51, st.getCards().size());
        c = st.draw(52);
        Assert.assertNotNull(c);
        Assert.assertNotNull(c[0]);
        Assert.assertEquals(51, c.length);
        Assert.assertEquals(0, st.getCards().size());
    }
    
    @Test
    public void testReturnCard(){
        Card52Stack st = new Card52Stack();
        Card c = st.draw(1)[0];
        st.returnCard(c);
        Assert.assertEquals(52, st.getCards().size());
        Assert.assertNotSame(c, st.getCards().peek());
        int i=0;
    }
}

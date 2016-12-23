/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.cardsdealer.Card;
import com.mycompany.cardsdealer.FullSimpleStack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pnowicki
 */
public class FullSimpleStackTests {
    
    
    public FullSimpleStackTests() {
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
    public void constructorTest(){
        FullSimpleStack stack = new FullSimpleStack();
        Assert.assertNotNull(stack.getCards());
        Assert.assertNotNull(stack.getCards().element());
        Assert.assertEquals(52, stack.getCards().size());
        Assert.assertNotEquals(stack.getCards().peek(), stack.getCards().toArray(new Card[stack.getCards().size()-1])[3]);
    }
    
    @Test
    public void drawTest(){
        FullSimpleStack stack = new FullSimpleStack();
        Assert.assertNotEquals(stack.draw(1), stack.draw(1));
        Assert.assertEquals(50, stack.getCards().size());
    }
    
    @Test
    public void shuffleTest(){
        FullSimpleStack stack = new FullSimpleStack();
        int repetitions=0;
        for(int i=0; i<5; i++){
          Card c = stack.getCards().element();
          stack.shuffle();  
          if(c.equals(stack.getCards().element())){
              repetitions++;
          }
        }   
        Assert.assertTrue(repetitions<3);
    }
}

package pokerBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pokerEnums.eHandStrength;
import pokerEnums.eRank;
import pokerEnums.eSuit;

public class Hand_Test {
	Deck d = new Deck();
	Hand h;
	ArrayList<Integer> emptylist;
	@Before
	public void setUp() throws Exception {
		h = new Hand(d);
		emptylist = new ArrayList<Integer>();
		
	}

	@After
	public void tearDown() throws Exception {
		h = null;
	}

	@Test
	public void AddTest() {
		Card c = new Card(eSuit.CLUBS,eRank.KING,false);
		h.AddCardToHand(c);
		assertTrue(h.GetCardFromHand(5) == c);
	}
	@Test
	public void eval4oaKTest(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.KING,false);
		Card c3 = new Card(eSuit.HEARTS,eRank.KING,false);
		Card c4 = new Card(eSuit.DIAMONDS,eRank.KING,false);
		Card c5 = new Card(eSuit.HEARTS,eRank.FIVE,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue(h1.getHandStrength()== eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h1.getHighPairStrength() == eRank.KING.getRank());
		assertTrue(h1.getLowPairStrength() == 0);
		assertTrue(h1.getKicker().get(0) == eRank.FIVE.getRank());
	}
	@Test
	public void evalRoyalFlushTest(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.ACE,false);
		Card c2 = new Card(eSuit.SPADES,eRank.KING,false);
		Card c3 = new Card(eSuit.SPADES,eRank.QUEEN,false);
		Card c4 = new Card(eSuit.SPADES,eRank.JACK,false);
		Card c5 = new Card(eSuit.SPADES,eRank.TEN,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue(h1.getHandStrength()== eHandStrength.RoyalFlush.getHandStrength());
		assertTrue(h1.getHighPairStrength() == 0);
		assertTrue(h1.getLowPairStrength() == 0);
		assertTrue(h1.getKicker().isEmpty());
	
	}
	@Test
	public void evalStraightFlushTestwithACE(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.ACE,false);
		Card c2 = new Card(eSuit.SPADES,eRank.TWO,false);
		Card c3 = new Card(eSuit.SPADES,eRank.THREE,false);
		Card c4 = new Card(eSuit.SPADES,eRank.FOUR,false);
		Card c5 = new Card(eSuit.SPADES,eRank.FIVE,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.StraightFlush.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.FIVE.getRank());
		assertTrue("3",h1.getLowPairStrength() == 0);
		assertTrue("4",h1.getKicker().isEmpty());
	
	}
	@Test
	public void evalStraightFlushTestnoACE(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.SIX,false);
		Card c2 = new Card(eSuit.SPADES,eRank.TWO,false);
		Card c3 = new Card(eSuit.SPADES,eRank.THREE,false);
		Card c4 = new Card(eSuit.SPADES,eRank.FOUR,false);
		Card c5 = new Card(eSuit.SPADES,eRank.FIVE,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.StraightFlush.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.SIX.getRank());
		assertTrue("3",h1.getLowPairStrength() == 0);
		assertTrue("4",h1.getKicker().isEmpty());
	
	}	
	@Test
	public void eval5oaKindTest(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.ACE,false);
		Card c2 = new Card(eSuit.HEARTS,eRank.ACE,false);
		Card c3 = new Card(eSuit.SPADES,eRank.ACE,false);
		Card c4 = new Card(eSuit.DIAMONDS,eRank.ACE,false);
		Card c5 = new Card(eSuit.CLUBS,eRank.ACE,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		System.out.println(h1.getHandStrength());
		assertTrue("5of a kind",h1.getHandStrength()== eHandStrength.FiveOfAKind.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.ACE.getRank());
		assertTrue("3",h1.getLowPairStrength() == 0);
		assertTrue("4",h1.getKicker().isEmpty());
	
	}
	@Test
	public void evalFullHouseTest(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.KING,false);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.QUEEN,false);
		Card c4 = new Card(eSuit.HEARTS,eRank.QUEEN,false);
		Card c5 = new Card(eSuit.SPADES,eRank.QUEEN,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.FullHouse.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.QUEEN.getRank());
		assertTrue("3",h1.getLowPairStrength() == eRank.KING.getRank());
		assertTrue("4",h1.getKicker().isEmpty());
	
	}
	@Test
	public void evalFullHouseTest2(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.KING,false);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.KING,false);
		Card c4 = new Card(eSuit.HEARTS,eRank.QUEEN,false);
		Card c5 = new Card(eSuit.SPADES,eRank.QUEEN,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.FullHouse.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.KING.getRank());
		assertTrue("3",h1.getLowPairStrength() == eRank.QUEEN.getRank());
		assertTrue("4",h1.getKicker().isEmpty());
	
	}
	@Test
	public void eval3oaKind(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.KING,false);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.KING,false);
		Card c4 = new Card(eSuit.HEARTS,eRank.TEN,false);
		Card c5 = new Card(eSuit.SPADES,eRank.FIVE,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.KING.getRank());
		assertTrue("3",h1.getLowPairStrength() == 0);
		assertTrue("4",h1.getKicker().get(0)== eRank.TEN.getRank());
		assertTrue("4",h1.getKicker().get(1)== eRank.FIVE.getRank());
	
	}
	@Test
	public void eval3oaKind2(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.KING,false);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.KING,false);
		Card c4 = new Card(eSuit.HEARTS,eRank.TEN,false);
		Card c5 = new Card(eSuit.SPADES,eRank.ACE,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.KING.getRank());
		assertTrue("3",h1.getLowPairStrength() == 0);
		assertTrue("4",h1.getKicker().get(0)== eRank.ACE.getRank());
		assertTrue("4",h1.getKicker().get(1)== eRank.TEN.getRank());
	
	}
	@Test
	public void eval3oaKind3(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.TEN,false);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.TEN,false);
		Card c4 = new Card(eSuit.HEARTS,eRank.TEN,false);
		Card c5 = new Card(eSuit.SPADES,eRank.ACE,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue("3",h1.getLowPairStrength() == 0);
		assertTrue("4",h1.getKicker().get(0)== eRank.ACE.getRank());
		assertTrue("4",h1.getKicker().get(01)== eRank.KING.getRank());
	
	}
	@Test
	public void eval2Pair(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.KING,false);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.ACE,false);
		Card c4 = new Card(eSuit.HEARTS,eRank.TEN,false);
		Card c5 = new Card(eSuit.SPADES,eRank.ACE,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.TwoPair.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.ACE.getRank());
		assertTrue("3",h1.getLowPairStrength() == eRank.KING.getRank());
		assertTrue("4",h1.getKicker().get(0) == eRank.TEN.getRank());
	
	}
	@Test
	public void eval2Pair2(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.KING,false);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.ACE,false);
		Card c4 = new Card(eSuit.HEARTS,eRank.TEN,false);
		Card c5 = new Card(eSuit.SPADES,eRank.TEN,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.TwoPair.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.KING.getRank());
		assertTrue("3",h1.getLowPairStrength() == eRank.TEN.getRank());
		assertTrue("4",h1.getKicker().get(0)== eRank.ACE.getRank());
	
	}
	@Test
	public void eval2Pair3(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.KING,false);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.JACK,false);
		Card c4 = new Card(eSuit.HEARTS,eRank.TEN,false);
		Card c5 = new Card(eSuit.SPADES,eRank.TEN,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.TwoPair.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.KING.getRank());
		assertTrue("3",h1.getLowPairStrength() == eRank.TEN.getRank());
		assertTrue("4",h1.getKicker().get(0)== eRank.JACK.getRank());
	
	}
	@Test
	public void evalPair(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.KING,false);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.QUEEN,false);
		Card c4 = new Card(eSuit.HEARTS,eRank.TEN,false);
		Card c5 = new Card(eSuit.SPADES,eRank.JACK,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.Pair.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.KING.getRank());
		assertTrue("3",h1.getLowPairStrength() == 0);
		assertTrue("4",h1.getKicker().get(0)== eRank.QUEEN.getRank());
		assertTrue("4",h1.getKicker().get(1)== eRank.JACK.getRank());
		assertTrue("4",h1.getKicker().get(2)== eRank.TEN.getRank());
	
	}
	@Test
	public void evalPair2(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.QUEEN,false);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.QUEEN,false);
		Card c4 = new Card(eSuit.HEARTS,eRank.TEN,false);
		Card c5 = new Card(eSuit.SPADES,eRank.JACK,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.Pair.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.QUEEN.getRank());
		assertTrue("3",h1.getLowPairStrength() == 0);
		assertTrue("4",h1.getKicker().get(0)== eRank.KING.getRank());
		assertTrue("4",h1.getKicker().get(1)== eRank.JACK.getRank());
		assertTrue("4",h1.getKicker().get(2)== eRank.TEN.getRank());
	
	}
	@Test
	public void evalPair3(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.JACK,false);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.QUEEN,false);
		Card c4 = new Card(eSuit.HEARTS,eRank.TEN,false);
		Card c5 = new Card(eSuit.SPADES,eRank.JACK,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.Pair.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.JACK.getRank());
		assertTrue("3",h1.getLowPairStrength() == 0);
		assertTrue("4",h1.getKicker().get(0)== eRank.KING.getRank());
		assertTrue("4",h1.getKicker().get(1)== eRank.QUEEN.getRank());
		assertTrue("4",h1.getKicker().get(2)== eRank.TEN.getRank());
	
	}
	@Test
	public void evalPair4(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.TEN,false);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.QUEEN,false);
		Card c4 = new Card(eSuit.HEARTS,eRank.TEN,false);
		Card c5 = new Card(eSuit.SPADES,eRank.JACK,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.Pair.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue("3",h1.getLowPairStrength() == 0);
		assertTrue("4",h1.getKicker().get(0)== eRank.KING.getRank());
		assertTrue("4",h1.getKicker().get(1)== eRank.QUEEN.getRank());
		assertTrue("4",h1.getKicker().get(2)== eRank.JACK.getRank());
	
	}
	@Test
	public void evalHighCard(){
		Hand h1 = new Hand();
		Card c1= new Card(eSuit.SPADES,eRank.KING,false);
		Card c2 = new Card(eSuit.CLUBS,eRank.TWO,false);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.QUEEN,false);
		Card c4 = new Card(eSuit.HEARTS,eRank.TEN,false);
		Card c5 = new Card(eSuit.SPADES,eRank.JACK,false);
		h1.AddCardToHand(c1);
		h1.AddCardToHand(c2);
		h1.AddCardToHand(c3);
		h1.AddCardToHand(c4);
		h1.AddCardToHand(c5);
		h1.EvalHand();
		assertTrue("1",h1.getHandStrength()== eHandStrength.HighCard.getHandStrength());
		assertTrue("2",h1.getHighPairStrength() == eRank.KING.getRank());
		assertTrue("3",h1.getLowPairStrength() == 0);
		assertTrue("4",h1.getKicker().get(0)== eRank.QUEEN.getRank());
		assertTrue("4",h1.getKicker().get(1)== eRank.JACK.getRank());
		assertTrue("4",h1.getKicker().get(2)== eRank.TEN.getRank());
		assertTrue("4",h1.getKicker().get(3)== eRank.TWO.getRank());
	
	}
	@Test
	public void ComparatorTest(){
		Hand h1 = new Hand();
		Hand h2 = new Hand();
		
		h1.setHandStrength(80);
		h2.setHandStrength(40);
		
		assertEquals(Hand.HandRank.compare(h1,h2), -40);
	}
	@Test
	public void ComparatorTest2(){
		Hand h1 = new Hand();
		Hand h2 = new Hand();
		
		h1.setHandStrength(80);
		h2.setHandStrength(80);
		h1.setHiHand(10);
		h2.setHiHand(12);
		assertEquals(Hand.HandRank.compare(h1,h2), 2);

	}
	@Test
	public void ComparatorTest3(){
		Hand h1 = new Hand();
		Hand h2 = new Hand();
		
		h1.setHandStrength(70);
		h2.setHandStrength(70);
		h1.setHiHand(10);
		h2.setHiHand(10);
		h1.setLoHand(10);
		h2.setLoHand(12);
		
		
		assertEquals(Hand.HandRank.compare(h1,h2), 2);

	}
}

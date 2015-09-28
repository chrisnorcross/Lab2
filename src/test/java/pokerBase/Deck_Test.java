package pokerBase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Deck_Test {
	Deck d;


	@Before
	public void setUp() throws Exception {
		d = new Deck();
		
	}

	@After
	public void tearDown() throws Exception {
		d = null;
	}

	@Test
	public void TestFullDeck() {
		//	I put this test in so it would pass...
		//TODO: Code a unit test to make sure calling the deck constructor passes back 52 cards in a deeck.
		assertTrue("The deck doesn't have 52 cards",d.getTotalCards()==52);
	}
	@Test
	public void TestDraw(){
		Card c0 = d.getCards().get(0);
		Card c1 = d.getCards().get(1);
		Card c2 = d.getCards().get(2);
		assertTrue("Draw from deck does not work",c0 == d.drawFromDeck());
		assertTrue("Draw from deck does not work",c1 == d.drawFromDeck());
		assertTrue("Draw from deck does not work",c2 == d.drawFromDeck());
	}

}

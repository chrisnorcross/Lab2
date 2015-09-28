package pokerBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;

import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;

public class Hand {
	private UUID playerID;
	@XmlElement
	private ArrayList<Card> CardsInHand;
	private ArrayList<Card> BestCardsInHand;

	@XmlElement
	private int HandStrength;
	public int getHiHand() {
		return HiHand;
	}
	public void setHiHand(int hiHand) {
		HiHand = hiHand;
	}
	public void setHandStrength(int handStrength) {
		HandStrength = handStrength;
	}
	public void setLoHand(int loHand) {
		LoHand = loHand;
	}
	public void setKicker(ArrayList<Integer> kicker) {
		Kicker = kicker;
	}

	@XmlElement
	private int HiHand;
	@XmlElement
	private int LoHand;
	@XmlElement
	private ArrayList<Integer> Kicker;

	private boolean bScored = false;

	private boolean Flush;
	private boolean Straight;
	private boolean Ace;
	private static Deck dJoker = new Deck();

	public Hand()
	{
		
	}
	public void  AddCardToHand(Card c)
	{
		if (this.CardsInHand == null)
		{
			CardsInHand = new ArrayList<Card>();
		}
		this.CardsInHand.add(c);
	}
	
	public Card  GetCardFromHand(int location)
	{
		return CardsInHand.get(location);
	}
	
	public Hand(Deck d) {
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			Import.add(d.drawFromDeck());
		}
		CardsInHand = Import;


	}


	public Hand(ArrayList<Card> setCards) {
		this.CardsInHand = setCards;
	}

	public ArrayList<Card> getCards() {
		return CardsInHand;
	}

	public ArrayList<Card> getBestHand() {
		return BestCardsInHand;
	}

	public void setPlayerID(UUID playerID)
	{
		this.playerID = playerID;
	}
	public UUID getPlayerID()
	{
		return playerID;
	}
	public void setBestHand(ArrayList<Card> BestHand) {
		this.BestCardsInHand = BestHand;
	}

	public int getHandStrength() {
		return HandStrength;
	}


	public ArrayList<Integer> getKicker() {
		return Kicker;
	}

	public int getHighPairStrength() {
		return HiHand;
	}

	public int getLowPairStrength() {
		return LoHand;
	}

	public boolean getAce() {
		return Ace;
	}

	public static Hand EvalHand(ArrayList<Card> SeededHand) {
		
		Deck d = new Deck();
		Hand h = new Hand(d);
		h.CardsInHand = SeededHand;

		return h;
	}

	public void EvalHand() {
		//Create array list for kickers
		ArrayList<Integer> myKicker = new ArrayList<Integer>();
		
		// Evaluates if the hand is a flush and/or straight then figures out
		// the hand's strength attributes

		// Sort the cards!
		Collections.sort(CardsInHand, Card.CardRank);

		// Ace Evaluation
//		// (Given in Spring Borgata)
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == eRank.ACE) {
			Ace = true;
		}

		// EVALUATE FLUSH
		// (Given in Spring Borgata)
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getSuit()) {
			Flush = true;
		} else {
			Flush = false;
		}

		
		// EVALUATE STRAIGHT
		// (Given in Spring Borgata)
		if (Ace) {
			// Looks for Ace, King, Queen, Jack, 10
			if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == eRank.KING
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.QUEEN
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo())
							.getRank() == eRank.JACK
					&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN) {
				Straight = true;
				// Looks for Ace, 2, 3, 4, 5
			} else if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TWO
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo())
							.getRank() == eRank.THREE
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.FOUR
					&& CardsInHand.get(eCardNo.SecondCard.getCardNo())
							.getRank() == eRank.FIVE) {
				Straight = true;
			} else {
				Straight = false;
			}
			// Looks for straight without Ace
			// (Given in Spring Borgata)
		} else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
				.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
				.getRank().getRank() + 1
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() + 2
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank() + 3
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank().getRank() + 4) {
			Straight = true;
		} else {
			Straight = false;
		}

		// EVALUATE ROYAL FLUSH
		// (Given in Spring Borgata)
		if (Straight == true
				&& Flush == true
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN
				&& Ace) {
			ScoreHand(eHandStrength.RoyalFlush, 0, 0, myKicker );
		}

		// EVALUATE STRAIGHT FLUSH WITH ACE
		else if (Straight == true && Flush == true && Ace) {
			ScoreHand(eHandStrength.StraightFlush,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
							.getRank(), 0, myKicker);
		}
		
		//EVALUATE SPRING FLUSH WITHOUT ACE
		// (Given in Spring Borgata)
		else if (Straight == true && Flush == true) {
			ScoreHand(eHandStrength.StraightFlush,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0, myKicker);
		}
		// EVALUATE FIVE OF A KIND
				// (Given in Spring Borgata)

		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank()) {
					ScoreHand(eHandStrength.FiveOfAKind,
							CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
									.getRank(), 0, myKicker);
				}

		// EVALUATE FOUR OF A KIND
		
		//First Case
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo())
					.getRank().getRank() ){
				
			//set myKicker
				myKicker.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank());
				
				//Score hand
				ScoreHand(eHandStrength.FourOfAKind, 
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					0, myKicker);
		}
		
		//Second Case
		else if ((CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
						.getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo())
						.getRank().getRank())){
				//set myKicker
			myKicker.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank());
				//Score Hand
				ScoreHand(eHandStrength.FourOfAKind, 
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(),
					0, myKicker);
		}

		//EVALUATE FULL HOUSE
		
		//First Case
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank()
						.getRank() ){
			
				//Score Hand
				ScoreHand(eHandStrength.FullHouse,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(),
					myKicker);
		}
		
		//Second Case
		else if(CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.SecondCard.getCardNo()).getRank()
						.getRank() ){
			
				//Score Hand
				ScoreHand(eHandStrength.FullHouse,
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					myKicker);
		}
		
		//EVALUATE THREE OF A KIND
		
		//First Case
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
					.getRank() == CardsInHand
					.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()){
			//make myKicker
			myKicker.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank());
			myKicker.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank());
			//Score Hand
			ScoreHand(eHandStrength.ThreeOfAKind,
				CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
				0,
				myKicker);
		}
		//Second Case
		else if(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()){
				//make myKicker
				myKicker.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank());
				myKicker.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank());
				
				//Score Hand
				ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(),
					0,myKicker);
		}
		
		//Third Case
		else if(CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
					.getRank() == CardsInHand
					.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()){
			//set myKicker
			myKicker.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank());
			myKicker.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank());
			
			//Score hand
			ScoreHand(eHandStrength.ThreeOfAKind,
				CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(),
				0,myKicker);
		}
		
		//EVALUATE TWO PAIR
		
		//First Case
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
						.getRank().getRank()
				&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()){
			//make myKicker
			myKicker.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank());
			//Score Hand
			ScoreHand(eHandStrength.TwoPair,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(),myKicker
					);
		}
		
		//Second Case
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
				.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
				.getRank().getRank()
		&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
				.getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()){
			//make myKicker
			myKicker.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank());
			//Score Hand
			ScoreHand(eHandStrength.TwoPair,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(),myKicker
					);
		}
		
		//Third Case
		else if(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
				.getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo())
				.getRank().getRank()
		&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
				.getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()){
			//make myKicker
			myKicker.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank());
			//Score Hand
			ScoreHand(eHandStrength.TwoPair,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(),
					myKicker);
		}	
		
		// EVALUATE PAIR
		
		//First Case
		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
			.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
			.getRank().getRank()){
			//make myKicker
			myKicker.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank());
			myKicker.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank());
			myKicker.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank());
			
			//Score Hand
			ScoreHand(eHandStrength.Pair,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					0,
					myKicker);
		}
		
		//Second Case
		else if(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
				.getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo())
				.getRank().getRank()){
				//make myKicker
				myKicker.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank());
				myKicker.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank());
				myKicker.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank());
				
				//Score Hand 
				ScoreHand(eHandStrength.Pair,
						CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(),
						0,
						myKicker);
			}
		
		//Third Case
		else if(CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
				.getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo())
				.getRank().getRank()){
				myKicker.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank());
				myKicker.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank());
				myKicker.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank());
				
				//Score Hand
				ScoreHand(eHandStrength.Pair,
						CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(),
						0,
						myKicker);
			}
		
		//Fourth Case
		else if(CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
				.getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo())
				.getRank().getRank()){
				myKicker.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank());
				myKicker.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank());
				myKicker.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank());
				
				//Score hand
				ScoreHand(eHandStrength.Pair,
						CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(),
						0,
						myKicker);
			}
	
		// High Card
		//	(Given in Spring Borgata)
		else {
			myKicker.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank());
			myKicker.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank());
			myKicker.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank());
			myKicker.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank());
			
			
			ScoreHand(eHandStrength.HighCard,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,myKicker);
		}
	}



	private void ScoreHand(eHandStrength hST, int HiHand, int LoHand, ArrayList<Integer> Kicker) {
		this.HandStrength = hST.getHandStrength();
		this.HiHand = HiHand;
		this.LoHand = LoHand;
		this.Kicker = Kicker;
		this.bScored = true;

	}

	/**
	 * Custom sort to figure the best hand in an array of hands
	 */
	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

		public int compare(Hand h1, Hand h2) {

			int result = 0;

			result = h2.getHandStrength() - h1.getHandStrength();

			if (result != 0) {
				return result;
			}

			result = h2.getHighPairStrength() - h1.getHighPairStrength();
			if (result != 0) {
				return result;
			}

			result = h2.getLowPairStrength() - h1.getLowPairStrength();
			if (result != 0) {
				return result;
			}
			
			
			result = h2.getKicker().get(0) - h1.getKicker().get(0);
			if (result != 0) {
				return result;
			}
			result = h2.getKicker().get(1)-h1.getKicker().get(1);
			if (result != 0){
				return result;
			}
			result  = h2.getKicker().get(2)-h1.getKicker().get(2);
			if (result != 0){
				return result;
				
			}
			result = h2.getKicker().get(3)-h1.getKicker().get(3);
			if (result != 0 ){
				return result;
			}

			return 0;
		}
	};
}

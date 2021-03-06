import javax.swing.JFrame;

public class EqualPairLevel extends EasyLevel {

	protected EqualPairLevel(ScoreCounterLabel score,TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(score,validTurnTime, mainFrame);
		super.turnsTakenCounter.setDifficultyModeLabel("Medium Level");
		
		
	}

	@Override
	protected boolean addToTurnedCardsBuffer(Card card) {
		
		this.turnedCardsBuffer.add(card);
		
		if(this.turnedCardsBuffer.size() == getCardsToTurnUp())
		{
			// there are two cards faced up
			// record the player's turn
			this.turnsTakenCounter.increment();
			// get the other card (which was already turned up)
			Card otherCard = (Card) this.turnedCardsBuffer.get(0);
			// the cards match, so remove them from the list (they will remain face up)
			//	if( otherCard.getNum() == card.getNum())
			if(otherCard.getRank() == card.getRank())	
			{
				this.turnedCardsBuffer.clear();
				this.scoreLabel.addScore(50);
			}
			// the cards do not match, so start the timer to turn them down
			else
				{
					this.turnDownTimer.start();
					this.scoreLabel.addScore(-5);
				}
		}
		return true;
	}

	@Override
	protected boolean turnUp(Card card) 
	{
		// the card may be turned
		if(this.turnedCardsBuffer.size() < getCardsToTurnUp()) 
		{
			return this.addToTurnedCardsBuffer(card);
		}
		// there are already the number of mode (two face up cards) in the turnedCardsBuffer
		return false;
	}

	@Override
	protected String getMode() 
	{
		// TODO Auto-generated method stub
		return "MediumMode";
	}



}

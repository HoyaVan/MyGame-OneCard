public class AIPlayer implements GameParticipantable
{

    @Override
    public int takeTurn(DeckAndPlayerHands deckAndPlayerCards, int cardsToDraw)
    {
        System.out.println("AI's turn...");

        // Try to find a valid card to play
        for (int i = 0; i < deckAndPlayerCards.getAiHand().size(); i++)
        {
            Card cardToPlay = deckAndPlayerCards.getAiHand().get(i);

            if (isValidPlay(cardToPlay, deckAndPlayerCards.getCurrentCardOnTable()))
            {
                // Play the card
                deckAndPlayerCards.playCardForAI(i);
                return cardsToDraw; // Return unchanged cardsToDraw
            }
        }

        // No valid card, AI draws a card
        deckAndPlayerCards.drawCardsForAI(cardsToDraw);

        return cardsToDraw; // Return unchanged cardsToDraw
    }
}
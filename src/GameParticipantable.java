// Week 4: Interfaces
public interface GameParticipantable
{
    int takeTurn(DeckAndPlayerHands game, int accumulatedDraws);

    default boolean isValidPlay(Card cardToPlay, Card currentCardOnTable) {
        // A Joker card (number = -1) is always a valid play
        if (cardToPlay.getNumber() == -1) {
            return true;
        }

        // If the current card on the table is a Joker, any card can be played
        if (currentCardOnTable.getNumber() == -1) {
            return true;
        }

        // Other cards must match the suit or value of the current card
        return cardToPlay.getNumber() == currentCardOnTable.getNumber()
                || cardToPlay.getShape().equals(currentCardOnTable.getShape());
    }
}

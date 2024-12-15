public class FaceCard extends Card
{
    public FaceCard(final int number,
                    final String shape)
    {
        super(number, shape);
    }

    @Override
    public String getCardEffect()
    {
        switch (getNumber()) {
            case 11: return "Jack card. Allows additional play!";
            case 12: return "Queen card. Allows additional play!";
            case 13: return "King card. Allows additional play!";
            default: return "Face card.";
        }
    }
}

//    @Override
//    public void applyEffect(DeckAndPlayerHands game, ActionCards gamePlay) {
//        System.out.println("Face card (" + toString() + ") played! You can play another card of the same shape.");
//        // Logic for allowing the player to play another card can be handled here
//    }

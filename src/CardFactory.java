public class CardFactory {
    public static Card createCard(final int number,
                                  final String shape)
    {
        // Week 7. lambda
        return switch (number)
        {
            case 1 -> new AceCard(shape); // Ace card
            case 11 -> new FaceCard(11, shape); // Jack: allows additional play
            case 12 -> new FaceCard(12, shape); // Queen: allows additional play
            case 13 -> new FaceCard(13, shape); // King: allows additional play
            case -1 -> new JokerCard(); // Joker card
            default -> new NormalCard(number, shape); // Normal cards (2-10)
        };
    }
}

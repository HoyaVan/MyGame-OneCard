public class JokerCard extends Card
{
    public JokerCard()
    {
        super(-1, "Any");
    }

    @Override
    public String getCardEffect()
    {
        return "Joker card. Wildcard that can match any card!";
    }
}

//    // Week 2: Inheritance, Polymorphism
//    @Override
//    public void applyEffect(DeckAndPlayerHands game, ActionCards gamePlay) {
//        System.out.println("Joker played! Opponent must draw 5 cards unless blocked by another Joker.");
//        // Implement Joker attack logic
//    }

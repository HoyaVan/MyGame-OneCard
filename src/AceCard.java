public class AceCard extends Card {
    public AceCard(String shape) {
        super(1, shape);
    }

    @Override
    public String getCardEffect() {
        return "Ace card. Play grants an extra turn!";
    }
}

//    @Override
//    public void applyEffect(DeckAndPlayerHands game, ActionCards gamePlay) {
//        System.out.println("Ace card (" + toString() + ") played! Blocks an attack or initiates an attack for 3 cards!");
//        // Logic for blocking or initiating an attack
//    }
//
//    @Override
//    public int getPunishment() {
//        return punishment;
//    }

import java.util.Scanner;

public class Player implements GameParticipantable {
    private Scanner scanner;

    public Player(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int takeTurn(DeckAndPlayerHands deckAndPlayerCards, int cardsToDraw) {

        boolean turnComplete = false;

        while (!turnComplete)
        {

            System.out.print("Select a card index to play or type 'draw': ");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("draw"))
            {
                // Player chooses to draw a card
                deckAndPlayerCards.drawCardsForHuman(cardsToDraw);
                turnComplete = true;
            } else
            {
                try
                {
                    int cardIndex = Integer.parseInt(input) - 1; // Parse input as an integer

                    if (cardIndex < 0 || cardIndex >= deckAndPlayerCards.getPlayerHand().size())
                    {
                        System.out.println("Invalid index. Please select a valid card index or type 'draw'.");
                    } else
                    {
                        // Get the card the player wants to play
                        Card cardToPlay = deckAndPlayerCards.getPlayerHand().get(cardIndex);

                        // Validate the play
                        if (isValidPlay(cardToPlay, deckAndPlayerCards.getCurrentCardOnTable()))
                        {
                            // Play the card
                            deckAndPlayerCards.playCardForHuman(cardIndex);
                            turnComplete = true;
                        } else
                        {
                            System.out.println("Invalid play. The card does not match the current card's suit or value.");
                        }
                    }
                } catch (NumberFormatException e)
                {
                    System.out.println("Invalid input. Please select a valid card index or type 'draw'.");
                }
            }
        }

        return cardsToDraw; // Return the same cardsToDraw count for the next turn
    }

}
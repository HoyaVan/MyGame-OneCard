import java.util.Scanner;

public class OneCardGame {

    public static void main()
    {
        final Scanner scanner;
        final DeckAndPlayerHands deckAndPlayerCards;

        scanner = new Scanner(System.in);
        deckAndPlayerCards = new DeckAndPlayerHands();

        int cardsToDraw = 1;

        // Create participants
        GameParticipantable player;
        GameParticipantable ai;

        ai = new AIPlayer();
        player = new Player(scanner);

        // Game loop
        while (!deckAndPlayerCards.isAIWinner() && !deckAndPlayerCards.isPlayerWinner())
        {
            System.out.println(deckAndPlayerCards);
            // Player's turn
            cardsToDraw = player.takeTurn(deckAndPlayerCards, cardsToDraw);

            // Display the effect of the current card on the table
            System.out.println(deckAndPlayerCards.getCurrentCardOnTable().getCardEffect());

            // Check if game is over after player's turn
            if (deckAndPlayerCards.isPlayerWinner()) break;

            // AI's turn
            cardsToDraw = ai.takeTurn(deckAndPlayerCards, cardsToDraw);

            // Display the effect of the current card on the table
            System.out.println(deckAndPlayerCards.getCurrentCardOnTable().getCardEffect());

            // Check if game is over after AI's turn
            if (deckAndPlayerCards.isAIWinner() ) break;
        }

        deckAndPlayerCards.printFinalPlayerHands();

//      Week 8: Paths, Files, Scanner, Streaming
//      Save game state to a file at the end of the game
        deckAndPlayerCards.writeFinalHandsToFile("game_state.txt");

        scanner.close();
    }
}

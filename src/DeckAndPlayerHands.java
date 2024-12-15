import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class DeckAndPlayerHands {

    private static final int NUM_INITIAL_CARDS = 7;
    private static final int MAX_NUM_INITIAL_CARDS = 15;
    private static final int NUM_CARDS_IN_SUIT = 13; // Ace to King
    private static final int JOKER_CARD_NUMBER = -1;
    private static final String JOKER_CARD_SHAPE = "Any";

    private final List<Card> deck;
    private final List<Card> playerHand;
    private final List<Card> aiHand;
    private Card currentCardOnTable;

    public DeckAndPlayerHands()
    {
        this.deck = new ArrayList<>();
        this.playerHand = new ArrayList<>();
        this.aiHand = new ArrayList<>();
        initializeDeck();
        dealInitialCards();
    }

    public List<Card> getPlayerHand()
    {
        return playerHand;
    }

    public List<Card> getAiHand()
    {
        return aiHand;
    }

    public List<Card> getDeck()
    {
        return deck;
    }

    public Card getCurrentCardOnTable()
    {
        return currentCardOnTable;
    }

    public void setCurrentCardOnTable(final Card currentCardOnTable)
    {
        this.currentCardOnTable = currentCardOnTable;
    }

    private void initializeDeck() {
        final String[] shapes;

        shapes = new String[] {"Hearts", "Diamonds", "Clubs", "Spades"};

        for (final String shape : shapes)
        {

            for (int number = 1; number <= NUM_CARDS_IN_SUIT; number++)
            {
                // Ace to King
                deck.add(CardFactory.createCard(number, shape));
            }
        }

        // Add Joker cards
        deck.add(CardFactory.createCard(JOKER_CARD_NUMBER, "Any"));
        deck.add(CardFactory.createCard(JOKER_CARD_NUMBER, "Any"));
        Collections.shuffle(deck); // Shuffle the deck
    }

    private void dealInitialCards()
    {

        if (deck == null || deck.size() < MAX_NUM_INITIAL_CARDS)
        {
            throw new IllegalStateException("Not enough cards in the deck to deal initial hands.");
        }

        for (int i = 0; i < NUM_INITIAL_CARDS; i++)
        {
            playerHand.add(deck.removeFirst());
            aiHand.add(deck.removeFirst());
        }

        // Set the initial card on the table
        setInitialCurrentCard();
    }

    private void setInitialCurrentCard()
    {

        // Cannot set the initial current card.
        validateDeck(deck);

        // Draw the first card from the deck
        currentCardOnTable = deck.removeFirst();

        System.out.println("The initial card on the table is: " + currentCardOnTable);
    }

    public void drawCardsForHuman(final int numberOfCards) {
        drawCards(playerHand, numberOfCards, "Player");
    }

    public void drawCardsForAI(final int numberOfCards) {
        drawCards(aiHand, numberOfCards, "AI");
    }

    public void playCardForHuman(final int index) {
        playCard(playerHand, index, "Player");
    }

    public void playCardForAI(final int index) {
        playCard(aiHand, index, "AI");
    }

    // combined drawcards functions
    private void drawCards(final List<Card> hand, final int numberOfCards, final String playerType) {
        validateDeck(deck);

        // Ensure there are enough cards in the deck to draw
        int cardsToDraw = Math.min(numberOfCards, deck.size());

        for (int i = 0; i < cardsToDraw; i++) {
            hand.add(deck.removeFirst()); // Draw the top card
        }

        System.out.println(playerType + " drew " + cardsToDraw + " card(s).");
    }

    // combined playcards functions
    private void playCard(final List<Card> hand, final int index, final String playerType) {
        validatePlayCard(hand, index, playerType);

        // Remove the card from the hand and set it as the current card on the table
        currentCardOnTable = hand.remove(index);
        System.out.println(playerType + " played: " + currentCardOnTable);
    }

    public Card drawFromDeck()
    {

        validateDeck(deck);

        return deck.removeFirst();
    }

    public Card removeFromPlayerHand(final int index)
    {

        validatePlayCard(playerHand, index, "Player");

        return playerHand.remove(index);
    }

    public Card removeFromAIHand(final int index)
    {

        validatePlayCard(playerHand, index, "AI");

        return aiHand.remove(index);
    }

    public int getDeckSize()
    {
        return deck.size();
    }

    public boolean isAIWinner()
    {
        return aiHand != null && aiHand.isEmpty();
    }

    // Check if the human player has won
    public boolean isPlayerWinner()
    {
        return playerHand != null && playerHand.isEmpty();
    }

    private String generateHandDetails(final List<Card> hand,
                                       final String playerType)
    {
        final StringBuilder result;
        result = new StringBuilder();

        result.append(playerType)
                .append("'s final hand:")
                .append(System.lineSeparator());

        if (hand == null || hand.isEmpty())
        {
            result.append(playerType)
                    .append(" won, they have no cards left.")
                    .append(System.lineSeparator());
        }
        else
        {
            for (int i = 0; i < hand.size(); i++)
            {
                result.append(i + 1)
                        .append(". ")
                        .append(hand.get(i).toString())
                        .append(System.lineSeparator());
            }
        }
        return result.toString();
    }

    public void printFinalPlayerHands()
    {
        final StringBuilder result;

        result = new StringBuilder("Game Over!")
                .append(System.lineSeparator());

        result.append(generateHandDetails(playerHand, "Player"));
        result.append(generateHandDetails(aiHand, "AI"));

        System.out.println(result);
    }

    public void writeFinalHandsToFile(final String fileName)
    {
        final Path filePath;
        final List<String> lines;

        filePath = Paths.get(fileName);
        lines = new ArrayList<>();

        lines.add("Game Over!");
        lines.add(generateHandDetails(playerHand, "Player"));
        lines.add(generateHandDetails(aiHand, "AI"));
        lines.add("End of game.");

        try {
            Files.write(filePath, lines);
            System.out.println("Final hands written to file: " + fileName);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    @Override
    public String toString()
    {
        final StringBuilder result;
        result = new StringBuilder();

        // Player's hand
        if (playerHand == null || playerHand.isEmpty())
        {
            result.append("Player's hand is empty.")
                    .append(System.lineSeparator());
        }
        else
        {
            result.append("Player's hand:")
                    .append(System.lineSeparator());

            for (int i = 0; i < playerHand.size(); i++)
            {
                result.append(i + 1)
                        .append(". ")
                        .append(playerHand.get(i).toString())
                        .append(System.lineSeparator());
            }
        }

        // AI's hand size
        if (aiHand == null)
        {
            result.append("AI's hand is not initialized.")
                    .append(System.lineSeparator());
        }
        else
        {
            result.append("AI has ")
                    .append(aiHand.size())
                    .append(" cards.")
                    .append(System.lineSeparator());
        }

        // Current card on the table
        result.append("Current card on the table: ")
                .append(currentCardOnTable != null ? currentCardOnTable.toString() : "None")
                .append(System.lineSeparator());

        return result.toString();
    }

    private void validateDeck(final List<Card> deck)
    {

        if (deck == null || deck.isEmpty()) {

            throw new IllegalStateException("The deck is empty.");

        }
    }

    private void validatePlayCard(final List<Card> hand,
                                  final int index,
                                  final String playerType)
    {

        if (hand == null || hand.isEmpty()) {

            throw new IllegalStateException(playerType + "'s hand is empty. No card can be played.");

        }

        if (index < 0 || index >= hand.size()) {

            throw new IllegalArgumentException("Invalid index. Please select a valid card index for " + playerType + ".");

        }
    }
}

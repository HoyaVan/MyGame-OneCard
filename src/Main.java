import java.util.Scanner;

public class Main
{
    public static void main(final String[] args)
    {

        final Scanner playgame;
        String userInput;

        playgame = new Scanner(System.in);

        System.out.println("Welcome to the game land!!");

        while (true)
        {
            System.out.println(System.lineSeparator() +
                    "Press O : One card game" +
                    System.lineSeparator() +
                    "Press Q : Quit the game" +
                    System.lineSeparator() +
                    "Enter your choice:");

            userInput = playgame.nextLine();

            try
            {
                if (validateGameStartInput(userInput))
                {
                    switch (userInput.toUpperCase())
                    {
                        case "O":
                            OneCardGame.main(); // Create an instance of OneCardGame
                            break;
                        case "Q":
                            System.out.println("Thanks for playing! Goodbye!");
                            playgame.close();
                            return;
                        default:
                            System.out.println("Unexpected error.");
                    }
                }
            }
            catch (IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    static boolean validateGameStartInput(final String userInput)
    {
        final String validInputs;
        final String validInputGuideline;

        validInputs = "OoQq";
        validInputGuideline = "Invalid input! Please enter a valid game:" +
                System.lineSeparator() + "O : One card game" +
                System.lineSeparator() + "Q : quit the game";

        if (userInput == null || userInput.isBlank())
        {
            throw new IllegalArgumentException(validInputGuideline);
        }

        if (userInput.length() != 1 || !validInputs.contains(userInput))
        {
            throw new IllegalArgumentException(validInputGuideline);
        }

        return true;
    }


}

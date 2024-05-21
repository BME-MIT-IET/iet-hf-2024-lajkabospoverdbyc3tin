
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * proto osztály létrehozása
 */
@Deprecated
public class Proto
{

    /**
     * A jatek peldanya
     */
    public static Game game;

    /**
     * az input alapján végez változtatást
     */
    public static int interpreter(String input)
    {
        return interpreter(input, null);
    }

    /**
     * Az Interpreter metódus felelős a játékos parancsok értelmezéséért és
     * végrehajtásáért a játékban.
     */
    public static int interpreter(String input, Player player)
    {
        String[] command = input.split(" ");
        if (player == null && command.length > 1)
        {
            player = game.getPlayerByID(command[1]);
        }
        switch (command[0].toLowerCase())
        {
        case "debug":
        {
            if (command[1].equals("on"))
            {
                Game.setDebugEnabled(true);
                return -2;
            }
            if (command[1].equals("off"))
            {
                Game.setDebugEnabled(false);
                return -2;
            }
            return -1;
        }
        case "wait":
        {
            return 0;
        }
        case "listinventory":
        {
            return player.listInventory();
        }
        case "listneighbours":
        {
            return player.listNeighbours();
        }
        case "move":
        {
            return player.move(game.getFieldByID(command[2]));
        }
        case "repair":
        {
            return player.repair();
        }
        case "damage":
        {
            return player.damage();
        }
        case "setpumpdirection":
        {
            return player.setPumpDirection((Pipe) game.getFieldByID(command[2]),
                    (Pipe) game.getFieldByID(command[3]));
        }
        case "placepump":
        {
            return player.placePump(player.getPumpFromInventoryByID(command[2]));
        }
        case "pickuppump":
        {
            return player.pickUpPump();
        }
        case "makeslippy":
        {
            return player.makeSlippy();
        }
        case "makesticky":
        {
            return player.makeSticky();
        }
        case "connectpipe":
        {
            return player.connectPipe(player.getPipeFromInventoryByID(command[2]));
        }
        case "disconnectpipe":
        {
            return player.disconnectPipe((Pipe) game.getFieldByID(command[2]));
        }
        case "pickuppipe":
        {
            return player.pickUpPipe();
        }
        case "step":
        {
            game.step();
            return 0;
        }
        default:
        {
            System.out.println("Ervenytelen parancs: " + input);
            return -1;
        }
        }
    }

    /**
     * A GameMode() metódus a játékot futtató mód, amely lehetővé teszi a
     * felhasználó számára, hogy soronként kiadja a parancsokat a játékosok számára.
     *
     * @throws IOException
     */
    public static void gameMode() throws IOException
    {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("#############");
        System.out.println("# Jatek mod #");
        System.out.println("#############");
        System.out.println("");
        System.out.println("");
        System.out.println("A program soronkent olvassa a parancsokat:");
        System.out.println("");
        while (true)
        {
            for (int i = 0; i < game.players.size(); i++)
            {
                System.out.println(game.players.get(i) + " jatekos kovetkezik");
                System.out.println("");
                String input = console.readLine();
                if (input.toLowerCase().equals("exit"))
                {
                    game.exit();
                    return;
                }
                else
                {
                    if (interpreter(input, game.players.get(i)) != 0)
                    {
                        i--;
                    }
                    Game.debugOutput.println("");
                }
            }
        }
    }

    /**
     * TEszt mod futtatasa az automata tesztekert
     *
     * @throws IOException
     */
    public static void testMode(String filename) throws IOException
    {
        BufferedReader file = new BufferedReader(
                new InputStreamReader(new FileInputStream("tests/" + filename + "/test.txt")));
        System.out.println("############");
        System.out.println("# Test mod #");
        System.out.println("############");
        System.out.println("");
        System.out.println("");
        System.out.println("A program a parancsokat a " + filename + " leiro fajlbol olvassa");
        System.out.println("");
        while (file.ready())
        {
            String input = file.readLine();
            interpreter(input);
        }
        file.close();
        if (Arrays.equals(Files.readAllBytes(Paths.get("tests/" + filename + "/expected.txt")),
                Files.readAllBytes(Paths.get("tests/" + filename + "/result.txt"))))
        {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("A teszt az elvart kimenetet produkalta");
        }
        else
        {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("A teszt NEM az elvart kimenetet produkalta");
        }
    }
}

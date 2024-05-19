package HwProject.src;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A játék elemeit (mezők és játékosok) és lefutásának legfontosabb függvényeit
 * tartalmazza.
 */
public class Game
{
    /**
     * Engedelyezett-e a bovitett hiba kimenet
     */
    protected static boolean debugEnabled;
    /**
     * az osztály minden példánya ugyanazt a debugOutput objektumot használja
     */
    protected static PrintStream debugOutput;
    /**
     * Szerelők pontjai
     */
    private int mechanicPoints;
    /**
     * Szabotőrök pontjai
     */
    private int saboteurPoints;
    /**
     * A fields eszköztár
     */
    protected ArrayList<Field> fields;
    protected Map<String, Field> fieldMap;
    /**
     * players eszköztár
     */
    protected ArrayList<Player> players;
    protected Map<String, Player> playerMap;

    /**
     * Az osztály szintű
     * debugEnabled adattagot állítja be
     *
     * @param debugEnabled ennek az értéke állítódik be
     */
    public static void setDebugEnabled(boolean debugEnabled)
    {
        Game.debugEnabled = debugEnabled;
        Field.debugEnabled = debugEnabled;
        Player.debugEnabled = debugEnabled;
    }

    /**
     * Konstruktor
     */
    public Game(PrintStream debugOutput)
    {
        setDebugEnabled(false);
        Game.debugOutput = debugOutput;
        Field.debugOutput = debugOutput;
        Player.debugOutput = debugOutput;
        Drain.setGame(this);
        Pipe.setGame(this);
        mechanicPoints = 0;
        saboteurPoints = 0;
        fields = new ArrayList<>();
        fieldMap = new HashMap<>();
        players = new ArrayList<>();
        playerMap = new HashMap<>();
        initializeFields();
    }

    /**
     * Initializes fields and adds them to the fields list and fieldMap.
     */
    private void initializeFields()
    {
        for (int i = 0; i < 13; i++) {
            Pipe pipe = new Pipe();
            fields.add(pipe);
            fieldMap.put("Pipe" + (i + 1), pipe);
        }
        addField(new Source(), "Source1", "Pipe1", "Pipe2");
        addField(new Source(), "Source2", "Pipe3", "Pipe4");
        addField(new Pump(10), "Pump1", "Pipe1", "Pipe6");
        addField(new Pump(10), "Pump2", "Pipe2", "Pipe5", "Pipe7", "Pipe8");
        addField(new Pump(10), "Pump3", "Pipe5", "Pipe9");
        addField(new Pump(10), "Pump4", "Pipe7", "Pipe11");
        addField(new Pump(10), "Pump5", "Pipe3", "Pipe6", "Pipe10", "Pipe12");
        addField(new Pump(10), "Pump6", "Pipe4", "Pipe8", "Pipe9", "Pipe10", "Pipe13");
        addField(new Drain(), "Drain1", "Pipe12");
        addField(new Drain(), "Drain2", "Pipe11", "Pipe13");
    }

    /**
     * Helper method to add a field to the game and connect it to pipes.
     */
    private void addField(Field field, String fieldID, String... pipeIDs)
    {
        for (String pipeID : pipeIDs) {
            field.connectPipe((Pipe) getFieldByID(pipeID));
        }
        fields.add(field);
        fieldMap.put(fieldID, field);
    }

    /**
     * lekéri a mező azonosítóját
     *
     * @param ID A keresett azonosito
     * @return A meghatarozott azonositoju mezo
     */
    public Field getFieldByID(String ID)
    {
        for (Field field : fields)
        {
            if (field.getID().equals(ID))
            {
                return field;
            }
        }
        return null;
    }

    /**
     * lekéri a játékos azonosítóját
     *
     * @param ID A keresett azonosito
     * @return A meghatarozott azonositoju jatekos
     */
    public Player getPlayerByID(String ID)
    {
        for (Player player : players)
        {
            if (player.getID().equals(ID))
            {
                return player;
            }
        }
        return null;
    }

    /**
     * Lépteti a játékosokat
     */
    public void step()
    {
        for (Field field : fields)
        {
            field.step();
        }
        if (debugEnabled)
        {
            debugOutput.println("");
        }
    }

    /**
     * A mechanic csapatnak pontot ad
     *
     * @param points a hozzaadando pontok
     */
    public void addMechanicPoint(int points)
    {
        if (debugEnabled)
        {
            debugOutput.println("Game: " + points + " pont a szereloknek");
        }
        mechanicPoints += points;
    }

    /**
     * A saboteur csapatnak pontot ad
     *
     * @param points a hozzaadando pontok
     */
    public void addSaboteurPoint(int points)
    {
        if (debugEnabled)
        {
            debugOutput.println("Game: " + points + " pont a szabotoroknek");
        }
        saboteurPoints += points;
    }

    public int getMechanicPoint()
    {
        return mechanicPoints;
    }

    public int getSaboteurPoint()
    {
        return saboteurPoints;
    }

    /**
     * A játékból való kilépés
     */
    public void exit()
    {
        debugOutput.println("");
        debugOutput.println("");
        debugOutput.println("Game: A szereloknek " + mechanicPoints + " pontja van.");
        debugOutput.println("Game: A szabotoroknek " + saboteurPoints + " pontja van.");
        debugOutput.println("");
        if (mechanicPoints > saboteurPoints)
        {
            debugOutput.println("Game: A szerelok nyertek!");
        }
        else if (mechanicPoints < saboteurPoints)
        {
            debugOutput.println("Game: A szabotorok nyertek!");
        }
        else
        {
            debugOutput.println("Game: Dontetlen!");
        }
    }
}

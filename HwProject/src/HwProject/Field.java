package HwProject;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * A játékpálya egy eleme/mezője. ösosztálya az aktív elemeknek és a csőnek. Egy
 * játékos megkísérelhet rálépni. Tartalmaz alapértelmezett implemetnációkat
 */
public abstract class Field
{
    public static final int SUCCESS = 0;
    private static final String LOG_FIELD = "Field - ";


    /**
     * Engedelyezett-e a kommunikacio
     */
    protected static boolean debugEnabled;
    /**
     * debug információ kimenetének céljára szolgál, hibakereséshez vagy az
     * alkalmazás állapotának ellenőrzéséhez
     */
    protected static PrintStream debugOutput;
    /**
     * Azonosító
     */
    protected String id;
    /**
     * Játékosok
     */
    protected ArrayList<Player> players;

    

    /**
     * Konstruktor
     */
    protected Field()
    {
        players = new ArrayList<>();
    }

    public ArrayList<String> getdirection()
    {
        return new ArrayList<>();
    }

    public ArrayList<Integer> getstates()
    {   
        
        return new ArrayList<>();
    }

    /**
     * az objektum szöveges reprezentációjának visszaadása
     *
     * @return A mezo szoveges megfeleloje
     */
    @Override
    public String toString()
    {
        return id;
    }

    /**
     * A mezo azonositójának lekérése
     *
     * @return A mezo azonositoja
     */
    public String getID()
    {
        return id;
    }

    public ArrayList<Active> getends()
    {
        return null;
    }
    /**
     * lépés
     */
    public abstract void step();

    /**
     * PLACEHOLDER
     *
     * @return PLACEHOLDER
     */
    protected boolean isPlayerRemoveable()
    {
        return true;
    }

    /**
     * PLACEHOLDER
     *
     * @return PLACEHOLDER
     */
    protected boolean isPlayerAddable()
    {
        return true;
    }


    /**
     * Player típusú objektumot vár, amelyet a metódus hozzáad a players nevű
     * listához
     *
     * @param player játékos
     * @return Hibakod
     */
    protected int addPlayer(Player player)
    {
        players.add(player);
        if (debugEnabled)
        {
            debugOutput.println(LOG_FIELD + this + ": Sikeres hozzaadas");
        }
        return SUCCESS;
    }

    /**
     * Ha rajta all a player akkor leveszi Siker esetén 0 visszatérés
     *
     * @param player játékos
     * @return Hibakod
     */
    protected int removePlayer(Player player)
    {
        if (players.remove(player))
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_FIELD + this + ": Sikeres eltavolitas");
            }
            return SUCCESS;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_FIELD + this + ": Nincs ilyen jatekos");
            }
            return 2;
        }
    }

    /**
     * PLACEHOLDER
     *
     * @param player játékos
     * @return PLACEHOLDER
     */
    public Field movePlayer(Player player)
    {
        if (isNeighbour(player.position))
        {
            if (isPlayerAddable() && player.position.isPlayerRemoveable())
            {
                addPlayer(player);
                player.position.removePlayer(player);
                return this;
            }
            else
            {
                if (debugEnabled)
                {
                    debugOutput.println(LOG_FIELD + this + ", " + player.position
                            + ": Nem lehetseges a mozgatas");
                }
                return null;
            }
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_FIELD + this + ": Nem szomszedos mezo");
            }
            return null;
        }
    }

    /**
     * Azt kell eldöntenie, hogy az adott mező szomszédos-e az általa kapott field
     * mezővel
     *
     * @param field mező
     */
    public abstract boolean isNeighbour(Field field);

    /**
     * Kiírja a szomszédokat
     */
    public abstract void listNeighbours();

    /**
     * Beállítja a pumpa ki-és bemenetet
     *
     * @param input  bemenet
     * @param output kimenet
     * @return Hibakod
     */
    public int setPumpDirection(Pipe input, Pipe output)
    {
        return 1;
    }

    /**
     * Megjavítja az aktív elemet
     *
     * @return Hibakod
     */
    public int repair()
    {
        return 1;
    }

    /**
     * Elrontja az aktív elemet
     *
     * @return Hibakod
     */
    public int damage()
    {
        return 1;
    }

    /**
     * Egy pumpa pályárahelyezése
     *
     * @param pump pumpa
     * @return Hibakod
     */
    public int placePump(Pump pump)
    {
        return 1;
    }

    /**
     * Egy pumpa felvétele
     *
     * @return A felvett pumpa
     */
    public Pump pickUpPump()
    {
        return null;
    }

    /**
     * Egy cső csúszóssátétele
     *
     * @return Hibakod
     */
    public int makeSlippy()
    {
        return 1;
    }

    /**
     * Egy cső ragadóssátétele
     *
     * @return Hibakod
     */
    public int makeSticky()
    {
        return 1;
    }

    /**
     * Csö csatlakoztatása
     *
     * @param pipe csö
     * @return Hibakod
     */
    public int connectPipe(Pipe pipe)
    {
        return 1;
    }

    /**
     * Cső lecsatlakoztatása
     *
     * @param pipe csö
     * @return Hibakod
     */
    public int disconnectPipe(Pipe pipe)
    {
        return 1;
    }

    /**
     * Csö felvétele
     *
     * @return A felvett cso
     */
    public Pipe pickUpPipe()
    {
        return null;
    }
}

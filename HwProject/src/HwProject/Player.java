package HwProject;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * PLACEHOLDER
 */
public abstract class Player
{
    /**
     * Engedelyezett-e a kommunikacio
     */
    protected static boolean debugEnabled;
    /**
     * Kimenet a kommunikaciohoz
     */
    protected static PrintStream debugOutput;
    /**
     * A jatekos azonositoja
     */
    protected String id;
    /**
     * A jatekos pozicioja
     */
    protected Field position;

    /**
     * Konstruktor
     */
    protected Player(String id, Field position)
    {
        this.id = id;
        this.position = position;
        position.addPlayer(this);
        if (debugEnabled)
        {
            debugOutput.println("Player - " + this + ": Sikeres inicializalas");
        }
    }

    /**
     * visszaadja az azonosítóját
     *
     * @return A jatekos szoveges megfeleloje
     */
    @Override
    public String toString()
    {
        return id;
    }

    /**
     * visszaadja az azonosítóját
     *
     * @return A jatekos azonositoja
     */
    public String getID()
    {
        return id;
    }

    /**
     * Azonosító alapján lekéri a pumpát az inventoryból
     *
     * @param id A keresett azonosito
     * @return A meghatarozott azonositoju pumpa
     */
    public Pump getPumpFromInventoryByID(String id)
    {
        return null;
    }

    /**
     * Azonosító alapján lekéri a csövet az inventoryból
     *
     * @param id A keresett azonosito
     * @return A meghatarozott azonositoju cso
     */
    public Pipe getPipeFromInventoryByID(String id)
    {
        return null;
    }

    /**
     * kiírja az inventory tartalmát
     *
     * @return Hibakod
     */
    public int listInventory()
    {
        return -2;
    }

  
    public ArrayList<String> getInventory()
    {

        return null;
    }
    

    /**
     * kiírja a szomszédokat
     *
     * @return Hibakod
     */
    public int listNeighbours()
    {
        position.listNeighbours();
        return -2;
    }

    /**
     * Ha szabad a mezo, es szomszed, akkor atmozdul, es a jatekos poziciojat is
     * beallitja a mezok mellett
     *
     * @param newPosition a celmezo ahova lepni szeretne
     * @return Hibakod
     */
    public int move(Field newPosition)
    {
        Field actualNewPosition = newPosition.movePlayer(this);
        if (actualNewPosition != null)
        {
            position = actualNewPosition;
            if (debugEnabled)
            {
                debugOutput.println("Player - " + this + ": Sikeres mozgas");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Player - " + this + ": Sikertelen mozgas");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * beállítja a pumpa irányát
     *
     * @param input  bemenet
     * @param output kimenet
     * @return Hibakod
     */
    public int setPumpDirection(Pipe input, Pipe output)
    {
        return -1;
    }

    /**
     * megjavítás
     *
     * @return Hibakod
     */
    public int repair()
    {
        return -1;
    }

    /**
     * elrontás
     *
     * @return Hibakod
     */
    public int damage()
    {
        return -1;
    }

    /**
     * pumpa lehelyezése
     *
     * @param pump pumpa
     * @return Hibakod
     */
    public int placePump(Pump pump)
    {
        return -1;
    }

    /**
     * pumpa felvétele
     *
     * @return Hibakod
     */
    public int pickUpPump()
    {
        return -1;
    }

    /**
     * csúszóssá tevés
     *
     * @return Hibakod
     */
    public int makeSlippy()
    {
        return -2;
    }

    /**
     * ragadóssá tevés
     *
     * @return Hibakod
     */
    public int makeSticky()
    {
        return -2;
    }

    /**
     * csövel csatlakoztatása
     *
     * @param pipe
     * @return Hibakod
     */
    public int connectPipe(Pipe pipe)
    {
        return -1;
    }

    /**
     * csö lecsatlakoztatása
     *
     * @param pipe
     * @return Hibakod
     */
    public int disconnectPipe(Pipe pipe)
    {
        return -1;
    }

    /**
     * cső felvétele
     *
     * @return Hibakod
     */
    public int pickUpPipe()
    {
        return -1;
    }
}

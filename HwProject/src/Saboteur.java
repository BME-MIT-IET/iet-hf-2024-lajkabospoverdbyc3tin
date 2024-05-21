
/**
 * Az osztály a nomádok csapatának példányait reprezentálja. A nomádok ki tudják
 * lyukasztani a csöveket és át tudják állítani a pumpákat.
 */
public class Saboteur extends Player
{
    /**
     * Konstruktor
     */
    public Saboteur(String ID, Field position)
    {
        super(ID, position);
    }

    /**
     * Pumpa input output irányának beállítása
     *
     * @param input  a bemeneti csó
     * @param output a kimeneti cső
     * @return Hibakod
     */
    @Override
    public int setPumpDirection(Pipe input, Pipe output)
    {
        if (position.setPumpDirection(input, output) == 0)
        {
            if (debugEnabled)
            {
                debugOutput.println("Saboteur - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Saboteur - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * A cuccos elrontasa, csak akkor ha ronthato
     *
     * @return Hibakod
     */
    @Override
    public int damage()
    {
        if (position.damage() == 0)
        {
            if (debugEnabled)
            {
                debugOutput.println("Saboteur - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Saboteur - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * Csúszóssá teszi az elemet
     *
     * @return Hibakod
     */
    @Override
    public int makeSlippy()
    {
        if (position.makeSlippy() == 0)
        {
            if (debugEnabled)
            {
                debugOutput.println("Saboteur - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Saboteur - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }

    /**
     * Ragadóssá teszi az elemet
     *
     * @return Hibakod
     */
    @Override
    public int makeSticky()
    {
        if (position.makeSticky() == 0)
        {
            if (debugEnabled)
            {
                debugOutput.println("Saboteur - " + this + ": Sikeres lepes");
                debugOutput.println("");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Saboteur - " + this + ": Sikertelen lepes");
                debugOutput.println("");
            }
            return -1;
        }
    }
}

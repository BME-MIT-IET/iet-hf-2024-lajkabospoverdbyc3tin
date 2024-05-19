import java.util.ArrayList;

/**
 * A játékban található aktív elemek (forrás, ciszterna, cső) közös
 * tulajdonságait egyesítő absztrakt ősosztály.
 */
public abstract class Active extends Field
{
    /**
     * a csövek, amikkel össze van kötve.
     */
    protected ArrayList<Pipe> connectedPipes;

    /**
     * Konstruktor
     */
    public Active()
    {
        connectedPipes = new ArrayList<>();
    }

    /**
     * egy adott csovet elvesz es egy masikat lerak Lehet kell modositani amiatt h
     * vizsgalja h vannak e rajta
     *
     * @param oldPipe a lecserelendo cso
     * @param newPipe az uj cso
     * @return Hibakod
     */
    public int ReplacePipe(Pipe oldPipe, Pipe newPipe)
    {
        if (connectedPipes.remove(oldPipe))
        {
            connectedPipes.add(newPipe);
            if (debugEnabled)
            {
                debugOutput.println("Active - " + this + ": Sikeres csere");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Active - " + this + ": Nincs ilyen regi cso");
            }
            return 2;
        }
    }

    /**
     * a bemeneti Field objektum szomszédja-e a jelenlegi objektumnak
     *
     * @param field PLACEHOLDER
     * @return PLACEHOLDER
     */
    @Override
    public boolean isNeighbour(Field field)
    {
        return connectedPipes.contains(field);
    }

    /**
     * Kiirja a szomszedokat
     */
    @Override
    public void listNeighbours()
    {
        for (Pipe pipe : connectedPipes)
        {
            debugOutput.println(pipe);
        }
        debugOutput.println("");
    }

    /**
     * Beallitja a kapcsolatot mindket elemen Ha sikertelen akkor hibauzenet
     *
     * @return Hibakod
     */
    @Override
    public int connectPipe(Pipe pipe)
    {
        int error = pipe.connectActive(this);
        if (error == 0)
        {
            connectedPipes.add(pipe);
            if (debugEnabled)
            {
                debugOutput.println("Active - " + this + ": Sikeres felkapcsolas");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Active - " + this + ": Sikertelen cso beallitas");
            }
            return error;
        }
    }

    /**
     * a megadott csövet lecsatlakoztatja. Csak ha szomszéd Hiba esetén jelez
     *
     * @return Hibakod
     */
    @Override
    public int disconnectPipe(Pipe pipe)
    {
        if (isNeighbour(pipe))
        {
            int error = pipe.disconnectActive(this);
            if (error == 0)
            {
                connectedPipes.remove(pipe);
                if (debugEnabled)
                {
                    debugOutput.println("Active - " + this + ": Sikeres lekapcsolas");
                }
                return 0;
            }
            else
            {
                if (debugEnabled)
                {
                    debugOutput.println("Active - " + this + ": Sikertelen cso beallitas");
                }
                return error;
            }
        }
        // Nem szomszedos csore lett hivva
        if (debugEnabled)
        {
            debugOutput.println("Active - " + this + ": Nem szomszedos cso");
        }
        return 2;
    }
}

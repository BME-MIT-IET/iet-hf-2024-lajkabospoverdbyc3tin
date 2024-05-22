package HwProject;
import java.util.ArrayList;
import java.util.Random;

/**
 * A pálya egyik aktív eleme a pumpa. A pumpák kötik össze a csöveket, tárolnak
 * vizet, pumpálják át a vizet a csöveken keresztül egymásba. Új pumpát
 * beszerelni a szerelők tudnak, pumpát átállítani mindkét csapat tud.
 */
public class Pump extends Active
{
    /**
     * Engedelyezett-e a veletlenszeru mukodes
     */
    protected static boolean randomEnabled;
    /**
     * Az adattag értéke határozza meg, hogy az adott osztályból eddig mennyi
     * példányosítás történt
     */
    protected static int instanceNr = 0;
    /**
     * vízszint
     */
    protected int waterLevel;
    /**
     * max vízszint
     */
    protected int maxWaterLevel;
    /**
     * maximálisan csatlakoztatott csövek
     */
    protected int maxConnectedPipes;
    /**
     * elrontott-e
     */
    protected boolean isDamaged;
    /**
     * cső bemenete
     */
    protected Pipe input;
    /**
     * cső kimenete
     */
    protected Pipe output;
    

    /**
     * Konstruktor
     */
    public Pump(int maxConnectedPipes)
    {
        instanceNr++;
        id = "Pump" + instanceNr;
        waterLevel = 0;
        maxWaterLevel = 50;
        this.maxConnectedPipes = maxConnectedPipes;
        isDamaged = false;
    }

    /**
     * lépés a játékban
     */
    @Override
    public void step()
    {
        pushWater();
        pullWater();
        if (randomEnabled)
        {
            trytoDamage();
        }
        if (debugEnabled)
        {
            debugOutput.println("");
        }
    }

    /**
     * Nyomja a vizet
     */
    protected void pushWater()
    {
        if (!isDamaged && output != null)
        {
            waterLevel -= output.recieveWater(waterLevel);
            if (debugEnabled)
            {
                debugOutput.println("Pump - " + this + ": Sikeres nyomas");
            }
        }
    }


    /**
     * Szivja a vizet
     */
    protected void pullWater()
    {
        if (!isDamaged && input != null)
        {
            waterLevel += input.transmitWater(maxWaterLevel - waterLevel);
            if (debugEnabled)
            {
                debugOutput.println("Pump - " + this + ": Sikeres szivas");
            }
        }
    }

    @Override
    public ArrayList<String> getdirection()
    {
        ArrayList<String> dir = new ArrayList<>();
        if(input != null)
        dir.add(input.getID());
        else
        dir.add("N");

        if(output != null)
        dir.add(output.getID());
        else
        dir.add("N");

        return dir;
    }
    /**
     * Ha lehet akkor elrontja
     *
     * @return Hibakod
     */
    public int trytoDamage()
    {
        if (randomEnabled)
        {
            if (new Random().nextDouble() < 0.2)
            {
                if (isDamaged == false)
                {
                    if (debugEnabled)
                    {
                        debugOutput.println("Pump - " + this + ": Sikeres rongalas");
                    }
                    isDamaged = true;
                    return 0;
                }
                else
                {
                    if (debugEnabled)
                    {
                        debugOutput.println("Pump - " + this + ": Mar meg van rongalva");
                    }
                    return 3;
                }
            }
            else
            {
                if (debugEnabled)
                {
                    debugOutput.println("Pump - " + this + ": Sikertelen rongalas");
                }
                return 2;
            }
        }
        else
        {
            if (isDamaged == false)
            {
                if (debugEnabled)
                {
                    debugOutput.println("Pump - " + this + ": Sikeres rongalas");
                }
                isDamaged = true;
                return 0;
            }
            else
            {
                if (debugEnabled)
                {
                    debugOutput.println("Pump - " + this + ": Mar meg van rongalva");
                }
                return 3;
            }
        }
    }

    /**
     * itt tortenik meg az iranyok beallitasa ha szomszedok
     *
     * @param input  a bementi irany
     * @param output a kimeneti irany
     * @return Hibakod
     */
    @Override
    public int setPumpDirection(Pipe input, Pipe output)
    {
        if (isNeighbour(input) && isNeighbour(output))
        {
            this.input = input;
            this.output = output;
            if (debugEnabled)
            {
                debugOutput.println("Pump - " + this + ": Sikeres beallitas");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Pump - " + this + ": Nem megfelelo parameterek");
            }
            return 2;
        }
    }

    /**
     * A pumpa megjavítasa
     *
     * @return Hibakod
     */
    @Override
    public int repair()
    {
        if (isDamaged == true)
        {
            isDamaged = false;
            if (debugEnabled)
            {
                debugOutput.println("Pump - " + this + ": Sikeres javitas");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Pump - " + this + ": Mar meg van javitva");
            }
            return 2;
        }
    }

    /**
     * Hozzakapcsol egy csovet a pumpahoz
     *
     * @param pipe a kapcsolando pumpa
     * @return Hibakod
     */
    @Override
    public int connectPipe(Pipe pipe)
    {
        if (connectedPipes.size() < maxConnectedPipes)
        {
            return super.connectPipe(pipe);
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Pump - " + this + ": Nincs tobb szabad hely");
            }
            return 4;
        }
    }

    /**
     * Lekapcsol egy pumpat
     *
     * @param pipe a lekapcsolando pumpa
     * @return Hibakod
     */
    @Override
    public int disconnectPipe(Pipe pipe)
    {
        int error = super.disconnectPipe(pipe);
        if (error == 0)
        {
            if (pipe == input)
            {
                input = null;
            }
            if (pipe == output)
            {
                output = null;
            }
            return 0;
        }
        else
        {
            return error;
        }
    }
}

package src;


import java.util.ArrayList;

/**
 * Ez az osztály a cső. A játék során a cső nem szolgál víztárolóként, ezért ez
 * nem egy aktív elem. A csöveken mozognak a játékosok, egy csövön egyszerre
 * csak egy karakter állhat. Egy csövet egy szerelő bármikor elvághat egy új
 * pumpa elhelyezése céljából, mely művelet után a lehelyezett pumpában már
 * képes lesz víz gyűjtésére. A szabotőrök kilyukaszthatnak egy csövet, a lyukas
 * csöveket azt a szerelők befoltozhatják.
 */
public class Pipe extends Field
{


    /**
     * Az adattag értéke határozza meg, hogy az adott osztályból eddig mennyi
     * példányosítás történt
     */
    protected static int instanceNr = 0;
    /**
     * akkor kap értéket, amikor egy Game objektumot állítunk be
     */
    protected static Game game;
    /**
     * vízszint
     */
    protected int waterLevel;
    /**
     * maxvízszint
     */
    protected int maxWaterLevel;
    /**
     * el van-e rontva
     */
    protected boolean isDamaged;
    /**
     * csuszos e az elem
     */
    protected boolean isSlippy;
    /**
     * enum, ragados-e az elem
     */
    public StickyStates stickyState;

    /**
     * aktív elemek végei
     */
    protected ArrayList<Active> ends;
    /**
     * ha valamit megjavítanak utána egy adott számú körig nem lehet elrontani
     */
    protected int protectedRounds;

    protected int tickCounter = 0;

    private static final String LOG_PIPE = "Pipe - ";

    /**
     * beállítja a game változó értékét a megadott Game objektumra
     * 
     * @param game a jatek entitasa
     */
    public static void setGame(Game game)
    {
        Pipe.game = game;
    }

    /**
     * Konstruktor
     */
    public Pipe()
    {
        instanceNr++;
        id = "Pipe" + instanceNr;
        waterLevel = 0;
        maxWaterLevel = 15;
        isDamaged = false;
        isSlippy = false;
        stickyState = StickyStates.None;
        ends = new ArrayList<>();
        protectedRounds = 0;
    }

    /**
     * ha azon körök száma, amikor nem lehet elrontani, akkor körönként csökkenteni
     * kell, hogy aztán megint el lehessen rontani
     */
    @Override
    public void step()
    {
        if (protectedRounds > 0)
        {
            protectedRounds--;
        }
        tickCounter--;
        if(tickCounter == 0)
        {
            stickyState = StickyStates.None;
            tickCounter = 3;
        }
        
    }

    @Override
    public ArrayList<Active> getends()
    {
        return ends;
    }

    @Override
    public ArrayList<Integer> getstates()
    {   
        ArrayList<Integer> states = new ArrayList<>();

        if(isDamaged)
            states.add(1);
        else states.add(0);

        if(isSlippy)
            states.add(1);
        else states.add(0);

        if(stickyState == StickyStates.None)
            states.add(0);
        if(stickyState == StickyStates.Sticky)
            states.add(1);
        if(stickyState == StickyStates.StickyAndOccupied)
            states.add(1);

        return states;
    }


    /**
     * megvizsgalja hogy le lehet e venni a jatekost a csorol
     *
     * @return jatekos eltavolthato-e
     */
    @Override
    protected boolean isPlayerRemoveable()
    {
        if (stickyState != StickyStates.Sticky)
        {
            return true;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Ragasztos cso");
            }
            return false;
        }
    }

    /**
     * megvizsgalja hogy hozza lehet e adni jatekost a csohoz
     *
     * @return 
     */
    @Override
    protected boolean isPlayerAddable()
    {
        if (!isLoose())
        {
            if (isFree())
            {
                return true;
            }
            else
            {
                if (debugEnabled)
                {
                    debugOutput.println(LOG_PIPE + this + ": Foglalt cso");
                }
                return false;
            }
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Lelogo cso");
            }
            return false;
        }
    }

    /**
     * Ha rajta all a player akkor leveszi Siker esetén 0 visszatérés
     *
     * @param player játékos
     * @return Hibakod
     */
    @Override
    protected int removePlayer(Player player)
    {
        if (players.remove(player))
        {
            if (stickyState == StickyStates.StickyAndOccupied)
            {
                stickyState = StickyStates.Sticky;
            }
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Sikeres eltavolitas");
            }
            return SUCCESS;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Nincs ilyen jatekos");
            }
            return 2;
        }
    }

    /**
     * A jatekost atmozgatja a mezore ha lehetseges a felteteleknek megfeleloen
     *
     * @param player játékos
     * @return PLACEHOLDER
     */
    @Override
    public Field movePlayer(Player player)
    {
        if (isNeighbour(player.position))
        {
            if (isPlayerAddable() && player.position.isPlayerRemoveable())
            {
                if (isSlippy)
                {
                    if (debugEnabled)
                    {
                        debugOutput.println(LOG_PIPE + this + ": Csuszos cso");
                    }
                    int neighbourIndex = 0;
                    Field neighbour = ends.get(neighbourIndex);
                    if (neighbour.isPlayerAddable())
                    {
                        neighbour.addPlayer(player);
                        player.position.removePlayer(player);
                        return neighbour;
                    }
                    else
                    {
                        if (debugEnabled)
                        {
                            debugOutput.println(LOG_PIPE + neighbour + ", " + player.position
                                    + ": Nem lehetseges a mozgatas");
                        }
                        return null;
                    }
                }
                else
                {
                    addPlayer(player);
                    player.position.removePlayer(player);
                    return this;
                }
            }
            else
            {
                if (debugEnabled)
                {
                    debugOutput.println(LOG_PIPE + this + ", " + player.position
                            + ": Nem lehetseges a mozgatas");
                }
                return null;
            }
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Nem szomszedos mezo");
            }
            return null;
        }
    }

    /**
     * ellenőrzi, hogy az adott mező szomszédos-e az adott csővel
     *
     * @param field objektumot kap paraméterként, és igazzal tér vissza, ha a kapott
     *              Field objektum szerepel az ends listában, vagyis ha a kapott
     *              mező a Pipe objektum végpontja
     */
    @Override
    public boolean isNeighbour(Field field)
    {
        return ends.contains(field);
    }

    /**
     * Ha ures a players akkor jo
     *
     * @return akkor tér vissza igazzal, ha a players lista üres, azaz nincs játékos
     *         az adott mezőn
     */
    protected boolean isFree()
    {
        return players.isEmpty();
    }

    /**
     * Jelenleg akkor Loose ha nincs mind a 2 vege beallitva
     *
     * @return igaz, ha az adott cső csak egyik végpontjával kapcsolódik másik
     *         mezőhöz.
     */
    protected boolean isLoose()
    {
        return ends.size() < 2;
    }

    /**
     * Felkapcsol egy aktiv mezot. Akkor tudjuk hozzadugni ha meg nincs 2 vege
     * beallitva
     *
     * @param active a kapcsolando aktiv mezo
     * @return int
     */
    public int connectActive(Active active)
    {
        if (isLoose())
        {
            if (!ends.contains(active))
            {
                ends.add(active);
                if (debugEnabled)
                {
                    debugOutput.println(LOG_PIPE + this + ": Sikeres felkapcsolas");
                }
                return SUCCESS;
            }
            else
            {
                if (debugEnabled)
                {
                    debugOutput.println(LOG_PIPE + this + ": Mar felkapcsolt aktiv elem");
                }
                return 3;
            }
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Mindket veg foglalt");
            }
            return 2;
        }
    }

    /**
     * Lekapcsol egy aktiv mezot. Lekoti a kert aktivot, csak sajat mezore hivodik
     *
     * @param active a kapcsolando aktiv mezo
     * @return Hibakod
     */
    public int disconnectActive(Active active)
    {
        if (isFree())
        {
            if (ends.remove(active))
            {
                spillWater();
                if (debugEnabled)
                {
                    debugOutput.println(LOG_PIPE + this + ": Sikeres lekapcsolas");
                }
                return SUCCESS;
            }
            else
            {
                if (debugEnabled)
                {
                    debugOutput.println(LOG_PIPE + this + ": Nincs ilyen veg");
                }
                return 4;
            }
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Foglalt cso");
            }
            return 3;
        }
    }

    /**
     * Ha a debugEnabled változó igazra van állítva, akkor a metódus kiírja a
     * standard kimenetre a cső azonosítóját, az átvitt vízmennyiséget és a vízszint
     * értéké
     *
     * @param quantity Az atadando viz mennyisege
     * @return Az atadott viz mennyisege
     */
    public int transmitWater(int quantity)
    {
        int transmitted = Integer.min(quantity, waterLevel);
        waterLevel -= transmitted;
        if (debugEnabled)
        {
            debugOutput.println(
                    LOG_PIPE + this + ": " + transmitted + " viz atadva, vizszint: " + waterLevel);
        }
        return transmitted;
    }

    /**
     * Kapott vízmennyiség
     *
     * @param quantity A fogadando viz mennyisege
     * @return A fogadott viz mennyisege
     */
    public int recieveWater(int quantity)
    {
        int recieved = Integer.min(quantity, maxWaterLevel - waterLevel);
        waterLevel += recieved;
        if (isDamaged || isLoose())
        {
            spillWater();
        }
        if (debugEnabled)
        {
            debugOutput.println(
                    LOG_PIPE + this + ": " + recieved + " viz atveve, vizszint: " + waterLevel);
        }
        return recieved;
    }

    /**
     * A kifolyo vizbol pontot ad
     */
    protected void spillWater()
    {
        game.addSaboteurPoint(waterLevel);
        waterLevel = 0;
        if (debugEnabled)
        {
            debugOutput.println(LOG_PIPE + this + ": Kifolyt viz");
        }
    }

    /**
     * Kiírja a szomszédokat
     */
    @Override
    public void listNeighbours()
    {
        for (Active active : ends)
        {
            debugOutput.println(active);
        }
        debugOutput.println("");
    }

    /**
     * Megjavitja ha meg lehet
     *
     * @return Hibakod
     */
    @Override
    public int repair()
    {
        if (isDamaged)
        {
            isDamaged = false;
            protectedRounds = 2; // majd random
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Sikeres javitas");
            }
            return SUCCESS;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Mar meg van javitva");
            }
            return 2;
        }
    }

    /**
     * Elrontja ha lehet
     *
     * @return Hibakod
     */
    @Override
    public int damage()
    {
        if (!isDamaged && protectedRounds <= 0)
        {
            isDamaged = true;
            spillWater();
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Sikeres rongalas");
            }
            return SUCCESS;
        }
        else if (protectedRounds > 0)
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": A cso vedett");
            }
            return 3;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Mar meg van rongalva");
            }
            return 2;
        }
    }

    /**
     * Lehelyez egy pumpat a cso mezore. Megjvaitja a mezot, majd letrehoz egy uj
     * csovet, es ballitja a vegeket ezeknek megfeleloen mindharom entitason
     *
     * @param pump A lehelyezendo pumpa
     * @return Hibakod
     */
    @Override
    public int placePump(Pump pump)
    {
        repair();
        game.fields.add(pump);
        Pipe newPipe = new Pipe();
        game.fields.add(newPipe);
        ends.get(0).replacePipe(this, newPipe);
        newPipe.connectActive(ends.get(0));
        ends.remove(0);
        pump.connectPipe(this);
        pump.connectPipe(newPipe);
        if (debugEnabled)
        {
            debugOutput.println(LOG_PIPE + this + ": Sikeres pumpa lehelyezes");
        }
        return SUCCESS;
    }

    /**
     * Csúszóssá teszi
     */
    @Override
    public int makeSlippy()
    {
        if (isSlippy)
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Mar csuszos");
            }
            return 2;
        }
        else
        {
            isSlippy = true;
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Sikeres csuszossa tetel");
            }
            return SUCCESS;
        }
    }

    /**
     * ragadóssá teszi
     *
     * @return 0: sikeres
     * @return 2: a cső már csúszós volt
     */
    @Override
    public int makeSticky()
    {
        if (stickyState != StickyStates.None)
        {
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Mar ragados");
            }
            return 2;
        }
        else
        {
            stickyState = StickyStates.StickyAndOccupied;
            if (debugEnabled)
            {
                debugOutput.println(LOG_PIPE + this + ": Sikeres ragadossa tetel");
            }
            return SUCCESS;
        }
    }
}

package src;


import java.util.ArrayList;

/**
 * A tesztesetek megvalósításai A blokkok egy játéktér inicializálással
 * kezdődnek Ezt követően megtörténnek a szükséges lépések a lefutás érdekében
 * 
 */
public class Test
{
    public static void PrintInit()
    {
        System.out.println("");
        System.out.println("############################################");
        System.out.println("");
        System.out.println("Test: Init");
        System.out.println("");
    }

    public static void PrintTest()
    {
        System.out.println("");
        System.out.println("Kesz");
        System.out.println("");

        System.out.println("Test: Teszteset");
        System.out.println("");
    }

    public static void PrintFinish()
    {
        System.out.println("");
        System.out.println("Kesz");
        System.out.println("");
        System.out.println("############################################");
    }

    /**
     * sikeres mozgás üres csőre
     * 
     */
    public static void MoveFreePipeMechanic()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Pump(5));
        fields.add(new Pipe());
        fields.add(new Drain());
        fields.get(0).connectPipe((Pipe) fields.get(1));
        fields.get(2).connectPipe((Pipe) fields.get(1));
        Mechanic mechanic = new Mechanic(fields.get(0));
        Saboteur saboteur = new Saboteur(fields.get(2));
        PrintTest();
        mechanic.move(fields.get(1));
        PrintFinish();
    }

    /**
     * sikertelen mozgás foglalt csőre
     * 
     */
    public static void MoveOccupiedPipeMechanic()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Pump(5));
        fields.add(new Pipe());
        fields.add(new Drain());
        fields.get(0).connectPipe((Pipe) fields.get(1));
        fields.get(2).connectPipe((Pipe) fields.get(1));
        Mechanic mechanic = new Mechanic(fields.get(0));
        Saboteur saboteur = new Saboteur(fields.get(1));
        PrintTest();
        mechanic.move(fields.get(1));
        PrintFinish();
    }

    /**
     * sikeres mozgás aktív elemre
     * 
     */
    public static void MoveActiveMechanic()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Pump(5));
        fields.add(new Pipe());
        fields.add(new Drain());
        fields.get(0).connectPipe((Pipe) fields.get(1));
        fields.get(2).connectPipe((Pipe) fields.get(1));
        Mechanic mechanic = new Mechanic(fields.get(1));
        Saboteur saboteur = new Saboteur(fields.get(2));
        PrintTest();
        mechanic.move(fields.get(2));
        PrintFinish();
    }

    /**
     * sikeres mozgás üres csőre
     * 
     */
    public static void MoveFreePipeSaboteur()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Pump(5));
        fields.add(new Pipe());
        fields.add(new Drain());
        fields.get(0).connectPipe((Pipe) fields.get(1));
        fields.get(2).connectPipe((Pipe) fields.get(1));
        Mechanic mechanic = new Mechanic(fields.get(0));
        Saboteur saboteur = new Saboteur(fields.get(2));
        PrintTest();
        saboteur.move(fields.get(1));
        PrintFinish();
    }

    /**
     * sikertelen mozgás foglalt csőre
     * 
     */
    public static void MoveOccupiedPipeSaboteur()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Pump(5));
        fields.add(new Pipe());
        fields.add(new Drain());
        fields.get(0).connectPipe((Pipe) fields.get(1));
        fields.get(2).connectPipe((Pipe) fields.get(1));
        Mechanic mechanic = new Mechanic(fields.get(1));
        Saboteur saboteur = new Saboteur(fields.get(2));
        PrintTest();
        saboteur.move(fields.get(1));
        PrintFinish();
    }

    /**
     * sikeres mozgás aktív elemre
     * 
     */
    public static void MoveActiveSaboteur()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Pump(5));
        fields.add(new Pipe());
        fields.add(new Drain());
        fields.get(0).connectPipe((Pipe) fields.get(1));
        fields.get(2).connectPipe((Pipe) fields.get(1));
        Mechanic mechanic = new Mechanic(fields.get(0));
        Saboteur saboteur = new Saboteur(fields.get(1));
        PrintTest();
        saboteur.move(fields.get(0));
        PrintFinish();
    }

    /**
     * Lekapcsol egy csövet arrébb viszi, felkapcsolja máshova
     * 
     */
    public static void PipeDisconnectConnect()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(5));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe3);
        actives.get(3).connectPipe(pipe3);
        Mechanic mechanic = new Mechanic(actives.get(0));
        PrintTest();
        mechanic.move(pipe1);
        mechanic.move(actives.get(1));
        mechanic.disconnectPipe(pipe1);
        mechanic.move(pipe2);
        mechanic.move(actives.get(2));
        mechanic.connectPipe(pipe1);
        PrintFinish();
    }

    /**
     * Kilyukaszt egy csovet
     * 
     */
    public static void PipeDamage()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(5));
        Pipe pipe1 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        Saboteur sab = new Saboteur(actives.get(0));
        PrintTest();
        sab.move(pipe1);
        sab.damage();
        PrintFinish();
    }

    /**
     * Megjavít egy csövet
     * 
     */
    public static void PipeRepair()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(5));
        Pipe pipe1 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        Saboteur sab = new Saboteur(actives.get(0));
        Mechanic mechanic = new Mechanic(actives.get(0));
        PrintTest();
        sab.move(pipe1);
        sab.damage();
        sab.move(actives.get(1));
        mechanic.move(pipe1);
        mechanic.repair();
        PrintFinish();
    }

    /**
     * A rendszer elront egy pumpát
     * 
     */
    public static void PumpDamage()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        Source source = new Source();
        Pipe pipe1 = new Pipe();
        Pump pump = new Pump(5);
        source.connectPipe(pipe1);
        pump.connectPipe(pipe1);
        PrintTest();
        pump.trytoDamage();
        PrintFinish();
    }

    /**
     * Megjavít egy pumpát
     * 
     */
    public static void PumpRepair()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        Source source = new Source();
        Pipe pipe1 = new Pipe();
        Pump pump = new Pump(5);
        source.connectPipe(pipe1);
        pump.connectPipe(pipe1);
        pump.trytoDamage();
        Mechanic mechanic = new Mechanic(source);
        PrintTest();
        mechanic.move(pipe1);
        mechanic.move(pump);
        mechanic.repair();
        PrintFinish();
    }

    /**
     * lehelyez egy pumpat a jatekos
     * 
     */
    public static void PumpPlace()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Drain());
        actives.add(new Pump(5));
        Pipe pipe1 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        Mechanic mechanic = new Mechanic(actives.get(0));
        PrintTest();
        mechanic.pickUpPump();
        mechanic.move(pipe1);
        mechanic.placePump(mechanic.pumps.get(0));
        PrintFinish();
    }

    /**
     * Beallitja a pumpa iranyat
     * 
     */
    public static void PumpSetDirection()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        Source source = new Source();
        Pipe pipe1 = new Pipe();
        Pump pump = new Pump(5);
        Drain drain = new Drain();
        Pipe pipe2 = new Pipe();
        source.connectPipe(pipe1);
        pump.connectPipe(pipe1);
        pump.connectPipe(pipe2);
        drain.connectPipe(pipe2);
        Mechanic mechanic = new Mechanic(source);
        PrintTest();
        mechanic.move(pipe1);
        mechanic.move(pump);
        mechanic.setPumpDirection(pipe1, pipe2);
        PrintFinish();
    }

    /**
     * Felvesz egy csovet a drainbol
     * 
     */
    public static void DrainPickUpPipe()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Drain());
        actives.add(new Pump(5));
        Pipe pipe1 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        Mechanic mechanic = new Mechanic(actives.get(0));
        PrintTest();
        mechanic.pickUpPipe();
        PrintFinish();
    }

    /**
     * felvesz egy pumpat a drainbol
     * 
     */
    public static void DrainPickUpPump()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Drain());
        actives.add(new Pump(5));
        Pipe pipe1 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        Mechanic mechanic = new Mechanic(actives.get(0));
        PrintTest();
        mechanic.pickUpPump();
        PrintFinish();
    }

    /**
     * Vizet pumpal a rendszerben
     * 
     */
    public static void WaterPump()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe3);
        actives.get(3).connectPipe(pipe3);
        actives.get(1).setPumpDirection(pipe1, pipe2);
        actives.get(2).setPumpDirection(pipe2, pipe3);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Eloszor eppen, majd Vizet folyat serult pumpaba
     * 
     */
    public static void WaterDamagedPump()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Pump(8));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        Pipe pipe4 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe3);
        actives.get(3).connectPipe(pipe3);
        actives.get(3).connectPipe(pipe4);
        actives.get(4).connectPipe(pipe4);
        actives.get(1).setPumpDirection(pipe1, pipe2);
        actives.get(2).setPumpDirection(pipe2, pipe3);
        actives.get(3).setPumpDirection(pipe3, pipe4);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        ((Pump) actives.get(2)).trytoDamage();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet folyat teli csovekbe
     * 
     */
    public static void WaterFullPipe()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe3);
        actives.get(3).connectPipe(pipe3);
        actives.get(1).setPumpDirection(pipe1, pipe2);
        actives.get(2).setPumpDirection(pipe2, pipe3);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        ((Pump) actives.get(2)).trytoDamage();
        System.out.println("--------- Test: Pumpa elrontva, a megelozo cso fel fog telni");
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet folyat ures csobe
     * 
     */
    public static void WaterEmptyPipe()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe3);
        actives.get(3).connectPipe(pipe3);
        actives.get(1).setPumpDirection(pipe1, pipe2);
        actives.get(2).setPumpDirection(pipe2, pipe3);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        ((Pump) actives.get(1)).trytoDamage();
        System.out.println("--------- Test: Pumpa elrontva, az output cso ki fog urulni");
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet dolyat serult csobe
     * 
     */
    public static void WaterToDamagedPipe()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe3);
        actives.get(3).connectPipe(pipe3);
        actives.get(1).setPumpDirection(pipe1, pipe2);
        actives.get(2).setPumpDirection(pipe2, pipe3);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        pipe2.damage();
        System.out.println("--------- Test: Cso elrontva, a belekerulo viz kifolyik");
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet probal szivni serult csobol
     * 
     */
    public static void WaterFromDamagedPipe()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Pump(5));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe3);
        actives.get(3).connectPipe(pipe3);
        actives.get(1).setPumpDirection(pipe1, pipe2);
        actives.get(2).setPumpDirection(pipe2, pipe3);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        pipe2.damage();
        System.out.println(
                "--------- Test: Cso elrontva, a belole nem lehet vizet kiszivni, mert ures");
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet folyat a drainbe
     * 
     */
    public static void WaterDrain()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe2);
        actives.get(1).setPumpDirection(pipe1, pipe2);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet sziv a forrasbol
     * 
     */
    public static void WaterSource()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe2);
        actives.get(1).setPumpDirection(pipe1, pipe2);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }

    /**
     * Vizet folyat felig bedugott csobe
     * 
     */
    public static void WaterLoosePipe()
    {
        PrintInit();
        Game game = new Game();
        Drain.setGame(game);
        Pipe.setGame(game);
        ArrayList<Active> actives = new ArrayList<>();
        actives.add(new Source());
        actives.add(new Pump(3));
        actives.add(new Drain());
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        actives.get(0).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe1);
        actives.get(1).connectPipe(pipe2);
        actives.get(2).connectPipe(pipe2);
        actives.get(1).setPumpDirection(pipe1, pipe2);
        PrintTest();
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        actives.get(2).disconnectPipe(pipe2);
        System.out.println("--------- Test: Cso lecsatlakoztatva, a belekerulo viz kifolyik");
        for (int i = 0; i < 5; i++)
        {
            for (Active active : actives)
            {
                active.step();
                System.out.println("");
            }
            System.out.println("");
        }
        PrintFinish();
    }
}

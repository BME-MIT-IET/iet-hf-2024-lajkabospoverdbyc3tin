import java.util.ArrayList;

public class GameModel
{

	public static Game game = new Game(System.out);
	protected ArrayList<iObserver> observers;
    int watertarget;

	public GameModel()
	{
		observers = new ArrayList<>();
	}

	public void AddObserver(iObserver o)
	{
		observers.add(o);
	}
	
	public void RemoveObserver(iObserver o)
	{
		observers.remove(o);
	}
	
	public void NotifyObserversGameUpdated(Game game)
	{	
		for(iObserver observer : observers)
		{
			observer.notifyGameUpdated(game);
		}
	}
	
	public void NotifyObserversPlayerUpdated(ArrayList<Player> players)
	{	
		for(iObserver observer : observers)
		{
			observer.notifyPlayerUpdated(players);
		}
	}

	public void NotifyObserversGameStarted(int n, int water)
	{	
        watertarget = water;
		for(iObserver observer : observers)
		{
			observer.notifyGameStart(n, water);;
		}
	}

	public void NotifyObserversCommandSent(String command)
	{
		for(iObserver observer : observers)
		{
			observer.notifyCommandSent(command);;
		}
	}

    public void NotifyObserversGameEnded(String winner)
    {
        for(iObserver observer : observers)
        {
            observer.notifyGameEnded(winner);
        }
    }
	
	
	public void PerformAction(String action)
	{
		System.out.println(action);
		if(Interpreter(action) == 0)
		{
			String[] command = action.split(" ");
			

			NotifyObserversGameUpdated(game);
            NotifyObserversPlayerUpdated(game.players);
            game.step();
            System.out.println(game.getMechanicPoint());
            System.out.println(game.getSaboteurPoint());
            if(game.getMechanicPoint() >= watertarget)
            NotifyObserversGameEnded("Mechanic");
            if(game.getSaboteurPoint() >= watertarget)
            NotifyObserversGameEnded("Saboteur");
            
		}
	}

	public int Interpreter(String input)
    {
        return Interpreter(input, null);
    }

    /**
     * Az Interpreter metódus felelős a játékos parancsok értelmezéséért és
     * végrehajtásáért a játékban.
     */
    public int Interpreter(String input, Player player)
    {
        String[] command = input.split(" ");
        if (player == null && command.length > 1)
        {
            player = game.getPlayerByID(command[1]);
        }
        
        switch (command[0].toLowerCase())
        {
     
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
            System.out.println(command[2]);
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
        case "changedirection":
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
        case "slippy":
        {
            return player.makeSlippy();
        }
        case "sticky":
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
        
        default:
        {
            System.out.println("Ervenytelen parancs: " + input);
            return -1;
        }
        }


    }
}

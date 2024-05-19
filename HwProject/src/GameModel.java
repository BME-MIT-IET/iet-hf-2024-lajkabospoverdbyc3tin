package HwProject.src;


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

	public void addObserver(iObserver o)
	{
		observers.add(o);
	}
	
	public void removeObserver(iObserver o)
	{
		observers.remove(o);
	}
	
	public void notifyObserversGameUpdated(Game game)
	{	
		for(iObserver observer : observers)
		{
			observer.notifyGameUpdated(game);
		}
	}
	
	public void notifyObserversPlayerUpdated(ArrayList<Player> players)
	{	
		for(iObserver observer : observers)
		{
			observer.notifyPlayerUpdated(players);
		}
	}

	public void notifyObserversGameStarted(int n, int water)
	{	
        watertarget = water;
		for(iObserver observer : observers)
		{
			observer.notifyGameStart(n, water);;
		}
	}

	public void notifyObserversCommandSent(String command)
	{
		for(iObserver observer : observers)
		{
			observer.notifyCommandSent(command);;
		}
	}

    public void notifyObserversGameEnded(String winner)
    {
        for(iObserver observer : observers)
        {
            observer.notifyGameEnded(winner);
        }
    }
	
	
	public void performAction(String action)
	{
		if(interpreter(action) == 0)
		{			

			notifyObserversGameUpdated(game);
            notifyObserversPlayerUpdated(game.players);
            game.step();
            System.out.println(game.getMechanicPoint());
            System.out.println(game.getSaboteurPoint());
            if(game.getMechanicPoint() >= watertarget)
                notifyObserversGameEnded("Mechanic");
            if(game.getSaboteurPoint() >= watertarget)
                notifyObserversGameEnded("Saboteur");
            
		}
	}

	public int interpreter(String input)
    {
        return interpreter(input, null);
    }

    /**
     * Az Interpreter metódus felelős a játékos parancsok értelmezéséért és
     * végrehajtásáért a játékban.
     */
    public int interpreter(String input, Player player)
    {
        String[] command = input.split(" ");
        if (player == null && command.length > 1)
        {
            player = game.getPlayerByID(command[1]);
        }
        if(player == null)
            return -1;
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

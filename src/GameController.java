import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;


public class GameController implements iObserver
{
	public String[] currentaction;
    GameModel  model;
	private PanelView panelView;
    private GameFieldView gameFieldView;
    private StartView  startView;
    private JFrame mainFrame;

	public GameController(GameModel model)
    {   
        this.model = model;
        model.AddObserver(this);

		
    	startView = new StartView(model);
 

		mainFrame = new JFrame("Sivatagi Vizhalozatok");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.add(startView);

        mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public void startGame(int n, int water)
    {   
        for(int i = 1; i <= n; i++)
        {
            GameModel.game.players.add(new Mechanic("Mechanic"+i, getFreeDrain()));
            GameModel.game.players.add(new Saboteur("Saboteur"+i, getFreeSource()));
        }
        for (Field field : GameModel.game.fields) 
        {   
            //System.out.println(field.GetID() + "  " + field.players.get(0));    
        }

        mainFrame.remove(startView);
        panelView = new PanelView(n, model);
    	gameFieldView = new GameFieldView(GameModel.game);
        mainFrame.add(panelView);
        mainFrame.add(gameFieldView);

        mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }

    private void endgame(String winner) {
        mainFrame.dispose();
        EndView endview = new EndView(winner);
    }

    public Field getFreeDrain()
    {
        int min = Integer.MAX_VALUE;
        for (Field field : GameModel.game.fields)
        {
            if(field.getID().startsWith("Drain") && field.players.size() <=min)
            min = field.players.size();
        }
        for (Field field : GameModel.game.fields)
        {
            if(field.getID().startsWith("Drain") && field.players.size() <=min)
            return field;
        }
        return null;
       
    }

    public Field getFreeSource()
    {
        int min = Integer.MAX_VALUE;
        for (Field field : GameModel.game.fields)
        {
            if(field.getID().startsWith("Source") && field.players.size() <=min)
            min = field.players.size();
        }
        for (Field field : GameModel.game.fields)
        {
            if(field.getID().startsWith("Source") && field.players.size() <=min)
            return field;
        }
        return null;
       
    }

    

    @Override
    public void notifyGameUpdated(Game g) {
        gameFieldView.UpdateView(g);
    }

    @Override
    public void notifyPlayerUpdated(ArrayList<Player> players) {
        panelView.updatePlayer(players);
    }

    @Override
    public void notifyGameStart(int players, int water) {
        startGame(players, water);
    }

    @Override
    public void notifyCommandSent(String command) {
        model.PerformAction(command);
    }

    @Override
    public void notifyGameEnded(String winner) {
        endgame(winner);
    }

    
}

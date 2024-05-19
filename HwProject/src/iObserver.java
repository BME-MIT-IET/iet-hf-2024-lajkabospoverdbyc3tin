package HwProject.src;


import java.util.ArrayList;

public interface iObserver
{
	
	public void notifyGameUpdated(Game g);

	public void notifyGameStart(int players, int water);

	public void notifyPlayerUpdated(ArrayList<Player> players);

	public void notifyCommandSent(String command);

	public void notifyGameEnded(String winner);
}

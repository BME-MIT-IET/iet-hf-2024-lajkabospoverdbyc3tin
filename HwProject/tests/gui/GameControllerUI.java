package gui;

import Game.GameController;
import Game.GameModel;

import javax.swing.*;

public class GameControllerUI extends GameController {

    GameControllerUI(GameModel model) {
        super(model);
    }

    public JFrame getFrame() {
        return mainFrame;
    }
}

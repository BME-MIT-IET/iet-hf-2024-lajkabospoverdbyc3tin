package gui;

import static gui.TestingTools.findButtonByText;


import HwProject.*;
import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.exception.ActionFailedException;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StartWindowTests {
    private FrameFixture window;

    @BeforeEach
    public void setUp() {
        var gameController = new GameControllerUI(new GameModel());
        var gui = gameController.getFrame();

        window = new FrameFixture(gui);
        window.show(); // shows the frame to test
    }

    @Test
    @GUITest
    public void gameStartsWithTitle() {
        window.requireTitle("Sivatagi Vizhalozatok");
    }

    @Test
    @GUITest
    public void startWindowBackground() {
        window.panel("StartPanel").background().requireEqualTo(Color.BLACK);
    }

    @Test
    @GUITest
    public void startWindowNotResizable() {
        assertThrows(ActionFailedException.class, () -> {
            var oldSize = window.target().getSize();
            var newSize = new Dimension(oldSize.width + 100, 800);
            window.resizeTo(newSize);
            //window.requireSize(oldSize);
        });
    }

    @Test
    @GUITest
    public void guiExists() {
        window.requireVisible();
    }

    @Test
    @GUITest
    public void checkButtonEnabled() {
        window.button().requireText("Start").requireEnabled();
    }

    @Test
    @GUITest
    public void gameStartsWithNoSettings() {
        window.button().requireText("Start").click();
        window.button(findButtonByText("Done")).requireEnabled().requireVisible();
    }

    @Test
    @GUITest
    public void gameStartsWithWrongSettings() {
        window.textBox("PlayerInput").enterText("-1");
        window.textBox("WaterInput").enterText("ADASDASDASD");
        window.button().requireText("Start").click();
        window.button(findButtonByText("Done")).requireEnabled().requireVisible();
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }

}

import org.assertj.swing.core.GenericTypeMatcher;

import javax.swing.*;

public class TestingTools {

    public static GenericTypeMatcher<JButton> findButtonByText(String text) {
        return new GenericTypeMatcher<JButton>(JButton.class) {
            @Override
            protected boolean isMatching(JButton button) {
                return text.equals(button.getText());
            }
        };
    }

}

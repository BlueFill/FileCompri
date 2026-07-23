import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @author Doldisurround, BlueFill
 * @version 1.0.0
 */

public class Button extends JButton {
    public Button(String title, ActionListener onClick) {
        super(title);
        addActionListener(onClick);
    }
}

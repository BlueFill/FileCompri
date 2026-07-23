import javax.swing.*;
import java.awt.event.ActionListener;

public class Button extends JButton {
    public Button(String title, ActionListener onClick){
        super(title);
        addActionListener(onClick);
    }
}

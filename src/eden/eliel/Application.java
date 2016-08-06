package eden.eliel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Eden on 8/7/2016.
 */
public class Application extends JFrame {
    private JTextField _inputField;
    private ArrayList<JTextField> _seats;
    private JPanel _seatsPanel;

    public Application(){
        setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setInputField();
        setSeatsPanel();

        add(_inputField);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(_seatsPanel);

        pack();
    }

    private void setInputField(){
        _inputField = new JTextField();
    }
    private void setSeatsPanel(){
        _seatsPanel = new JPanel();
        _seatsPanel.setLayout(new BoxLayout(_seatsPanel,BoxLayout.PAGE_AXIS));

        _seats = new ArrayList<>();
        _seats.add(new JTextField());

        for (JTextField seat:_seats){
            _seatsPanel.add(seat);
        }
    }
}

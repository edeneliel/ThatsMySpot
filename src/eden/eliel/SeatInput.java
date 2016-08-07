package eden.eliel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Eden on 8/7/2016.
 */
public class SeatInput extends JPanel{
    int _seatId;
    JLabel _seatTag;
    JTextField _seatInput;
    JButton _cancelBtn;

    public SeatInput(int seatNum){
        setLayout(new FlowLayout(FlowLayout.LEFT));

        _seatId = seatNum;
        _seatTag = new JLabel("Seat " + _seatId + ": ");
        _seatInput = new JTextField(5);
        _cancelBtn = new JButton("X");

        add(_seatTag);
        add(_seatInput);
        add(_cancelBtn);
    }

    public int getSeatId() {
        return _seatId;
    }
    public String getSeatInput() {
        return _seatInput.getText();
    }
    public void addCancelButtonListener(ActionListener action){
        _cancelBtn.addActionListener(action);
    }
    public void updateId(int newId){
        _seatId = newId;
        _seatTag.setText("Seat " + _seatId + ": ");
    }
}

package eden.eliel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Eden on 8/7/2016.
 */
public class Application extends JFrame {
    private SpotSaver _spotSaver;
    private JPanel _inputPanel;
    private JPanel _seatsPanel;
    private JPanel _btnPanel;
    private JButton _activateBtn;
    private JButton _addSeatBtn;
    private JTextField _inputUrl;
    private ArrayList<SeatInput> _seats;

    public Application(){
        setLayout(new BoxLayout(getContentPane(),BoxLayout.LINE_AXIS));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        _spotSaver = new SpotSaver();
        setInputPanel();
        setButtonPanel();

        _inputPanel.setAlignmentY(0f);
        add(_inputPanel);
        _btnPanel.setAlignmentY(0f);
        add(_btnPanel);

        pack();
    }

    private void setButtonPanel(){
        _btnPanel = new JPanel();
        _btnPanel.setLayout(new BoxLayout(_btnPanel,BoxLayout.PAGE_AXIS));
        _btnPanel.setAlignmentX(RIGHT_ALIGNMENT);
        _btnPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));

        setActivateBtn();
        setAddSeatBtn();

        _activateBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        _btnPanel.add(_activateBtn);
        _btnPanel.add(Box.createRigidArea(new Dimension(0,10)));
        _addSeatBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        _btnPanel.add(_addSeatBtn);
    }
    private void setInputPanel() {
        _inputPanel = new JPanel();
        _inputPanel.setLayout(new BoxLayout(_inputPanel,BoxLayout.PAGE_AXIS));

        setInputField();
        setSeatsPanel();

        _inputPanel.add(_inputUrl);
        _inputPanel.add(Box.createRigidArea(new Dimension(0,10)));
        _inputPanel.add(_seatsPanel);
    }
    private void setInputField(){
        _inputUrl = new JTextField();
        _inputUrl.setColumns(30);
    }
    private void setSeatsPanel(){
        _seatsPanel = new JPanel();
        _seatsPanel.setLayout(new BoxLayout(_seatsPanel,BoxLayout.Y_AXIS));

        _seats = new ArrayList<>();
        SeatInput firstSeat = new SeatInput(1);
        firstSeat.addCancelButtonListener(e1 -> {
            JOptionPane.showMessageDialog(this, "You need atleast one seat");
        });
        _seats.add(firstSeat);

        updateSeatsInputs();
    }
    private void setActivateBtn(){
        _activateBtn = new JButton("Activate");
        _activateBtn.addActionListener(e -> {
            String [] seatsIds = new String [_seats.size()];
            for (int i = 0; i < _seats.size(); i++)
                seatsIds[i] = _seats.get(i).getSeatInput();
            _spotSaver.Execute(_inputUrl.getText(),seatsIds);
        });
    }
    private void setAddSeatBtn(){
        _addSeatBtn = new JButton("Add Seat");
        _addSeatBtn.addActionListener(e -> {
            SeatInput seat = makeNewSeat();
            _seats.add(seat);
            updateSeatsInputs();
        });
    }

    private SeatInput makeNewSeat(){
        SeatInput seat = new SeatInput(_seats.size()+1);
        seat.addCancelButtonListener(e -> {
            _seats.remove(seat.getSeatId()-1);
            minimizeSeatsArray();
            updateSeatsInputs();
        });
        return seat;
    }
    private void updateSeatsInputs(){
        _seatsPanel.removeAll();
        for (SeatInput seat:_seats)
            _seatsPanel.add(seat);
        _seatsPanel.updateUI();
        pack();
    }
    private void minimizeSeatsArray(){
        for (int i = 0; i < _seats.size(); i++)
            _seats.get(i).updateId(i+1);
    }
}

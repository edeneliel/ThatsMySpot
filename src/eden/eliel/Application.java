package eden.eliel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Eden on 8/7/2016.
 */
public class Application extends JFrame {
    private SpotSaver _spotSaver;
    private JPanel _inputPanel;
    private JPanel _btnPanel;
    private JButton _activateBtn;
    private JButton _selectSeatsBtn;
    private JTextField _inputUrl;
    private String[] _selectedSeatsIds;

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

    public void setSelectedSeats(String[] seats){
        _selectedSeatsIds = seats;
    }

    private void setButtonPanel(){
        _btnPanel = new JPanel();
        _btnPanel.setLayout(new BoxLayout(_btnPanel,BoxLayout.PAGE_AXIS));
        _btnPanel.setAlignmentX(RIGHT_ALIGNMENT);
        _btnPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));

        setActivateBtn();
        setSelectSeatsBtn();

        _activateBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        _btnPanel.add(_activateBtn);
        _btnPanel.add(Box.createRigidArea(new Dimension(0,10)));
        _selectSeatsBtn.setAlignmentX(RIGHT_ALIGNMENT);
        _btnPanel.add(_selectSeatsBtn);
    }
    private void setInputPanel() {
        _inputPanel = new JPanel();
        _inputPanel.setLayout(new BoxLayout(_inputPanel,BoxLayout.PAGE_AXIS));

        setInputField();

        _inputPanel.add(_inputUrl);
        _inputPanel.add(Box.createRigidArea(new Dimension(0,200)));
    }
    private void setInputField(){
        _inputUrl = new JTextField();
        _inputUrl.setColumns(30);
    }
    private void setActivateBtn(){
        _activateBtn = new JButton("Activate");
        _activateBtn.addActionListener(e -> {
            _spotSaver.Execute(_inputUrl.getText(), _selectedSeatsIds);
        });
    }
    private void setSelectSeatsBtn() {
        _selectSeatsBtn = new JButton("Select Seats");

        _selectSeatsBtn.addActionListener(e -> {
            new SelectSeatsForm(this,_inputUrl.getText());
        });
    }
}

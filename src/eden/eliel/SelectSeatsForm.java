package eden.eliel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created by Eden on 8/11/2016.
 */
public class SelectSeatsForm extends JFrame {
    private final int ROW = 0;
    private final int COLUMN = 1;

    private SeatsUrl _seatsUrl;
    private ArrayList<Seat> _seats;
    private ArrayList<Seat> _selectedSeats;
    private JLabel _loadingMsg;

    public SelectSeatsForm(Application app,String url){
        setVisible(true);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                String [] seatsIds = new String[_selectedSeats.size()];
                for (int i=0; i < seatsIds.length; i++)
                    seatsIds[i] = _selectedSeats.get(i).getId();
                app.setSelectedSeats(seatsIds);
            }
        });

        _loadingMsg = new JLabel("Loading...");
        add(_loadingMsg);
        pack();

        _seatsUrl = new SeatsUrl();
        _seats = _seatsUrl.getSeatsArray(url);

        setLayout(new GridLayout(getMaxSeats(_seats,ROW),getMaxSeats(_seats,COLUMN)));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        remove(_loadingMsg);

        _selectedSeats = new ArrayList<>();

        addSeatsFromArray(_seats);

        pack();
    }

    private void addSeatsFromArray(ArrayList<Seat> seats){
        int maxRow = getMaxSeats(_seats,ROW);
        int maxColumn = getMaxSeats(_seats,COLUMN);

        Seat[][] seatsMat = new Seat[maxRow][maxColumn];

        for (Seat seat : seats){
            seatsMat[seat.getRow()-1][seat.getColumn()-1] = seat;
        }

        for (int i = 0; i < maxRow*maxColumn; i++){
            Seat currentSeat = seatsMat[i/maxColumn][i%maxColumn];
            if (currentSeat != null){
                add(setSeatBtn(currentSeat));
            }
            else
                add(new JPanel());
        }
    }
    private int getMaxSeats(ArrayList<Seat> seats,int direction){
        int max = 0;
        int value;

        for (Seat seat : seats){
            if (direction == ROW)
                value = seat.getRow();
            else
                value = seat.getColumn();
            if (value > max)
                max = value;
        }
        return max;
    }
    private JButton setSeatBtn(Seat seat){
        JButton tempBtn = new JButton("-");
        if (!seat.getStatus()){
            tempBtn.setEnabled(false);
            tempBtn.setBackground(Color.GRAY);
        }
        else
            tempBtn.setBackground(Color.GREEN);

        tempBtn.addActionListener(e -> {
            if (tempBtn.getBackground() == Color.GREEN){
                tempBtn.setBackground(Color.ORANGE);
                _selectedSeats.add(seat);
            }
            else {
                tempBtn.setBackground(Color.GREEN);
                _selectedSeats.remove(seat);
            }

        });

        return tempBtn;
    }
}

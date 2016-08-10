package eden.eliel;

/**
 * Created by Eden on 8/10/2016.
 */
public class Seat {
    private int _row, _column;
    private boolean _status;

    public Seat(int row,int column,boolean status){
        _row = row;
        _column = column;
        _status = status;
    }

    public int getRow() {
        return _row;
    }
    public int getColumn() {
        return _column;
    }
    public boolean getStatus() {
        return _status;
    }
}

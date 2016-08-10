package eden.eliel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Eden on 8/10/2016.
 */
public class Seat {
    private final String SEAT_PATTERN = "_Seat_(\\d+)_(\\d+)";

    private String _id;
    private int _row, _column;
    private boolean _status;

    public Seat(String id, boolean status){
        Pattern p = Pattern.compile(SEAT_PATTERN);
        Matcher matcher;

        matcher = p.matcher(id);
        matcher.find();

        _id = id;
        _row = Integer.parseInt(matcher.group(1));
        _column = Integer.parseInt(matcher.group(2));
        _status = status;
    }

    public String getId() {
        return _id;
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

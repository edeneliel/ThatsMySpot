package eden.eliel;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SpotSaver ss = new SpotSaver();
        String [] seats = {"_Seat_1_4","_Seat_1_5"};
        ss.Execute("https://www.yesplanet.co.il/ecom?s=1010001&p=102512080716-300175#ready",seats);
    }
}

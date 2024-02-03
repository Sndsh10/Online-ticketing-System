package bcpa.backend;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 */

/**
 * @author java.developer
 *
 */
public class SeatHoldManager {
    private static final int HOLD_DURATION = 5; // in minutes
    private static final int ALARM_INTERVAL = 1; // in minutes

    private ArrayList<Seat> heldSeats;
    private Timer timer;

    public SeatHoldManager() {
        this.heldSeats = new ArrayList<>();
        this.timer = new Timer(true);
    }

    public void holdSeat(Seat seat) {
        heldSeats.add(seat);
        setHoldTimer(seat);
    }

    private void setHoldTimer(Seat seat) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Seat hold alarm for seat " + seat + ". Time to complete transaction!");
                // Additional actions for the alarm, e.g., prompt user
            }
        };

        timer.schedule(task, HOLD_DURATION * 60 * 1000, ALARM_INTERVAL * 60 * 1000);
    }

    public void releaseHeldSeats() {
        for (Seat seat : heldSeats) {
            seat.setStatus("Available");
        }
        System.out.println("Held seats released due to incomplete transactions.");
        heldSeats.clear();
        timer.cancel(); // Cancel the timer after releasing held seats
        timer.purge();
    }
}


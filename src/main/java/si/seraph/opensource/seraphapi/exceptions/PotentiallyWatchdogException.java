package si.seraph.opensource.seraphapi.exceptions;

public class PotentiallyWatchdogException extends Exception {

    public PotentiallyWatchdogException() { System.out.println("Potential Watchdog Entity - Ignoring"); }

}
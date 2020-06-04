package si.seraph.opensource.seraphapi.apiwrappers.hypixel.exceptions;

import si.seraph.opensource.seraphapi.utils.SeraphLogger;

public class PotentiallyWatchdogException extends Exception implements SeraphLogger {

    public PotentiallyWatchdogException() { LOGGER.error("Potential Watchdog Entity - Ignoring"); }

}

package si.seraph.opensource.seraphapi.apiwrappers.hypixel.exceptions;

import si.seraph.opensource.seraphapi.utils.SeraphLogger;

public class PlayerReturnedNullException extends Exception implements SeraphLogger {
    public PlayerReturnedNullException() { LOGGER.error("Player returned as null");}

}

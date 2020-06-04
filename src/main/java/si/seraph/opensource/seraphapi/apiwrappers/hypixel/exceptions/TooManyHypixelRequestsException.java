package si.seraph.opensource.seraphapi.apiwrappers.hypixel.exceptions;

import si.seraph.opensource.seraphapi.utils.SeraphLogger;

public class TooManyHypixelRequestsException extends Exception implements SeraphLogger {

    public TooManyHypixelRequestsException() {
        LOGGER.error("API Limit! Wait 1 minute");
    }

}

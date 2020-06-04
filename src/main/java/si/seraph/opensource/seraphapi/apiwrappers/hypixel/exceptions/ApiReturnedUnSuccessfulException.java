package si.seraph.opensource.seraphapi.apiwrappers.hypixel.exceptions;

import si.seraph.opensource.seraphapi.utils.SeraphLogger;

public class ApiReturnedUnSuccessfulException extends Exception implements SeraphLogger {

    public ApiReturnedUnSuccessfulException() {
        LOGGER.error("Failed API Request");
    }

}

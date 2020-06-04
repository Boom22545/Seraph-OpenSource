package si.seraph.opensource.seraphapi.apiwrappers.hypixel.exceptions;

import si.seraph.opensource.seraphapi.utils.SeraphLogger;

public class InvalidKeyException extends Exception implements SeraphLogger {

    public InvalidKeyException() { LOGGER.error("Hypixel API key is invalid!"); }

}

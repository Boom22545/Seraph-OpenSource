package si.seraph.opensource.seraphapi.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface SeraphLogger {

    String SERAPH_LOGGER_NAME = "Seraph Logger";
    // SeraphLogger - Use this instead of System.out.println
    String ERROR = "An error has occurred - code: ";
    Logger LOGGER = LogManager.getLogger(SERAPH_LOGGER_NAME);

}

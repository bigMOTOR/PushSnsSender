package PushSnsSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bigMOTOR on 03/08/15.
 */

public class PushSnsSenderCustomException extends Exception {

    private static final Logger theLogger = LoggerFactory.getLogger(PushSnsSenderCustomException.class);

    public PushSnsSenderCustomException(String message) {

        super(message);
        theLogger.error("PushSnsSenderCustomException: {}", message);
        
    }

}

package PushSnsSender;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bigMOTOR on 18/06/15.
 */

public class SnsNotification {

    private final String subject;
    private final String message;

    //create a JSON
    @JsonCreator
    public SnsNotification(@JsonProperty("subject") String subject, @JsonProperty("message") String message) {
        this.subject = subject;
        this.message = message;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "SnsNotification{" + "subject='" + this.subject + '\'' + ", message='" + this.message + '\'' + '}';
    }

}

package PushSnsSender;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bigMOTOR on 18/06/15.
 */

public class SnsNotification {

    private final String topic;
    private final String message;

    //create a JSON
    @JsonCreator
    public SnsNotification(@JsonProperty("topic") String topic, @JsonProperty("message") String message) {
        this.topic = topic;
        this.message = message;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "SnsNotification{" + "topic='" + this.topic + '\'' + ", message='" + this.message + '\'' + '}';
    }

}

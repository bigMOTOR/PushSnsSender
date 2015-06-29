package PushSnsSender;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SnsAppleNotificationTemplate description with providing to simple Json and SNS-APN's Json
 * v. 1.0.0
 * Created by bigMOTOR on 25/06/15.
 */

public class SnsAppleNotificationTemplate {

    private final String topic;
    private final String message;
    private final int badge;
    private final String sound;

    //create a JSON
    @JsonCreator
    public SnsAppleNotificationTemplate(@JsonProperty("topic") String topic,
                                        @JsonProperty("message") String message,
                                        @JsonProperty("badge") int badge,
                                        @JsonProperty("sound") String sound) {

        this.topic = topic;
        this.message = message;
        this.badge = badge;
        this.sound = sound;

    }

    public String getTopic() {
        return this.topic;
    }

    public String getMessage() {
        return this.message;
    }

    public int getBadge() {
        return this.badge;
    }

    public String getSound() {
        return this.sound;
    }

    @Override
    public String toString() {
        return "{\"topic\": \"" + this.topic + "\",\n" +
                "\"message\": \"" + this.message + "\",\n" +
                "\"badge\": " + this.badge + ",\n" +
                "\"sound\": \"" + this.sound + "\"}";
    }

    public String toSnSAppleString() {
        return "{\"apn\": {\"default\": \"" + this.message + "\", \"APNS_SANDBOX\": \"{\\\"aps\\\": {\\\"alert\\\":\\\"" + this.message + "\\\",\\\"badge\\\": " + this.badge + ",\\\"sound\\\":\\\"" + this.sound + "\\\"}}\"}}";
    }

}

package PushSnsSender;

import com.amazonaws.AmazonServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.auth.BasicAWSCredentials;

/**
 * Created by bigMOTOR on 18/06/15.
 */

//Creation of the controller which will be handle our requests

@RestController                      //- RestController annotation
@RequestMapping("/sns")              //- Mapping annotation

public class SnsSenderController {   //requests controller class

    private static final Logger theLogger = LoggerFactory.getLogger(SnsSenderController.class);
    private static BasicAWSCredentials awsCreds;

    //AWS credentials from properties
    @Value("${awsAccessKey}")
    private String awsAccessKey;
    @Value("${awsSecretKey}")
    private String awsSecretKey;

    //Mapping annotation (all HTTP POST at /sns/send to sendNotification method
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void sendNotification(@RequestBody SnsNotification notification) {

        String topicARN = notification.getTopic(); //"arn:aws:sns:us-east-1:753780547714:EnMarketNewsUpdate";
        String theMessage = notification.getMessage(); //"My text published to SNS topic";

        theLogger.info("notification topic is: {}", topicARN);
        theLogger.info("notification message is: {}", theMessage);

        this.publishToSnsTopic(topicARN, theMessage);

        /*
        {   "topic": "arn:aws:sns:us-east-1:753780547714:EnMarketNewsUpdate", "message": "kjshjkhdkdsh" }
         */

    }


    //publish to an SNS topic
    private void publishToSnsTopic(String topicARN, String messageBody) {

        theLogger.info("publishToSnsTopic has been called for topicARN:{} with messageBody: {}", topicARN, messageBody);

        //check th params
        if (topicARN == null || messageBody == null) {
            theLogger.error("Cannot send SNS notification because topicARN or messageBody is empty!");
            return;
        }

        //create AWS credentials
        if (awsAccessKey == null || awsSecretKey == null) {
            theLogger.error("AWS credentials not configured!");
            return;
        }
        else {
            awsCreds = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        }

        //create a new SNS client with credentials
        AmazonSNSClient snsClient = new AmazonSNSClient(awsCreds);
        snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));

        //publish to an SNS topic
        try {
            PublishRequest publishRequest = new PublishRequest(topicARN, messageBody);
            PublishResult publishResult = snsClient.publish(publishRequest);

            //print MessageId of message published to SNS topic
            theLogger.info("MessageId: {} was successfully published", publishResult.getMessageId());
        }
        catch (Exception theException) {
            theLogger.error("Cannot send SNS notification! Exception: " + theException);
        }
        finally {
            snsClient.shutdown();
        }

    }

}


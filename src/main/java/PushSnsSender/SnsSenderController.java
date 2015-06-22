package PushSnsSender;

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

    @Value("${awsAccessKey}")
    public String awsAccessKey;

    @Value("${awsSecretKey}")
    public String awsSecretKey;

    @RequestMapping(value = "/send", method = RequestMethod.POST)   //- Mapping annotation (all HTTP POST at /sns/send
    //will be  будут matched to sendNotification
    @ResponseStatus(HttpStatus.OK)
    public void sendNotification(@RequestBody SnsNotification notification) {

        String topicARN = "arn:aws:sns:us-east-1:753780547714:EnMarketNewsUpdate";
        String tmpMessage = "My text published to SNS topic";

        this.publishToSnsTopic(topicARN, tmpMessage);

        //this.notificationMessagingTemplate.sendNotification("arn:aws:sns:us-east-1:753780547714:EnMarketNewsUpdate", notification.getMessage(), notification.getSubject());

    }


    //publish to an SNS topic
    private void publishToSnsTopic(String topicARN, String messageBody) {

        theLogger.info("publishToSnsTopic has been called for topicARN:{} with messageBody: {}", topicARN, messageBody);

        //create AWS credentials
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsAccessKey, awsSecretKey);

        //create a new SNS client with credentials
        AmazonSNSClient snsClient = new AmazonSNSClient(awsCreds);
        snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));

        //publish to an SNS topic
        PublishRequest publishRequest = new PublishRequest(topicARN, messageBody);
        PublishResult publishResult = snsClient.publish(publishRequest);
        //print MessageId of message published to SNS topic
        theLogger.info("MessageId - " + publishResult.getMessageId());

    }

}


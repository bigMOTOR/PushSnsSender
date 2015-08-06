# PushSnsSender

PushSnsSender - your own way to send Apple Push Notifications (APN) to iOS devices.

PushSnsSender is the lightweight microservice which allows you to send APN to an iOS devices through the powerful Amazon SNS service. It is really simple and easy to modificate.

## When should I use PushSnsSender?
You have implemented APN inside your iOS app and you need some kind of backend now - a service which provides you APN sending. You don't want to use neither of public SaaS because most of them are overengineered and you really like to understand what is happening under the hood. Therefore you need to run your own lightweight microservice which allows you to send APN.
Beside of that SNS is a powerful thing that allows you to use this microservice to send Google Push Notifications, e-mails, SMS and etc...

## How to use
Amazon Web Services (AWS):
- Create an AWS account, turn on SNS service and configure it according to the AWS docs http://docs.aws.amazon.com/sns/latest/dg/GettingStarted.html.
- Create a SNS topic.
- Subscribe your device to the SNS topic.

PushSnsSender:
- Configure PushSnsSender with application.properties file including AWS credentials and log settings.
- Run PushSnsSender.
- Send HTTP POST request to http://localhost:8080/sns/send with Content-Type: "application/json" and folowing context:

      {"topic": "arn:aws:sns:YOUR-TOPIC",  "message": "Hooray!",  "badge": 0,  "sound": "bingbong.aiff", "isDebug": true }
_Set "isDebug" to false if you are working with production iOS app._

## Using technologies
- Java 1.8;
- Spring Boot 1.2.4;
- Maven 3.3;
- AWS SDK for Java 1.10.1.

## Communication
- If you found a bug, open an issue.
- If you have a feature request, open an issue.
- If you want to contribute, submit a pull request.
All push and pull requests are welcome.

## Credits
PushSnsSender is owned and maintained by Nikolay Fiantsev (aka bigMOTOR).

## License
PushSnsSender is released under the BSD license.



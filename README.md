
# JAVA CODING CHALLENGE

Adidas java coding challenge about a newsletter subscription.


## Deployment

First its  need an active mongoDB database where we will store our subscriptions and user credentials for the api.

We will have to modify the properties of the email and subscription Service to point to said mongoDb connection on the "application.properties" file.

Then specify for the 3 applications where they should be hosted on the "application.properties" file

Use this on every project to create the JAR file
```bash
mvn clean package
```

Now we run emailService

```bash
java -jar emailService/target/emailservice-1.0.0.jar  
```

then 
```bash
java -jar subscriptionService/target/subscriptionservice-1.0.0.jar  
```

and finally 
```bash
java -jar publicService/target/publicservice-1.0.0.jar  
```

## Email Service

The email service only sends emails.

When it receives an email to send, he doesnt send it immediatly. It stores the email in a list.

When it receives the call to actually send the emails, it sends all the emails stored in the list and then flushes it.

Instead of sending emails There is a log message indicating that the email was sent mocking an actual send of an email.

You need a valid token access to use this service.
## Subscription Service

This service manages all of the operations with subscriptions. There are four in total:

-Get all subscriptions

-Get a subscription by email

-Cancel subscription by email

-Create a subscription

All of the subscription information is stored in a mongo database.

Subscriptions have the following fields:
-First name (non required)
-Gender (male,female,other)(non required)
-Email where the info will be sent
-Newsletter Identifier
-Consent to send information
-Birth Date 
-Subscription identifier that will be determined on the moment of creation

You need a valid token to acess this service
## Public service

There are 2 endpoints for the user

### Subscription
Very basic page where a user puts its personal information to create a subscription.

There is a button at the end that completes the subscription.

Its mapped at the path "/"

### Manage subscription
Page where the user gets information about his subscription and has access to a button that cancels it.

### Scheduled processes
#### New email from newsletter
Every minute the public service gets the information of every subscription and chooses the
 emails from the ones subscribed to the specified newsletter id.

Then it adds to the email service the generated email with all the receivers

#### Send emails
Every 30 seconds there is a call to the email service to flush the queue and send the accumulated emails

## Other

There is a Data initializer on the subscription service that will create some subscriptions and the users:

admin:password  that has permission to use the api

user:password  that doesn't.

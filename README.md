I. Prerequisites:
- Java 11 or higher
- [`IDE`](https://www.jetbrains.com/idea/) (preferably IntelIJ, the candidate must choose what he feels most comfortable with)
- [`Postman`](https://www.postman.com/downloads/) 
- GitHub client
- [`Maven`](https://maven.apache.org)

II. External Validation API
1. Clone GitHub repository:```git@github.com:laurentiu-miu/validator.git```
2. Start the application from command line
3. Read the [`OpenAPI Documentation of External API`](http://localhost:8080/swagger-ui.html)

III. What to do:
1. Initialize a maven Spring Boot Application named Person Manager.
2. Create an entity Person that contains name, email, phone.
3. Create REST endpoints to address CRUD operations for the entity Person.
4. Copy past (from validator project) the class rest.api.client.Mailer 
   into Person Manager and use it to send email (after saving it to the database) to newly added email.
   from: person.manager@service.com
   subject: "Welcome to Person Manager"
   body: "Now you are on our list mwahahaha!!!"
5. All newly added persons must have email & phone validated (using external API validator):
   - if the phone or email is not valid the person must not be added.
   - all modifications on email & phone must be validated in order to be updated
   - if a 5xx category error appear then a retry 3 times with a backoff of 5 sec
6. Schedule a job to publish to log total number of persons every 5 minutes
7. Use spring async(with sleep 5 sec) to send invalid phones and emails to log
8. Check what are the transitive maven dependency
9. Perform Unit Test on created endpoint

I. Prerequisites:
- Java 8 at least
- IDE(preferably IntelIJ, the candidate must choose what he feels most comfortable with) https://www.jetbrains.com/idea/
- Postman https://www.postman.com/downloads/
- github
- maven

II. What should you do?
1. Initialize a maven Spring Boot Application named Person Manager.
2. Create an entity Person that contains name, email, phone.
3. Create REST endpoints to address CRUD operations for the entity Person.
4. All newly added persons must have email & phone validated (using external API validator):
- if the phone or email is not valid the persone must not be addded.
- all modifications on email & phone must be validated in order to be updated

III. External Validation API
Github repository: git@github.com:laurentiu-miu/validator.git
Application start: mvn spring-boot:run
Documentation of external API can be found at: http://localhost:8080/swagger-ui.html
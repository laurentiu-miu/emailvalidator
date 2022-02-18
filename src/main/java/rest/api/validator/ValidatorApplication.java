package rest.api.validator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.Random;
import java.util.regex.Pattern;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ValidatorApplication {

	public static final String REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	public static void main(String[] args) {
		SpringApplication.run(ValidatorApplication.class, args);
	}

	public static boolean patternMatches(String emailAddress, String regexPattern) {
		return Pattern.compile(regexPattern)
				.matcher(emailAddress)
				.matches();
	}

	@Operation(summary = "API email validator endpoint.",
			description = "The following restrictions are imposed in the email addresses local-part by using this regex:\n"
					+ "\n"
					+ "- It allows numeric values from 0 to 9\n"
					+ "- Both uppercase and lowercase letters from a to z are allowed\n"
					+ "- Allowed are underscore “_”, hyphen “-” and dot “.”\n"
					+ "- Dot isn't allowed at the start and end of the local-part\n"
					+ "- Consecutive dots aren't allowed\n"
					+ "- For the local part, a maximum of 64 characters are allowed\n"
					+ "\n"
					+ "Restrictions for the domain-part in this regular expression include:"
					+ "- It allows numeric values from 0 to 9\n"
					+ "- We allow both uppercase and lowercase letters from a to z\n"
					+ "- Hyphen “-” and dot “.” isn't allowed at the start and end of the domain-part\n"
					+ "- No consecutive dots\n"
					+"\n"
					+"Valid email addresses examples:\n"
					+ "- username@domain.com\n"
					+ "- user.name@domain.com\n"
					+ "- user-name@domain.com\n"
					+ "- username@domain.co.in\n"
					+ "- user_name@domain.com\n"
					+ "\n"
					+ "Invalid email addresses examples:\n"
					+ "- username.@domain.com\n"
					+ "- .user.name@domain.com\n"
					+ "- user-name@domain.com.\n"
					+ "- username@.com",
			tags = { "Email API" },
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200"
					)
			})
	@GetMapping("/api1/v1/validate")
	public String validateEmail(@RequestParam String email){
		String result = patternMatches(email,REGEX_PATTERN) ? "is valid" : "is invalid";
		return String.format("The email %s %s!",email,result);
	}

	@Operation(summary = "API phone validator endpoint 1.",
			description = "The following validator check if the number length is 10:\n",
			tags = { "Phone API" },
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200"
					),
					@ApiResponse(description = "Service Unavailable", responseCode = "503", content = @Content)
			})
	@GetMapping("/api2/v1/validate")
	public String validatePhone1(@RequestParam String phone){
		Random random = new Random();
		if(random.ints().findFirst().getAsInt() % 2 == 0){
			throw new RuntimeException("The server had an unexpected problem please try again!");
		}
		String result = phone != null && phone.length() == 10 ? "is valid" : "is invalid";
		return String.format("The phone %s %s!",phone, result);
	}

	@Operation(summary = "API phone validator endpoint 2.",
			description = "The following validator check if the string digits are numbers:\n",
			tags = { "Phone API" },
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200"
					),
					@ApiResponse(description = "Service Unavailable", responseCode = "503", content = @Content)
			})
	@GetMapping("/api2/v2/validate")
	public String validatePhone2(@RequestParam String phone){

		String result = phone.matches("[0-9]+") ? "is valid" : "is invalid";
		return String.format("The phone %s %s!",phone, result);
	}
}

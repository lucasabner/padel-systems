package fourzeta;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class FourZetaApplication {

	public static void main(String[] args) throws ParseException, IOException {
		SpringApplication.run(FourZetaApplication.class, args);
	}
}

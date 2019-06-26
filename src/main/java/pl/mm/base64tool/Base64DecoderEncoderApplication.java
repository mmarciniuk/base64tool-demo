package pl.mm.base64tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.mm.base64tool.config.Config;

@SuppressWarnings("WeakerAccess")
@Import(Config.class)
@SpringBootApplication
public class Base64DecoderEncoderApplication {

	public static void main(String[] args) {
		SpringApplication.run(Base64DecoderEncoderApplication.class, args);
	}

}

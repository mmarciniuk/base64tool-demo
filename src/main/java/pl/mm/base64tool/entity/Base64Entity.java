package pl.mm.base64tool.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Base64Entity {

	private String decodedPayload;
	private String encodedPayload;

}

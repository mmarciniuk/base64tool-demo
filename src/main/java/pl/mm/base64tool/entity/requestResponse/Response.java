package pl.mm.base64tool.entity.requestResponse;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Response {

	private Object header;
	private Object body;
	private Object error;

}

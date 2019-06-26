package pl.mm.base64tool.entity.requestResponse;

import lombok.Data;

@Data
public class Response {

	private Object header;
	private Object body;
	private Object error;

}

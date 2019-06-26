package pl.mm.base64tool.entity;

import lombok.Data;

@Data
public class Base64Entity {

	public enum Status {
		DECODED, ENCODED
	}

	private Status status;
	private String originalPayload;
	private String payload;

}

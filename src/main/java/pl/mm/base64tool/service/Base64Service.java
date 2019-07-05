package pl.mm.base64tool.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.mm.base64tool.entity.Base64Entity;
import pl.mm.base64tool.entity.requestResponse.Response;

import java.io.IOException;
import java.util.Base64;

@Service
public class Base64Service {

	public Response decode(String payloadToDecode) {
		byte[] decodePayloadInBytes = Base64.getDecoder().decode(payloadToDecode.getBytes());

		Base64Entity base64Entity = new Base64Entity();
		base64Entity.setStatus(Base64Entity.Status.DECODED);
		base64Entity.setOriginalPayload(payloadToDecode);
		base64Entity.setPayload(new String(decodePayloadInBytes));

		Response response = new Response();
		response.setBody(base64Entity);

		return response;
	}

	public Response encode(String payloadToEncode) {
		String encodedPayload = Base64.getEncoder().encodeToString(payloadToEncode.getBytes());

		Base64Entity base64Entity = new Base64Entity();
		base64Entity.setStatus(Base64Entity.Status.ENCODED);
		base64Entity.setOriginalPayload(payloadToEncode);
		base64Entity.setPayload(encodedPayload);

		Response response = new Response();
		response.setBody(base64Entity);

		return response;
	}

	public Response encodeFile(MultipartFile multipartFile) throws IOException {
		String encodedPayloadOfFile = Base64.getEncoder().encodeToString(multipartFile.getBytes());

		Base64Entity base64Entity = new Base64Entity();
		base64Entity.setStatus(Base64Entity.Status.ENCODED);
		base64Entity.setOriginalPayload(multipartFile.getOriginalFilename());
		base64Entity.setPayload(encodedPayloadOfFile);

		Response response = new Response();
		response.setBody(base64Entity);

		return response;
	}

}

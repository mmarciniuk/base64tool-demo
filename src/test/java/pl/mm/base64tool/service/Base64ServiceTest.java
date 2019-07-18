package pl.mm.base64tool.service;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.mm.base64tool.BaseTest;
import pl.mm.base64tool.entity.Base64Entity;
import pl.mm.base64tool.entity.requestResponse.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Base64ServiceTest extends BaseTest {

	@InjectMocks
	private Base64Service base64Service;

	@Test
	public void basicEncodeTest() {
		String textToEncode = "basicTest";
		String encodedText = "YmFzaWNUZXN0";

		Base64Entity base64EntityExpected = new Base64Entity();
		base64EntityExpected.setDecodedPayload(textToEncode);
		base64EntityExpected.setEncodedPayload(encodedText);

		Response expectedResponse = new Response();
		expectedResponse.setHeader(null);
		expectedResponse.setBody(base64EntityExpected);
		expectedResponse.setError(null);

		Response response = base64Service.encode(textToEncode);

		Assert.assertEquals(expectedResponse, response);
	}

	@Test
	public void basicDecodeTest() {
		String textToDecode = "YmFzaWNUZXN0";
		String decodedText = "basicTest";

		Base64Entity base64EntityExpected = new Base64Entity();
		base64EntityExpected.setEncodedPayload(textToDecode);
		base64EntityExpected.setDecodedPayload(decodedText);

		Response expectedResponse = new Response();
		expectedResponse.setHeader(null);
		expectedResponse.setBody(base64EntityExpected);
		expectedResponse.setError(null);

		Response response = base64Service.decode(textToDecode);

		Assert.assertEquals(expectedResponse, response);
	}

	@Test
	public void encodeFileTest() throws IOException {
		String encodedText = "VGhpcyBpcyB0ZXN0IGZpbGUu";

		Resource resource = new ClassPathResource("fileForEncodeFileTest.txt");
		InputStream inputStream = new FileInputStream(resource.getFile());
		MultipartFile multipartFileToEncode = new MockMultipartFile(resource.getFile().getName(), inputStream);

		Base64Entity base64EntityExpected = new Base64Entity();
		base64EntityExpected.setDecodedPayload(multipartFileToEncode.getOriginalFilename());
		base64EntityExpected.setEncodedPayload(encodedText);

		Response expectedResponse = new Response();
		expectedResponse.setHeader(null);
		expectedResponse.setBody(base64EntityExpected);
		expectedResponse.setError(null);

		Response response = base64Service.encodeFile(multipartFileToEncode);

		Assert.assertEquals(expectedResponse, response);
	}
}
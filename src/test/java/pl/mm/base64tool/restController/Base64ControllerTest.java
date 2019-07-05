package pl.mm.base64tool.restController;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.mm.base64tool.entity.Base64Entity;
import pl.mm.base64tool.entity.requestResponse.Response;
import pl.mm.base64tool.service.Base64Service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class Base64ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private Base64Service base64Service;

	@Test
	public void encode() throws Exception {
		String textToEncode = "basicTest";
		String encodedText = "YmFzaWNUZXN0";

		Base64Entity base64EntityExpected = new Base64Entity();
		base64EntityExpected.setDecodedPayload(textToEncode);
		base64EntityExpected.setEncodedPayload(encodedText);

		Response expectedResponse = new Response();
		expectedResponse.setHeader(null);
		expectedResponse.setBody(base64EntityExpected);
		expectedResponse.setError(null);

		String pathParameter = "?payloadToEncode=" + textToEncode;
		String expectedJsonResponse = "{\"header\":null,\"body\":{\"decodedPayload\":\"basicTest\",\"encodedPayload\":\"YmFzaWNUZXN0\"},\"error\":null}";

		Mockito.doReturn(expectedResponse).when(base64Service).encode(textToEncode);

		this.mockMvc.perform(get(Base64Controller.Mappings.BASE + Base64Controller.Mappings.ENCODE + pathParameter))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("*", Matchers.is(expectedJsonResponse)));
	}

	@Test
	public void decode() {
		String textToDecode = "YmFzaWNUZXN0";
		String decodedText = "basicTest";

		Base64Entity base64EntityExpected = new Base64Entity();
		base64EntityExpected.setEncodedPayload(textToDecode);
		base64EntityExpected.setDecodedPayload(decodedText);

		Response expectedResponse = new Response();
		expectedResponse.setHeader(null);
		expectedResponse.setBody(base64EntityExpected);
		expectedResponse.setError(null);


	}
}
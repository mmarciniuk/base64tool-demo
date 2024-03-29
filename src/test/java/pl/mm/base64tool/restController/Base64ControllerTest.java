package pl.mm.base64tool.restController;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.mm.base64tool.BaseTest;
import pl.mm.base64tool.entity.Base64Entity;
import pl.mm.base64tool.entity.requestResponse.Response;
import pl.mm.base64tool.service.Base64Service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class Base64ControllerTest extends BaseTest {

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

		Mockito.doReturn(expectedResponse).when(base64Service).encode(textToEncode);

		this.mockMvc.perform(get(Base64Controller.Mappings.BASE + Base64Controller.Mappings.ENCODE + pathParameter))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.header", Matchers.is(expectedResponse.getHeader())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.body.decodedPayload", Matchers.is(base64EntityExpected.getDecodedPayload())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.body.encodedPayload", Matchers.is(base64EntityExpected.getEncodedPayload())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.is(expectedResponse.getError())));
	}

	@Test
	public void decode() throws Exception {
		String textToDecode = "YmFzaWNUZXN0";
		String decodedText = "basicTest";

		Base64Entity base64EntityExpected = new Base64Entity();
		base64EntityExpected.setEncodedPayload(textToDecode);
		base64EntityExpected.setDecodedPayload(decodedText);

		Response expectedResponse = new Response();
		expectedResponse.setHeader(null);
		expectedResponse.setBody(base64EntityExpected);
		expectedResponse.setError(null);

		String pathParameter = "?payloadToDecode=" + textToDecode;

		Mockito.doReturn(expectedResponse).when(base64Service).decode(textToDecode);

		this.mockMvc.perform(get(Base64Controller.Mappings.BASE+ Base64Controller.Mappings.DECODE+pathParameter))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.header", Matchers.is(expectedResponse.getHeader())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.body.decodedPayload", Matchers.is(base64EntityExpected.getDecodedPayload())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.body.encodedPayload", Matchers.is(base64EntityExpected.getEncodedPayload())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.is(expectedResponse.getError())));
	}
}
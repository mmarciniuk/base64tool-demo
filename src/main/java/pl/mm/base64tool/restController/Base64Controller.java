package pl.mm.base64tool.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mm.base64tool.entity.requestResponse.Response;
import pl.mm.base64tool.service.Base64Service;

@SuppressWarnings("WeakerAccess")
@RestController
@RequestMapping(Base64Controller.Mappings.BASE)
public class Base64Controller {

	@Autowired
	private Base64Service base64Service;

	@SuppressWarnings("WeakerAccess")
	public static class Mappings {
		public static final String BASE = "/api";
		public static final String DECODE = "/decode";
		public static final String ENCODE = "/encode";
	}

	@GetMapping(Mappings.DECODE)
	public Response decode(@RequestParam(name = "payloadToDecode") String payloadToDecode) {
		return base64Service.decode(payloadToDecode);
	}

	@GetMapping(Mappings.ENCODE)
	public Response encode(@RequestParam(name = "payloadToEncode") String payloadToEncode) {
		return base64Service.encode(payloadToEncode);
	}
}

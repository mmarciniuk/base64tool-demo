package pl.mm.base64tool.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.mm.base64tool.entity.Base64Entity;
import pl.mm.base64tool.entity.requestResponse.Response;
import pl.mm.base64tool.service.Base64Service;

import java.io.IOException;

@SuppressWarnings({"SameReturnValue", "WeakerAccess"})
@Controller
@RequestMapping(HomeController.Mappings.BASE)
public class HomeController {

	@Autowired
	private Base64Service base64Service;

	@SuppressWarnings("WeakerAccess")
	public static final class Mappings {
		public static final String BASE = "";
		public static final String INDEX = "";
		public static final String GREETING = "/greeting";
		public static final String PROCESS = "/processText";
		public static final String UPLOAD_FILE_TO_ENCODE = "/uploadFileToEncode";
	}

	public static final class ModelAttributesNames {
		public static final String BASE_64_ENTITY = "base64Entity";
		public static final String BASE_64_ENTITY_FILE = "base64EntityFile";
	}

	@GetMapping(Mappings.INDEX)
	public String index(Model model) {
		model.addAttribute(ModelAttributesNames.BASE_64_ENTITY, new Base64Entity());
		model.addAttribute(ModelAttributesNames.BASE_64_ENTITY_FILE, new Base64Entity());

		return "index";
	}

	@PostMapping(value = Mappings.PROCESS, params = "encode")
	public String encode(@ModelAttribute(ModelAttributesNames.BASE_64_ENTITY) Base64Entity base64Entity, Model model) {
		Response response = base64Service.encode(base64Entity.getDecodedPayload());

		model.addAttribute(ModelAttributesNames.BASE_64_ENTITY, response.getBody());
		model.addAttribute(ModelAttributesNames.BASE_64_ENTITY_FILE, new Base64Entity());

		return "index";
	}

	@PostMapping(value = Mappings.PROCESS, params = "decode")
	public String decode(@ModelAttribute(ModelAttributesNames.BASE_64_ENTITY) Base64Entity base64Entity, Model model) {
		Response response = base64Service.decode(base64Entity.getEncodedPayload());

		model.addAttribute(ModelAttributesNames.BASE_64_ENTITY, response.getBody());
		model.addAttribute(ModelAttributesNames.BASE_64_ENTITY_FILE, new Base64Entity());

		return "index";
	}

	@PostMapping(Mappings.UPLOAD_FILE_TO_ENCODE)
	public String encodeFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {
		Response response = base64Service.encodeFile(file);

		model.addAttribute(ModelAttributesNames.BASE_64_ENTITY_FILE, response.getBody());
		model.addAttribute(ModelAttributesNames.BASE_64_ENTITY, new Base64Entity());

		return "index";
	}

	@GetMapping(Mappings.GREETING)
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

}

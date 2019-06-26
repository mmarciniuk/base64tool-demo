package pl.mm.base64tool.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mm.base64tool.entity.Base64Entity;
import pl.mm.base64tool.entity.requestResponse.Response;
import pl.mm.base64tool.service.Base64Service;

@SuppressWarnings("SameReturnValue")
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
	}

	@GetMapping(Mappings.INDEX)
	public String index(Model model) {
		model.addAttribute("base64Entity", new Base64Entity());
		return "index";
	}

	@PostMapping(value = Mappings.PROCESS, params = "encode")
	public String encode(@ModelAttribute("base64Entity") Base64Entity base64Entity, Model model){
		Response response = base64Service.encode(base64Entity.getOriginalPayload());

		model.addAttribute("base64Entity", response.getBody());

		return "index";
	}

	@PostMapping(value = Mappings.PROCESS, params = "decode")
	public String decode(@ModelAttribute("base64Entity") Base64Entity base64Entity, Model model){
		Response response = base64Service.decode(base64Entity.getOriginalPayload());

		model.addAttribute("base64Entity", response.getBody());

		return "index";
	}

	@GetMapping(Mappings.GREETING)
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

}

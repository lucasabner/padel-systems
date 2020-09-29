package fourzeta.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import fourzeta.models.Usuario;
import fourzeta.repository.UsuarioRepository;

@Controller
public class InicialController {
	

	@Autowired
	UsuarioRepository ur;

	@RequestMapping(value = "usuario{id}inicial", method = RequestMethod.GET)
	public ModelAndView inicial(@PathVariable("id") int id) {
		Usuario usuario = ur.findById(id);
		ModelAndView mv = new ModelAndView("inicial/inicial");
		mv.addObject("usuario", usuario);

		return mv;
	}


	@RequestMapping("/creditos")
	public String verCreditos() {
		return "inicial/creditos";
	}

	@RequestMapping("/sair")
	public String sair() {
		return "redirect:/";
	}

}

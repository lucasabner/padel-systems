package fourzeta.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fourzeta.models.Usuario;
import fourzeta.repository.UsuarioRepository;

@Controller
public class LoginController {

	public static String[] codigos = new String[10];

	@Autowired
	UsuarioRepository ur;

	@RequestMapping("/")
	public String login() {
		return "login/login";
	}

	@RequestMapping("/inicial")
	public String sucesso() {
		return "inicial/inicial";
	}

	@RequestMapping("/falha")
	public String falha() {
		return "login/falha.xhtml";
	}

	@RequestMapping("/template")
	public String template() {
		return "/template.xhtml";
	}

	@RequestMapping("/cadastro")
	public String cadastro() {
		return "login/cadastro";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String formUser(Usuario usuario) {
		for (Usuario u : ur.findAll()) {

			if (usuario.getNickname().equals(u.getNickname()) && usuario.getSenha().equals(u.getSenha())) {

				int idInt = u.getId();
				String codigo = "" + idInt;
				return "redirect:usuario" + codigo + "inicial";

			}
		}
		return "redirect:/falha";
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public String formCadastro(Usuario usuario) {
		ur.save(usuario);
		return "redirect:/";
	}
}

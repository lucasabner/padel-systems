package fourzeta.resources;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fourzeta.GenericDAO;
import fourzeta.models.Usuario;

@RestController
@Controller
@RequestMapping("/usuario")
public class UsuarioResource {

	private GenericDAO dao = new GenericDAO<Usuario>();

	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Usuario> listaUsuarios() {
		Iterable<Usuario> lista = dao.findAll("Usuario");
		return lista;
	}

	@PostMapping()
	public Usuario registraUsuario(@RequestBody @Valid Usuario usuario) {
		return (Usuario) dao.save(usuario);
	}

	@DeleteMapping()
	public Usuario deletaUsuario(@RequestBody @Valid Usuario usuario) {
		dao.remove(usuario, usuario.getId());
		return usuario;
	}

}

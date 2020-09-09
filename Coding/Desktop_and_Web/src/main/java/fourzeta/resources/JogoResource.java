package fourzeta.resources;

import java.util.List;

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
import fourzeta.models.Jogo;

@RestController
@Controller
@RequestMapping("/jogo")
public class JogoResource {

	private GenericDAO dao = new GenericDAO<Jogo>();

	@GetMapping(produces = "application/json")
	public @ResponseBody List<Jogo> listaJogos() {
		List<Jogo> lista = dao.findAll("Jogo");
		return lista;
	}

	@PostMapping()
	public Jogo registraJogo(@RequestBody @Valid Jogo jogo) {
		return (Jogo) dao.save(jogo);
	}

	@DeleteMapping()
	public Jogo deletaJogo(@RequestBody @Valid Jogo jogo) {
		dao.remove(jogo, jogo.getId());
		return jogo;
	}

}

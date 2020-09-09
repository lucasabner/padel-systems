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
import fourzeta.models.Torneio;

@RestController
@Controller
@RequestMapping("/torneio")
public class TorneioResource {

	private GenericDAO dao = new GenericDAO<Torneio>();

	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Torneio> listaTorneios() {
		Iterable<Torneio> lista = dao.findAll("Torneio");
		return lista;
	}

	@PostMapping()
	public Torneio registraTorneio(@RequestBody @Valid Torneio torneio) {
		return (Torneio) dao.save(torneio);
	}

	@DeleteMapping()
	public Torneio deletaTorneio(@RequestBody @Valid Torneio torneio) {
		dao.remove(torneio, torneio.getId());
		return torneio;
	}

}

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
import fourzeta.models.Chave;

@RestController
@Controller
@RequestMapping("/chave")
public class ChaveResource {

	private GenericDAO dao = new GenericDAO<Chave>();

	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Chave> listaChaves() {
		Iterable<Chave> lista = dao.findAll("Chave");
		return lista;
	}

	@PostMapping()
	public Chave registraChave(@RequestBody @Valid Chave chave) {
		return (Chave) dao.save(chave);
	}

	@DeleteMapping()
	public Chave deletaChave(@RequestBody @Valid Chave chave) {
		dao.remove(chave, chave.getId());
		return chave;
	}

}

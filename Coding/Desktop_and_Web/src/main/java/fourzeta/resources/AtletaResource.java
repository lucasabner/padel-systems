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
import fourzeta.models.Atleta;

@RestController
@Controller
@RequestMapping("/atleta")
public class AtletaResource {

	private GenericDAO dao = new GenericDAO<Atleta>();

	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Atleta> listaAtletas() {
		Iterable<Atleta> listaAtletas = dao.findAll("Atleta");
		return listaAtletas;
	}

	@PostMapping()
	public Atleta registraAtleta(@RequestBody @Valid Atleta atleta) {
		return (Atleta) dao.save(atleta);
	}

	@DeleteMapping()
	public Atleta deletaAtleta(@RequestBody @Valid Atleta atleta) {
		dao.remove(atleta, atleta.getId());
		return atleta;
	}

}

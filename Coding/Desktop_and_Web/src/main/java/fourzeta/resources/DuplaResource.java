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
import fourzeta.models.Dupla;

@RestController
@Controller
@RequestMapping("/dupla")
public class DuplaResource {

	private GenericDAO dao = new GenericDAO<Dupla>();

	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Dupla> listaDuplas() {
		Iterable<Dupla> lista = dao.findAll("Dupla");
		return lista;
	}

	@PostMapping()
	public Dupla registraDupla(@RequestBody @Valid Dupla dupla) {
		return (Dupla) dao.save(dupla);
	}

	@DeleteMapping()
	public Dupla deletaDupla(@RequestBody @Valid Dupla dupla) {
		dao.remove(dupla, dupla.getId());
		return dupla;
	}

}

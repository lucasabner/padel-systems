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
import fourzeta.models.Quadra;

@RestController
@Controller
@RequestMapping("/quadra")
public class QuadraResource {

	private GenericDAO dao = new GenericDAO<Quadra>();

	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Quadra> listaQuadras() {
		Iterable<Quadra> lista = dao.findAll("Quadra");
		return lista;
	}


	@PostMapping()
	public Quadra registraQuadra(@RequestBody @Valid Quadra quadra) {
		return (Quadra) dao.save(quadra);
	}

	@DeleteMapping()
	public Quadra deletaQuadra(@RequestBody @Valid Quadra quadra) {
		dao.remove(quadra, quadra.getId());
		return quadra;
	}

}

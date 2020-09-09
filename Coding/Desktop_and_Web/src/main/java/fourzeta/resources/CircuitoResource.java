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
import fourzeta.models.Circuito;

@RestController
@Controller
@RequestMapping("/circuito")
public class CircuitoResource {
	
	private GenericDAO dao = new GenericDAO<Circuito>();
	
	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Circuito> listaCircuitos() {
		Iterable<Circuito> lista = dao.findAll("Circuito");
		return lista;
	}
	
	
	@PostMapping()
	public Circuito registraCircuito(@RequestBody @Valid Circuito circuito) {
		return (Circuito) dao.save(circuito);
	}
	
	
	@DeleteMapping()
	public Circuito deletaCircuito(@RequestBody @Valid Circuito circuito) {
		dao.remove(circuito, circuito.getId());
		return circuito;
	}
	

}

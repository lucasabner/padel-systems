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
import fourzeta.models.Ranking;

@RestController
@Controller
@RequestMapping("/ranking")
public class RankingResource {

	private GenericDAO dao = new GenericDAO<Ranking>();

	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Ranking> listaRankings() {
		Iterable<Ranking> lista = dao.findAll("Ranking");
		return lista;
	}

	@PostMapping()
	public Ranking registraRanking(@RequestBody @Valid Ranking ranking) {
		return (Ranking) dao.save(ranking);
	}

	@DeleteMapping()
	public Ranking deletaRanking(@RequestBody @Valid Ranking ranking) {
		dao.remove(ranking, ranking.getId());
		return ranking;
	}

}

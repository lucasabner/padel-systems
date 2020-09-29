package fourzeta.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import fourzeta.models.Chave;
import fourzeta.models.Circuito;
import fourzeta.models.Dupla;
import fourzeta.models.Torneio;
import fourzeta.repository.ChaveRepository;
import fourzeta.repository.CircuitoRepository;
import fourzeta.repository.DuplaRepository;
import fourzeta.repository.RankingRepository;
import fourzeta.repository.TorneioRepository;

@Controller
public class TorneioController {

	@Autowired
	private CircuitoRepository cr;

	@Autowired
	private TorneioRepository tr;

	@Autowired
	private ChaveRepository chr;

	@Autowired
	private DuplaRepository dr;

	@Autowired
	private RankingRepository rr;

	@RequestMapping(value = "/EditarUsuario{idUser}circuito{idCirc}torneio{idTorn}", method = RequestMethod.GET)
	public ModelAndView formEditTorneio(@PathVariable("idUser") int idUser, @PathVariable("idCirc") int idCirc,
			@PathVariable("idTorn") int idTorn) {
		Torneio torneio = tr.findById(idTorn);
		Circuito circuito = torneio.getCircuito();
		ModelAndView mv = new ModelAndView("torneio/formEditTorneio");
		mv.addObject("circuito", circuito);
		mv.addObject("torneio", torneio);

		System.out.println("erro");
		return mv;

	}

	@RequestMapping(value = "/EditarUsuario{idUser}circuito{idCirc}torneio{idTorn}", method = RequestMethod.POST)
	public String editarTorneio(Torneio torneio, @PathVariable("idUser") int idUser, @PathVariable("idCirc") int idCirc,
			@PathVariable("idTorn") int idTorn) {
		Circuito circuito = cr.findById(idCirc);
		Torneio torn = tr.findById(idTorn);
		torneio.setId(idTorn);
		torneio.setCircuito(circuito);
		torneio.getRankings().addAll(torn.getRankings());
		torneio.getChaves().addAll(torn.getChaves());
		torneio.getDuplas().addAll(torn.getDuplas());
		torneio.setQtdAtletas(torn.getQtdAtletas());
		torneio.getQuadras().addAll(torn.getQuadras());
		tr.save(torneio);

		int idInt1 = circuito.getUsuario().getId();
		int idInt2 = circuito.getId();
		String codigoUser = "" + idInt1;
		String codigoCirc = "" + idInt2;

		return "redirect:/usuario" + codigoUser + "circuito" + codigoCirc;
	}

	@RequestMapping(value = "/cadastrarUsuario{idUser}circuito{idCirc}torneio", method = RequestMethod.GET)
	public ModelAndView formTorneio(@PathVariable("idUser") int idUser, @PathVariable("idCirc") int idCirc) {

		Circuito circuito = cr.findById(idCirc);
		ModelAndView mv = new ModelAndView("torneio/formTorneio");
		mv.addObject("circuito", circuito);

		return mv;
	}

	@RequestMapping(value = "/cadastrarUsuario{idUser}circuito{idCirc}torneio", method = RequestMethod.POST)
	public String cadastrarTorneio(Torneio torneio, @PathVariable("idUser") int idUser,
			@PathVariable("idCirc") int idCirc) {
		Circuito circuito = cr.findById(idCirc);

		torneio.setCircuito(circuito);
		circuito.getTorneios().add(torneio);

		tr.save(torneio);
//		cr.save(circuito);

		int idInt1 = circuito.getUsuario().getId();
		int idInt2 = circuito.getId();
		String codigoUser = "" + idInt1;
		String codigoCirc = "" + idInt2;

		return "redirect:/usuario" + codigoUser + "circuito" + codigoCirc;
	}

	@RequestMapping(value = "/Usuario{idUser}circuito{idCirc}torneio{idTorn}", method = RequestMethod.GET)
	public ModelAndView detalhesTorneio(@PathVariable("idUser") int idUser, @PathVariable("idCirc") int idCirc,
			@PathVariable("idTorn") int idTorn) {
		Torneio torneio = tr.findById(idTorn);
		Circuito circuito = torneio.getCircuito();
		ModelAndView mv = new ModelAndView("torneio/detalhesTorneio");
		mv.addObject("circuito", circuito);

		mv.addObject("torneio", torneio);

		Iterable<Chave> chaves = chr.findByTorneio(torneio);
		mv.addObject("chaves", chaves);

		Iterable<Dupla> duplas = dr.findByTorneio(torneio);
		mv.addObject("duplas", duplas);

		return mv;
	}

	@RequestMapping("/deletarTorneio")
	public String deletarTorneio(int id) {
		Torneio torneio = tr.findById(id);
		torneio.getCircuito().getTorneios().remove(torneio);

		rr.deleteAll(torneio.getRankings());

		tr.delete(torneio);

		int idInt1 = torneio.getCircuito().getUsuario().getId();
		int idInt2 = torneio.getCircuito().getId();
		String codigoUser = "" + idInt1;
		String codigoCirc = "" + idInt2;

		return "redirect:/usuario" + codigoUser + "circuito" + codigoCirc;
	}
}

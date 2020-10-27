package fourzeta.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fourzeta.models.Circuito;
import fourzeta.models.Dupla;
import fourzeta.models.Ranking;
import fourzeta.models.Torneio;
import fourzeta.repository.AtletaRepository;
import fourzeta.repository.ChaveRepository;
import fourzeta.repository.CircuitoRepository;
import fourzeta.repository.DuplaRepository;
import fourzeta.repository.RankingRepository;
import fourzeta.repository.TorneioRepository;

@Controller
public class DuplaController {

	@Autowired
	private CircuitoRepository cr;

	@Autowired
	private TorneioRepository tr;

	@Autowired
	private ChaveRepository chr;

	@Autowired
	private RankingRepository rr;

	@Autowired
	private DuplaRepository dr;

	@Autowired
	private AtletaRepository ar;

	private Ranking ranking1 = new Ranking();
	private Ranking ranking2 = new Ranking();

	@RequestMapping(value = "/editUsuario{idUser}circuito{idCirc}torneio{idTorn}dupla{idD}", method = RequestMethod.GET)
	public ModelAndView formEditDupla(@PathVariable("idUser") int idUser, @PathVariable("idCirc") int idCirc,
			@PathVariable("idTorn") int idTorn, @PathVariable("idD") int idD) {
		Dupla dupla = dr.findById(idD);
		Torneio torneio = dupla.getTorneio();
		Circuito circuito = torneio.getCircuito();
		ModelAndView mv = new ModelAndView("torneio/formEditDupla");
		mv.addObject("circuito", circuito);
		mv.addObject("dupla", dupla);
		mv.addObject("torneio", torneio);

		return mv;
	}

	@RequestMapping(value = "/editUsuario{idUser}circuito{idCirc}torneio{idTorn}dupla{idD}", method = RequestMethod.POST)
	public String editarDupla(Dupla dupla, @PathVariable("idUser") int idUser, @PathVariable("idCirc") int idCirc,
			@PathVariable("idTorn") int idTorn, @PathVariable("idD") int idD) {
		Torneio torneio = tr.findById(idTorn);
		dupla.setId(idD);
		dupla.setTorneio(torneio);

		ranking1 = new Ranking();
		ranking2 = new Ranking();

		ranking1.setCategoria(dupla.getCategoria());
		ranking1.setCircuito(torneio.getCircuito());
		ranking1.setAtleta(dupla.getAtleta1());
		ranking1.setTorneio(torneio);

		ranking2.setCategoria(dupla.getCategoria());
		ranking2.setCircuito(torneio.getCircuito());
		ranking2.setAtleta(dupla.getAtleta2());
		ranking2.setTorneio(torneio);

		dupla.getAtleta1().getRankings().add(ranking1);
		dupla.getAtleta2().getRankings().add(ranking2);

		ar.save(dupla.getAtleta1());
		ar.save(dupla.getAtleta2());

		dr.save(dupla);

		rr.save(ranking1);
		rr.save(ranking2);

		int idInt1 = torneio.getCircuito().getUsuario().getId();
		int idInt2 = torneio.getCircuito().getId();
		int idInt3 = torneio.getId();
		String codigoUser = "" + idInt1;
		String codigoCirc = "" + idInt2;
		String codigoTorn = "" + idInt3;

		return "redirect:/Usuario" + codigoUser + "circuito" + codigoCirc + "torneio" + codigoTorn;
	}

	@RequestMapping(value = "/inscricaoUsuario{idUser}circuito{idCirc}torneio{idTorn}dupla", method = RequestMethod.GET)
	public ModelAndView formDupla(@PathVariable("idUser") int idUser, @PathVariable("idCirc") int idCirc,
			@PathVariable("idTorn") int idTorn) {
		Torneio torneio = tr.findById(idTorn);
		Circuito circuito = torneio.getCircuito();
		ModelAndView mv = new ModelAndView("torneio/inscricaoDupla");
		mv.addObject("circuito", circuito);
		mv.addObject("torneio", torneio);

		return mv;
	}

	@RequestMapping(value = "/inscricaoUsuario{idUser}circuito{idCirc}torneio{idTorn}dupla", method = RequestMethod.POST)
	public String cadastrarDupla(Dupla dupla, @PathVariable("idUser") int idUser, @PathVariable("idCirc") int idCirc,
			@PathVariable("idTorn") int idTorn, @RequestParam("cpf1") String cpf1, @RequestParam("cpf2") String cpf2) {
		Torneio torneio = tr.findById(idTorn);
//		RedirectAttributes attributes

		dupla.getAtleta1().setId(Long.parseLong(cpf1.replaceAll("[.-]", "")));
		dupla.getAtleta2().setId(Long.parseLong(cpf2.replaceAll("[.-]", "")));
		int idInt1 = torneio.getCircuito().getUsuario().getId();
		int idInt2 = torneio.getCircuito().getId();
		int idInt3 = torneio.getId();
		String codigoUser = "" + idInt1;
		String codigoCirc = "" + idInt2;
		String codigoTorn = "" + idInt3;
//		if (ar.findByCpf(dupla.getAtleta1().getId()) != null) {
//			attributes.addFlashAttribute("mensagem", "Cpf já existente!");
//			return "redirect:/inscricaoUsuario" + codigoUser + "circuito" + codigoCirc + "torneio" + codigoTorn
//					+ "dupla";
//		}

		torneio.setQtdAtletas(torneio.getQtdAtletas() + 2);
		dupla.setTorneio(torneio);
		ranking1 = new Ranking();
		ranking2 = new Ranking();

		ranking1.setCategoria(dupla.getCategoria());
		ranking1.setCircuito(torneio.getCircuito());
		ranking1.setAtleta(dupla.getAtleta1());
		ranking1.setTorneio(torneio);

		ranking2.setCategoria(dupla.getCategoria());
		ranking2.setCircuito(torneio.getCircuito());
		ranking2.setAtleta(dupla.getAtleta2());
		ranking2.setTorneio(torneio);

		dupla.getAtleta1().getRankings().add(ranking1);
		dupla.getAtleta2().getRankings().add(ranking2);
		
		ar.save(dupla.getAtleta1());
		ar.save(dupla.getAtleta2());

		dr.save(dupla);
		

		rr.save(ranking1);
		rr.save(ranking2);

		tr.save(torneio);

		return "redirect:/Usuario" + codigoUser + "circuito" + codigoCirc + "torneio" + codigoTorn;
	}

	@RequestMapping("/DeletarDupla")
	public String deletarDupla(int id) {
		Dupla dupla = dr.findById(id);
		dupla.getTorneio().getDuplas().remove(dupla);
		dupla.getTorneio().setQtdAtletas(dupla.getTorneio().getQtdAtletas() - 2);

		for (Ranking r : rr.findByCircuito(dupla.getTorneio().getCircuito())) {
			if (r.getAtleta() == dupla.getAtleta1()
					|| r.getAtleta() == dupla.getAtleta2() && r.getTorneio() == dupla.getTorneio()) {
				rr.delete(r);
			}
		}

		int idInt1 = dupla.getTorneio().getCircuito().getUsuario().getId();
		int idInt2 = dupla.getTorneio().getCircuito().getId();
		int idInt3 = dupla.getTorneio().getId();
		String codigoUser = "" + idInt1;
		String codigoCirc = "" + idInt2;
		String codigoTorn = "" + idInt3;

		return "redirect:/Usuario" + codigoUser + "circuito" + codigoCirc + "torneio" + codigoTorn;
	}

}

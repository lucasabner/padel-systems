package fourzeta.controllers.web;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import fourzeta.models.Chave;
import fourzeta.models.Circuito;
import fourzeta.models.Dupla;
import fourzeta.models.OrderDuplasPontuacao;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.repository.ChaveRepository;
import fourzeta.repository.CircuitoRepository;
import fourzeta.repository.DuplaRepository;
import fourzeta.repository.RankingRepository;
import fourzeta.repository.TorneioRepository;
import fourzeta.resources.ChaveResource;
import fourzeta.resources.DuplaResource;

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
//		torneio.setQtdAtletas(torn.getQtdAtletas());
//		torneio.getQuadras().addAll(torn.getQuadras());
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
		Circuito circuito = new Circuito();
		for (Circuito c: cr.findAll()) {
			if(c.getId() == torneio.getCircuito().getId()) {
				circuito = c;
			}
		}

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
	
	
	@RequestMapping("/encerrarInscricao")
	public String encerrarInscricao(int id) {
		Torneio torneio = tr.findById(id);
		torneio.setInscEncerradas(true);
		tr.save(torneio);
		montarChave(torneio);
		
		int idInt1 = torneio.getCircuito().getUsuario().getId();
		int idInt2 = torneio.getCircuito().getId();
		int idInt3 = torneio.getId();
		String codigoUser = "" + idInt1;
		String codigoCirc = "" + idInt2;
		String codigoTorn = "" + idInt3;

		return "redirect:/Usuario" + codigoUser + "circuito" + codigoCirc + "torneio" + codigoTorn;
	}
	
	public void montarChave(Torneio torneio) {
//		List<Dupla> duplas = torneio.getDuplas();
		
		List<Dupla> duplas = (List<Dupla>) dr.findByTorneio(torneio);
		retirarSuplentes(duplas);
		
		List<Dupla> primeira = new ArrayList<Dupla>();
		List<Dupla> segunda = new ArrayList<Dupla>();
		List<Dupla> terceira = new ArrayList<Dupla>();
		List<Dupla> quarta = new ArrayList<Dupla>();
		List<Dupla> quinta = new ArrayList<Dupla>();
		List<Dupla> sexta = new ArrayList<Dupla>();
		
		for (Dupla d : duplas) {
			switch (d.getCategoria()) {
			case "PRIMEIRA":
				primeira.add(d);
				break;
			case "SEGUNDA":
				segunda.add(d);
				break;
			case "TERCEIRA":
				terceira.add(d);
				break;
			case "QUARTA":
				quarta.add(d);
				break;
			case "QUINTA":
				quinta.add(d);
				break;
			case "SEXTA":
				sexta.add(d);
				break;
			}
		}

		primeira.sort(new OrderDuplasPontuacao());
		segunda.sort(new OrderDuplasPontuacao());
		terceira.sort(new OrderDuplasPontuacao());
		quarta.sort(new OrderDuplasPontuacao());
		quinta.sort(new OrderDuplasPontuacao());
		sexta.sort(new OrderDuplasPontuacao());

		distribuirChaves(primeira, "PRIMEIRA", torneio);
		distribuirChaves(segunda, "SEGUNDA", torneio);
		distribuirChaves(terceira, "TERCEIRA",torneio);
		distribuirChaves(quarta, "QUARTA",torneio);
		distribuirChaves(quinta, "QUINTA",torneio);
		distribuirChaves(sexta, "SEXTA", torneio);

	}

	private void distribuirChaves(List<Dupla> duplas, String cat, Torneio torneio) {
		List<Chave> chaves = new ArrayList<Chave>();
		int numChaves = duplas.size() / 3;

		// Cria chaves
		for (int i = 0; i < numChaves; i++) {
			// criando objetos em loop
			Chave c = new Chave();
			c.setNome("Chave");
			c.setCategoria(cat);
			c.setTorneio(torneio);
			chaves.add(c);
		}

		// Primeiro linha
		int numDupla = 0;
		int aux = 0;
		for (int i = 0; i < numChaves; i++) {
			chaves.get(aux).setDupla1(duplas.get(numDupla));
			aux++;
			numDupla++;
		}

		// Segunda linha
		aux--;
		for (int i = 0; i < numChaves; i++) {
			chaves.get(aux).setDupla2(duplas.get(numDupla));
			aux--;
			numDupla++;
		}

		// Terceira linha
		aux++;
		for (int i = 0; i < numChaves; i++) {
			chaves.get(aux).setDupla3(duplas.get(numDupla));
			aux++;
			numDupla++;
		}
			chr.saveAll(chaves);
	}

	public void retirarSuplentes(List<Dupla> duplas) {
		int numDuplasSemSuplentes = duplas.size() % 3;
		System.out.println("saporitico  " + numDuplasSemSuplentes);
		for (int i = 0; i < numDuplasSemSuplentes; i++) {
			System.out.println("saporitico "+ duplas.get(duplas.size()-1).getId());
			duplas.remove(duplas.size() - 1);
		}
	}
}

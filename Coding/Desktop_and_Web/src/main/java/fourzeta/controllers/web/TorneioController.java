package fourzeta.controllers.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fourzeta.models.Chave;
import fourzeta.models.Circuito;
import fourzeta.models.Dupla;
import fourzeta.models.Etapa;
import fourzeta.models.Impedimento;
import fourzeta.models.Jogo;
import fourzeta.models.OrderDuplasPontuacao;
import fourzeta.models.Torneio;
import fourzeta.repository.ChaveRepository;
import fourzeta.repository.CircuitoRepository;
import fourzeta.repository.DuplaRepository;
import fourzeta.repository.JogoRepository;
import fourzeta.repository.RankingRepository;
import fourzeta.repository.TorneioRepository;
import fourzeta.resources.ChaveResource;
import fourzeta.resources.JogoResource;

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
	
	@Autowired
	private JogoRepository jr;
	

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
		
		Iterable<Jogo> jogos = jr.findByTorneio(torneio);
		mv.addObject("jogos", jogos);

		Iterable<Dupla> duplas = dr.findByTorneio(torneio);
		
		if(torneio.isInscEncerradas()) {
			((List<Dupla>) duplas).sort(new OrderDuplasPontuacao());
		}
		
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
		distribuirJogos(torneio);
		
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
	
	public void distribuirJogos(Torneio torneio) {
		List<Chave> chaves =  (List<Chave>) chr.findByTorneio(torneio);
		List<Jogo> jogos = new ArrayList<Jogo>();
		
		for (Chave chave : chaves) { // Jogos 1 VS 2
			Jogo jo1 = new Jogo();
			if (chave.getDupla1() != null && chave.getDupla2() != null) {
				jo1.setPartida(chave.getDupla1().toString() + "     X     " + chave.getDupla2().toString());
				jo1.setDupla1(chave.getDupla1());
				jo1.setDupla2(chave.getDupla2());
				jo1.setCategoria(chave.getCategoria());
				jo1.setChave(chave);
				jo1.setTorneio(torneio);
			}
			jogos.add(distribuirHorarios(chave, chave.getDupla1(), chave.getDupla2(), jo1));
			chave.setJogos(jogos);
			torneio.getJogos().add(jo1);
			chr.save(chave);
			jr.save(jo1);
			tr.save(torneio);

		}
		for (Chave chave : chaves) { // Jogos 1 VS 3
			Jogo jo2 = new Jogo();
			if (chave.getDupla1() != null && chave.getDupla3() != null) {
				jo2.setPartida(chave.getDupla1().toString() + "     X     " + chave.getDupla3().toString());
				jo2.setDupla1(chave.getDupla1());
				jo2.setDupla2(chave.getDupla3());
				jo2.setCategoria(chave.getCategoria());
				jo2.setChave(chave);
				jo2.setTorneio(torneio);
			}
			jogos.add(distribuirHorarios(chave, chave.getDupla1(), chave.getDupla3(), jo2));
			chave.setJogos(jogos);
			torneio.getJogos().add(jo2);
			chr.save(chave);
			jr.save(jo2);
			tr.save(torneio);
			
		}
		for (Chave chave : chaves) { // Jogos 2 VS 3
			Jogo jo3 = new Jogo();
			if (chave.getDupla2() != null && chave.getDupla3() != null) {
				jo3.setPartida(chave.getDupla2().toString() + "     X     " + chave.getDupla3().toString());
				jo3.setDupla1(chave.getDupla2());
				jo3.setDupla2(chave.getDupla3());
				jo3.setCategoria(chave.getCategoria());
				jo3.setChave(chave);
				jo3.setTorneio(torneio);
			}
			jogos.add(distribuirHorarios(chave, chave.getDupla2(), chave.getDupla3(), jo3));
			chave.setJogos(jogos);
			torneio.getJogos().add(jo3);
			chr.save(chave);
			jr.save(jo3);
			tr.save(torneio);

		}
	}

	public void retirarSuplentes(List<Dupla> duplas) {
		int numDuplasSemSuplentes = duplas.size() % 3;
		System.out.println("saporitico  " + numDuplasSemSuplentes);
		for (int i = 0; i < numDuplasSemSuplentes; i++) {
			System.out.println("saporitico "+ duplas.get(duplas.size()-1).getId());
			duplas.remove(duplas.size() - 1);
		}
	}
	
	public static Jogo distribuirHorarios(Chave chave, Dupla d1, Dupla d2, Jogo jogo) {
		List<List<String>> hrs = getHorarios();
		if (jogo.getEtapa() == Etapa.CHAVEAMENTO.name()) {
			if (!d1.getImpedimento().equals(Impedimento.NENHUM.name())) {
				switch (d1.getImpedimento().toString()) {
				case "QUINTA":
					hrs.remove(horariosQuinta);
					break;
				case "SEXTA":
					hrs.remove(horariosSexta);
					break;
				case "SABADO":
					hrs.remove(horariosSabadoManha);
					break;
				}
			}

			if (!d2.getImpedimento().equals(Impedimento.NENHUM.name())) {
				switch (d2.getImpedimento().toString()) {
				case "QUINTA":
					hrs.remove(horariosQuinta);
					break;
				case "SEXTA":
					hrs.remove(horariosSexta);
					break;
				case "SABADO":
					hrs.remove(horariosSabadoManha);
					break;
				}
			}
		}

		jogo.setData(hrs.get(0).remove(0));

		return jogo;
	}

	public static List<List<String>> getHorarios() {
		List<List<String>> horarios = new ArrayList<List<String>>();
		horariosQuinta.add("QUINTA-FEIRA 18:00");
		horariosQuinta.add("QUINTA-FEIRA 18:50");
		horariosQuinta.add("QUINTA-FEIRA 19:40");
		horariosQuinta.add("QUINTA-FEIRA 20:30");
		horariosQuinta.add("QUINTA-FEIRA 21:20");
		horariosQuinta.add("QUINTA-FEIRA 22:10");
		horariosQuinta.add("QUINTA-FEIRA 23:00");
		horariosSexta.add("SEXTA-FEIRA 18:00");
		horariosSexta.add("SEXTA-FEIRA 18:50");
		horariosSexta.add("SEXTA-FEIRA 19:40");
		horariosSexta.add("SEXTA-FEIRA 20:30");
		horariosSexta.add("SEXTA-FEIRA 21:20");
		horariosSexta.add("SEXTA-FEIRA 22:10");
		horariosSexta.add("SEXTA-FEIRA 23:00");
		horariosSabadoManha.add("SÁBADO 08:00");
		horariosSabadoManha.add("SÁBADO 08:50");
		horariosSabadoManha.add("SÁBADO 09:40");
		horariosSabadoManha.add("SÁBADO 10:30");
		horariosSabadoManha.add("SÁBADO 11:20");
		horariosSabadoTarde.add("SÁBADO 13:50");
		horariosSabadoTarde.add("SÁBADO 14:40");
		horariosSabadoTarde.add("SÁBADO 15:30");
		horariosSabadoTarde.add("SÁBADO 16:20");
		horariosSabadoTarde.add("SÁBADO 17:10");
		horariosSabadoNoite.add("SÁBADO 18:00");
		horariosSabadoNoite.add("SÁBADO 18:50");
		horariosSabadoNoite.add("SÁBADO 19:40");
		horariosSabadoNoite.add("SÁBADO 20:30");
		horariosSabadoNoite.add("SÁBADO 21:20");
		horariosSabadoNoite.add("SÁBADO 22:10");
		horariosSabadoNoite.add("SÁBADO 23:00");
		horariosDomingoManha.add("DOMINGO 08:00");
		horariosDomingoManha.add("DOMINGO 08:50");
		horariosDomingoManha.add("DOMINGO 09:40");
		horariosDomingoManha.add("DOMINGO 10:30");
		horariosDomingoManha.add("DOMINGO 11:20");
		horariosDomingoTarde.add("DOMINGO 13:50");
		horariosDomingoTarde.add("DOMINGO 14:40");
		horariosDomingoTarde.add("DOMINGO 15:30");
		horariosDomingoTarde.add("DOMINGO 16:20");
		horariosDomingoTarde.add("DOMINGO 17:10");
		horariosDomingoNoite.add("DOMINGO 18:00");
		horariosDomingoNoite.add("DOMINGO 18:50");
		horariosDomingoNoite.add("DOMINGO 19:40");
		horariosDomingoNoite.add("DOMINGO 20:30");
		horariosDomingoNoite.add("DOMINGO 21:20");
		horariosDomingoNoite.add("DOMINGO 22:10");
		horariosDomingoNoite.add("DOMINGO 23:00");
		horarios.add(horariosQuinta);
		horarios.add(horariosSexta);
		horarios.add(horariosSabadoManha);
		horarios.add(horariosSabadoTarde);
		horarios.add(horariosSabadoNoite);
		horarios.add(horariosDomingoManha);
		horarios.add(horariosDomingoTarde);
		horarios.add(horariosDomingoNoite);
		return horarios;
	}
	private static List<String> horariosQuinta = new ArrayList<String>();
	private static List<String> horariosSexta = new ArrayList<String>();
	private static List<String> horariosSabadoManha = new ArrayList<String>();
	private static List<String> horariosSabadoTarde = new ArrayList<String>();
	private static List<String> horariosSabadoNoite = new ArrayList<String>();
	private static List<String> horariosDomingoManha = new ArrayList<String>();
	private static List<String> horariosDomingoTarde = new ArrayList<String>();
	private static List<String> horariosDomingoNoite = new ArrayList<String>();
}

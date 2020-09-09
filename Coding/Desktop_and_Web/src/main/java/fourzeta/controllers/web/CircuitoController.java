package fourzeta.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fourzeta.models.Circuito;
import fourzeta.models.Ranking;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.repository.CircuitoRepository;
import fourzeta.repository.RankingRepository;
import fourzeta.repository.TorneioRepository;
import fourzeta.repository.UsuarioRepository;

@Controller
public class CircuitoController {

	@Autowired
	private CircuitoRepository cr;
	
	@Autowired
	private TorneioRepository tr;
	
	@Autowired
	private RankingRepository rr;
	
	@Autowired
	private UsuarioRepository ur;
	
	
	@RequestMapping(value="/editarUsuario{idUser}circuito{idCirc}", method=RequestMethod.GET)
	public ModelAndView formEdit(@PathVariable("idUser") int idUser,@PathVariable("idCirc") int idCirc){
		Circuito circuito = cr.findById(idCirc);
		ModelAndView mv = new ModelAndView("circuito/formEditCircuito");
		mv.addObject("circuito", circuito);
		System.out.println(circuito.getId() + "antes");
		
		return mv;
		
	}
	
	@RequestMapping(value="/editarUsuario{idUser}circuito{idCirc}", method=RequestMethod.POST)
	public String editarCircuito(Circuito circuito, @PathVariable("idUser") int idUser,@PathVariable("idCirc") int idCirc ){
		System.out.println(idUser + "idUserEdit");
		System.out.println(idCirc + "idCirc");
		
		Circuito circ = cr.findById(idCirc);
		circuito.getTorneios().addAll(circ.getTorneios());
		circuito.getRankings().addAll(circ.getRankings());
		
		circuito.setId(idCirc);
		
		Usuario usuario = ur.findById(idUser);
		circuito.setUsuario(usuario);
		
		cr.save(circuito);
		
		
		int idInt= circuito.getUsuario().getId();
		String codigo = "" + idInt;
		return "redirect:/usuario"+ codigo +"circuitos";
	}
	
	
	@RequestMapping(value="/cadastrarUsuario{idUser}circuito", method=RequestMethod.GET)
	public ModelAndView formCircuito(@PathVariable("idUser") int idUser){
		ModelAndView mv = new ModelAndView("circuito/formCircuito");
		Usuario usuario = ur.findById(idUser);
		mv.addObject("usuario", usuario);
		
		return mv;
	}
	
	@RequestMapping(value="/cadastrarUsuario{idUser}circuito", method=RequestMethod.POST)
	public String cadastrarCircuito(Circuito circuito, @PathVariable("idUser") int idUser){
		circuito.setUsuario(ur.findById(idUser));
		cr.save(circuito);
		
		int idInt= circuito.getUsuario().getId();
		String codigo = "" + idInt;
		return "redirect:/usuario"+ codigo+ "circuitos";
	}
	
	
	@RequestMapping(value="usuario{idUser}circuitos", method=RequestMethod.GET)
	public ModelAndView listaCircuitos(@PathVariable("idUser") int idUser){
		ModelAndView mv = new ModelAndView("circuito/listaCircuitos");
		Usuario usuario = ur.findById(idUser);
		
		System.out.println(idUser);
		
		Iterable<Circuito> circuitos = cr.findByUsuario(usuario);
		mv.addObject("circuitos", circuitos);
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@RequestMapping(value="usuario{idUser}circuito{idCirc}", method=RequestMethod.GET)
	public ModelAndView detalhesCircuito(@PathVariable("idUser") int idUser,@PathVariable("idCirc") int idCirc){
		Circuito circuito = cr.findById(idCirc);
		ModelAndView mv = new ModelAndView("circuito/detalhesCircuito");
		mv.addObject("circuito", circuito);
		
		Iterable<Torneio> torneios = tr.findByCircuito(circuito);
		mv.addObject("torneios", torneios);
		
		Iterable<Ranking> rankings = rr.findByCircuito(circuito);
		mv.addObject("rankings", rankings);
		
		mv.addObject("usuario", circuito.getUsuario());
		
		return mv;
	}
	
	@RequestMapping("/deletarCircuito")
	public String deletarCircuito(int id){
		Circuito circuito = cr.findById(id);
		circuito.getUsuario().getCircuitos().remove(circuito);
		
		cr.delete(circuito);
		
		
		int idInt= circuito.getUsuario().getId();
		String codigo = "" + idInt;
		return "redirect:/usuario"+ codigo+ "circuitos";
	}
	
	
//	@RequestMapping(value="/{id}", method=RequestMethod.POST)
//	public String detalhesCircuitoPost(@PathVariable("id") int id){
//		if(result.hasErrors()){
//			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
//			return "redirect:/{codigo}";
//		}
//		Evento evento = er.findByCodigo(codigo);
//		convidado.setEvento(evento);
//		cr.save(convidado);
//		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
//		return "redirect:/{codigo}";
//	}
	
	
}	

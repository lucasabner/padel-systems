package fourzeta.controllers.web;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fourzeta.models.Chave;
import fourzeta.models.Dupla;
import fourzeta.models.Jogo;
import fourzeta.models.dto.ChaveDTO;
import fourzeta.models.dto.DuplaDTO;
import fourzeta.models.dto.JogoDTO;
import fourzeta.repository.ChaveRepository;
import fourzeta.repository.CircuitoRepository;
import fourzeta.repository.DuplaRepository;
import fourzeta.repository.JogoRepository;
import fourzeta.repository.RankingRepository;
import fourzeta.repository.TorneioRepository;

@RestController
@RequestMapping("rest")
public class TestRestController {

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

	@GetMapping(value = "/chavesByTorneio-{idTorn}")
	public List<ChaveDTO> findAllChavesByTorneio(@PathVariable("idTorn") int idTorn) {
//		int id, int idTorn, int idDupla1, int idDupla2, int idDupla3, List<Jogo> jogos, String nome,
//		String cat
		List<ChaveDTO> chavesDTO = new ArrayList<>();
		for (Chave c : chr.findAllChaves(tr.findById(idTorn).getId())) {
			chavesDTO.add(new ChaveDTO(c.getId(), c.getTorneio().getId(), c.getDupla1().getId(), c.getDupla2().getId(),
					c.getDupla3().getId(), c.getJogos(), c.getNome(), c.getCategoria()));
		}
		return chavesDTO;
	}

	@GetMapping(value = "/duplasByTorneio-{idTorn}")
	public List<DuplaDTO> findAllDuplasByTorneio(@PathVariable("idTorn") int idTorn) {
//		int id, int idTorn, int idA1, int idA2, String cat, String impedimento, long ponTotal
		List<DuplaDTO> duplasDTO = new ArrayList<>();
		for (Dupla d : dr.findByTorneio(tr.findById(idTorn))) {
			duplasDTO.add(new DuplaDTO(d.getId(), d.getTorneio().getId(), d.getAtleta1().getId(),
					d.getAtleta2().getId(), d.getCategoria(), d.getImpedimento(), d.getPonTotal()));
		}

		return duplasDTO;
	}

	@GetMapping(value = "/jogosByTorneio-{idTorn}")
	public List<JogoDTO> findAllJogosByTorneio(@PathVariable("idTorn") int idTorn) {
//		int id, int idTorn, int idChave, int idDupla1, int idDupla2, String cat, String data, String horario, String placar, String etapa
		List<JogoDTO> jogos = new ArrayList<>();

		for (Jogo j : jr.findByTorneio(tr.findById(idTorn))) {
			jogos.add(new JogoDTO(j.getId(), j.getTorneio().getId(), j.getChave().getId(), j.getDupla1().getId(),
					j.getDupla2().getId(), j.getCategoria(), j.getData(), j.getHorario(), j.getPlacar(), j.getEtapa()));
		}
		return jogos;
	}
	
	@GetMapping(value = "/listarJogos")
	public List<JogoDTO> findAllJogos() {
//		int id, int idTorn, int idChave, int idDupla1, int idDupla2, String cat, String data, String horario, String placar, String etapa
		List<JogoDTO> jogos = new ArrayList<>();

		for (Jogo j : jr.findAll()) {
			JogoDTO jogo = new JogoDTO();
			jogo.setId(j.getId());
			jogos.add(jogo);
		}
		return jogos;
	}

	@GetMapping(value = "/chave-{idChave}")
	public ResponseEntity<ChaveDTO> getChave(@PathVariable("idChave") int idChave) {
		Chave c = chr.findById(idChave);
		ChaveDTO chaveDTO = new ChaveDTO(c.getId(), c.getTorneio().getId(), c.getDupla1().getId(), c.getDupla2().getId(),
				c.getDupla3().getId(), c.getJogos(), c.getNome(), c.getCategoria());
		return ResponseEntity.ok().body(chaveDTO);
	}
	@GetMapping(value = "/chave/{idChave}")
	public ResponseEntity<?> getChave2(@PathVariable("idChave") int idChave) {
		try {
		Chave c = chr.findById(idChave);
		ChaveDTO chaveDTO = new ChaveDTO(c.getId(), c.getTorneio().getId(), c.getDupla1().getId(), c.getDupla2().getId(),
				c.getDupla3().getId(), c.getJogos(), c.getNome(), c.getCategoria());
		return ResponseEntity.ok().body(chaveDTO);
		}catch(EntityNotFoundException e){
			StandardError err =  new StandardError();
			err.setTimestamp(Instant.now());
			err.setStatus(HttpStatus.NOT_FOUND.value());
			err.setError("Resource not Found");
			err.setMessage(e.getMessage());
			err.setPath("test");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
			
		}
	}

	@PostMapping(value = "/criarJogo")
	public Jogo createJogo(@RequestBody Jogo jogo) {
		return 	jr.save(jogo);
	}
	
	@DeleteMapping("/deletarJogo/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") int jogoId){
        Jogo jogo = jr.findById(jogoId);
        jr.delete(jogo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

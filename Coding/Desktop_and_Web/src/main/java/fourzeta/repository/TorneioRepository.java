package fourzeta.repository;

import org.springframework.data.repository.CrudRepository;

import fourzeta.models.Circuito;
import fourzeta.models.Torneio;

public interface TorneioRepository extends CrudRepository<Torneio, String>{
	Iterable<Torneio> findByCircuito(Circuito circuito);
	Torneio findById(int id);
}

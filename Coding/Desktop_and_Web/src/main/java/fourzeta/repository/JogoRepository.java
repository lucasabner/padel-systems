package fourzeta.repository;

import org.springframework.data.repository.CrudRepository;

import fourzeta.models.Jogo;
import fourzeta.models.Torneio;

public interface JogoRepository extends CrudRepository<Jogo, String> {
	Iterable<Jogo> findByTorneio(Torneio torneio);
	Jogo findById(int id);

}

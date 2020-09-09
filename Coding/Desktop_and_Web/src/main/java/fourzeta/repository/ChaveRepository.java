package fourzeta.repository;

import org.springframework.data.repository.CrudRepository;

import fourzeta.models.Chave;
import fourzeta.models.Torneio;

public interface ChaveRepository extends CrudRepository<Chave, String> {
	Iterable<Chave> findByTorneio(Torneio torneio);
	Chave findById(int id);

}

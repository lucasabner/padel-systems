package fourzeta.repository;

import org.springframework.data.repository.CrudRepository;

import fourzeta.models.Dupla;
import fourzeta.models.Torneio;

public interface DuplaRepository extends CrudRepository<Dupla, String> {
	Iterable<Dupla> findByTorneio(Torneio torneio);
	Dupla findById(int id);

}

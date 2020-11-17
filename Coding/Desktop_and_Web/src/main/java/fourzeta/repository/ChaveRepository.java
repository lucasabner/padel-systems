package fourzeta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fourzeta.models.Chave;
import fourzeta.models.Torneio;

public interface ChaveRepository extends CrudRepository<Chave, String> {
	Iterable<Chave> findByTorneio(Torneio torneio);

	Chave findById(int id);

	@Query(value = "SELECT * FROM Chave c WHERE c.torneio_id = ?1", nativeQuery = true)
	List<Chave> findAllChaves(int torneio_id);


}

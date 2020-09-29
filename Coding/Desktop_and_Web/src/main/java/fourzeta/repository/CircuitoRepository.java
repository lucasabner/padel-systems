package fourzeta.repository;

import org.springframework.data.repository.CrudRepository;
import fourzeta.models.Circuito;
import fourzeta.models.Usuario;

public interface CircuitoRepository extends CrudRepository<Circuito, String>{
	Circuito findById(int id);
	Iterable<Circuito> findByUsuario(Usuario torneio);
}

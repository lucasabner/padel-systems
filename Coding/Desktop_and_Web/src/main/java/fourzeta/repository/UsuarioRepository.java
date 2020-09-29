package fourzeta.repository;

import org.springframework.data.repository.CrudRepository;
import fourzeta.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Usuario findById(int id);
}

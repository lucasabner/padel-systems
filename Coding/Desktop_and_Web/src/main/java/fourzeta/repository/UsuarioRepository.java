package fourzeta.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fourzeta.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Usuario findById(int id);
}

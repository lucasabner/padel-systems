package fourzeta.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fourzeta.models.Atleta;
import fourzeta.models.Dupla;

public interface AtletaRepository extends CrudRepository<Atleta, String> {
//	Iterable<Atleta> findByDuplas(List<Dupla> dupla);
	Atleta findById(int id);

}

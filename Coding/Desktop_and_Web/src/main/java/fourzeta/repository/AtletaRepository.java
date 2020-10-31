package fourzeta.repository;

import org.springframework.data.repository.CrudRepository;
import fourzeta.models.Atleta;

public interface AtletaRepository extends CrudRepository<Atleta, String> {
//	Iterable<Atleta> findByDuplas(List<Dupla> dupla);
	Atleta findByCpf(long cpf);

}

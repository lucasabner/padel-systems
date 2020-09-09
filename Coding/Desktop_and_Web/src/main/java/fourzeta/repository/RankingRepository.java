package fourzeta.repository;

import org.springframework.data.repository.CrudRepository;

import fourzeta.models.Circuito;
import fourzeta.models.Ranking;

public interface RankingRepository extends CrudRepository<Ranking, String> {
	Iterable<Ranking> findByCircuito(Circuito circuito);
//	Optional<Ranking> findById(String rg);
	Ranking findById(int id);
	
}

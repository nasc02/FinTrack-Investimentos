package br.fintrack.repositories;

import java.util.List;

import br.fintrack.models.Investiment;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan()
public interface InvestimentRepository extends CrudRepository<Investiment, Short> {
    List<Investiment> findByid(Short id);
}

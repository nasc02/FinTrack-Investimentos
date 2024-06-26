package br.fintrack.repositories;

import java.beans.JavaBean;
import java.util.List;

import br.fintrack.models.Investiment;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@EnableScan()
public interface InvestimentRepository extends CrudRepository<Investiment, String> {
    List<Investiment> findByuserId(String id);
}

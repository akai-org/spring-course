package pl.org.akai.springbasic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.org.akai.springbasic.entity.ProgrammingLanguage;

@Repository
public interface ProgrammingLanguageRepository extends CrudRepository<ProgrammingLanguage, Integer> {
}

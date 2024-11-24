package br.com.engjaconi.curso_springboot_3.repository;

import br.com.engjaconi.curso_springboot_3.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}

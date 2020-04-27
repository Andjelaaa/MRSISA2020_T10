package main.mrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import main.mrs.model.Dijagnoza;



public interface DijagnozaRepository extends JpaRepository<Dijagnoza, Long>{
	Page<Dijagnoza> findAll(Pageable arg0);

	 
	  long count();

	 
	  void delete(Dijagnoza arg0);

	 
	  void deleteAll();

	 
	  void deleteAll(Iterable<? extends Dijagnoza> arg0);

	 
	  void deleteById(Long arg0);

	 
	  boolean existsById(Long arg0);

	 
	  Optional<Dijagnoza> findById(Long arg0);

	 
	  <S extends Dijagnoza> S save(S arg0);

	 
	  <S extends Dijagnoza> long count(Example<S> arg0);

	 
	  <S extends Dijagnoza> boolean exists(Example<S> arg0);

	 
	  <S extends Dijagnoza> Page<S> findAll(Example<S> arg0, Pageable arg1);

	 
	  <S extends Dijagnoza> Optional<S> findOne(Example<S> arg0);

	 
	  void deleteAllInBatch();

	 
	  void deleteInBatch(Iterable<Dijagnoza> arg0);

	 
	  List<Dijagnoza> findAll();

	 
	  <S extends Dijagnoza> List<S> findAll(Example<S> arg0, Sort arg1);

	 
	  <S extends Dijagnoza> List<S> findAll(Example<S> arg0);

	 
	  List<Dijagnoza> findAll(Sort arg0);

	 
	  List<Dijagnoza> findAllById(Iterable<Long> arg0);

	 
	  void flush();

	 
	  Dijagnoza getOne(Long arg0);

	 
	  <S extends Dijagnoza> List<S> saveAll(Iterable<S> arg0);

	 
	  <S extends Dijagnoza> S saveAndFlush(S arg0);

}

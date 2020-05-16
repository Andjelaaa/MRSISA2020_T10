package main.mrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import main.mrs.model.Operacija;

public interface OperacijaRepository  extends JpaRepository<Operacija, Long>{
	Page<Operacija> findAll(Pageable arg0);

	 
	  long count();

	 
	  void delete(Operacija arg0);

	 
	  void deleteAll();

	 
	  void deleteAll(Iterable<? extends Operacija> arg0);

	 
	  void deleteById(Long arg0);

	 
	  boolean existsById(Long arg0);

	 
	  Optional<Operacija> findById(Long integer);

	 
	  <S extends Operacija> S save(S arg0);

	 
	  <S extends Operacija> long count(Example<S> arg0);

	 
	  <S extends Operacija> boolean exists(Example<S> arg0);

	 
	  <S extends Operacija> Page<S> findAll(Example<S> arg0, Pageable arg1);

	 
	  <S extends Operacija> Optional<S> findOne(Example<S> arg0);

	 
	  void deleteAllInBatch();

	 
	  void deleteInBatch(Iterable<Operacija> arg0);

	 
	  List<Operacija> findAll();

	 
	  <S extends Operacija> List<S> findAll(Example<S> arg0, Sort arg1);

	 
	  <S extends Operacija> List<S> findAll(Example<S> arg0);

	 
	  List<Operacija> findAll(Sort arg0);

	 
	  List<Operacija> findAllByIdIn(List<Integer> arg0);

	 
	  void flush();

	 
	  Operacija getOne(Long arg0);

	 
	  <S extends Operacija> List<S> saveAll(Iterable<S> arg0);

	 
	  <S extends Operacija> S saveAndFlush(S arg0);

	@Query(value = "SELECT * FROM OPERACIJA_LEKAR WHERE LEKAR_ID = ?1", nativeQuery = true)
	List<Integer> findAllByLekarId(Integer id);

	

}

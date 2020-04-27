package main.mrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import main.mrs.model.Lekar;

public interface LekarRepository extends JpaRepository<Lekar, Long>{
	Page<Lekar> findAll(Pageable arg0);

	 
	  long count();

	 
	  void delete(Lekar arg0);

	 
	  void deleteAll();

	 
	  void deleteAll(Iterable<? extends Lekar> arg0);

	 
	  void deleteById(Long arg0);

	 
	  boolean existsById(Long arg0);

	 
	  Optional<Lekar> findById(Long arg0);

	 
	  <S extends Lekar> S save(S arg0);

	 
	  <S extends Lekar> long count(Example<S> arg0);

	 
	  <S extends Lekar> boolean exists(Example<S> arg0);

	 
	  <S extends Lekar> Page<S> findAll(Example<S> arg0, Pageable arg1);

	 
	  <S extends Lekar> Optional<S> findOne(Example<S> arg0);

	 
	  void deleteAllInBatch();

	 
	  void deleteInBatch(Iterable<Lekar> arg0);

	 
	  List<Lekar> findAll();

	 
	  <S extends Lekar> List<S> findAll(Example<S> arg0, Sort arg1);

	 
	  <S extends Lekar> List<S> findAll(Example<S> arg0);

	 
	  List<Lekar> findAll(Sort arg0);

	 
	  List<Lekar> findAllById(Iterable<Long> arg0);

	 
	  void flush();

	 
	  Lekar getOne(Long arg0);

	 
	  <S extends Lekar> List<S> saveAll(Iterable<S> arg0);

	 
	  <S extends Lekar> S saveAndFlush(S arg0);
}

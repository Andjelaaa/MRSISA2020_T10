package main.mrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import main.mrs.model.MedSestra;
import main.mrs.model.MedSestra;

public interface MedSestraRepository extends JpaRepository<MedSestra, Long>{
	Page<MedSestra> findAll(Pageable arg0);

	 
	  long count();

	 
	  void delete(MedSestra arg0);

	 
	  void deleteAll();

	 
	  void deleteAll(Iterable<? extends MedSestra> arg0);

	 
	  void deleteById(Long arg0);

	 
	  boolean existsById(Long arg0);

	 
	  Optional<MedSestra> findById(Long arg0);

	 
	  <S extends MedSestra> S save(S arg0);

	 
	  <S extends MedSestra> long count(Example<S> arg0);

	 
	  <S extends MedSestra> boolean exists(Example<S> arg0);

	 
	  <S extends MedSestra> Page<S> findAll(Example<S> arg0, Pageable arg1);

	 
	  <S extends MedSestra> Optional<S> findOne(Example<S> arg0);

	 
	  void deleteAllInBatch();

	 
	  void deleteInBatch(Iterable<MedSestra> arg0);

	 
	  List<MedSestra> findAll();

	 
	  <S extends MedSestra> List<S> findAll(Example<S> arg0, Sort arg1);

	 
	  <S extends MedSestra> List<S> findAll(Example<S> arg0);

	 
	  List<MedSestra> findAll(Sort arg0);

	 
	  List<MedSestra> findAllById(Iterable<Long> arg0);

	 
	  void flush();

	 
	  MedSestra getOne(Long arg0);

	 
	  <S extends MedSestra> List<S> saveAll(Iterable<S> arg0);

	 
	  <S extends MedSestra> S saveAndFlush(S arg0);


	MedSestra findByEmail(String name);
}


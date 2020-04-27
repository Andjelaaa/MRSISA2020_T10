package main.mrs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import main.mrs.model.TipPregleda;
import main.mrs.repository.TipPregledaRepository;

@Service
public class TipPregledaService {
	@Autowired
	private TipPregledaRepository TipPregledaRepository;
	
	public TipPregleda findOne(Long id) {
		return TipPregledaRepository.findById(id).orElseGet(null);
	}

	public List<TipPregleda> findAll() {
		return TipPregledaRepository.findAll();
	}
	
	public Page<TipPregleda> findAll(Pageable page) {
		return TipPregledaRepository.findAll(page);
	}

	public TipPregleda save(TipPregleda TipPregleda) {
		return TipPregledaRepository.save(TipPregleda);
	}

	public void remove(Long id) {
		TipPregledaRepository.deleteById(id);
	}

	public TipPregleda findByNaziv(String naziv) {
		// TODO: promeniti
		return TipPregledaRepository.findAll().get(0);
	}
}

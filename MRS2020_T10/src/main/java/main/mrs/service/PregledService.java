package main.mrs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import main.mrs.model.Pregled;
import main.mrs.repository.PregledRepository;

@Service
public class PregledService {
	@Autowired
	private PregledRepository PregledRepository;

	public Pregled findOne(Long id) {
		return PregledRepository.findById(id).orElseGet(null);
	}

	public List<Pregled> findAll() {
		return PregledRepository.findAll();
	}

	public Page<Pregled> findAll(Pageable page) {
		return PregledRepository.findAll(page);
	}

	public Pregled save(Pregled Pregled) {
		return PregledRepository.save(Pregled);
	}

	public void remove(Long id) {
		PregledRepository.deleteById(id);
	}

	public Pregled findById(long pregledId) {
		// TODO Auto-generated method stub
		return PregledRepository.findById(pregledId);
	}
	public List<Pregled> findByTipPregleda(int tipPregledaId) {
		// TODO Auto-generated method stub
		return PregledRepository.findByTipPregleda(tipPregledaId);
	}

	public List<Pregled> findAfterDate(Date datum) {
		// TODO Auto-generated method stub
		return PregledRepository.findAfterDate(datum);
	}
}
package main.mrs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import main.mrs.model.Pacijent;
import main.mrs.model.TipPregleda;
import main.mrs.repository.PacijentRepository;

@Service
public class PacijentService {
	@Autowired
	private PacijentRepository PacijentRepository;
	
	public Pacijent findOne(Long id) {
		return PacijentRepository.findById(id).orElseGet(null);
	}

	public List<Pacijent> findAll() {
		return PacijentRepository.findAll();
	}
	
	public Page<Pacijent> findAll(Pageable page) {
		return PacijentRepository.findAll(page);
	}

	public Pacijent save(Pacijent Pacijent) {
		return PacijentRepository.save(Pacijent);
	}

	public void remove(Long id) {
		PacijentRepository.deleteById(id);
	}
	
	public Pacijent findById(int pacijentId) {
		return PacijentRepository.findById(pacijentId);
	}

	

}

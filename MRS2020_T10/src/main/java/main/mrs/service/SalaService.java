package main.mrs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import main.mrs.model.Sala;
import main.mrs.repository.SalaRepository;

@Service
public class SalaService {
	@Autowired
	private SalaRepository SalaRepository;

	public Sala findOne(Long id) {
		return SalaRepository.findById(id).orElseGet(null);
	}

	public List<Sala> findAll() {
		return SalaRepository.findAll();
	}

	public Page<Sala> findAll(Pageable page) {
		return SalaRepository.findAll(page);
	}

	public Sala save(Sala Sala) {
		return SalaRepository.save(Sala);
	}

	public void remove(Long id) {
		SalaRepository.deleteById(id);
	}
}

package main.mrs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import main.mrs.model.Lekar;
import main.mrs.repository.LekarRepository;

@Service
public class LekarService {
	@Autowired
	private LekarRepository LekarRepository;

	public Lekar findOne(Long id) {
		return LekarRepository.findById(id).orElseGet(null);
	}

	public List<Lekar> findAll() {
		return LekarRepository.findAll();
	}

	public Page<Lekar> findAll(Pageable page) {
		return LekarRepository.findAll(page);
	}

	public Lekar save(Lekar Lekar) {
		return LekarRepository.save(Lekar);
	}

	public void remove(Long id) {
		LekarRepository.deleteById(id);
	}
}

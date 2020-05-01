package main.mrs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import main.mrs.model.AdminKC;
import main.mrs.repository.AdminKCRepository;

@Service
public class AdminKCService {
 
	@Autowired
	private AdminKCRepository adminKCRepository;
	
	public AdminKC findOne(Long id) {
		return adminKCRepository.findById(id).orElseGet(null);
	}

	public List<AdminKC> findAll() {
		return adminKCRepository.findAll();
	}
	
	public Page<AdminKC> findAll(Pageable page) {
		return adminKCRepository.findAll(page);
	}

	public AdminKC save(AdminKC AdminKC) {
		return adminKCRepository.save(AdminKC);
	}

	public void remove(Long id) {
		adminKCRepository.deleteById(id);
	}
	

}

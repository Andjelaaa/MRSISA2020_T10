package main.mrs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import main.mrs.model.AdminKlinike;
import main.mrs.repository.AdminKlinikeRepository;


@Service
public class AdminKlinikeService {

	@Autowired
	private AdminKlinikeRepository adminKlinikeRepository;
	
	public AdminKlinike findOne(Long id) {
		return adminKlinikeRepository.findById(id).orElseGet(null);
	}

	public List<AdminKlinike> findAll() {
		return adminKlinikeRepository.findAll();
	}
	
	public Page<AdminKlinike> findAll(Pageable page) {
		return adminKlinikeRepository.findAll(page);
	}

	public AdminKlinike save(AdminKlinike AdminKlinike) {
		return adminKlinikeRepository.save(AdminKlinike);
	}

	public void remove(Long id) {
		adminKlinikeRepository.deleteById(id);
	}

}

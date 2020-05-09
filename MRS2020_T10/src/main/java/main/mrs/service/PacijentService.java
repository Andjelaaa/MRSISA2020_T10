package main.mrs.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import main.mrs.model.Korisnik;
import main.mrs.model.Pacijent;
import main.mrs.repository.PacijentRepository;

@Service
public class PacijentService{
	@Autowired
	private PacijentRepository PacijentRepository;
	

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public Pacijent findOne(Integer integer) {
		return PacijentRepository.findById(integer);
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

	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		Pacijent korisnik = PacijentRepository.findByEmail(arg0);
		if (korisnik == null) {
			return null;
		} else {
			return (Pacijent) korisnik;
		}
	}
	
	public String encodePassword(String lozinka) {
		return passwordEncoder.encode(lozinka);
	}

	public Pacijent findByEmail(String name) {
		// TODO Auto-generated method stub
		return PacijentRepository.findByEmail(name);
	}

	

}

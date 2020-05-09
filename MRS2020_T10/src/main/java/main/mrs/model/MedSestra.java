package main.mrs.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="MedicinskaSestra")
public class MedSestra extends Korisnik implements UserDetails {
	
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Klinika klinika;
   
   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   // @JoinColumn(name="medSestra", nullable=false)
    public RadniKalendar radniKalendar;
   
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Autoritet> autoriteti;
   
  
	public RadniKalendar getRadKalendar() {
		return radniKalendar;
	}
	
	
	public void setRadKalendar(RadniKalendar radKalendar) {
		this.radniKalendar = radKalendar;
	}
	


	public Klinika getKlinika() {
		return klinika;
	}
	
	
	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	public List<Autoritet> getAutoriteti() {
	return autoriteti;
	}
	
	public void setAutoriteti(List<Autoritet> autoriteti) {
		this.autoriteti = autoriteti;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.autoriteti;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.getLozinka();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
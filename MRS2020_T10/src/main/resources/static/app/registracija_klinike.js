Vue.component('regklinike', {
	data: function(){
		return{
			nazivKlinike: '',
			adresaKlinike: '',
			emailKlinike: '',
			kontaktKlinike: '',
			opis: '',
			greska1: '',
			greska2: '',
			greska3: '',
			greska4: '',
			greska5: '',
			dbError: ''
		   
		}
	}, 
	
	template: `
		<div>
		
		<nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#/sprofil">Pocetna</a>
		
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <li class="nav-item">
		        <a class="nav-link" href="#/regklinika">Registruj kliniku</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/odobri_zahtev">Zahtevi za registraciju</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/kreirajzk">Kreiraj zdravstveni karton</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/sifrarnik1">Sifrarnik lekova</a>
		      </li>
		       <li class="nav-item">
		        <a class="nav-link" href="#/sifrarnik2">Sifrarnik dijagnoza</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/">Profil</a>
		      </li>
		       <li class="nav-item">
		        <a class="nav-link" href="#/">Odjavi se</a>
		      </li>
		    </ul>
		    <form class="form-inline my-2 my-lg-0">
		      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
		      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		    </form>
		  </div>
		</nav>
		</br>
		<h3 style="margin:20px"> Registracija klinike: </h3>
		{{dbError}}
		<table class="table table-sm">
		   <tr>
		   
		   		<td>Naziv klinike: </td>
		   		<td><input class="form-control" id="nazivKlinike" type="text" v-model="nazivKlinike"></td>
		   		<td style="color: red">{{greska1}}</td>

		   </tr>
		   <tr>
		   		
		   		<td>Adresa klinike: </td>
		   		<td><input  class="form-control" id="adresaKlinike" type="text" v-model="adresaKlinike"></td>
		   		<td style="color: red">{{greska2}}</td>
		   		
		   </tr>
		   <tr>
		   
		   		<td>Email klinike: </td>
		   		<td><input  class="form-control" id="emailKlinike" type="text" v-model="emailKlinike"></td>
		   		<td style="color: red">{{greska3}}</td>
		   		
		   </tr>
		   <tr>
		   	
		   		<td>Kontakt telefon klinike: </td>
		   		<td><input class="form-control" id="kontaktKlinike" type="text" v-model="kontaktKlinike"></td>
		   		<td style="color: red">{{greska4}}</td>
		  
		   </tr>
		   <tr>
		   
		   		<td>Opis klinike: </td>
		   		<td><input class="form-control"  id="opis" type="text" v-model="opis"></td>
		   		<td style="color: red">{{greska5}}</td>
		   
		   </tr>
		    <tr>
		   
		   		<td><button v-on:click = "nazad()" class="btn btn-success"> Nazad </button></td>
		   		<td><button v-on:click = "napraviKliniku()" class="btn btn-danger"> Napavi kliniku </button></td>	   
		   </tr>
		   
		</v-data-table>
	    
		</div>
	
	`, 
	methods : {

		validacija: function(){
			this.greska1 = '';
			this.greska2 = '';
			this.greska3 = '';
			this.greska4 = '';
			this.greska5 = '';

			if(!this.nazivKlinike)
				this.greska1 = 'Naziv je obavezno polje!';
			if(!this.adresaKlinike)
				this.greska2 = 'Adresa je obavezno polje!';
			if(!this.emailKlinike)
				this.greska3 = 'Email je obavezno polje!';
			if(!this.kontaktKlinike)
				this.greska4 = 'Kontakt klinike je obavezno polje!';
			if(!this.opis)
				this.greska5 = 'Opis je obavezno polje!';
			if(this.nazivKlinike && this.adresaKlinike && this.emailKlinike && this.kontaktKlinike && this.opis){
				return 0;
			}
			return 1;
		},
		napraviKliniku : function(){
			this.dbError = '';
			if(this.validacija()==1)
				return;
			
			var newKlinika ={ "naziv": this.nazivKlinike, "adresa": this.adresaKlinike,
					"opis":this.opis , "emailKlinike":this.emailKlinike,
					"kontaktKlinike": this.kontaktKlinike};
			axios
			.post('api/klinika', newKlinika)
			.then((response)=>{
				this.$router.push('/');
			}).catch((response)=>{
				this.dbError = 'Klinika vec postoji';
			}
				
			);
		},
		nazad: function(){
				return;
		}
	}

});
Vue.component('nadjipacijenta', {

	data: function(){
		return{	
			korisnik:{},
			uloga: '',
			pacijent: {},
			zKarton: {},
			pregled:{},
			pocinjanje:false,
			izvestaj: {opis:'', recept:{}, dijagnoza:{naziv:'', sifra:''}},
			dijagnoze:[],
			lekovi:[],
			odabraniLekovi:[],

		}
	}, 
	
	template: `
	<div>
		<nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  	<a v-if="uloga=='ROLE_LEKAR'" class="navbar-brand" href="#/lekar">Pocetna</a>
			<a v-if="uloga=='ROLE_MED_SESTRA'" class="navbar-brand" href="#/medsestra">Pocetna</a>
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <li class="nav-item active">
		        <a class="nav-link" href="#/pacijenti">Pacijenti</a>
		      </li>
		      
		      <li class="nav-item">
		        <a class="nav-link" href="#/odmor">Zahtev za godisnji odmor/odsustvo</a>
		      </li>
		        <li class="nav-item">
		        <a v-if="uloga=='ROLE_MED_SESTRA'" class="nav-link" href="#/overa">Overa recepata</a>
		      </li>
		      <li class="nav-item">
		        <a  v-if="uloga=='ROLE_MED_SESTRA'" class="nav-link" href="#/kalendarr">Radni kalendar</a>
		        <a  v-if="uloga=='ROLE_LEKAR'" class="nav-link" href="#/kalendarlekar">Radni kalendar</a>
		      </li>
		      <li class="nav-item">
		        <a  v-if="uloga=='ROLE_MED_SESTRA'" class="nav-link" href="#/medsestra">Profil: {{korisnik.ime}} {{korisnik.prezime}}</a>
		        <a v-if="uloga=='ROLE_LEKAR'" class="nav-link" href="#/profil">Profil: {{korisnik.ime}} {{korisnik.prezime}}</a>
		      </li>
		    </ul>
		    <form class="form-inline my-2 my-lg-0">
		      
		      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="odjava()">Odjavi se</button>
		    </form>
		  </div>
		</nav>

		<div class="float-left" style="margin: 20px">		
		<h3> Pacijent {{pacijent.ime}} {{pacijent.prezime}} </h3>
		
		<table class="table table-hover table-light ">		
		   <tr>		   		
		   		<th>Krvna grupa</th>
		   		<th>Visina</td>
		   		<th>Tezina</th>
		   		<th>Dioptrija</th>
		   		<th>Pol</th>
		   		<th>Datum rodjenja</th>
		   		<th></th>
		   </tr>
		  <tbody>
		   <tr>
		   		<td>{{zKarton.krvnaGrupa}}</td>
		   		<td>{{zKarton.visina}}</td>
		   		<td>{{zKarton.tezina}}</td>
		   		<td>{{zKarton.dioptrija}}</td>
		   		<td>{{zKarton.pol}}</td>
		   		<td>{{zKarton.datumRodjenja}}</td>
		   		<td></td>		   		
		   </tr>
		   </tbody>
		    
		</table>
	<button v-if="uloga=='ROLE_LEKAR' && pregled!=null" class="btn btn-light" v-on:click="pocni()"> Zapocni pregled </button>
	<p v-if="pregled==null"> Nema pregleda za zapocinjanje </p>
	</div>
	<div class="float-right" style="margin: 20px" v-if="pocinjanje">
		<h3> Unesi izvestaj </h3>
		
		<table class="table table-hover table-light ">		
		  <tbody>
		   <tr>
		   		<td>Informacije o pregledu</td>
		   		<td><input type="text" class="form-control" v-model="izvestaj.opis"></td>   		
		   </tr>
		    <tr>
		   		<td>Dijagnoza</td>
		   		<td>
		   			<select class="form-control"  v-model="izvestaj.dijagnoza">
						<option v-for="t in dijagnoze" :value="t">{{t.naziv}} {{t.sifra}}</option>
					</select>
		   		</td>   		
		   </tr>
		    <tr>
		   		<td>Dodaj lekove u receptu</td>
		   		<td>
		   			 <div class="dropdown">
						  <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    Odaberi lek
						  </button>
						  <form class="dropdown-menu" aria-labelledby="dropdownMenuButton" >
						    	<label class="dropdown-item" v-for="l in lekovi" name="" value="l.naziv">
						    		<input id="l" name="l.naziv" :value="l" type="checkbox" v-model="odabraniLekovi">{{l.naziv}}
						    	</label>
						  </form>
					</div>
		   		</td>   		
		   </tr>
		   <tr>
		   		<td>
		   			<button class="btn btn-light" v-on:click="noviPO()"> Zakazi novi pregled/operaciju </button>
		   		</td>
		   		<td>
		   			<button class="btn btn-light" v-on:click="zavrsiPregled()"> Zavrsi pregled </button>
		   		</td>   		
		   </tr>
		   </tbody>
		    
		</table>
	</div>	
	</div>
	
	`, methods : {
		odjava : function(){
			localStorage.removeItem("token");
			this.$router.push('/');
		},
		pocni:function(){
			this.pocinjanje = true;
		},
		noviPO:function(){
			//iskcacui prozor za novi pregled ili operaciju
		},
	    zavrsiPregled:function(){
	    	if(!this.izvestaj.opis)
	    		alert("Niste uneli informacije o pregledu!");
	    	else{
	    		this.pocinjanje = false;
		    	this.izvestaj.recept.lek = this.odabraniLekovi;
		    	axios
	           	.post('api/izvestaj/'+ this.pregled.id, this.izvestaj)
	           	.then(response => {
	           		alert("Uspesno je zavrsen izvestaj");
	           	});
		    	this.izvestaj= {opis:'', recept:{}, dijagnoza:{naziv:'', sifra:''}};
		    	this.pregled= null;
	    	}
	    	
	    	
	    }
	},
	
	mounted(){
		
	this.token = localStorage.getItem("token");
	axios
	.get('/auth/dobaviUlogovanog', { headers: { Authorization: 'Bearer ' + this.token }} )
    .then(response => { this.korisnik = response.data;
	    axios
		.put('/auth/dobaviulogu', this.korisnik, { headers: { Authorization: 'Bearer ' + this.token }} )
	    .then(response => {
	    	this.uloga = response.data;
	    	if (this.uloga != "ROLE_LEKAR" && this.uloga != "ROLE_MED_SESTRA") {
	    		this.$router.push('/');
	    	}else{
	    	
	    		axios
	           	.get('api/pacijent/'+this.$route.params.lbo)
	           	.then(response => {
	           			this.pacijent = response.data; 
	           			this.zKarton = this.pacijent.zKarton;
	           			axios
	    	           	.get('api/pregled/'+this.pacijent.id+'/'+ this.korisnik.id)
	    	           	.then(response => {
	    	           			this.pregled = response.data; 
	    	           	}).catch((response)=>
	    	           			{this.pregled = null; 
	    	           			console.log("Pregled ne postoji");}
	    	           	);
	           	});
	    		axios
	           	.get('api/dijagnoze/all')
	           	.then(response => {
	           		this.dijagnoze = response.data; 
	           	});
	    		 axios
	    	     	.get('api/lekovi/all')
	    	     	.then(response => (this.lekovi = response.data));
	    	
	    	}
	    })
	    .catch(function (error) { console.log(error);});
	    
    })
    .catch(function (error) { router.push('/') });	 
}

});
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
			showModal: false,
			noviTermin: {},
			datumVremeGreska: '',
			tipTerminaGreska: '',
			trajanjeGreska: '',
			tipTermina: ''
			

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
		   			<button class="btn btn-light" id="show-modal" @click="showModal = true">Zakazi novi pregled/operaciju</button>
						<modal v-if="showModal" @close="showModal = false">
        
        					<h3 slot="header">Novi termin</h3>
        					<table slot="body" >
								<tbody>
									<tr>
										<td>Novi termin za: </td>
										<td>
											<select class="form-control" id="selectSala" v-model="tipTermina">
												<option value="pregled">Pregled</option>
												<option value="operacija">Operacija</option>
											</select>
											<p style="color: red">{{tipTerminaGreska}}</p>
										</td>
										
										
									</tr>	
									<tr>			   
								   		<td>Datum i vreme: </td>
								   		<td><input class="form-control" id="datumvreme" type="datetime-local" v-model="noviTermin.datumVreme">
								   			<p style="color: red">{{datumVremeGreska}}</p>
								   		</td>
								   		
			   						</tr>
									<tr>			   
								   		<td>Trajanje: </td>
								   		<td><input class="form-control" id="trajanje" type="number" v-model="noviTermin.trajanje">
								   			<p style="color: red">{{trajanjeGreska}}</p>
								   		</td>
								   		
			   						</tr>
									
									
								</tbody>
								</table>
        					
        					<div slot="footer">
        						<button style="margin:5px;" class="btn btn-success" v-on:click="noviPO()"> Sacuvaj </button>       						
								<button style="margin:5px;" class="btn btn-secondary" @click="showModal=false" v-on:click="isprazniPolja()"> Nazad </button>								
							</div>
						</modal>
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
		validacija: function(){
			this.datumVremeGreska = '';
			this.trajanjeGreska = '';
			
			if(!this.tipTermina)
				this.tipTerminaGreska = 'Tip termina je obavezno polje!';
			if(!this.noviTermin.trajanje)
				this.trajanjeGreska = 'Trajanje je obavezno polje!';
			if(!this.noviTermin.datumVreme)
				this.datumVremeGreska = 'Datum i vreme je obavezno polje!';


			if(this.tipTermina && this.noviTermin.trajanje && this.noviTermin.datumVreme){
				return 0;
			}
			return 1;
			
		},
		noviPO:function(){
			if(this.validacija()==1)
				return;
			this.showModal = false;
			this.noviTermin.lekar = this.korisnik;
			this.noviTermin.pacijent = this.pacijent;

			if(this.tipTermina == 'pregled'){
				axios
				.post('api/pregled/lekarzahtev', this.noviTermin)
				.then((res)=>{
					alert('Uspesno poslat zahtev za pregled!');
				}).catch((res)=>{
					this.error = 'Greska pri dodavanju';
				});
				
			}else if(this.tipTermina == 'operacija'){
				this.noviTermin.lekar = [];
				this.noviTermin.lekar.push(this.korisnik);
				axios
				.post('api/operacije/lekarzahtev', this.noviTermin)
				.then((res)=>{
					alert('Uspesno poslat zahtev za operaciju!');
				}).catch((res)=>{
					this.error = 'Greska pri dodavanju';
				});				
			}
			this.isprazniPolja();
			
			
			
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
	    },
	    isprazniPolja: function(){
	    	this.noviTermin= {};
			this.datumVremeGreska= '';
			this.tipTerminaGreska= '';
			this.trajanjeGreska= '';
			this.tipTermina= '';
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
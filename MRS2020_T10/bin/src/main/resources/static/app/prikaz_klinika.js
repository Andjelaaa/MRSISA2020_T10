Vue.component('klinike-prikaz', {
	data: function(){
		return{
			klinike: null,
			idPacijenta: 1,
			tipPregleda: {naziv: null},
			datum: null,
			tipoviPregleda: null,
			greskaDatum: '',
			greskaTipPregleda: ''
		}
	},

	template: `
	<div>
	<nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#/pacijent">Pocetna</a>
		
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <li class="nav-item">
		        <a class="nav-link" href="#/klinike">Klinike</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/pacijentpregledi">Pregledi/Operacije</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/">Zdravstveni karton</a>
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
		<p class="leva">Sve klinike</p>
		<p class="desna">Pretraga termina</p>
		<div class="float-left">
		<table class="table table-hover table-light">
			<tr>
			<th>Naziv klinike</th>
			<th>Adersa</th>
			<th>Prosecna ocena</th>
			<th>Kontakt</th>
			<th></th>
			</tr>
			
			<tr v-for="(k, index) in klinike">
				<td>{{k.naziv}}</td>
				<td>{{k.adresa}}</td>
				<td>{{k.prosecnaOcena}}</td>
				<td>{{k.kontaktKlinike}}</td>
				<td><button v-on:click = "detalji(k.id)" class="btn btn-light" >Detalji</button></td>
			</tr>
		</table>
		</div>
		<div class="float-right" style="width:45%">
		<table class="table table-hover table-light">
			<tr>
				<td>Pretrazi preglede od: </td>
				<td><input class="form-control"  id="datum" type="date" v-model="datum"></td>
				<td>{{this.greskaDatum}}</td>
			</tr>
			
			<tr>
				<td>Pretrazi po tipu pregleda</td>
				<td>
					<select id="selectTP" class="form-control" v-model="tipPregleda.naziv">
						<option v-for="t in tipoviPregleda" :value="t.naziv">{{t.naziv}}</option>
					</select>
				</td>
				<td>{{this.greskaTipPregleda}}</td>
				
			</tr>
			<tr>
				<td></td>
				<td><button v-on:click = "pretragaTermina()" class="btn btn-light">Pretrazi</button></td>
			</tr>
		</table>
		</div>
		</div>
	`, 
	
	methods : {
		
		validacija: function(){
			this.greskaDatum = '';
			this.greskaTipPregleda = '';
			
			if(!this.datum)
				this.greskaDatum = 'Datum je obavezno polje!';

			if(!this.tipPregleda)
				this.greskaTipPregleda = 'Tip pregleda je obavezno polje!';
			if(this.datum && this.greskaTipPregleda){
				return 0;
			}
			return 1;

		},
		pretragaTermina: function(){
			if(this.validacija() == 1){
				return;
			}
			this.greskaDatum = '';
			this.greskaTipPregleda = '';
			// da se desi pretraga po datumu i tipu pregleda
			
		},
		
		detalji: function(klinikaId){
			if(this.datum && this.greskaTipPregleda){
				// onda detalje za zakazivanje
			}
			else{
				// samo detalji za kliniku
				console.log(klinikaId);
				this.$router.push('/detaljiKlinike/'+klinikaId);
			}
		}
		
	},
	
	mounted () {
		axios
		.get('api/klinika/all')
		.then(res => {
			this.klinike = res.data;
		})
		axios
          .get('api/tippregleda/all')
          .then(res => {
        	  this.tipoviPregleda = res.data;
          })
	},

});
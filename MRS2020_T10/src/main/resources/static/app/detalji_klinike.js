Vue.component('klinika-detalji', {
	data: function(){
		return{
			klinika: {naziv: '', adresa: '', prosecnaOcena: 0, kontaktKlinike: '000/000'},
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
		
		<p class="desna"><br>Pretraga termina</p>
		<div class="float-left" style="margin:20px">
		<td><button v-on:click = "brzoZakazivanje()" class="btn btn-success">Brzo zakazivanje</button></td>
		<br>
		<h3>Detalji klinike</h3>
		<br>

		<table  class="table table-hover table-light ">
			<tr>
			<th>Naziv klinike</th>
			<td>{{klinika.naziv}}</td>
			</tr>
			<tr>
			<th>Adersa</th>
			<td>{{klinika.adresa}}</td>
			</tr>
			<tr>
			<th>Prosecna ocena</th>
			<td>{{klinika.prosecnaOcena}}</td>
			</tr>
			<tr>
			<th>Broj ocenjivanja</th>
			<td>{{klinika.brojOcena}}</td>
			</tr>
			<tr>
			<th>Kontakt</th>
			<td>{{klinika.kontaktKlinike}}</td>
			</tr>
			<th>Email</th>
			<td>{{klinika.emailKlinike}}</td>
			
		</table>
		</div>
		</div>
	`, 
	
	methods : {
		brzoZakazivanje: function(klinikaId)
		{
			this.$router.push('/predefinisanipregledi/'+ this.$route.params.name)
		},
		
		
	},
	
	mounted () {
		axios
		.get('api/klinika/detalji/'+ this.$route.params.name)
		.then(res => {
			this.klinika = res.data;
		})
		
	},

});
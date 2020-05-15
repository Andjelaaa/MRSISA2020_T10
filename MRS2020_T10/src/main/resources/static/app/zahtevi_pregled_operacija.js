Vue.component('zahtevipo', {

	data: function(){
		return{	
			admin:{},
			klinika: {},
			pregledi: []
		}
	}, 
	
	template: `
	
		<div>
		<nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#/admin">Pocetna</a>
		
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <li class="nav-item">
		        <a class="nav-link" href="#/lekari">Lekari</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/sale">Sale</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/tipovipregleda">Tipovi pregleda</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/dpregled">Novi termin za pregled</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="#/zahtevipo">Zahtevi za pregled/operaciju</a>
		      </li>
		    </ul>
		    <form class="form-inline my-2 my-lg-0">
		      <!--input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"-->
		      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="odjava()">Odjavi se</button>
		    </form>
		  </div>
		</nav>
		</br>
		<div class="float-left" style="margin-left: 20px">
			<h3> Zahtevi za preglede </h3>
		<table class="table table-hover table-light">
			<thead>
				<th>Pacijent</th>
				<th>Lekar</th>
				<th>Datum i vreme</th>
				<th>Tip pregleda</th>
				<th>Sala</th>
				<th></th>
			
			</thead>
			<tbody>
			
			   <tr v-for="pregled in pregledi">			   		
			   		<td>{{pregled.pacijent.ime}} {{pregled.pacijent.prezime}}</td>
			   		<td>{{pregled.lekar.ime}} {{pregled.lekar.prezime}}</td>
			   		<td>{{pregled.datumVreme | formatDate}}</td>
			   		<td>{{pregled.tipPregleda.naziv}}</td>
			   		<td v-if="pregled.sala == null"><button v-on:click="nadji(pregled)">Nadji salu</button></td>
			   		<td v-else>pregled.sala</td>
			   		<td v-if="pregled.sala == null"></td>
			   		<td v-else><button>Posalji</button></td>

			   </tr>
			   
		   </tbody>
		</table>
		</div>

	</div>
	
	`, 
	methods : {
		odjava : function(){
				localStorage.removeItem("token");
				this.$router.push('/');
		},
		nadji: function(pregled){
			this.$router.push('/zakazisalu/'+pregled.id.toString());
			
		}
		
	},
	mounted(){
		
		this.token = localStorage.getItem("token");
		axios
		.get('/auth/dobaviUlogovanog', { headers: { Authorization: 'Bearer ' + this.token }} )
        .then(response => { this.admin = response.data; 
	        axios
			.put('/auth/dobaviulogu', this.admin, { headers: { Authorization: 'Bearer ' + this.token }} )
		    .then(response => {
		    	this.uloga = response.data;
		    	if (this.uloga != "ROLE_ADMIN_KLINIKE") {
		    		this.$router.push('/');
		    	}else{
		    		axios
			      	.get('api/pregled/zahtevi')
			      	.then(response => {
			      		this.pregledi = response.data;			      		
			      	})
			        .catch(function (error) { console.log('Greska') });		    		
		    	}
		    })
		    .catch(function (error) { console.log(error); });
   
        })
        .catch(function (error) { router.push('/'); });
		
	}		      		
	 

});
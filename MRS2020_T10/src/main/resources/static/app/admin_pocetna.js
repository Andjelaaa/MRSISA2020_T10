Vue.component('admin', {

	data: function(){
		return{	
			admin:{},
			klinika: {}
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
		    </ul>
		    <form class="form-inline my-2 my-lg-0">
		      <!--input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"-->
		      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="odjava()">Odjavi se</button>
		    </form>
		  </div>
		</nav>
		</br>
		<div class="float-left" style="margin: 20px">
			<h3> Klinika: {{klinika.naziv}} </h3>
		<table class="table">
			<tbody>
			
			   <tr>
			   		
			   		<td>Opis: </td>
			   		<td>{{klinika.opis}}</td>
			   </tr>
			   <tr>
			   
			   		<td>Adresa: </td>
			   		<td>{{klinika.adresa}}</td>	
			   </tr>

			   <tr>
			   		
			   		<td>Email: </td>
			   		<td>{{klinika.emailKlinike}}</td>
			   </tr>
			   <tr>
			   		
			   		<td>Kontakt: </td>
			   		<td>{{klinika.kontaktKlinike}}</td>
			   </tr>
			   <tr>
			   		
			   		<td>Prosecna ocena: </td>
			   		<td>{{klinika.prosecnaOcena}}</td>
			   </tr>
			    <tr>
			   
			   		<td></td>
			   		<td><button v-on:click="izmeni()" class="btn btn-light float-right">Izmeni</button></td>
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
		}
		
	},
	mounted(){
		
		this.token = localStorage.getItem("token");
		axios
		.get('/auth/dobaviUlogovanog', { headers: { Authorization: 'Bearer ' + this.token }} )
        .then(response => { this.admin = response.data; 
	        axios
	      	.get('api/admini/klinika/'+this.admin.email )
	      	.then(response => (this.klinika = response.data))
	        .catch(function (error) { this.$router.push('/'); });
        })
        .catch(function (error) { this.$router.push('/'); });
	
	
		 
	}

});
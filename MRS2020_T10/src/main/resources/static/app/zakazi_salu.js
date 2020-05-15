Vue.component('zakazisalu', {

	data: function(){
		return{	
			admin:{},
			klinika: {},
			pregled: {},
			sale: [],
			zauzeca: [] 
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
		      <li class="nav-item">
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
			<h3> Slobodne sale </h3>
		<table class="table table-hover table-light">
			<thead>
				<th>Naziv</th>
				<th>Broj</th>
				<th>Zauzece</th>
				<th></th>
			
			</thead>
			<tbody>
			
			   <tr v-for="s, i in sale">			   		
			   		<td>{{s.naziv}}</td>
			   		<td>{{s.broj}}</td>
			   		<td><p  v-for="z in zauzeca[i]">{{z.pocetak | formatDate}} - {{z.kraj | formatDate}}</p></td>
			   		<td><button>Posalji</button></td>

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
	created(){
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
			      	.get('api/sala/all')
			      	.then(response => {
			      		this.sale = response.data;
			      		for(var s of this.sale){
			      			console.log(s.id);
			      			axios
					      	.get('api/sala/zauzece/'+this.$route.params.id+'/'+s.id)
					      	.then(response => {
					      		console.log(response.data);
					      		this.zauzeca.push(response.data);
					      	})
					        .catch(function (error) { console.log('Greska11') });	
			      			
			      		}
			      	})
			        .catch(function (error) { console.log('Greska22') });		    		
		    	}
		    })
		    .catch(function (error) { console.log(error); });
   
        })
        .catch(function (error) { router.push('/'); });
		
	}		      		
	 

});
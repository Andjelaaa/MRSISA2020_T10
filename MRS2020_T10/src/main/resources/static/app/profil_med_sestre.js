Vue.component('medsestra', {

	data: function(){
		return{	
			medicinska_sestra:{},
			uloga: '',
			token: ''
		}
	}, 
	
	template: `
	<div>
		<nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#/med_sestra_pocetna">Pocetna</a>
		
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <li class="nav-item">
		        <a class="nav-link" href="#/pacijenti">Pacijenti</a>
		      </li>
		      
		      <li class="nav-item">
		        <a class="nav-link" href="#/odmor">Zahtev za godisnji odmor/odsustvo</a>
		      </li>
		       <li class="nav-item">
		        <a class="nav-link" href="#/overa">Overa recepata</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/kalendarr">Radni kalendar</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/medsestra">Profil: {{medicinska_sestra.ime}} {{medicinska_sestra.prezime}}</a>
		      </li>
		    </ul>
		    <form class="form-inline my-2 my-lg-0">
		      
		      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="odjava()">Odjavi se</button>
		    </form>
		  </div>
		</nav>
		</br>
		</table>
		<div class="float-left" style="margin: 20px">
		<table class="table">
			<tbody>
			   <tr>		   		
			   		<td>Ime</td>
			   		<td>{{medicinska_sestra.ime}}</td>
			   			<td><button v-on:click = "izmeni(medicinska_sestra.ime)" class="btn btn-light">Izmeni</button></td>	 
			   </tr>
			  
			   <tr>
			   		<td>Prezime</td>
			   		<td>{{medicinska_sestra.prezime}}</td>
			   			<td><button v-on:click = "izmeni(medicinska_sestra.prezime)" class="btn btn-light">Izmeni</button></td>	 
			   </tr>
			    <tr>
			   		<td>Email</td>
			   		<td>{{medicinska_sestra.email}}</td>	  
			   			<td><button v-on:click = "izmeni(medicinska_sestra.email)" class="btn btn-light">Izmeni</button></td>	  
			   	</tr>
			   	<tr>
			   		<td>Adresa</td>
			   		<td>{{medicinska_sestra.adresa}}</td>
			   			<td><button v-on:click = "izmeni(medicinska_sestra.adresa)" class="btn btn-light">Izmeni</button></td>	 	   
			   	</tr>
			   	<tr>
			   		<td>Grad</td>
			   		<td>{{medicinska_sestra.grad}}</td>	   
			   			<td><button v-on:click = "izmeni(medicinska_sestra.grad)" class="btn btn-light">Izmeni</button></td>	 
			   	</tr>
				<tr>
			   		<td>Drzava</td>
			   		<td>{{medicinska_sestra.drzava}}</td>	  
			   			<td><button v-on:click = "izmeni(medicinska_sestra.drzava)" class="btn btn-light">Izmeni</button></td>	  
			   	</tr>
			   	<tr>
			   		<td>Kontakt</td>
			   		<td>{{medicinska_sestra.kontakt}}</td>
			   		<td><button v-on:click = "izmeni(medicinska_sestra.kontakt)" class="btn btn-light">Izmeni</button></td>	 	   
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
		izmeni:function(i){
			alert("Not implemented yet");
		}
		
	},
	mounted(){
		
		this.token = localStorage.getItem("token");
		axios
		.get('/auth/dobaviUlogovanog', { headers: { Authorization: 'Bearer ' + this.token }} )
	    .then(response => { this.medicinska_sestra = response.data;
		    axios
			.put('/auth/dobaviulogu', this.medicinska_sestra, { headers: { Authorization: 'Bearer ' + this.token }} )
		    .then(response => {
		    	this.uloga = response.data;
		    	if (this.uloga != "ROLE_MED_SESTRA") {
		    		this.$router.push('/');
		    	}
		    })
		    .catch(function (error) { console.log(error);});
		    
	    })
	    .catch(function (error) { this.$router.push('/'); });	 
	}
	
});
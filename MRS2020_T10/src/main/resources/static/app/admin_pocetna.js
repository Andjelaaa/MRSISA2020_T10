Vue.component('admin', {

	data: function(){
		return{	
			admin:{},
			klinika: {},
			showModal:false,
			selected:{},
			selectedBackup:{}
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
			   		<td>
			   		<button class="btn btn-light" id="show-modal" @click="showModal = true" v-on:click="select()">Izmeni</button>
						<modal v-if="showModal" @close="showModal = false">
        
        					<h3 slot="header">Izmena sale</h3>
        					<table slot="body" >
								<tbody>
										
									<tr>
										<td>Naziv:</td>
										<td><input class="form-control" type="text"  v-model="selected.naziv"/></td>
									</tr>
									<tr>
										<td>Opis:</td>
										<td><input  class="form-control" type="text" v-model = "selected.opis"/></td>
									</tr>
									<tr>
										<td>Adresa:</td>
										<td><input  class="form-control" type="text" v-model = "selected.adresa"/></td>
									</tr>
									<tr>
										<td>Kontakt:</td>
										<td><input  class="form-control" type="text" v-model = "selected.kontaktKlinike"/></td>
									</tr>
									<tr>
										<td>Email:</td>
										<td><input  class="form-control" type="text" v-model = "selected.emailKlinike"/></td>
									</tr>
									
								</tbody>
								</table>
        					
        					<div slot="footer">
        						<button @click="showModal=false" style="margin:5px;" class="btn btn-success" v-on:click="sacuvaj()"> Sacuvaj </button>       						
								<button style="margin:5px;" class="btn btn-secondary" @click="showModal=false" v-on:click="restore()"> Nazad </button>								
							</div>
						</modal>
						</td>
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
		select : function(s){
			this.selectedBackup.naziv = this.klinika.naziv;
			this.selectedBackup.opis = this.klinika.opis;
			this.selectedBackup.adresa = this.klinika.adresa;
			this.selectedBackup.kontaktKlinike = this.klinika.kontaktKlinike;
			this.selectedBackup.emailKlinike = this.klinika.emailKlinike;
			this.selected = this.klinika;

		},
		restore: function(){
			this.klinika.naziv = this.selectedBackup.naziv;
			this.klinika.opis = this.selectedBackup.opis;
			this.klinika.adresa = this.selectedBackup.adresa;
			this.klinika.kontaktKlinike = this.selectedBackup.kontaktKlinike;
			this.klinika.emailKlinike = this.selectedBackup.emailKlinike;
		},
		sacuvaj: function(){
			if(this.klinika.naziv != '' && this.klinika.opis != '' && this.klinika.adresa != '' && this.klinika.kontaktKlinike != '' && this.klinika.emailKlinike!= ''){
				axios
				.put('api/klinika/'+this.klinika.id, this.klinika)
				.then((res)=>{
					console.log('Uspesna izmena');
				}).catch((res)=>{
					console.log('Neuspesna izmena');
				});
			}else{
				this.restore();
			}
			
		},
		
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
			      	.get('api/admini/klinika/'+this.admin.email )
			      	.then(response => (this.klinika = response.data))
			        .catch(function (error) { this.$router.push('/'); });		    		
		    	}
		    })
		    .catch(function (error) { console.log(error); });
   
        })
        .catch(function (error) { router.push('/'); });
	
	
		 
	}

});
Vue.component('sale', {
	data: function(){
		return{
			sale: null,
			pretraga: '',
			selected: {naziv:'', broj: 0},
			selectedBackup: {naziv:'', broj: 0},
			showModal: false,
			
			sala: {naziv:''},
			nazivGreska: '',
			brojGreska: '',
			klinikaGreska: '',
			error: ''

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
		      <li class="nav-item active">
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
		      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
		      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		    </form>
		  </div>
		</nav>
		</br>
		

		<div class="float-left">
		Naziv: <input type="text" id="search" v-model="pretraga" >
		<button v-on:click = "pretrazi()" class="btn btn-light">Pretrazi</button>
		<table class="table table-hover table-light">
		   <tr>		   		
		   		<th>Naziv</th>
		   		<th>Broj</th>
		   </tr>
		  <tbody>
		   <tr v-for="s in sale">
		   		<td>{{s.naziv}}</td>
		   		<td>{{s.broj}}</td>
		   		<td>
					<button class="btn btn-light" id="show-modal" @click="showModal = true" v-on:click="select(s)">Izmeni</button>
						<modal v-if="showModal" @close="showModal = false">
        
        					<h3 slot="header">Izmena sale</h3>
        					<table slot="body" >
								<tbody>
										
									<tr>
										<td>Naziv:</td>
										<td><input class="form-control" type="text"  v-model="selected.naziv"/></td>
									</tr>
									<tr>
										<td>Broj:</td>
										<td><input  class="form-control" type="number" v-model = "selected.broj"/></td>
									</tr>
									
								</tbody>
								</table>
        					
        					<div slot="footer">
        						<button @click="showModal=false" style="margin:5px;" class="btn btn-success" v-on:click="sacuvaj(selected)"> Sacuvaj </button>       						
								<button style="margin:5px;" class="btn btn-secondary" @click="showModal=false" v-on:click="restore(selected)"> Nazad </button>								
							</div>
						</modal>
				</td>
				<td><button class="btn btn-light" v-on:click="obrisi(s)">Obrisi</button></td>
		   </tr>
		   </tbody>
		    
		</table>
		
		</div>
		<br>
		
		<div class="float-right" style="width:75%">
		<h3> Nova sala </h3>
		<p>{{error}}</p>
		<table>
			<tbody>
			   <tr>
			   
			   		<td>Broj sale: </td>
			   		<td><input class="form-control" id="broj" type="number" v-model="sala.broj"></td>
			   		<td style="color: red">{{brojGreska}}</td>
	
			   </tr>

			   <tr>
			   		
			   		<td>Naziv sale: </td>
			   		<td><input class="form-control" id="opis" type="text" v-model="sala.naziv"></td>
			   		<td style="color: red">{{nazivGreska}}</td>
			   </tr>
		   
			    <tr>
			   
			   		<td></td>
			   		<td><button v-on:click="dodaj()" class="btn btn-success float-right">Dodaj</button></td>
			   </tr>
		   </tbody>
		</table>
	
		</div>

		</div>
	
	`, 
	methods : {
		nazad : function(){
			this.$router.push('/admin');
			return;
		},
		pretrazi: function(){
			console.log(this.pretraga);
			if(this.pretraga){
				axios
		       	.get('api/sala/search/'+ this.pretraga)
		       	.then(response => (this.sale = response.data));
				
			}
			
			
		},
		select : function(s){
			this.selectedBackup.naziv = s.naziv;
			this.selectedBackup.broj = s.broj;
			this.selected = s;

		},
		restore: function(s){
			s.naziv = this.selectedBackup.naziv;
			s.broj = this.selectedBackup.broj;
		},
		obrisi: function(s){
			console.log(s.id);
			axios
			.delete('api/sala/'+s.id)
			.then((res)=>{
				console.log('uspesno');
				 axios
			       	.get('api/sala/all')
			       	.then(response => (this.sale = response.data));
			}).catch((res)=>{
				console.log('Neuspesno brisanje');
			});
			
		},
		sacuvaj: function(s){
			axios
			.put('api/sala/'+s.id, s)
			.then((res)=>{
				console.log('Uspesna izmena');
			}).catch((res)=>{
				console.log('Neuspesna izmena');
			});
			
		},
		validacija: function(){
			this.nazivGreska = '';
			this.brojGreska = '';
			
			if(!this.sala.naziv)
				this.nazivGreska = 'Naziv je obavezno polje!';

			if(!this.sala.broj)
				this.brojGreska = 'Broj je obavezno polje!';
			if(this.sala.naziv && this.sala.broj){
				return 0;
			}
			return 1;
			
		},
		dodaj : function(){	
			this.error = '';
			if(this.validacija()==1)
				return;
			
			axios
			.post('api/sala', this.sala)
			.then((res)=>{
				console.log('uspesno');
				axios
		       	.get('api/sala/all')
		       	.then(response => (this.sale = response.data));
			}).catch((res)=>{
				this.error = 'Vec postoji sala sa istim brojem';
			}
				
			)
		}
		
	},
	mounted(){
		 axios
       	.get('api/sala/all')
       	.then(response => (this.sale = response.data));
	}

});
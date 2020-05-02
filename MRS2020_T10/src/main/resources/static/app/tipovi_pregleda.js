Vue.component('tipovipregleda', {
	data: function(){
		return{
			tipoviPregleda: null,
			pretraga: '',
			selected: {naziv:'', opis: ''},
			selectedBackup: {naziv:'', opis: ''},
			showModal: false,
			
			tipPregleda: {naziv:'', opis:'', brojAktvnih:0},
			nazivGreska: '',
			opisGreska: '',
			error: ''

		}
	}, 
	
	template: `
		<div>		
		<h1>Tipovi pregleda</h1>
		<div class="float-left d-block p-2">		
		
		Pretraga: <input type="text" id="search" v-model="pretraga">
		<button v-on:click = "pretrazi()" class="btn btn-light">Pretrazi</button>
		<table class="table table-hover table-light">
		
		   <tr>		   		
		   		<th>Naziv</th>
		   		<th>Opis</th>
		   </tr>
		  <tbody>
		   <tr  v-for="t in tipoviPregleda">
		   		<td>{{t.naziv}}</td>
		   		<td>{{t.opis}}</td>
		   		<td>
					<button class="btn btn-light" id="show-modal" @click="showModal = true" v-on:click="select(t)">Izmeni</button>
						<modal v-if="showModal" @close="showModal = false">
        
        					<h3 slot="header">Izmena tipa pregleda</h3>
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
									
								</tbody>
								</table>
        					
        					<div slot="footer">
        						<button @click="showModal=false" style="margin:5px;" class="btn btn-success" v-on:click="sacuvaj(selected)"> Sacuvaj </button>       						
								<button style="margin:5px;" class="btn btn-secondary" @click="showModal=false" v-on:click="restore(selected)"> Nazad </button>								
							</div>
						</modal>
				</td>
				
				<td><button class="btn btn-light" v-on:click="obrisi(t)">Obrisi</button></td>
		   </tr>
		   </tbody>
		</table>
		<button v-on:click = "nazad()" class="btn btn-light">Nazad</button>
		</div>
		<br>
		<div class="d-block p-2">
		<h3> Novi tipa pregleda </h3>
		<p>{{error}}</p>
		
		<table>
			<tbody>
			   <tr>
			   
			   		<td>Naziv tipa pregleda: </td>
			   		<td><input class="form-control" id="naziv" type="text" v-model="tipPregleda.naziv"></td>
			   		<td style="color: red">{{nazivGreska}}</td>
	
			   </tr>

			   <tr>
			   		
			   		<td>Opis tipa pregleda: </td>
			   		<td><input class="form-control" id="opis" type="text" v-model="tipPregleda.opis"></td>
			   		<td style="color: red">{{opisGreska}}</td>
			   </tr>		   
			    <tr>
			   
			   		<td><button v-on:click="dodaj()" class="btn btn-light">Dodaj</button></td>
			   		<td></td>
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
		       	.get('api/tippregleda/search/'+ this.pretraga)
		       	.then(response => (this.tipoviPregleda = response.data));	
			}	
		},
		select : function(s){
			this.selectedBackup.naziv = s.naziv;
			this.selectedBackup.opis = s.opis;
			this.selected = s;

		},
		restore: function(s){
			s.naziv = this.selectedBackup.naziv;
			s.opis = this.selectedBackup.opis;
		},
		obrisi: function(s){
			axios
			.delete('api/tippregleda/'+s.id)
			.then((res)=>{
				console.log('uspesno');
				 axios
			       	.get('api/tippregleda/all')
			       	.then(response => (this.tipoviPregleda = response.data));
			}).catch((res)=>{
				console.log('Neuspesno brisanje');
			});
			
		},
		sacuvaj: function(s){
			axios
			.put('api/tippregleda/'+s.id, s)
			.then((res)=>{
				console.log('Uspesna izmena');
			}).catch((res)=>{
				console.log('Neuspesna izmena');
			});
			
		},
		validacija: function(){
			this.nazivGreska = '';
			this.opisGreska = '';
			
			if(!this.tipPregleda.naziv)
				this.nazivGreska = 'Naziv je obavezno polje!';

			if(!this.tipPregleda.opis)
				this.opisGreska = 'Opis je obavezno polje!';
			if(this.tipPregleda.naziv && this.tipPregleda.opis){
				return 0;
			}
			return 1;
			
		},
		dodaj : function(){	
			this.error = '';
			if(this.validacija()==1)
				return;
			
			axios
			.post('api/tippregleda', this.tipPregleda)
			.then((res)=>{
				console.log('uspesno');
				axios
		       	.get('api/tippregleda/all')
		       	.then(response => (this.tipoviPregleda = response.data));
			}).catch((res)=>{
				this.error = 'Vec postoji pregled sa istim imenom';
			}
				
			)
		}
		
	},
	mounted(){
		 axios
       	.get('api/tippregleda/all')
       	.then(response => (this.tipoviPregleda = response.data));
	}

});
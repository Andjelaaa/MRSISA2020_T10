Vue.component('sifrarnik2', {
	data: function(){
		return{
			dijagnoze:{},
			naziv:'',
			sifra:'',
			greska:'',
			greska1:'',
			greska2:'',
			showModal: false,
			selected: {naziv:'', sifra:''},
			selectedBackup: {naziv:'', sifra:''}
		}
	}, 
	
	template: `
		<div>
		<p class="leva">Sifrarnik sijagnoza </p>
		<p class="desna">Unesite novu dijagnozu</p>
		<table id="leva" class="table table-bordered " >
		   <tr>		   		
		   		<th>Naziv</th>
		   		<th>Sifra</th>
		   		<th></th>
		   </tr>
		  
		   <tr  v-for="l in dijagnoze">
		   		<td>{{l.naziv}}</td>
		   		<td>{{l.sifra}}</td>
		   		<td>		   		
			   		<button class="btn btn-light" id="show-modal" @click="showModal = true" v-on:click="select(l)">Izmeni</button>
						<modal v-if="showModal" @close="showModal = false">
	    
	    					<h3 slot="header">Izmena leka</h3>
	    					<table slot="body" class="table table-hover table-light">
								<tbody>
										
									<tr>
										<td>Naziv</td>
										<td><input class="form-control" type="text"  v-model="selected.naziv"/></td>
									</tr>
									<tr>
										<td>Sifra</td>
										<td><input  class="form-control" type="text" v-model = "selected.sifra"/></td>
									</tr>									
								</tbody>
								</table>
	    					
	    					<div slot="footer">
	    						<button @click="showModal=false" style="margin:5px;" class="btn btn-success" v-on:click="save()"> Save </button>       						
								<button style="margin:5px;" class="btn btn-secondary" @click="showModal=false" v-on:click="restore(selected)"> Cancel </button>								
							</div>
						</modal>
				</td>
		   </tr>
		    <tr>
		   		<td></td>
		   		<td><button v-on:click = "nazad()">Nazad</button></td>
		   		
		   </tr>
		   </table>
		   <table id="desna" class="table table-bordered">

		   <tr>		   		
		   		<td>Naziv</td>
		   		<td><input id="naziv" type="text" v-model="naziv"></td>
		   		<td style="color: red">{{greska1}}</td>
		   </tr>
		  
		   <tr>
		   		<td>Sifra</td>
		   		<td><input id="sifra" type="text" v-model="sifra"></td>
		   		<td style="color: red">{{greska2}}</td>
		   </tr>
		    <tr>
		   		<td></td>
		   		<td><button v-on:click = "napraviDijagnozu()">Dodaj dijagnozu</button></td>	   
		   		
		   </tr>
		   
		  </table>
			{{greska}}
		</div>
	
	`, 
	methods : {
		nazad : function(){
			this.$router.push('/sprofil');
			return;
		},
		validacija : function(){
			this.greska1 = '';
			this.greska2 = '';
			
			if(!this.naziv)
				this.greska1 = 'Naziv je obavezno polje!';

			if(!this.sifra)
				this.greska2 = 'Sifra je obavezno polje!';
			if(this.naziv && this.sifra){
				return 0;
			}
			return 1;
			
			
		},
		napraviDijagnozu: function(){
			this.greska = '';
			if(this.validacija()==1)
				return;
			this.greska1 = '';
			this.greska2 = '';
			var newDijagn ={ "naziv": this.naziv, "sifra": this.sifra};
			axios
			.post('api/dijagnoze', newDijagn)
			.then((response)=>{
				this.dijagnoze.push(newDijagn);
				this.naziv ='';
				this.sifra='';
				this.greska='';
			}).catch((response)=>{
				this.greska = 'Dijagnoza vec postoji';
			});
		},
		select : function(l){
			this.selectedBackup.naziv = l.naziv;
			this.selectedBackup.sifra = l.sifra;
			this.selected = l;

		},
		restore: function(l){
			l.naziv = this.selectedBackup.naziv;
			l.sifra = this.selectedBackup.sifra;
		},
		save: function(){
			axios
			.post('api/dijagnoze/izmena', {dijagnoza:this.selected, naziv:this.selectedBackup.naziv})
			.then((response)=>{
				 this.naziv ='';
				 this.sifra='';
				 this.greska = '';
			}).catch((response)=>{
				 this.naziv ='';
				 this.sifra='';
				 this.selected.naziv = this.selectedBackup.naziv;
				 this.selected.sifra = this.selectedBackup.sifra;
				 this.greska = 'Dijagnoza vec postoji';
			});
		}
		
		
	},
	mounted(){
		 axios
       	.get('api/dijagnoze/all')
       	.then(response => (this.dijagnoze = response.data));
	}

});
Vue.component('odobri_zaht', {

	data: function(){
		return{
			zahtevi:{},
			greska:'',
			opis:'',
			showModal: false
		}
	}, 
	
	template: `
	
		<div>
		<h1> Zdravo, superadmine! OVde ce tabela!</h1>
		<table class="table table-bordered" >
		   <tr>		   		
		   		<th>Email adresa</th>
		   		<th>Ime</th>
		   		<th>Prezime</th>
		   		<th>Adresa prebivalista</th>
		   		<th>Grad</th>
		   		<th>Drzava</th>
		   		<th>Broj telefona</th>
		   		<th>LBO</th>
		   		<th>Lozinka</th>
		   		<th></th>
		   		<th></th>
		   </tr>
		  
		   <tr  v-for="z,ind in zahtevi">
		   		<td>{{z.email}}</td>
		   		<td>{{z.ime}}</td>
		   		<td>{{z.prezime}}</td>
		   		<td>{{z.adresa}}</td>
		   		<td>{{z.grad}}</td>
		   		<td>{{z.drzava}}</td>
		   		<td>{{z.kontakt}}</td>
		   		<td>{{z.lbo}}</td>
		   		<td>{{z.lozinka}}</td>
		   		<td><button v-on:click = "prihvati(z)">Prihvati</button></td>
		   		<td><button class="btn btn-light" id="show-modal" @click="showModal = true" >Odbij zahtev</button>
						<modal v-if="showModal" @close="showModal = false">
	    
	    					<h3 slot="header">Unesite razlog odbijanja registracije</h3>
	    					<table slot="body" class="table table-hover table-light">
								<tbody>
									<tr>
										<td><input  class="form-control" type="text" v-model = "opis"/></td>
									</tr>									
								</tbody>
							</table>
	    					
	    					<div slot="footer">
	    						<button @click="showModal=false" style="margin:5px;" class="btn btn-success" v-on:click="save(ind, z)"> Ukloni zahtev </button>       						
								<button style="margin:5px;" class="btn btn-secondary" @click="showModal=false" v-on:click="restore()"> Odustani </button>								
							</div>
						</modal>
				</td>
		   </tr>
		   <tr>
		   		<td></td>
		   		<td><button v-on:click = "nazad()">Nazad</button></td>
		   </tr>
		   
		</table>
		
		</div>
	
	`, 
	methods : {
		nazad : function(){
			this.$router.push('/sprofil');
			return;
		},
		
		prihvati:function(zahtev){
			
			axios
			.post('api/adminkc/accepted', zahtev)
			.then((response)=>{
				this.greska='';
				alert("Poslat email");
			}).catch((response)=>{
				this.greska = 'Email nije poslat';
			}
				
			);
			//alert("Obrisi iz baze i onda salji email i alertuj da je poslao potvrdu za reg");
			
		},
		odbij:function(ind, zahtevBrisi){
			axios
			.post('api/adminkc/denied/', {user: zahtevBrisi, opis: this.opis})
			.then((response)=>{
				this.greska='';
				this.zahtevi.splice(ind, 1);
				alert("Poslat email");
				
			}).catch((response)=>{
				this.greska = 'Email nije poslat';
			}
			);
			

		},
		restored:function(){
			this.opis = '';
			this.index ='';

		},
		save:function(ind, z){
			if(!this.opis){
				alert("Opis ne moze biti prazan");
				
			}			    
			else{
				this.odbij(ind, z);
				
			}
			
		}
		
	},
	mounted(){
		 axios
      	.get('api/zahtevreg/all')
      	.then(response => (this.zahtevi = response.data));
	}
});
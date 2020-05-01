Vue.component('odobri_zaht', {

	data: function(){
		return{
			zahtevi:{},
			greska:''	
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
		  
		   <tr  v-for="z in zahtevi">
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
		   		<td><button v-on:click = "odbij(z)">Odbij zahtev</button></td>
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
				this.greska = 'Klinika vec postoji';
			}
				
			);
			//alert("Obrisi iz baze i onda salji email i alertuj da je poslao potvrdu za reg");
			
		},
		odbij:function(zahtev){
			alert("Obrisi iz baze i onda ga posalji na drugu stranicu da napise razlog odbijanja " +
					"i onda ga vrati na stranicu gde su zahtevi, sad ili to ili onaj iskacuci");

		},
		
		
	},
	mounted(){
		 axios
      	.get('api/zahtevreg/all')
      	.then(response => (this.zahtevi = response.data));
	}
});
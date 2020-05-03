Vue.component('klinike-prikaz', {
	data: function(){
		return{
			klinike: null,
			idPacijenta: 1,
			tipPregleda: {naziv: null},
			datum: null,
			tipoviPregleda: null,
			greskaDatum: '',
			greskaTipPregleda: ''
		}
	},

	template: `
	<div>
		<p class="leva">Sve klinike</p>
		<p class="desna">Pretraga termina</p>
		<table id="leva" class="table table-bordered ">
			<tr>
			<th>Naziv klinike</th>
			<th>Adersa</th>
			<th>Prosecna ocena</th>
			<th>Kontakt</th>
			<th></th>
			</tr>
			
			<tr v-for="(k, index) in klinike">
				<td>{{k.naziv}}</td>
				<td>{{k.adresa}}</td>
				<td>{{k.prosecnaOcena}}</td>
				<td>{{k.kontaktKlinike}}</td>
				<td><button v-on:click = "detalji(k.id)">Detalji</button></td>
			</tr>
		</table>
		
		<table id="desna" class="table table-bordered ">
			<tr>
				<td>Pretrazi preglede od: </td>
				<td><input id="datum" type="date" v-model="datum"></td>
				<td>{{this.greskaDatum}}</td>
			</tr>
			
			<tr>
				<td>Pretrazi po tipu pregleda</td>
				<td>
					<select id="selectTP" v-model="tipPregleda.naziv">
						<option v-for="t in tipoviPregleda" :value="t.naziv">{{t.naziv}}</option>
					</select>
				</td>
				<td>{{this.greskaTipPregleda}}</td>
				
			</tr>
			<tr>
				<td></td>
				<td><button v-on:click = "pretragaTermina()">Pretrazi</button></td>
			</tr>
		</table>
		</div>
	`, 
	
	methods : {
		
		validacija: function(){
			this.greskaDatum = '';
			this.greskaTipPregleda = '';
			
			if(!this.datum)
				this.greskaDatum = 'Datum je obavezno polje!';

			if(!this.tipPregleda)
				this.greskaTipPregleda = 'Tip pregleda je obavezno polje!';
			if(this.datum && this.greskaTipPregleda){
				return 0;
			}
			return 1;

		},
		pretragaTermina: function(){
			if(this.validacija() == 1){
				return;
			}
			this.greskaDatum = '';
			this.greskaTipPregleda = '';
			// da se desi pretraga po datumu i tipu pregleda
			
		},
		
		detalji: function(klinikaId){
			if(this.datum && this.greskaTipPregleda){
				// onda detalje za zakazivanje
			}
			else{
				// samo detalji za kliniku
				console.log(klinikaId);
				this.$router.push('/detaljiKlinike/'+klinikaId);
			}
		}
		
	},
	
	mounted () {
		axios
		.get('api/klinika/all')
		.then(res => {
			this.klinike = res.data;
		})
		axios
          .get('api/tippregleda/all')
          .then(res => {
        	  this.tipoviPregleda = res.data;
          })
	},

});
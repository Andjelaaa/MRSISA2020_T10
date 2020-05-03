Vue.component('predefpregledi', {
	data: function(){
		return{
			pregledi: null,
			idPacijenta: 1,
			datum: null,
			tipoviPregleda: null,
			tipPregleda: {naziv: null}
		}
	},
	
	template: `
		<div>
		<h1> Zakazivanje predefinisanih termina </h1>
		<table>
			<tr>
				<td>Pretrazi preglede od: </td>
				<td><input id="datum" type="date" v-model="datum"></td>
				<td><button v-on:click = "pretragaDatum()">Pretrazi</button></td>
			</tr>
			
			<tr>
				<td>Pretrazi po tipu pregleda</td>
				<td>
					<select id="selectTP" v-model="tipPregleda.naziv">
						<option v-for="t in tipoviPregleda" :value="t.naziv">{{t.naziv}}</option>
					</select>
				</td>
				<td><button v-on:click = "pretragaTip()">Pretrazi</button></td>
			</tr>
		</table>
		
		<table border='1'>
			<tr>
			<th>Datum i vreme</th>
			<th>Trajanje</th>
			<th>Tip pregleda</th>
			<th>Lekar</th>
			<th>Sala</th>
			<th></th>
			</tr>
			
			<tr v-for="(p, index) in pregledi">
				<td>{{p.datumVreme}}</td>
				<td>{{p.trajanje}}</td>
				<td>{{p.tipPregleda.naziv}}</td>
				<td>{{p.lekar.ime}} {{p.lekar.prezime}}</td>
				<td>{{p.sala.broj}}</td>
				<td><button v-on:click = "zakazi(p.id, index)">Zakazi</button></td>
			</tr>
		</table>
		</div>
	`,
	
	methods : {
		zakazi : function(pregledId, i){
			// upit da li je siguran
			this.pregledi.splice(i,1);
			axios
	          .post('api/pregled/'+pregledId+'/'+this.idPacijenta)
	          .then(res => {
	        	console.log('uspesno');
	        	// poruka o uspesnom zakazivanju
	          })
	          .catch((res)=>{
	        	  console.log('neuspesno');
	          })
		},
		validacija : function(){
			return 0;
		},
		pretragaDatum : function(){
			if(this.validacija() == 1)
				return;
			console.log(this.datum);
			axios
			.get('api/pregled/datum/'+this.datum)
			.then(res=>{
				this.pregledi = res.data;
				if(this.pregledi == null)
					console.log('nema rezultat');
			}).catch((res)=>{
				// nema rezultata ili nesto drugo da je u pitanju
				console.log('nema rezultat');
			})
		},
		
		pretragaTip: function(){
			if(this.validacija() == 1)
				return;
			axios
			.get('api/pregled/tip/'+this.tipPregleda.naziv)
			.then(res=>{
				this.pregledi = res.data;
				if(this.pregledi == null)
					console.log('nema rezultat');
			}).catch((res)=>{
				// nema rezultata ili nesto drugo da je u pitanju
				console.log('neuspesno');
			})
		},
	},
	
	mounted () {
		axios
		.get('api/pregled/slobodniPregledi')
		.then(res => {
			this.pregledi = res.data;
		})
		axios
          .get('api/tippregleda/all')
          .then(res => {
        	  this.tipoviPregleda = res.data;

          })
	},
});
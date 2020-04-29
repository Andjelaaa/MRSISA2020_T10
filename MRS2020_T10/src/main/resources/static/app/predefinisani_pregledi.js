Vue.component('predefpregledi', {
	data: function(){
		return{
			pregledi: null,
			idPacijenta: 1
		}
	},
	
	template: `
		<div>
		<h1> Zakazivanje predefinisanih termina </h1>
		<table border='1'>
			<tr>
			<th>Datum i vreme</th>
			<th>Trajanje</th>
			<th>Tip pregleda</th>
			<th>Lekar</th>
			<th>Sala</th>
			</tr>
			
			<tr v-for="(p, index) in pregledi">
				<td>{{p.datumVreme}}</td>
				<td>{{p.trajanje}}</td>
				<td>tip</td>
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
	},
	
	mounted () {
		axios
		.get('api/pregled/all')
		.then(res => {
			this.pregledi = res.data;
		})
	},
});
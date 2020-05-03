Vue.component('zakazani-pregledi', {
	data: function(){
		return{
			pregledi: null,
			idPacijenta: 1
		}
	},

	template: `
	<div>
		<h1> Otkazivanje termina </h1>
		
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
				<td><button v-on:click = "otkazi(p.id, index)">Otkazi</button></td>
			</tr>
		</table>
		</div>
	`, 
	
	methods : {
		
		otkazi : function(pregledId, i){
			// upit da li je siguran - prozor sa informacijama
			axios
	          .post('api/pregled/otkazi/'+pregledId+'/'+this.idPacijenta)
	          .then(res => {
	        	this.pregledi.splice(i,1);
	        	console.log('uspesno');
	        	// poruka o uspesnom otkazivanju
	          })
	          .catch((res)=>{
	        	  // poruka o neuspesnom otkazivanju
	        	  console.log('neuspesno');
	          })
		},	
	},
	
	mounted () {
		axios
		.get('api/pregled/zakazaniPregledi/'+this.idPacijenta)
		.then(res => {
			this.pregledi = res.data;
		})
		
	},

});
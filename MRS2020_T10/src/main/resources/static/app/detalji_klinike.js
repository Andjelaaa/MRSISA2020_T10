Vue.component('klinika-detalji', {
	data: function(){
		return{
			klinika: {naziv: '', adresa: '', prosecnaOcena: 0, kontaktKlinike: '000/000'},
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
		<td><button v-on:click = "brzoZakazivanje()">Brzo zakazivanje</button></td>
		<p class="leva">Detalji klinike</p>
		<p class="desna">Pretraga termina</p>
		<table id="leva" class="table table-bordered ">
			<tr>
			<th>Naziv klinike</th>
			<th>Adersa</th>
			<th>Prosecna ocena</th>
			<th>Kontakt</th>
			<th></th>
			</tr>
			
			<tr>
				<td>{{klinika.naziv}}</td>
				<td>{{klinika.adresa}}</td>
				<td>{{klinika.prosecnaOcena}}</td>
				<td>{{klinika.kontaktKlinike}}</td>
			</tr>
		</table>
		</div>
	`, 
	
	methods : {
		brzoZakazivanje: function(klinikaId)
		{
			this.$router.push('/predefinisanipregledi/'+ this.$route.params.name)
		},
		
		
	},
	
	mounted () {
		axios
		.get('api/klinika/detalji/'+ this.$route.params.name)
		.then(res => {
			this.klinika = res.data;
		})
		
	},

});
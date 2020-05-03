Vue.component('pacijent', {
	data: function(){
		return{
			pacijentId: 1
		}
	},
	template: `
	<div class="back">
    <h1>Dobrodosli :)</h1>
    <br>
    <table>
		<tr>
			<td><button v-on:click="pocetna()" class="btn btn-light">Pocetna</button></td>
			<td><button v-on:click="klinike()" class="btn btn-light">Klinike</button></td>
			<td><button v-on:click="preglediOperacije()" class="btn btn-light">Pregledi/Operacije</button></td>
			<td><button v-on:click="zk()" class="btn btn-light">Zdravstveni karton</button></td>
			<td><button v-on:click="profil()" class="btn btn-light">Profil</button></td>
			<td><button v-on:click="odjava()" class="btn btn-light">Odjavi se</button></td>
		</tr>
    </table>
	</div>
	`, 
	methods: {
		pocetna: function(){
			this.$router.push('/pacijent');
		},
		klinike: function(){
			this.$router.push('/klinike');
		},
		preglediOperacije: function(){
			
		},
		zk: function(){
			
		},
		profil: function(){
			
		},
		odjava: function(){
			this.$router.push('/');
		},
	},
	mounted(){
		
	},
});
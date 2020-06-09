Vue.component('potvrdareg', {
	
	template: `
	<div>
	
		<table  class="table  table-light ">
			<tr>
			<td>Uspesno ste aktivirali Vas profil. Mozete se ulogovati.</td>
			<td><button v-on:click="logovanje()" class="btn btn-light">Uloguj se</button></td>
			</tr>
			
			
		</table>
		</div>
	`, 
	
	methods : {
		logovanje: function()
		{
			this.$router.push('/');
		},
		
		
	},
	
	mounted () {
		axios
		.get('api/verification/potvrdiRegistraciju/'+this.$route.params.token)
		.then(res => {
			alert("Dobrodosao u klub");
		})
		
	},

});
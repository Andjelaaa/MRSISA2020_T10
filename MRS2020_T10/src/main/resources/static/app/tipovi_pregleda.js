Vue.component('tipovipregleda', {
	data: function(){
		return{
			tipoviPregleda: null,
			pretraga: ''

		}
	}, 
	
	template: `
		<div>
		<h1>Tipovi pregleda</h1>
		
		
		Pretraga: <input type="text" id="search" v-model="pretraga">
		<button v-on:click = "pretrazi()">Pretrazi</button>

		
		<table border='1'>
		   <tr>		   		
		   		<th>Naziv</th>
		   		<th>Opis</th>
		   </tr>
		  
		   <tr  v-for="t in tipoviPregleda">
		   		<td>{{t.naziv}}</td>
		   		<td>{{t.opis}}</td>
		   </tr>
		    
		</table><br>
		<button v-on:click = "nazad()">Nazad</button>
		<button onclick="window.location.href='#/dtpregled'"> Dodaj tip pregleda </button>

		</div>
	
	`, 
	methods : {
		nazad : function(){
			this.$router.push('/admin');
			return;
		},
		pretrazi: function(){
			console.log(this.pretraga);
			if(this.pretraga){
				axios
		       	.get('api/tippregleda/search/'+ this.pretraga)
		       	.then(response => (this.tipoviPregleda = response.data));
				
			}
			
			
		}
		
	},
	mounted(){
		 axios
       	.get('api/tippregleda/all')
       	.then(response => (this.tipoviPregleda = response.data));
	}

});
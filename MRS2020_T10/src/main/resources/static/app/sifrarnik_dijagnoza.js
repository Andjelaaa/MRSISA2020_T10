Vue.component('sifrarnik2', {
	data: function(){
		return{
			dijagnoze:{},
			naziv:'',
			sifra:'',
			greska:'',
			greska1:'',
			greska2:''
		}
	}, 
	
	template: `
		<div>
		<h1>Sifrarnik dijagnoza </h1>
		<table border='1'>
		   <tr>		   		
		   		<th>Naziv</th>
		   		<th>Sifra</th>
		   </tr>
		  
		   <tr  v-for="l in dijagnoze">
		   		<td>{{l.naziv}}</td>
		   		<td>{{l.sifra}}</td>
		   </tr>
		    <tr>
		   
		   		<td><button v-on:click = "nazad()">Nazad</button></td>
		   		
		   </tr>
		   </table>
		   <table >
		   <tr>		   		
		   		<td>Naziv</td>
		   		<td><input id="naziv" type="text" v-model="naziv"></td>
		   		<td style="color: red">{{greska1}}</td>
		   </tr>
		  
		   <tr>
		   		<td>Sifra</td>
		   		<td><input id="sifra" type="text" v-model="sifra"></td>
		   		<td style="color: red">{{greska2}}</td>
		   </tr>
		    <tr>
		   		
		   		<td><button v-on:click = "napraviDijagnozu()">Dodaj dijagnozu</button></td>	   
		   		
		   </tr>
		   
		  </table>
			{{greska}}
		</div>
	
	`, 
	methods : {
		nazad : function(){
			this.$router.push('/sprofil');
			return;
		},
		validacija : function(){
			this.greska1 = '';
			this.greska2 = '';
			
			if(!this.naziv)
				this.greska1 = 'Naziv je obavezno polje!';

			if(!this.sifra)
				this.greska2 = 'Sifra je obavezno polje!';
			if(this.naziv && this.sifra){
				return 0;
			}
			return 1;
			
			
		},
		napraviDijagnozu: function(){
			this.greska = '';
			if(this.validacija()==1)
				return;
			
			var newLek ={ "naziv": this.naziv, "sifra": this.sifra};
			axios
			.post('api/dijagnoze', newLek)
			.then((response)=>{
				this.$router.push('/');
			}).catch((response)=>{
				this.greska = 'Dijagnoza vec postoji';
			});
		}
		
	},
	mounted(){
		 axios
       	.get('api/dijagnoze/all')
       	.then(response => (this.dijagnoze = response.data));
	}

});
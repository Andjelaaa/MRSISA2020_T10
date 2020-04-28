Vue.component('dodsale', {
	data: function(){
		return{
			sala: {naziv:''},
			nazivGreska: '',
			brojGreska: '',
			klinikaGreska: '',
			error: ''
		}
	}, 
	
	template: `
	
		<div>
		<h1> Forma za dodavanje sale </h1>
		<p>{{error}}</p>
		<table>
			<tbody>
			   <tr>
			   
			   		<td>Broj sale: </td>
			   		<td><input id="broj" type="number" v-model="sala.broj"></td>
			   		<td style="color: red">{{brojGreska}}</td>
	
			   </tr>

			   <tr>
			   		
			   		<td>Naziv sale: </td>
			   		<td><input id="opis" type="text" v-model="sala.naziv"></td>
			   		<td style="color: red">{{nazivGreska}}</td>
			   </tr>
		   
			    <tr>
			   
			   		<td><button v-on:click="nazad()">Nazad</button></td>
			   		<td><button v-on:click="dodaj()">Dodaj</button></td>
			   		<td></td>
			   </tr>
		   </tbody>
		</table>
	
		</div>
	
	`, 
	methods : {
		nazad : function(){
			this.$router.push('/admin')
			return;
		},
		validacija: function(){
			this.nazivGreska = '';
			this.brojGreska = '';
			
			if(!this.sala.naziv)
				this.nazivGreska = 'Naziv je obavezno polje!';

			if(!this.sala.broj)
				this.brojGreska = 'Broj je obavezno polje!';
			if(this.sala.naziv && this.sala.broj){
				return 0;
			}
			return 1;
			
		},
		dodaj : function(){	
			this.error = '';
			if(this.validacija()==1)
				return;
			
			axios
			.post('api/sala', this.sala)
			.then((res)=>{
				console.log('uspesno');
				this.$router.push('/');
			}).catch((res)=>{
				this.error = 'Vec postoji sala sa istim brojem';
			}
				
			)
		}
		
	}

});
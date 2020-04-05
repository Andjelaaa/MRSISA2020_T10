Vue.component('dodtipapregleda', {
	data: function(){
		return{
			tipPregleda: {},
			nazivGreska: '',
			opisGreska: '',
			sifraGreska: ''
		}
	}, 
	
	template: `
	
		<div>
		<h1> Forma za dodavanje tipa pregleda </h1>
		<table>
			<tbody>
			   <tr>
			   
			   		<td>Naziv tipa pregleda: </td>
			   		<td><input id="naziv" type="text" v-model="tipPregleda.naziv"></td>
			   		<td style="color: red">{{nazivGreska}}</td>
	
			   </tr>
			   <tr>
			   
			   		<td>Sifra tipa pregleda: </td>
			   		<td><input id="sifra" type="text" v-model="tipPregleda.sifra"></td>
			   		<td style="color: red">{{sifraGreska}}</td>
			   		
			   </tr>
			   <tr>
			   		
			   		<td>Opis tipa pregleda: </td>
			   		<td><input id="opis" type="text" v-model="tipPregleda.opis"></td>
			   		<td style="color: red">{{opisGreska}}</td>
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
//			this.$router.push('/prethodnastranica')
			return;
		},
		validacija: function(){
			this.nazivGreska = '';
			this.sifraGreska = '';
			this.opisGreska = '';
			
			if(!this.tipPregleda.naziv)
				this.nazivGreska = 'Naziv je obavezno polje!';
			if(!this.tipPregleda.sifra)
				this.sifraGreska = 'Sifra je obavezno polje!';
			if(!this.tipPregleda.opis)
				this.opisGreska = 'Opis je obavezno polje!';
			if(this.tipPregleda.naziv && this.tipPregleda.sifra && this.tipPregleda.opis){
				return 0;
			}
			return 1;
			
		},
		dodaj : function(){	
			if(this.validacija()==1)
				return;
			
//			axios
//			.post('rest/dodaj/tippregleda', tipPregleda)
//			.then((res)=>{
//				this.$router.push('/');
//			})
		}
		
	}

});
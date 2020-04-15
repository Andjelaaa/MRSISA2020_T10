Vue.component('regklinike', {
	data: function(){
		return{
			nazivKlinike: '',
			adresaKlinike: '',
			emailKlinike: '',
			kontaktKlinike: '',
			opis: '',
			greska1: '',
			greska2: '',
			greska3: '',
			greska4: '',
			greska5: ''
		   
		}
	}, 
	
	template: `
	
		<div>
		<h1> Registracija klinike: </h1>
		<table>
		   <tr>
		   
		   		<td>Naziv klinike: </td>
		   		<td><input id="nazivKlinike" type="text" v-model="nazivKlinike"></td>
		   		<td style="color: red">{{greska1}}</td>

		   </tr>
		   <tr>
		   		
		   		<td>Adresa klinike: </td>
		   		<td><input id="adresaKlinike" type="text" v-model="adresaKlinike"></td>
		   		<td style="color: red">{{greska2}}</td>
		   		
		   </tr>
		   <tr>
		   
		   		<td>Email klinike: </td>
		   		<td><input id="emailKlinike" type="text" v-model="emailKlinike"></td>
		   		<td style="color: red">{{greska3}}</td>
		   		
		   </tr>
		   <tr>
		   	
		   		<td>Kontakt telefon klinike: </td>
		   		<td><input id="kontaktKlinike" type="text" v-model="kontaktKlinike"></td>
		   		<td style="color: red">{{greska4}}</td>
		  
		   </tr>
		   <tr>
		   
		   		<td>Opis klinike: </td>
		   		<td><input id="opis" type="text" v-model="opis"></td>
		   		<td style="color: red">{{greska5}}</td>
		   
		   </tr>
		    <tr>
		   
		   		<td></td>
		   		<td><button v-on:click = "napraviKliniku()">Napavi kliniku</td>	   
		   </tr>
		   
		</table>
		
		<br>
		<br>
		<a href="#/dadmina">Dodaj admina klinike</a>
	
		</div>
	
	`, 
	methods : {

		validacija: function(){
			this.greska1 = '';
			this.greska2 = '';
			this.greska3 = '';
			this.greska4 = '';
			this.greska5 = '';

			if(!this.nazivKlinike)
				this.greska1 = 'Naziv je obavezno polje!';
			if(!this.adresaKlinike)
				this.greska2 = 'Adresa je obavezno polje!';
			if(!this.emailKlinike)
				this.greska3 = 'Email je obavezno polje!';
			if(!this.kontaktKlinike)
				this.greska4 = 'Kontakt klinike je obavezno polje!';
			if(!this.opis)
				this.greska5 = 'Opis je obavezno polje!';
			if(this.nazivKlinike && this.adresaKlinike && this.emailKlinike && this.kontaktKlinike && this.opis){
				return 0;
			}
			return 1;
		},
		napraviKliniku : function(){
			if(this.validacija()==1)
				return;
			}
		}

});
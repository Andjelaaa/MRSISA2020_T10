Vue.component('regklinike', {
	data: function(){
		return{
			nazivKlinike: '',
			adresaKlinike: '',
			emailKlinike: '',
			kontaktKlinike: '',
			idKlinike: ''
		}
	}, 
	
	template: `
	
		<div>
		<h1> Registracija klinike: </h1>
		<table>
		   <tr>
		   
		   		<td>Naziv klinike: </td>
		   		<td><input id="name" type="text" v-model="nazivKlinike"></td>

		   </tr>
		   <tr>
		   		
		   		<td>Adresa klinike: </td>
		   		<td><input id="name" type="text" v-model="adresaKlinike"></td>
		   		
		   </tr>
		   <tr>
		   
		   		<td>Email klinike: </td>
		   		<td><input id="name" type="text" v-model="emailKlinike"></td>
		   		
		   </tr>
		   <tr>
		   	
		   		<td>Kontakt telefon klinike: </td>
		   		<td><input id="name" type="text" v-model="kontaktKlinike"></td>
		  
		   </tr>
		   <tr>
		   
		   		<td>Id klinike: </td>
		   		<td><input id="name" type="text" v-model="idKlinike"></td>
		   
		   </tr>
		    <tr>
		   
		   		<td></td>
		   		<td><input type="submit" value= "Napavi kliniku"></td>
		   
		   </tr>
		   
		</table>
	
		</div>
	
	`, 

});
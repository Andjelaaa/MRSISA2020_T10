Vue.component('regklinike', {
	data: function(){
		return{
			clinicName: '',
			clinicAddress: '',
			clinicEmail: '',
			clinicNumber: '',
			idNumber: ''
		}
	}, 
	
	template: `
	
		<div>
		<h1> Registracija klinike: </h1>
		<table>
		   <tr>
		   
		   		<td>Naziv klinike: </td>
		   		<td><input id="name" type="text" v-model="clinicName"></td>

		   </tr>
		   <tr>
		   		
		   		<td>Adresa klinike: </td>
		   		<td><input id="name" type="text" v-model="clinicAddress"></td>
		   		
		   </tr>
		   <tr>
		   
		   		<td>Email klinike: </td>
		   		<td><input id="name" type="text" v-model="clinicEmail"></td>
		   		
		   </tr>
		   <tr>
		   	
		   		<td>Kontakt telefon klinike: </td>
		   		<td><input id="name" type="text" v-model="clinicNumber"></td>
		  
		   </tr>
		   <tr>
		   
		   		<td>Id klinike: </td>
		   		<td><input id="name" type="text" v-model="idNumber"></td>
		   
		   </tr>
		    <tr>
		   
		   		<td></td>
		   		<td><input type="submit" value= "Napavi kliniku"></td>
		   
		   </tr>
		   
		</table>
	
		</div>
	
	`, 

});
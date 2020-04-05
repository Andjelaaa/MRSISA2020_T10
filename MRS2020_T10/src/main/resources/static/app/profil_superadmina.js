Vue.component('superprofil', {

	data: function(){
		return{
			clinicName: '',
			clinicAddress: '',
			clinicEmail: ''
		}
	}, 
	
	template: `
	
		<div>
		
		<h1> Zdravo, superadmine! Ovo je tvoja home stranica na sajtu!</h1>
		<br>
		<button onclick="window.location.href='/regklinika'"> Kreiraj kliniku </button>
		
		</div>
	
	`, 

});
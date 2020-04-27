Vue.component('superprofil', {

	data: function(){
		return{	}
	}, 
	
	template: `
	
		<div>
		
		<h1> Zdravo, superadmine! Ovo je tvoja home stranica na sajtu!</h1>
		<br>
		<button onclick="window.location.href='#/regklinika'"> Kreiraj kliniku </button>
		<br>
		<a href="#/odobri_zahtev">Odobri zahtev za registracijom</a>
		<br>
		<a href="#/kreirajzk">Kreiraj zdravstveni karton</a>
		<br>
		<a href="#/sifrarnik1">Sifarnik lekova</a>
		<br>
		<a href="#/sifrarnik2">Sifarnik dijagnoza</a>
		
		</div>
	
	`, 

});
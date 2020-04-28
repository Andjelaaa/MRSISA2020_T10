Vue.component('admin', {

	data: function(){
		return{	}
	}, 
	
	template: `
	
		<div>
		
		<h1>Pocetna stranica: Admin klinike</h1>
		<br>
		<button onclick="window.location.href='#/dlekar'"> Dodaj lekara </button>
		<br>
		<br>
		<button onclick="window.location.href='#/dsala'"> Dodaj salu </button>
		<br>
		<br>
		<button onclick="window.location.href='#/tipovipregleda'"> Tipovi pregleda </button>
		<br>
		<br>
		<button onclick="window.location.href='#/dpregled'"> Novi termin za pregled </button>
		<br>
		
		</div>
	
	`, 

});
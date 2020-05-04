Vue.component('recept', {
	data: function(){
	return{
		lekovi:{},
		greska1: '',
		dbError: ''  
		}
	},
	
	template: `
	<div>
	<nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#/admin">Pocetna</a>
		
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <li class="nav-item active">
		        <a class="nav-link" href="#/lekari">Lekari</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/sale">Sale</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/tipovipregleda">Tipovi pregleda</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/dpregled">Novi termin za pregled</a>
		      </li>
		    </ul>
		    <form class="form-inline my-2 my-lg-0">
		      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
		      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		    </form>
		  </div>
		</nav>
		<h1> Novi recept: </h1>
		{{dbError}}
		<table>
			
		   <tr>
		   
		   		<td>Drzava: </td>
		   		<td><input id="drzava" type="text" v-model="drzava"></td>
		   		<td style="color: red">{{greska5}}</td>
		   
		   </tr>
		    
		    <tr>
		   
		   		<td><button v-on:click = "nazad()">Nazad</button></td>
		   		<td><button v-on:click = "recept()">Napravi recept</button></td>	   
		   </tr>
		   
		</table>
		
		</div>
	
	`, 
	methods : {
		nazad : function(){
			//this.$router.push('/nazaad negde nzm');
			return;
		},
		validacija : function(){
			
			this.greska1='';
			this.dbError= '';
			
			if(!this.lekovi)
				this.greska1 = 'Morate uneti bar jedan lek!';

						
			if(this.lekovi){
				return 0;
			}
			return 1;			
		},
		recept: function(){
			this.dbError = '';
			this.greska1='';
			if(this.validacija()==1)
				return;
			
			//upis u bp namesti
		}		
	},


});
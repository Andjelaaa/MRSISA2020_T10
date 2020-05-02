Vue.component('dodajadmina', {
	data: function(){
		return{
			adresa: '',
			drzava: '',
			email: '',
			grad: '',
			ime:'',
			prezime: '',
			lozinka:'',
			lozinka1:'',
			kontakt:'',
			klinika:'',
			greska0: '',
			greska1: '',
			greska2: '',
			greska3: '',
			greska4: '',
			greska5: '',
			greska6: '',
			greska7: '',
			dbError: ''
			
		   
		}
	}, 
	template: `
	<div>
		<h1> Registracija admina klinike: </h1>
		{{dbError}}
		<table>
			<tr>
		   		<td>Email: </td>
		   		<td><input id="email" type="text" v-model="email"></td>
		   		<td style="color: red">{{greska0}}</td>

		   </tr>
		   <tr>
		   		<td>Ime: </td>
		   		<td><input id="ime" type="text" v-model="ime"></td>
		   		<td style="color: red">{{greska1}}</td>

		   </tr>
		   <tr>
		   		
		   		<td>Prezime: </td>
		   		<td><input id="prezime" type="text" v-model="prezime"></td>
		   		<td style="color: red">{{greska2}}</td>
		   		
		   </tr>
		   <tr>
		   
		   		<td>Adresa prebivalista: </td>
		   		<td><input id="adresa" type="text" v-model="adresa"></td>
		   		<td style="color: red">{{greska3}}</td>
		   		
		   </tr>
		   <tr>
		   	
		   		<td>Grad: </td>
		   		<td><input id="grad" type="text" v-model="grad"></td>
		   		<td style="color: red">{{greska4}}</td>
		  
		   </tr>
		   <tr>
		   
		   		<td>Drzava: </td>
		   		<td><input id="drzava" type="text" v-model="drzava"></td>
		   		<td style="color: red">{{greska5}}</td>
		   
		   </tr>
		    <tr>
		   
		   		<td>Broj telefona: </td>
		   		<td><input id="kontakt" type="text" v-model="kontakt"></td>
		   		<td style="color: red">{{greska6}}</td>
		   
		   </tr>
		    <tr>
		   
		   		<td>Lozinka: </td>
		   		<td><input id="lozinka" type="password" v-model="lozinka"></td>
		   		<td style="color: red"></td>
		   
		   </tr>
		    <tr>
		   
		   		<td>Ponovi lozinku: </td>
		   		<td><input id="lozinka1" type="password" v-model="lozinka1"></td>
		   		<td style="color: red">{{greska7}}</td>
		   
		   </tr>
		    <tr>
		   
		   		<td><button v-on:click = "nazad()">Nazad</button></td>
		   		<td><button v-on:click = "regAdmina()">Registruj admina</button></td>	   
		   </tr>
		   
		</table>
		
		</div>
	
	`, 
	//kad se uvede autorizacija, dodam kliniku samo
	methods : {
		nazad : function(){
			this.$router.push('/profilklinike');
			return;
		},
		validacija : function(){
			this.greska0= '';
			this.greska1= '';
			this.greska2 ='';
			this.greska3= '';
			this.greska4= '';
			this.greska5= '';
			this.greska6= '';
			this.greska7='';
			this.dbError= '';
			
			
			if(this.lozinka !=this.lozinka1)
				this.greska7 = 'Lozinke moraju biti iste!';
			if(!this.lozinka && this.lozinka1)
				this.greska7 = 'Lozinke su obavezne!';
			if(!this.email)
				this.greska0 = 'Email je obavezno polje!';
			if(!this.ime)
				this.greska1 = 'Ime je obavezno polje!';
			if(!this.prezime)
				this.greska2 = 'Prezime je obavezno polje!';
			if(!this.adresa)
				this.greska3 = 'Adresa je obavezno polje!';
			if(!this.grad)
				this.greska4 = 'Grad je obavezno polje!';
			if(!this.drzava)
				this.greska5 = 'Drzava je obavezno polje!';
			if(!this.kontakt)
				this.greska6 = 'Broj je obavezno polje!';
						
			if(this.lozinka && this.lozinka1 && this.email && this.ime && this.prezime &&this.adresa
					&& this.grad&& this.drzava &&this.kontakt){
				return 0;
			}
			return 1;			
		},
		regAdmina: function(){
			this.dbError = '';

			if(this.validacija()==1)
				return;
			var newAdmin ={ "ime": this.ime, "email": this.email,
					"prezime": this.prezime,"grad": this.grad,
					"drzava": this.drzava,"kontakt": this.kontakt,
					"lozinka": this.lozinka,"adresa": this.adresa};
			axios
			.post('api/admini', newAdmin)
			.then((response)=>{
				 this.greska0= '';
				 this.greska1= '';
				 this.greska2 ='';
				 this.greska3= '';
				 this.greska4= '';
				 this.greska5= '';
				 this.greska6= '';
				 this.greska7='';
				 this.adresa='';
				 this.drzava= '';
				 this.email= '';
				 this.grad= '';
				 this.ime='';
				 this.prezime= '';
				 this.lozinka='';
				 this.lozinka1='';
				 this.kontakt='';
				 this.dbError= '';
				 alert("Uspesno dodat novi admin");
			}).catch((response)=>{
				 this.greska0= '';
				 this.greska1= '';
				 this.greska2 ='';
				 this.greska3= '';
				 this.greska4= '';
				 this.greska5= '';
				 this.greska6= '';
				 this.greska7='';
				 this.dbError = 'Admin vec postoji';
			});
		}		
	},

});
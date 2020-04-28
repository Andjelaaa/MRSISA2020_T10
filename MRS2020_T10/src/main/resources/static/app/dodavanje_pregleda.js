Vue.component('dpregled', {
	data: function(){
		return{
//			lekar: {tipPregleda: null},
//			tipPregleda: {},
//			tipoviPregleda: null,
//			imeGreska: '',
//			prezimeGreska: '',
//			emailGreska: '',
//			lozinkaGreska: '',
//			adresaGreska: '',
//			gradGreska: '',
//			drzavaGreska: '',
//			kontaktGreska: '',
//			pocGreska: '',
//			krajGreska: '',
//			specijalizacijaGreska: '',
//			error: ''
				
		}
	}, 
	
	template: `
	
		<div>
		<h1> Forma za registraciju lekara </h1>
		<p>{{error}}</p>
		<table>
			<tbody>
				<tr>			   
			   		<td>Email: </td>
			   		<td><input id="email" type="text" v-model="lekar.email"></td>
			   		<td style="color: red">{{emailGreska}}</td>
			   </tr>
			   <tr>
			   		<td>Lozinka: </td>
			   		<td><input id="lozinka" type="text" v-model="lekar.lozinka"></td>
			   		<td style="color: red">{{lozinkaGreska}}</td>
			   </tr>
			   <tr>
			   
			   		<td>Ime: </td>
			   		<td><input id="ime" type="text" v-model="lekar.ime"></td>
			   		<td style="color: red">{{imeGreska}}</td>	
			   </tr>
			   <tr>
			   		<td>Prezime: </td>
			   		<td><input id="prezime" type="text" v-model="lekar.prezime"></td>
			   		<td style="color: red">{{prezimeGreska}}</td>
			   </tr>	
			   <tr>			   
			   		<td>Adresa: </td>
			   		<td><input id="adresa" type="text" v-model="lekar.adresa"></td>
			   		<td style="color: red">{{adresaGreska}}</td>
			   </tr>
			   <tr>
			   		<td>Grad: </td>
			   		<td><input id="grad" type="text" v-model="lekar.grad"></td>
			   		<td style="color: red">{{gradGreska}}</td>
			   </tr>
			   <tr>			   
			   		<td>Drzava: </td>
			   		<td><input id="drzava" type="text" v-model="lekar.drzava"></td>
			   		<td style="color: red">{{drzavaGreska}}</td>
			   </tr>
			   <tr>			   
			   		<td>Kontakt telefon: </td>
			   		<td><input id="kontakt" type="text" v-model="lekar.kontakt"></td>
			   		<td style="color: red">{{kontaktGreska}}</td>
			   </tr>
			   <tr>			   
			   		<td>Pocetak radnog vremena (format HH:MM): </td>
			   		<td><input id="rvPocetak" type="text" v-model="lekar.rvPocetak"></td>
			   		<td style="color: red">{{pocGreska}}</td>
			   </tr>
			   <tr>			   
			   		<td>Kraj radnog vremena (format HH:MM): </td>
			   		<td><input id="rvKraj" type="text" v-model="lekar.rvKraj"></td>
			   		<td style="color: red">{{krajGreska}}</td>
			   </tr>
			   <tr>
			   		<td>Specijalizacija: </td>
			   		<td>
						<select id="selectTP" v-model="tipPregleda.naziv">
							<option v-for="t in tipoviPregleda" :value="t.naziv">{{t.naziv}}</option>
						</select>
					</td>
			   		<td style="color: red">{{specijalizacijaGreska}}</td>
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
			this.imeGreska = '';
			this.prezimeGreska = '';
			this.emailGreska = '';
			this.lozinkaGreska = '';
			this.adresaGreska = '';
			this.gradGreska = '';
			this.drzavaGreska = '';
			this.specijalizacijaGreska = '';
			this.pocGreska = '';
			this.krajGreska = '';
			this.kontaktGreska = '';
			
			if(!this.lekar.email)
				this.emailGreska = 'Email je obavezno polje!';
			if(!this.lekar.kontakt)
				this.kontaktGreska = 'Kontakt je obavezno polje!';
			if(!this.lekar.ime)
				this.imeGreska = 'Ime je obavezno polje!';
			if(!this.lekar.prezime)
				this.prezimeGreska = 'Prezime je obavezno polje!';
			if(!this.lekar.lozinka)
				this.lozinkaGreska = 'Lozinka je obavezno polje!';
			if(!this.lekar.adresa)
				this.adresaGreska = 'Adresa je obavezno polje!';
			if(!this.lekar.grad)
				this.gradGreska = 'Grad je obavezno polje!';
			if(!this.lekar.drzava)
				this.drzavaGreska = 'Drzava je obavezno polje!';
			if(!this.lekar.rvPocetak)
				this.pocGreska = 'Ovo je obavezno polje!';
			if(!this.lekar.rvKraj)
				this.krajGreska = 'Ovo je obavezno polje!';
			if(!this.lekar.tipPregleda)
				this.specijalizacijaGreska = 'Specijalizacija je obavezno polje!';

			if(this.lekar.email && this.lekar.ime && this.lekar.prezime && this.lekar.lozinka && this.lekar.adresa && this.lekar.grad && this.lekar.drzava && this.lekar.tipPregleda
					&& this.lekar.rvPocetak && this.lekar.rvKraj && this.lekar.kontakt){
				return 0;
			}
			return 1;
			
		},
		dodaj : function(){	
			this.error = '';
			this.lekar.tipPregleda = this.tipPregleda;
			if(this.validacija()==1)
				return;
			
			axios
			.post('api/lekar', this.lekar)
			.then((res)=>{
				console.log('uspesno');
				this.$router.push('/');
			}).catch((res)=>{
				this.error = 'Vec postoji lekar sa istim email-om';
			}
				
			)
		}
		
	},
	mounted () {
//           axios
//          .get('api/tippregleda/all')
//          .then(res => {
//        	  this.tipoviPregleda = res.data;
//
//          })
    },

});
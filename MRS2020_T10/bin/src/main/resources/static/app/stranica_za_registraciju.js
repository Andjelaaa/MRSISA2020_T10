Vue.component('strzareg', {
	data: function(){
		return{
			imePacijenta: '',
			prezimePacijenta: '',
			email:'',
			adresaPacijenta:'',
			grad:'',
			drzava:'',
			lozinka:'',
			lozinkaPonovo:'',
			telefonPacijenta:'',
			lbo:'',
			greska:'',
			lozinkaGreska:''
		}
	},
	template: `
		<div>
		<h1> Registracija pacijenta: </h1>
		<table>
			<tr>
				<td>Email adresa: </td>
				<td><input id="email" type="text" v-model="email"></td>
			</tr>
			<tr>
				<td>Ime: </td>
				<td><input id="ime" type="text" v-model="imePacijenta"></td>
			</tr>
			<tr>
				<td>Prezime: </td>
				<td><input id="prezime" type="text" v-model="prezimePacijenta"></td>
			</tr>
			<tr>
				<td>Adresa prebivalista: </td>
				<td><input id="adresa" type="text" v-model="adresaPacijenta"></td>
			</tr>
			<tr>
				<td>Grad: </td>
				<td><input id="grad" type="text" v-model="grad"></td>
			</tr>
			<tr>
				<td>Drzava: </td>
				<td><input id="drzava" type="text" v-model="drzava"></td>
			</tr>
			<tr>
				<td>Broj telefona: </td>
				<td><input id="telefon" type="text" v-model="telefonPacijenta"></td>
			</tr>
			<tr>
				<td>LBO: </td>
				<td><input id="lboBroj" type="text" v-model="lbo"></td>
			</tr>
			<br>
			<tr>
				<td>Lozinka: </td>
				<td><input id="lozinka1" type="password" v-model="lozinka"></td>
			</tr>
			<tr>
				<td>Ponovi lozinku: </td>
				<td><input id="lozinka2" type="password" v-model="lozinkaPonovo"></td>
				<td style="color: red">{{lozinkaGreska}}</td>
			</tr>
			<tr>
				<td><button v-on:click="nazad()">Nazad</button></td>
			   	<td><button v-on:click="registracija()">Registruj se</button></td>
			   	<td style="color: red">{{greska}}</td>
			</tr>
		</table>
	</div>
	`,
		
		methods : {
			nazad : function(){
//				this.$router.push('/prethodnastranica')
				return;
			},
			validacija: function(){
				this.greska = '';
				this.lozinkaGreska = '';
				
				if(!this.imePacijenta){
					this.greska = 'Sva polja su obavezna!';
					return 1;
				}
				if(!this.prezimePacijenta){
					this.greska = 'Sva polja su obavezna!';
					return 1;
				}
				if(!this.email){
					this.greska = 'Sva polja su obavezna!';
					return 1;
				}
				if(!this.adresaPacijenta){
					this.greska = 'Sva polja su obavezna!';
					return 1;
				}
				if(!this.grad){
					this.greska = 'Sva polja su obavezna!';
					return 1;
				}
				if(!this.drzava){
					this.greska = 'Sva polja su obavezna!';
					return 1;
				}
				if(!(this.lozinka==this.lozinkaPonovo)){
					this.lozinkaGreska = 'Lozinke se ne poklapaju';
					return 1;
				}
				if(!this.telefonPacijenta){
					this.greska = 'Sva polja su obavezna!';
					return 1;
				}
				if(!this.lbo){
					this.greska = 'Sva polja su obavezna!';
					return 1;
				}
				
				return 0;
				
			},
			registracija : function(){	
				this.greska = '';
				if(this.validacija()==1)
					return;
				var novZahtev = {"email": this.email, "lozinka": this.lozinka,
								"ime": this.imePacijenta, "prezime": this.prezimePacijenta,
								"adresa": this.adresaPacijenta, "grad": this.grad,
								"drzava": this.drzava, "kontakt": this.telefonPacijenta, "lbo": this.lbo};
				
				axios
				.post('api/zahtevreg', novZahtev)
				.then((res)=>{
					console.log('USPESNO');
					this.$router.push('/');
				}).catch((res)=>{
					console.log('NEUSPESNO');
					this.greska = 'Email vec postoji';
				}
					
				)
			}
			
		}
})
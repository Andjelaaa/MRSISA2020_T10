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
			lbo:''
			
			
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
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Registracija"></td>
			</tr>
		</table>
	</div>
	`
})
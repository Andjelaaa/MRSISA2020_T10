Vue.component('dpregled', {
	data: function(){
		return{
			pregled: {tipPregleda: null, lekar: null, sala: null},
			lekari: null,
			sale: null, 
			tipoviPregleda: null,
			tipPregleda: {},
			lekar: {},
			sala: {},			
			
			datumVremeGreska: '',
			tipPregledaGreska: '',
			trajanjeGreska: '',
			salaGreska: '',
			lekarGreska: '',
			error: ''
				
		}
	}, 
	
	template: `
	
		<div>
		<h1> Novi termin za pregled </h1>
		<p>{{error}}</p>
		<table>
			<tbody>
				<tr>			   
			   		<td>Datum i vreme: </td>
			   		<td><input id="datumvreme" type="datetime-local" v-model="pregled.datumVreme"></td>
			   		<td style="color: red">{{datumVremeGreska}}</td>
			   </tr>			   
			   <tr>			   
			   		<td>Tip pregleda: </td>
					<td>
						<select id="selectTP" v-model="tipPregleda.naziv">
							<option v-for="t in tipoviPregleda" :value="t.naziv">{{t.naziv}}</option>
						</select>
					</td>			   		
					<td style="color: red">{{tipPregledaGreska}}</td>
			   </tr>
			   <tr>			   
			   		<td>Trajanje: </td>
			   		<td><input id="trajanje" type="number" v-model="pregled.trajanje"></td>
			   		<td style="color: red">{{trajanjeGreska}}</td>
			   </tr>
			   <tr>			   
			   		<td>Sala: </td>
					<td>
						<select id="selectSala" v-model="sala.naziv">
							<option v-for="s in sale" :value="s.naziv">{{s.naziv}}</option>
						</select>
					</td>			   		
					<td style="color: red">{{salaGreska}}</td>
			   </tr>
			   
			   <tr>			   
			   		<td>Lekar: </td>
					<td>
						<select id="selectLekar" v-model="lekar.email">
							<option v-for="l in lekari" :value="l.email">{{l.ime}} {{l.prezime}}</option>
						</select>
					</td>			   		
					<td style="color: red">{{lekarGreska}}</td>
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
			this.datumVremeGreska = '';
			this.trajanjeGreska = '';
			this.tipPregledaGreska = '';
			this.salaGreska ='';
			this.lekarGreska = '';

			
			if(!this.tipPregleda.naziv)
				this.tipPregledaGreska = 'Tip pregleda je obavezno polje!';
			if(!this.pregled.trajanje)
				this.trajanjeGreska = 'Trajanje je obavezno polje!';
			if(!this.pregled.datumVreme)
				this.datumVremeGreska = 'Datum i vreme je obavezno polje!';
			if(!this.lekar.email)
				this.lekarGreska = 'Lekar je obavezno polje!';
			if(!this.sala.naziv)
				this.salaGreska = 'Sala je obavezno polje!';


			if(this.tipPregleda.naziv && this.pregled.trajanje && this.pregled.datumVreme && this.lekar.email && this.sala.naziv){
				return 0;
			}
			return 1;
			
		},
		dodaj : function(){	
			
			if(this.validacija()==1)
				return;
			
			this.error = '';
			this.pregled.tipPregleda = this.tipPregleda;
			this.pregled.sala = this.sala;
			this.pregled.lekar = this.lekar;
			
			axios
			.post('api/pregled', this.pregled)
			.then((res)=>{
				console.log('uspesno');
				this.$router.push('/');
			}).catch((res)=>{
				this.error = 'Greska pri dodavanju';
			}
				
			)
		}
		
	},
	watch: {
		// dobavlja lekare za odabrani tip pregleda, kada se on promeni
	    tipPregleda: function() {
	    	console.log(this.tipPregleda.naziv);
	    	 axios
	          .get('api/tippregleda/'+this.tipPregleda.naziv+'/lekari')
	          .then(res => {
	        	  this.lekari = res.data;

	          })
	    }
	
		// dodati fju koja uzima slobodne sale za odabrani datum i vreme
		// (proci kroz sve preglede i videti 
	},
	mounted () {
           axios
          .get('api/tippregleda/all')
          .then(res => {
        	  this.tipoviPregleda = res.data;

          })
          
           axios
          .get('api/lekar/all')
          .then(res => {
        	  this.lekari = res.data;

          })
          
           axios
          .get('api/sala/all')
          .then(res => {
        	  this.sale = res.data;

          })
    },

});
Vue.component('recept', {
	data: function(){
	return{
		lekovi:{},
		greska1: '',
		dbError: ''
		
	   
	}
	template: `
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
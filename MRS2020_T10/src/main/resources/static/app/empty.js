Vue.component('empty', {
	data: function(){		
		   return {
		    	kor : {},
		    	korisnik : {
		    		email : "",
		    		password : "",
		    	},
		    	uspesanLogin : true,
		    	promenioLozinku : true,
		    	token : "",
		    	uloga : "",
		    	greska: ''
		    }
	},

	template: `

<div class="container">
	<section id="content">
		<form action="">
			<h1>Login</h1>
			<div>
				<input type="text" placeholder="Email" required="" id="username" v-model="korisnik.username"/>
			</div>
			<div>
				<input type="password" placeholder="Password" required="" id="password" v-model="korisnik.password"/>
			</div>
			<div>
				<button v-on:click="login()" class="btn btn-success">Log in</button>
				<a href="#">Lost your password?</a>
				<a href="/#/registracija">Register</a>
			</div>
		</form><!-- form -->

	</section><!-- content -->
</div><!-- container -->

	`, 
	methods : {
		login : function () {
			axios
			.post('auth/login', this.korisnik)
			.then(response => (this.validiraj(response.data)))
			.catch(function (error) { 
				console.log(error);
				this.uspesanLogin = false;
				this.greska = "Probajte ponovo.";
			});

		},
		validiraj : function (korisnikToken) {
			this.token = korisnikToken.accessToken;
			if (this.token == null) {
				this.uspesanLogin = false;
				this.nijeAktiviran = false;
				this.jesteAktivanNijeVerifikovan = false;
				this.promenioLozinku = true;
			} else {
				this.uspesanLogin = true;
				this.nijeAktiviran = false;
				this.jesteAktivanNijeVerifikovan = false;
				axios
				.get('/auth/dobaviUlogovanog', { headers: { Authorization: 'Bearer ' + this.token }} )
		        .then(response => {
		        	this.kor = response.data;
					localStorage.setItem("token", this.token);
						
					console.log(this.kor.ime);
					axios
		    		.put('/auth/dobaviulogu', this.kor, { headers: { Authorization: 'Bearer ' + this.token }} )
		            .then(response => {
		            	this.uloga = response.data;
		            	if (this.uloga == "ROLE_PACIJENT") {
		            		console.log("PACIJENT JE");
		            		this.$router.push('/pacijent');
		            	} else if (this.uloga == "ROLE_LEKAR") {
		            		console.log("LEKAR JE");
		            		this.$router.push('/lekar');
		            	} else if (this.uloga == "ROLE_MED_SESTRA") {
		            		console.log("MED SESTRA JE");
		            		//this.$router.replace({ name: 'pacijenti' });
		            	} else if (this.uloga == "ROLE_ADMIN_KLINICKOG_CENTRA") {
		            		console.log("ADMIN KC JE");
		            		this.$router.push('/superprofil');
		            	} else if (this.uloga == "ROLE_ADMIN_KLINIKE") {
		            		console.log("ADMIN JE");
		            		this.$router.push('/admin');
		            	} else {
		            		this.greska = "Probajte ponovo.";
		            	}
		            })
		            .catch(function (error) { console.log(error); this.greska = "Probajte ponovo.";});
		        })
		        .catch(function (error) { console.log(error); this.greska = "Probajte ponovo.";});
			}
		}
	}

});
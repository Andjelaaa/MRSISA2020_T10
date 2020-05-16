Vue.component('calendar_ned', {
	data:function() {
		return{
			korisnik:{},
			filterDate: undefined,
		    selectedWeek: new Date(),
		    currentMonthAndYear: 'Maj 2020',
		    token:'',
		    pocetak:'',
		    kraj:'',
		    uloga:'',
		    mondayyyy:''
		}
	    
	  },
	  template:`
	  <div>
		  <nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  	<a  class="navbar-brand" href="#/lekar">Pocetna</a>
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <li class="nav-item active">
		        <a class="nav-link" href="#/pacijenti">Pacijenti</a>
		      </li>
		      
		      <li class="nav-item">
		        <a class="nav-link" href="#/odmor">Zahtev za godisnji odmor/odsustvo</a>
		      </li>
		      
		      <li class="nav-item">
		         <a  class="nav-link" href="#/kalendarlekar">Radni kalendar</a>
		      </li>
		      <li class="nav-item">
		        <a  class="nav-link" href="#/profil">Profil: {{korisnik.ime}} {{korisnik.prezime}}</a>
		      </li>
		    </ul>
		    <form class="form-inline my-2 my-lg-0">
		      
		      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="odjava()">Odjavi se</button>
		    </form>
		  </div>
		</nav>
		</br>
		
		<br>
		<ul class="nav nav-tabs">
		<li class="nav-item">
		    <a class="nav-link active" href="#/kalendarnedeljni">Nedeljni Kalendar</a>
		  </li>
		 <li class="nav-item">
		    <a class="nav-link" href="#/kalendarlekar">Mesecni Kalendar</a>
		  </li>
		  
		  <li class="nav-item">
		    <a class="nav-link" href="#/kalendargodisnji">Godisnji Kalendar</a>
		  </li>
		</ul>

		<div class="container" id="app">
		  <div class="row">
	    <div class="col-md-12">
	      <h3>
	      Radni kalendar
	      </h3>
	
	      <transition name="fade">
	        <div class="alert alert-success" v-if="filterDate != undefined"> Date selected is: {{formattedDate}}</div>
	      </transition>
	      <div id="app-table" class="table-responsive">
	        <table class="table table-bordered ">
	          <thead class="thead-default">
	            <tr>
	              <th colspan="1">
	                <a href="#" class="prev" v-on:click="previousWeek"> prethodna </a>
	              </th>
	              <th colspan="5" class="center-title">
	              {{currentMonthAndYear}}
	              </th>
	              <th colspan="1">
	                <a href="#" class="next" v-on:click="nextWeek"> sledeca</a>
	              </th>
	            </tr>
	            <tr>
		  		 
	              <th>Ponedeljak</th>
	              <th>Utorak</th>
	              <th>Sreda</th>
	              <th>Cetvrtak</th>
	              <th>Petak</th>
	              <th>Subota</th>
	              <th>Nedelja</th>
	              
	            </tr>
	          </thead>
	         
	          <tbody class="tbody-default" data-bind="foreach:gridArray">
	            <tr>
	            	<td v-for="item in gridArray">
		          	 	{{item.getDate()}}
				  	     <br>
				  	     {{pocetak}}-{{kraj}}			  	    
	             	</td>
	            </tr>
	
	          </tbody>
	        </table>
	      </div>
	
		  </div>
		  </div>
		  
		  </div>
  </div>`,

	  methods: {
	    previousWeek: function() {
	      var monday = new Date(this.mondayyyy.getTime()-7*24*60*60*1000);
	      this.selectedWeek = monday;
	      this.currentMonthAndYear = moment(monday).format('MMM YYYY');
	    },
	    nextWeek: function() {
	      var monday = new Date(this.mondayyyy.getTime()+7*24*60*60*1000);
		  this.selectedWeek = monday;
	      this.currentMonthAndYear = moment(monday).format('MMM YYYY');
	    },
	    setDate: function(date) {
	      if (date == this.filterDate) {
	        console.log('setting undefined');
	        this.filterDate = undefined;
	        //unselected
	      } else {
	        this.filterDate = date;
	      }
	    },
	    isActive: function(date) {
	      return date === this.filterDate;
	    },
	    getCalendarMatrix: function(date) {
	
	      var day_milliseconds = 24*60*60*1000;
	      var dates = [];
	     
	      var monday = new Date(date.getTime()-(date.getDay()-1)*day_milliseconds);
	      this.mondayyyy = monday;
	      var sunday = new Date(monday.getTime()+6*day_milliseconds);
	      dates.push(monday);
	      for(var i = 1; i < 6; i++){
	    	  dates.push(new Date(monday.getTime()+i*day_milliseconds));
	      }
	      dates.push(sunday);
	      return dates;
	    }
	  },
	  computed: {
	    // a computed getter
	    gridArray: function() {
	      var grid = this.getCalendarMatrix(this.selectedWeek);
	      return grid;
	    },
	    formattedDate: function() {
	      return this.filterDate ? moment(this.filterDate).format('lll') : '';
	    }
	  }
	  ,
	  mounted(){
	  	 
		  this.token = localStorage.getItem("token");
			axios
			.get('/auth/dobaviUlogovanog', { headers: { Authorization: 'Bearer ' + this.token }} )
		    .then(response => { 
		    	this.korisnik = response.data;	
		    	
		    	this.pocetak = this.korisnik.rvPocetak;
		    	this.kraj=this.korisnik.rvKraj;
			    });
		}
		

	});
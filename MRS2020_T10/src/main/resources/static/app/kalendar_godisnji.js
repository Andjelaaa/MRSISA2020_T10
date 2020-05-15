Vue.component('calendar_god', {
	data:function() {
		return{
			korisnik:{},
			filterDate: undefined,
		    selectedYear: new Date(),
		    currentMonthAndYear: 'Maj 2020',
		    token:'',
		    pocetak:'',
		    kraj:'',
		    uloga:''
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
		    <a class="nav-link" href="#/kalendarnedeljni">Nedeljni Kalendar</a>
		  </li>
		 <li class="nav-item">
		    <a class="nav-link " href="#/kalendarlekar">Mesecni Kalendar</a>
		  </li>
		  
		  <li class="nav-item">
		    <a class="nav-link active" href="#/kalendargodisnji">Godisnji Kalendar</a>
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
	      
	      
	      <table class="table table-bordered " v-for="nn in gridArray">
	      	  <tbody>
	      
			      <div id="app-table" class="table-responsive">
			        <table class="table table-bordered ">
			          <thead class="thead-default">
			            <tr>
			              <th colspan="7" class="center-title">
			                {{currentMonthAndYear}}
			              </th>
			            </tr>
			            <tr>
			              <th>P</th>
			              <th>U</th>
			              <th>S</th>
			              <th>C</th>
			              <th>P</th>
			              <th>S</th>
			              <th>N</th>
			            </tr>
			          </thead>
			         
			          <tbody class="tbody-default" >
			            <tr v-for="ned in nn">
			              	<td v-for="date in ned">
					  	     	{{date.getDate()}}
		  							<br>
					  	     	{{pocetak}}-{{kraj}}
					  	    
					  	    
			              </td>
			            
			            </tr>
			
			          </tbody>
			        </table>
			      </div>
			     </tbody>
		  		</table>
		  </div>
		  </div>
		  
		  </div>
  </div>`,
  //<a href="#" v-on:click="setDate(data)" v-bind:class="{'cal-selected':isActive(data)}">
  //{{date.getDate()}}
  //</a>
  //ne diraj, treba mi za lekara
	  methods: {
	   
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
	    getCalendarMatrix: function(month) {
	      var calendarMatrix = []
	      year= this.selectedYear.getFullYear();
	      var startDay = new Date(year, month, 1)
	      var lastDay = new Date(year, month + 1, 0)

	      // Modify the result of getDay so that we treat Monday = 0 instead of Sunday = 0
	      var startDow = (startDay.getDay() + 6) % 7;
	      var endDow = (lastDay.getDay() + 6) % 7;

	      // If the month didn't start on a Monday, start from the last Monday of the previous month
	      startDay.setDate(startDay.getDate() - startDow);

	      // If the month didn't end on a Sunday, end on the following Sunday in the next month
	      lastDay.setDate(lastDay.getDate() + (6 - endDow));

	      var week = [];
	   
	      while (startDay <= lastDay) {
	        week.push(new Date(startDay))
	        
	        if (week.length === 7) {
	          calendarMatrix.push(week);
	          week = [];
	         
	        }
	        startDay.setDate(startDay.getDate() + 1);
	      }
	      
	      return calendarMatrix;
	    },
	    
	    functionYear: function() {
	    	var grid =[]
	    	//salji godinu i mesec
	    	for(let i=1;i<=12;i++){
	    		
	    		 grid.push(this.getCalendarMatrix(i));
			     
	    	}
	    	return grid;
		},
	    
	  },
	  computed: {
	    // treba celu godinu da vrati 
	    gridArray: function() {
	      var grid = this.functionYear();
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
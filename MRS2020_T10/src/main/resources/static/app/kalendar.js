Vue.component('calendar', {
	data:function() {
		return{
			medicinska_sestra:{},
			filterDate: undefined,
		    selectedMonth: new Date(),
		    currentMonthAndYear: 'Maj 2020',
		    token:'',
		    pocetak:'',
		    kraj:''
		}
	    
	  },
	  template:`
	  <div>
		  <nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#/med_sestra_pocetna">Pocetna</a>
		
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <li class="nav-item">
		        <a class="nav-link" href="#/pacijenti">Pacijenti</a>
		      </li>
		      
		      <li class="nav-item">
		        <a class="nav-link" href="#/odmor">Zahtev za godisnji odmor/odsustvo</a>
		      </li>
		       <li class="nav-item">
		        <a class="nav-link" href="#/overa">Overa recepata</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/kalendarr">Radni kalendar</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#/medsestra">Profil: {{medicinska_sestra.ime}} {{medicinska_sestra.prezime}}</a>
		      </li>
		    </ul>
		    <form class="form-inline my-2 my-lg-0">
		      
		      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="odjava()">Odjavi se</button>
		    </form>
		  </div>
		</nav>
		</br>
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
	        <table class="table table-bordered fullscreen">
	          <thead class="thead-default">
	            <tr>
	              <th colspan="1">
	                <a href="#" class="prev" v-on:click="previousMonth"> prethodni </a>
	              </th>
	              <th colspan="5" class="center-title">
	                {{currentMonthAndYear}}
	              </th>
	              <th colspan="1">
	                <a href="#" class="next" v-on:click="nextMonth"> sledeci </a>
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
	            <tr v-for="item in gridArray">
	              <td v-for="date in item">
			  	     {{date.getDate()}}
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
  //<a href="#" v-on:click="setDate(data)" v-bind:class="{'cal-selected':isActive(data)}">
  //{{date.getDate()}}
  //</a>
  //ne diraj, treba mi za lekara
	  methods: {
	    previousMonth: function() {
	      var tmpDate = this.selectedMonth;
	      var tmpMonth = tmpDate.getMonth() - 1;
	      this.selectedMonth = new Date(tmpDate.setMonth(tmpMonth));
	      this.currentMonthAndYear = moment(this.selectedMonth).format('MMM YYYY');
	    },
	    nextMonth: function() {
	      var tmpDate = this.selectedMonth;
	      var tmpMonth = tmpDate.getMonth() + 1;
	      this.selectedMonth = new Date(tmpDate.setMonth(tmpMonth));
	      this.currentMonthAndYear = moment(this.selectedMonth).format('MMM YYYY');
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
	      var calendarMatrix = []

	      var startDay = new Date(date.getFullYear(), date.getMonth(), 1)
	      var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0)

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
	    }
	  },
	  computed: {
	    // a computed getter
	    gridArray: function() {
	      var grid = this.getCalendarMatrix(this.selectedMonth);
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
		    	this.medicinska_sestra = response.data;	
		    	
		    	this.pocetak = this.medicinska_sestra.radvr_pocetak;
		    	this.kraj=this.medicinska_sestra.radvr_kraj;
			    });
		}
		

	});
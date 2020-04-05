const Empty = { template: '<empty></empty>' }
const DodavanjeTipaPregleda = { template: '<dodtipapregleda></dodtipapregleda>' }
const RegistracijaKlinike = { template: '<regklinike></regklinike>' }
const StranicaZaRegistraciju = { template: '<strzareg></strzareg>' }


const router = new VueRouter({
	mode: 'hash',
	  routes: [
	    { path: '/', component: Empty},
	    { path: '/dtpregled', component: DodavanjeTipaPregleda},
	    { path: '/regklinika', component: RegistracijaKlinike},
	    { path: '/registracija', component: StranicaZaRegistraciju}
	    
	  ]
});

var app = new Vue({
	router,
	el: '#clinic'
});


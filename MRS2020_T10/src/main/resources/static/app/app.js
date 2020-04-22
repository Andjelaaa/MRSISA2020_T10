const Empty = { template: '<empty></empty>' }
const DodavanjeTipaPregleda = { template: '<dodtipapregleda></dodtipapregleda>' }
const RegistracijaKlinike = { template: '<regklinike></regklinike>' }
const StranicaZaRegistraciju = { template: '<strzareg></strzareg>' }
const ProfilSuperAdmina = { template: '<superprofil></superprofil>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
	    { path: '/', component: Empty},
	    { path: '/dtpregled', component: DodavanjeTipaPregleda},
	    { path: '/regklinika', component: RegistracijaKlinike},
	    { path: '/registracija', component: StranicaZaRegistraciju},
	    { path: '/sprofil', component: ProfilSuperAdmina}
	    
	  ]
});

var app = new Vue({
	router,
	el: '#klinika'
});


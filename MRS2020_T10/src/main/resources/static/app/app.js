const Empty = { template: '<empty></empty>' }
const DodavanjeTipaPregleda = { template: '<dodtipapregleda></dodtipapregleda>' }
const RegistracijaKlinike = { template: '<regklinike></regklinike>' }
const StranicaZaRegistraciju = { template: '<strzareg></strzareg>' }
const ProfilSuperAdmina = { template: '<superprofil></superprofil>' }
const DodavanjeSale = {template: '<dodsale></dodsale>'}
const DodavanjeLekara = {template: '<dodlekara></dodlekara>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
	    { path: '/', component: Empty},
	    { path: '/dtpregled', component: DodavanjeTipaPregleda},
	    { path: '/regklinika', component: RegistracijaKlinike},
	    { path: '/registracija', component: StranicaZaRegistraciju},
	    { path: '/sprofil', component: ProfilSuperAdmina},
	    { path: '/dsala', component: DodavanjeSale},
	    {path: '/dlekar', component: DodavanjeLekara}
	    
	  ]
});

var app = new Vue({
	router,
	el: '#klinika'
});
const Empty = { template: '<empty></empty>' }
const DodavanjeTipaPregleda = { template: '<dodtipapregleda></dodtipapregleda>' }
const RegistracijaKlinike = { template: '<regklinike></regklinike>' }
const StranicaZaRegistraciju = { template: '<strzareg></strzareg>' }
const ProfilSuperAdmina = { template: '<superprofil></superprofil>' }
const DodavanjeSale = {template: '<dodsale></dodsale>'}
const DodavanjeLekara = {template: '<dodlekara></dodlekara>'}
const SifrarnikLekova = {template: '<sifrarnik1></sifrarnik1>'}
const SifrarnikDijagnoza = {template: '<sifrarnik2></sifrarnik2>'}
const KreirajZK = {template: '<kreirajzk></kreirajzk>'}
const OdobravanjeReg = {template: '<odobri_zahtev></odobri_zahtev>'}
const LekarPocetna = {template: '<lekar></lekar>'}
const AdminPocetna = {template: '<admin></admin>'}
const DodavanjePregleda = {template: '<dpregled></dpregled>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
	    { path: '/', component: Empty},
	    { path: '/dtpregled', component: DodavanjeTipaPregleda},
	    { path: '/regklinika', component: RegistracijaKlinike},
	    { path: '/registracija', component: StranicaZaRegistraciju},
	    { path: '/sprofil', component: ProfilSuperAdmina},
	    { path: '/dsala', component: DodavanjeSale},
	    {path: '/dlekar', component: DodavanjeLekara},
	    {path: '/sifrarnik1', component: SifrarnikLekova},
	    {path: '/sifrarnik2', component: SifrarnikDijagnoza},
	    {path: '/kreirajzk', component: KreirajZK},
	    {path: '/odobri_zahtev', component: OdobravanjeReg},
	    {path: '/lekar', component: LekarPocetna},
	    {path: '/admin', component: AdminPocetna},
	    {path: '/dpregled', component: DodavanjePregleda}
	    
	  ]
});

var app = new Vue({
	router,
	el: '#klinika'
});
const Empty = { template: '<empty></empty>' }
const RegistracijaKlinike = { template: '<regklinike></regklinike>' }
const StranicaZaRegistraciju = { template: '<strzareg></strzareg>' }
const ProfilSuperAdmina = { template: '<superprofil></superprofil>' }
const SifrarnikLekova = {template: '<sifrarnik1></sifrarnik1>'}
const SifrarnikDijagnoza = {template: '<sifrarnik2></sifrarnik2>'}
const KreirajZK = {template: '<kreirajzk></kreirajzk>'}
const OdobravanjeReg = {template: '<odobri_zaht></odobri_zaht>'}
const LekarPocetna = {template: '<lekar></lekar>'}
const AdminPocetna = {template: '<admin></admin>'}
const DodavanjePregleda = {template: '<dpregled></dpregled>'}
const TipoviPregleda = {template: '<tipovipregleda></tipovipregleda>'}
const PredefPregledi = {template: '<predefpregledi></predefpregledi>'}
const DodavanjeAdmina = {template: '<dodajadmina></dodajadmina>'}
const ProfilMedSestre = {template: '<medsestra></medsestra>'}
const Recept = {template: '<recept></recept>'}
const ProfilKlinike = {template: '<klinika></klinika>'}
const Sale = {template: '<sale></sale>'}
const Lekari = {template: '<lekari></lekari>'}
const PacijentPocetna = {template: '<pacijent></pacijent>'}
const ZakazaniPregledi = {template: '<zakazani-pregledi></zakazani-pregledi>'}
const KlinikePrikaz = {template: '<klinike-prikaz></klinike-prikaz>'}
const KlinikaDetalji = {template: '<klinika-detalji></klinika-detalji>'}
const PotvrdaPacijenta = {template: '<potvrdareg></potvrdareg>'}
const PacijentiLista = {template: '<pacijenti></pacijenti>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
	    { path: '/', component: Empty},
	    { path: '/regklinika', component: RegistracijaKlinike},
	    { path: '/registracija', component: StranicaZaRegistraciju},
	    { path: '/sprofil', component: ProfilSuperAdmina},
	    { path: '/sifrarnik1', component: SifrarnikLekova},
	    { path: '/sifrarnik2', component: SifrarnikDijagnoza},
	    { path: '/kreirajzk', component: KreirajZK},
	    { path: '/odobri_zahtev', component: OdobravanjeReg},
	    { path: '/lekar', component: LekarPocetna},
	    { path: '/admin', component: AdminPocetna},
	    { path: '/dpregled', component: DodavanjePregleda},
	    { path: '/tipovipregleda', component: TipoviPregleda},
	    { path: '/predefinisanipregledi/:name', component: PredefPregledi},
	    { path: '/dadmin', component: DodavanjeAdmina},
	    { path: '/medsestra', component: ProfilMedSestre},
	    { path: '/recept', component: Recept},
	    { path: '/profilklinike', component: ProfilKlinike},
	    { path: '/sale', component: Sale},
	    { path: '/lekari', component: Lekari},
	    { path: '/pacijent', component: PacijentPocetna},
	    { path: '/pacijentpregledi', component: ZakazaniPregledi},
	    { path: '/klinike', component: KlinikePrikaz},
	    { path: '/detaljiKlinike/:name', component: KlinikaDetalji},
	    { path: '/potvrdiRegistraciju/:token', component: PotvrdaPacijenta},
	    { path: '/pacijenti', component: PacijentiLista},
	    ]
});

Vue.filter('formatDate', function(value) {
	  if (value) {
	    return moment(String(value)).format('DD/MM/YYYY hh:mm')
	  }
});

var app = new Vue({
	router,
	el: '#klinika'
});
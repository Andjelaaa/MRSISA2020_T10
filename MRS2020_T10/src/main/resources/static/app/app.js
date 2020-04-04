const Empty = { template: '<empty></empty>' }


const router = new VueRouter({
	mode: 'hash',
	  routes: [
	    { path: '/', component: Empty}
	  ]
});

var app = new Vue({
	router,
	el: '#clinic'
});


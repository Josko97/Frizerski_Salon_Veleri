const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/IndexPage.vue'),
      },
      {
        path: '/rezervacija',
        component: () => import('pages/ReservationPage.vue'),
      },
      {
        path: '/login',
        component: () => import('pages/LoginPage.vue'),
      },
      {
        path: '/register',
        component: () => import('pages/RegisterPage.vue'),
      },
      {
        path: '/kontakt',
        component: () => import('pages/FormaPage.vue'),
      },
    ],
  },
]

export default routes

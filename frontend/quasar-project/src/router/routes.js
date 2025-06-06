const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '/',
        component: () => import('pages/HomePage.vue'),
      },

      {
        path: '/rezervacija',
        component: () => import('pages/ReservationPage.vue'),
      },
      {
        path: '/o-nama',
        component: () => import('pages/AboutPage.vue'),
      },
      {
        path: '/registracija',
        component: () => import('pages/RegisterPage.vue'),
      },
      {
        path: '/login',
        component: () => import('pages/LoginPage.vue'),
      },

    ],
  },
]

export default routes

const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '/home',
        component: () => import('pages/HomePage.vue'),
      },

      {
        path: '/rezervacija',
        component: () => import('pages/ReservationPage.vue'),
      },
    ],
  },
]

export default routes

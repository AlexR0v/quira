import { lazy }          from 'react'
import { Route, Routes } from 'react-router'

const Home = lazy(() => import('../pages/home.tsx'))
const SignIn = lazy(() => import('@/pages/auth/sign-in/page.tsx'))
const SignUp = lazy(() => import('@/pages/auth/sign-up/page.tsx'))
const AuthLayout = lazy(() => import('@/pages/auth/layout.tsx'))

export const Router = () => {
  return (
    <Routes>
      <Route
        index
        element={<Home />}
      />
      <Route element={<AuthLayout />}>
        <Route
          path='sign-in'
          element={<SignIn />}
        />
        <Route
          path='sign-up'
          element={<SignUp />}
        />
      </Route>
    </Routes>
  )
}

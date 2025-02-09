import { lazy }          from 'react'
import { Route, Routes } from 'react-router'

const Home = lazy(() =>import('../pages/home.tsx'))
const About = lazy(() =>import('../pages/about.tsx'))

export const Router = () => {
  return (
    <Routes>
      <Route index element={<Home />} />
      <Route path="about" element={<About />} />
    </Routes>
  )
}

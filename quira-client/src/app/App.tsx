import { Loader }                           from '@/components/ui/loader.tsx'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { Suspense }                         from 'react'
import { BrowserRouter }                    from 'react-router'
import { Router }                           from './router.tsx'

const queryClient = new QueryClient()

export const App = () => {
  
  return (
    <QueryClientProvider client={queryClient}>
      <BrowserRouter>
        <Suspense fallback={<Loader />}>
          <Router />
        </Suspense>
      </BrowserRouter>
    </QueryClientProvider>
  )
}

export default App

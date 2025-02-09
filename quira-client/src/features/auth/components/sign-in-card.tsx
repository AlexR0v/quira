import { Button }                                                         from '@/components/ui/button.tsx'
import { Card, CardContent, CardHeader, CardTitle }                       from '@/components/ui/card.tsx'
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from '@/components/ui/form.tsx'
import { Input }                                                          from '@/components/ui/input.tsx'
import { Separator }                                                      from '@/components/ui/separator.tsx'
import { zodResolver }                                                    from '@hookform/resolvers/zod'
import { useForm }                                                        from 'react-hook-form'
import { Link }                                                           from 'react-router'
import { z }                                                              from 'zod'

const formSchema = z.object({
  email: z.string().email({ message: 'Неверный email' }),
  password: z.string()
    .min(5, { message: 'Пароль должен быть не менее 5 символов' })
    .max(250, { message: 'Пароль должен быть не более 250 символов' }),
})

export const SignInCard = () => {
  
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      email: '',
      password: '',
    },
  })
  
  const onSubmit = (values: z.infer<typeof formSchema>) => {
    console.log(values)
    //mutation.mutate(values as any)
  }
  
  return (
    <Card className='w-full h-full md:w-[487px] border-none shadow-none'>
      <CardHeader className='flex items-center justify-center text-center p-7'>
        <CardTitle className='text-2xl'>Войти в аккаунт</CardTitle>
      </CardHeader>
      <div className='px-7'>
        <Separator />
      </div>
      <CardContent className='p-7'>
        <Form {...form}>
          <form
            onSubmit={form.handleSubmit(onSubmit)}
            className='space-y-4'
          >
            <FormField
              control={form.control}
              name='email'
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Email</FormLabel>
                  <FormControl>
                    <Input placeholder='Введите email' {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name='password'
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Пароль</FormLabel>
                  <FormControl>
                    <Input placeholder='Введите пароль' {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <Button
              className='w-full'
              disabled={false}
            >
              Войти
            </Button>
          </form>
        </Form>
      </CardContent>
      <div className='px-7'>
        <Separator />
      </div>
      <CardContent className='p-7 flex items-center justify-center'>
        Нет аккаунта?{' '}
        <Link
          to='/sign-up'
          className='text-blue-500'
        >
          Зарегистририроваться
        </Link>
      </CardContent>
    </Card>
  )
}
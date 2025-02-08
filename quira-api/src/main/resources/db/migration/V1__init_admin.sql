INSERT INTO public.users (id, username, email, password, role)
values (1, 'admin', 'admin@admin.com', '$2a$10$JuL4w0R.8sq9jqx7Pa49xeqmkmQIEG2QPVKQxsRQkk83XFOvdRo1W', 'ROLE_ADMIN')
ON CONFLICT DO NOTHING
RETURNING id;
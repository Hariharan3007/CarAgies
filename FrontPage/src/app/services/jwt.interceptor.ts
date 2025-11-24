import { HttpInterceptorFn } from '@angular/common/http';

export const JwtInterceptor: HttpInterceptorFn = (req, next) => {

  if (req.url.includes('/login') || req.url.includes('/signup')) {
    return next(req);
  }

  const token = localStorage.getItem('jwt');

  if (token) {
    const modifiedReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });

    console.log('AUTH HEADER:', modifiedReq.headers.get('Authorization'));
    console.log('REQUEST URL :', modifiedReq.url);

    return next(modifiedReq);
  }

  console.log('NO TOKEN, REQUEST URL:', req.url);
  return next(req);
};

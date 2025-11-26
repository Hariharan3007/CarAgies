import { HttpInterceptorFn } from '@angular/common/http';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {

  if(req.url.includes('/login') || req.url.includes('/signup')){
    return next(req);
  }

  const token = localStorage.getItem('auth_token');

  if(token && token.length > 0){
      const newReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
    }
  })
  console.log(newReq.headers);
  return next(newReq);
  }
  return next(req);
};

package org.sinerji.util;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

@Interceptor
@Transacional
@Priority(Interceptor.Priority.APPLICATION)
public class TransacionalInterceptor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager entityManager;

    @AroundInvoke
    public Object invoke(InvocationContext ctx) throws Exception {
        EntityTransaction tx = entityManager.getTransaction();
        boolean criador = false;

        try{
            if(!tx.isActive()){
                tx.begin();
                tx.rollback();

                tx.begin();

                criador = true;
            }

            return ctx.proceed();
        } catch(Exception e){
            if(tx != null && criador){
                tx.rollback();
            }
            throw e;
        } finally {
            if(tx != null && criador){
                tx.commit();
            }
        }
    }

}

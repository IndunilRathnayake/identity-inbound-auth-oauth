package org.wso2.carbon.identity.scope.endpoint.factories;

import org.wso2.carbon.identity.scope.endpoint.ScopesApiService;
import org.wso2.carbon.identity.scope.endpoint.impl.ScopesApiServiceImpl;

public class ScopesApiServiceFactory {

   private final static ScopesApiService service = new ScopesApiServiceImpl();

   public static ScopesApiService getScopesApi()
   {
      return service;
   }
}

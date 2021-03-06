/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.weld.extensions.log;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <code>LoggerProducer</code> provides a producer method for all
 * @Logger annotated log objects.  Each logger is application scoped
 * since the logger applies to the class, not each instance of the
 * class.
 * 
 * @author David Allen
 *
 */
class LoggerProducer
{
   
   @Produces
   public Logger produceLog(InjectionPoint injectionPoint)
   {
      if (injectionPoint.getAnnotated().isAnnotationPresent(Category.class))
      {
         String category = injectionPoint.getAnnotated().getAnnotation(Category.class).value();
         return LoggerFactory.getLogger(category);
      }
      else
      {
         return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
      }
   }
   
}

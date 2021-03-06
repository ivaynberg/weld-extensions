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
package org.jboss.weld.extensions.test.bean.generic.method;

import javax.enterprise.inject.Produces;

/**
 * A producer of generic beans
 * @author pmuir
 *
 */
public class GenericBeanProducer
{
   @Foo(1)
   @Produces
   @Message("hello1")
   public Baz getBaz1()
   {
      return null;
   }

   @Foo(2)
   @Produces
   @Message("hello2")
   public Baz getBaz2()
   {
      return null;
   }
   
   @Foo(1)
   @Produces
   @Service(1)
   public Waldo getWaldo1()
   {
      return new Waldo("Pete");
   }
   
   @Foo(2)
   @Produces
   @Service(2)
   public Waldo getWaldo2()
   {
      return new Waldo("Stuart");
   }

}

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
package org.jboss.weld.extensions.annotated;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author Stuart Douglas
 *
 */
public class AnnotationBuilder
{
   private final HashMap<Class<? extends Annotation>, Annotation> annotationMap = new HashMap<Class<? extends Annotation>, Annotation>();
   private final Set<Annotation> annotationSet = new HashSet<Annotation>();

   public AnnotationBuilder add(Annotation annotation)
   {
      annotationSet.add(annotation);
      annotationMap.put(annotation.annotationType(), annotation);
      return this;
   }

   public AnnotationBuilder remove(Class<? extends Annotation> annotation)
   {
      Iterator<Annotation> it = annotationSet.iterator();
      while (it.hasNext())
      {
         Annotation an = it.next();
         if (annotation.isAssignableFrom(an.annotationType()))
         {
            it.remove();
         }
      }
      annotationMap.remove(annotation);
      return this;
   }

   AnnotationStore create()
   {
      return new AnnotationStore(annotationMap, annotationSet);
   }
   
   public AnnotationBuilder addAll(Set<Annotation> annotations)
   {
      for (Annotation annotation : annotations)
      {
         add(annotation);
      }
      return this;
   }
   
   public AnnotationBuilder addAll(AnnotationStore annotations)
   {
      for (Annotation annotation : annotations.getAnnotations())
      {
         add(annotation);
      }
      return this;
   }

   public AnnotationBuilder addAll(AnnotatedElement element)
   {
      for (Annotation a : element.getAnnotations())
      {
         add(a);
      }
      return this;
   }

   public <T extends Annotation> T getAnnotation(Class<T> anType)
   {
      return (T) annotationMap.get(anType);
   }
   
   public boolean isAnnotationPresent(Class<?> annotationType)
   {
      return annotationMap.containsKey(annotationType);
   }
   
   @Override
   public String toString()
   {
      return annotationSet.toString();
   }

}

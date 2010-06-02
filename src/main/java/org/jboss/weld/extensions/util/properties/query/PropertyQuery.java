package org.jboss.weld.extensions.util.properties.query;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.jboss.weld.extensions.util.properties.Properties;
import org.jboss.weld.extensions.util.properties.Property;

/**
 * Queries a target class for properties that match certain criteria.  A
 * property may either be a private or public field, declared by the target 
 * class or inherited from a superclass, or a public method declared by the
 * target class or inherited from any of its superclasses.  For properties that
 * are exposed via a method, the property must be a JavaBean style property, i.e.
 * it must provide both an accessor and mutator method according to the JavaBean
 * specification.
 * 
 * This class is not thread-safe, however the result returned by the
 * getResultList() method is.
 * 
 * @author Shane Bryzak
 */
public class PropertyQuery<V>
{
   private final Class<?> targetClass;
   private final List<PropertyCriteria> criteria;
   
   PropertyQuery(Class<?> targetClass)
   {
      this.targetClass = targetClass;
      this.criteria = new ArrayList<PropertyCriteria>();
   }
   
   public PropertyQuery<V> addCriteria(PropertyCriteria criteria)
   {
      this.criteria.add(criteria);
      return this;
   }
   
   public List<Property<V>> getResultList()
   {
      List<Property<V>> results = new ArrayList<Property<V>>();

      Class<?> cls = targetClass;
      while (!cls.equals(Object.class))
      {
         // First check declared fields
         for (Field field : cls.getDeclaredFields())
         {
            for (PropertyCriteria c : criteria)
            {                     
               if (c.fieldMatches(field))
               {
                  results.add(Properties.<V>createProperty(field));
               }
            }
         }
         
         cls = cls.getSuperclass();
      }

      // Then check public methods (we ignore private methods)
      for (Method method : targetClass.getMethods())
      {
         for (PropertyCriteria c : criteria)
         {
            if (c.methodMatches(method))
            {
               results.add(Properties.<V>createProperty(method));
            }
         }
      }      
      
      return results;
   }
}

<<<<<<< HEAD
package com.maxheapsize.jsf2demo;
 
import javax.faces.bean.*;
 
@ManagedBean(name="demoService")
@ApplicationScoped
public class Service {
 
  public String reverse(String name) {
    return new StringBuffer(name).reverse().toString().toLowerCase();
  }
=======
package com.maxheapsize.jsf2demo;
 
import javax.faces.bean.*;
 
@ManagedBean(name="demoService")
@ApplicationScoped
public class Service {
 
  public String reverse(String name) {
    return new StringBuffer(name).reverse().toString().toLowerCase();
  }
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
}
package com.maxheapsize.jsf2demo;
 
import javax.faces.bean.*;
 
@ManagedBean(name="demoService")
@ApplicationScoped
public class Service {
 
  public String reverse(String name) {
    return new StringBuffer(name).reverse().toString().toLowerCase();
  }
}
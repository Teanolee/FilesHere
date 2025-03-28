/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

/**
 *
 * @author Tino Li
 */
abstract class User{
  String username;
  String password;
  
  public String getUsername(){
    return this.username;
  }
  public void setUsername(String username){
    this.username = username;
  }
  public String getPassword(){
    return this.password;
  }
  public void setPassword(String password){
    this.password = password;
  }
  //returns whether they can log in or not
  public boolean CheckLogIn(String username,String password){
    return ((this.username.equals(username))&&(this.password.equals(password)));
  }

}




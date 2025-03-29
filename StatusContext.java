/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package project;

/**
 *
 * @author accepting fate
 */

public class StatusContext {
    private StatusState state;
    
    public void setState(StatusState state){
        this.state = state;
    }
    
    public String request(){
        return state.handle();
    }
    
}

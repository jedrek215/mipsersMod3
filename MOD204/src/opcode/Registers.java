/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opcode;

import gui.frame;
/**
 *
 * @author macintoshuser
 */
public class Registers {
    private String value;
    private String regName;
    
    
public Registers(){
    value="0000000000000000";
    regName="R0";
}

public void setRValue(String n){
    value=n;
}

public void setRegName(String n){
    regName=n;
}

public String getRValue(){
    return value;
}


public String getRegName(){
    return regName;
}

}


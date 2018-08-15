/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opcode;

import java.util.ArrayList;

/**
 *
 * @author macintoshuser
 */
public class Code {
    private String programCounter;
    private String Address;
    private String opCode;
    private String Instruction;
    private String BinaryOPC;
    
    public Code(){
    programCounter="1000";
    Address="1000";
    opCode="";
    Instruction="";
    BinaryOPC="";
    
    
}
    public String getAdd(){
        return this.Address;
    }
    
    public String getPC(){
        return this.programCounter;
    }
    
    public String getOPC(){
        return this.opCode;
    }
    
    public String getIns(){
        return this.Instruction;
    }
    
    public String getbinOPC(){
        return this.BinaryOPC;
    }
    
    public void setAdd(String A){
        this.Address=A;
    }
    
    public void setPC(String A){
        this.programCounter=A;
    }
    
    public void setopc(String OPC){
        this.opCode=OPC;
    }
    
    public void setins(String INS){
        this.Instruction=INS;
    }
    
    public void setbinOPC(String bOPC){
        this.BinaryOPC=bOPC;
    }
    
    
    
    
}


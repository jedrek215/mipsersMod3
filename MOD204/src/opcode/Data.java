/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opcode;

/**
 *
 * @author macintoshuser
 */
public class Data {
    private String dAddress;
    private String dData;
    
    public Data(){
        dAddress="";
        dData="";
    }
    
    public void setdAddress(String Add){
        dAddress=Add;
    }
    
    public void setdData(String data){
        dData = data;
    }
    
    public String getdAddress(){
        return dAddress;
    }
    
    public String getdData(){
        return dData;
    }
    
}

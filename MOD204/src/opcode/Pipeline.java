/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opcode;
import opcode.Memory;
import opcode.Registers;
import opcode.Code;
import gui.frame;
/**
 *
 * @author macintoshuser
 */
public class Pipeline {
    private String IF_ID_IR;
    private String IF_ID_NPC;
    private String ID_EX_IR;
    private String ID_EX_NPC;
    private String ID_EX_A;
    private String ID_EX_B;
    private String ID_EX_IMM;
    private String EX_MEM_IR;
    private String EX_MEM_B;
    private String EX_MEM_ALU;
    private int EX_COND;
    private String MEM_WB_IR;
    private String MEM_WB_ALU;
    private String MEM_RANGE;
    private String MEM_WB_LMD;
    private String WB_RN;
    private int CC;
    private Registers[] registerList;
    
    public Pipeline(Registers[] r){
    IF_ID_IR="0000000000000000";
    IF_ID_NPC="0000000000000000";
    ID_EX_IR="0000000000000000";
    ID_EX_NPC="0000000000000000";
    ID_EX_A="0000000000000000";
    ID_EX_B="0000000000000000";
    ID_EX_IMM="0000000000000000";
    EX_MEM_IR="0000000000000000";
    EX_MEM_B="0000000000000000";
    EX_MEM_ALU="0000000000000000";
    EX_COND=0;
    MEM_WB_IR="0000000000000000";
    MEM_WB_ALU="0000000000000000";
    MEM_RANGE="N/A";
    MEM_WB_LMD="N/A";
    String WB_RN="0000000000000000";
    CC=0;
    registerList = r;
    }
 
    public void setIF(Code c){
        this.IF_ID_IR = c.getOPC();
        this.IF_ID_NPC = c.getPC();
    }
    
    public void setID(Code n){
        this.ID_EX_IR= n.getOPC();
        this.ID_EX_NPC= n.getOPC();
        this.ID_EX_A= registerList[Integer.parseInt(n.getbinOPC().substring(6,11),10)].getRValue();
        this.ID_EX_B= registerList[Integer.parseInt(n.getbinOPC().substring(12,17),10)].getRValue();
        this.ID_EX_IMM= "000000000000"+n.getOPC().substring(4);
    }
    
    public void setEX(Code n){
        this.EX_MEM_IR = n.getOPC();
        this.EX_MEM_B= registerList[Integer.parseInt(n.getbinOPC().substring(12,17),10)].getRValue();
        switch(n.getIns()){
            
            case 
        }
    }
    
    public void setMEM(Code n){
    
    }
    
    public void setWB(Code n){
    
    }
}

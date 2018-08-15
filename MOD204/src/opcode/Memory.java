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
public class Memory {
    private Object[] memory;
    
    
    public Memory(){
        this.memory = new Object[8192];
		for(int i = 0; i< memory.length; i++) {
			this.memory[i] = "00";
		}
    }
    
    public Object getMemoryValue(int index) {
		return memory[index];
	}
    
    public Object[] getMemory() {
		return this.memory;
    }
	
    public void storeInMemory(int index, String val) { // just used for store
		if (val.length() % 2 == 1) {
			// odd
			val = "0" + val;
		} 
		for(int j = val.length(); j > 0; j--) {
			this.memory[index] = val.substring(j - 2, j);
			j--;
			index++;
		}
    }
	
    public String loadFromMemory(int index) { // just used for load
		String load = "";
		for(int i = index; i < index + 8; i++) {
			load = this.memory[i] + load;
		}
		return load;
    }
    
    public void setOpcodeList(String opcode, int k) {
		// add the opcode instructions to memory as well
		
				this.memory[k] = opcode;
				
			
		
	}
    
    
}

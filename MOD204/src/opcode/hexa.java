package opcode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mico
 */

import java.util.*;
import gui.frame;
import javax.swing.table.DefaultTableModel;

public class hexa extends frame{
    private static final int sizeOfIntInHalfBytes = 8;
  private static final int numberOfBitsInAHalfByte = 4;
  private static final int halfByte = 0x0F;
  private static final char[] hexDigits = { 
    '0', '1', '2', '3', '4', '5', '6', '7', 
    '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
  };
  
  public static boolean checkReg(String r)
  {
        return r.toUpperCase().matches("([R][0-31])");       
  }

    public static void ld(String rt, String offset_base, Code[] c, int i){
        System.out.println("ld instruction");
        String opc = "110111";
        ldsd(opc,offset_base.substring(offset_base.indexOf("(")+1,offset_base.indexOf(")")),rt, offset_base.substring(offset_base.indexOf(",")+1,offset_base.indexOf("(")),c,i);
    }
    
    public static void sd(String rt, String offset_base, Code[] c, int i){
        System.out.println("sd instruction");
        String opc = "111111";
        ldsd(opc,offset_base.substring(offset_base.indexOf("(")+1,offset_base.indexOf(")")),rt, offset_base.substring(offset_base.indexOf(",")+1,offset_base.indexOf("(")),c,i);
    }
    
    public static void daddiu(String rs, String rt, String imm, Code[] c, int i){
        System.out.println("DADDIU parameters: " + rs + rt + imm);
        String opc = "011001";
        temp(opc, rs, rt, imm,c,i);
    }
    
    public static void xori(String rs, String rt, String imm, Code[] c, int i){
        System.out.println("XORI parameters: " + rs + rt + imm);
        String opc = "001110";
        temp(opc, rs, rt, imm,c,i);
    }
    
    public static void daddu(String rd, String rs, String rt, Code[] c, int i){
        System.out.println("DADDU parameters: " + rd + rs + rt);
         String opc = "000000";
        tempdaddu(opc, rs, rt, rd,c,i);
    }
    
    public static void slt(String rd, String rs, String rt, Code[] c, int i){
        System.out.println("SLT parameters: " + rd + rs + rt);
        String opc = "000000";
        tempslt(opc, rs, rt, rd,c,i);
    }
    
    public static void beqc(String rs, String rt, String offset, Code[] c, int i){
        System.out.println("beqc instruction");
        String opc = "001000";
        tempbeq(opc,rs,rt,offset,c,i);
    }
    
    public static void jump(String target, Code[] c, int i){
        System.out.println("jump instruction");
    }
    
    public static void nop(Code[] c, int i){
        System.out.println("NOP instruction");
        String opc = "000000";
        tempnop(opc,c,i);

    }
    
      // LABEL FIRST BEFORE BC, A-B, BC FIRST BEFORE LABEL, B-A
//    public static String distance(String offset, ArrayList<String[]> code){
//        String label = offset.concat(":");
//        int A=0, B=0;
// /*       for(int i = 0; i<code.size(); i++){
////            if((code.get(i)[0].compareToIgnoreCase("BC") == 0 || code.get(i)[0].compareToIgnoreCase("BLTC") == 0) && A == 0 )
////                if(code.get(i)[1].compareToIgnoreCase(offset) == 0 || code.get(i)[3].compareToIgnoreCase(offset) == 0  )
////                {
////                    A = i;  
////                }
////            if(code.get(i)[0].compareToIgnoreCase(labelcol) == 0)
////            {
////                 B = i;
////            }
//
//               if(Arrays.toString(code.get(i)).contains(offset))
//                   A=i;
//               else if(Arrays.toString(code.get(i)).contains(labelcol))
//                  B=i;
//        }
//        
//                return Integer.toString(B-A-1);
//               */ 
// 
//               for(int i = 0; i<code.size(); i++){
//            if((code.get(i)[0].equalsIgnoreCase("BC")|| code.get(i)[0].equalsIgnoreCase("BLTC")) && A == 0 )
//                if(code.get(i)[1].equalsIgnoreCase(offset))
//                {
//                    A = i;  
//                }
//            if(code.get(i)[0].equalsIgnoreCase(label))
//            {
//             B = i;
//            }
//    }   
//                return Integer.toString(B-A-1);
// }
public static String distance(String offset, ArrayList<String[]> code){
        String label = offset.concat(":");
        int A=0, B=0;
        
        for(int i = 0; i< code.size(); i++){
            if((code.get(i)[0].equalsIgnoreCase("BC") && A == 0 || code.get(i)[0].equalsIgnoreCase(label))){
                if(code.get(i)[1].equalsIgnoreCase(offset)){
                    A = i;  
                }
                if(code.get(i)[0].equalsIgnoreCase(label)){
                    B = i;
                }
            }
            else if(code.get(i)[0].equalsIgnoreCase("BLTC") && A == 0 || code.get(i)[0].equalsIgnoreCase(label)){
                if(code.get(i)[3].equalsIgnoreCase(offset)){
                    A = i;  
                }
                if(code.get(i)[0].equalsIgnoreCase(label)){
                    B = i;
                }
            }
    }   
                return Integer.toString(B-A-1);        
 }

    public static void dsubu(String rd, String rs, String rt, Code[] c, int i){
        System.out.println("DSUBU parameters: " + rd + rs + rt);
         String opc = "000000";
        tempdsubu(opc, rs, rt, rd,c,i);
    }
    
    public static void bltc(String rs, String rt, String offset, ArrayList<String[]> code, Code[] c, int i){
        String dist;
        System.out.println("BLTC parameters: "+rs+rt+offset);
        String opc = "010111";
        dist =distance(offset,code);
        tempbltc(opc,rs,rt,dist,c,i);
    }
    
    
    public static void bc(String offset, ArrayList<String[]> code, Code[] c, int i){
        String dist;
        System.out.println("BC instruction");
        String opc = "110010";
        dist = distance(offset, code);
        tempbc(opc,dist,c,i);

    }
    
    
    public static String decToHex(int dec) {
    StringBuilder hexBuilder = new StringBuilder(sizeOfIntInHalfBytes);
    hexBuilder.setLength(sizeOfIntInHalfBytes);
    for (int i = sizeOfIntInHalfBytes - 1; i >= 0; --i)
    {
      int j = dec & halfByte;
      hexBuilder.setCharAt(i, hexDigits[j]);
      dec >>= numberOfBitsInAHalfByte;
    }
    return hexBuilder.toString(); 
  }
    
    
    public static String hex2Bin(String hex) {
        return String.format("%16s", Integer.toBinaryString(Integer.parseInt(hex, 16))).replace(" ", "0");
    }
    
    public static String decimalToBinary(String in, int n) {
		// parse in into base 10, convert to binary, then back to string
		int temp = Integer.parseInt(in, 10);
		in = Integer.toBinaryString(temp);
		// include leading zeroes
		if(in.length() < n) {
			String zeroes = "";
			for(int i = 0; i < (n - in.length()); i++) {
				zeroes = zeroes + "0";
			}
			in = zeroes + in;
		} 
		else if (in.length() > n) {
			int extra = in.length() - n;
			in = in.substring(extra, in.length());
		}
		return in;
	}
    
    public static void temp(String opc, String one, String two, String three, Code[] c, int i){
        StringBuilder stringBuilder = new StringBuilder();
        String finalString, hexStr;
        String r1="", r2="";
        
        int decimal;
        
        if(one.length() == 3 && two.length() == 3)
        {
            r1 = one.substring(1, 2);
            r2 = two.substring(1, 2);
        }     
        else if(one.length() == 4 && two.length() == 3){
            r1 = one.substring(1, 3);
            r2 = two.substring(1, 2);
        }
        else if(one.length() == 3 && two.length() == 4){  
                r1 = one.substring(1, 2);
                r2 = two.substring(1, 3);
        }
        else
        {
            r1 = one.substring(1, 3);
            r2 = two.substring(1, 3);
        }
        
        if(Integer.parseInt(r1)>32 || Integer.parseInt(r2)>32 ||Integer.parseInt(r1)<0||Integer.parseInt(r2)<0 ){
            errorscreen.append("Error: Invalid Register.");
        }
        stringBuilder.append(opc);
        stringBuilder.append(decimalToBinary(r2,5));
        stringBuilder.append(decimalToBinary(r1,5));
        stringBuilder.append((hex2Bin(three.substring(1))));
        finalString = stringBuilder.toString();
     
        System.out.println("Final: "+ finalString);
        c[i].setbinOPC(finalString);//set binary version ng opcode sa Code Class
        decimal = Integer.parseInt(finalString,2);
        hexStr = Integer.toString(decimal,16); 
        System.out.println("Opcode: "+ hexStr.toUpperCase());
        c[i].setopc(hexStr.toUpperCase());// set opcode sa code class
     
//       System.out.println(c[i].getOPC());
//       System.out.println(c[i].getbinOPC());
//       System.out.println(c[i].getAdd());
//       System.out.println(c[i].getIns());
        
    }
    
    
    
    public static void tempdaddu(String opc, String one, String two, String three, Code[] c, int i){
        StringBuilder stringBuilder = new StringBuilder();
        String finalString, hexStr;
        
     
         if(!checkReg(one) && checkReg(two) && checkReg(three))
             errorscreen.append("Error: Invalid Register.");
        
        char r1, r2, r3;
        int decimal;
        r1 = one.charAt(one.length()-2);
        r2 = two.charAt(two.length()-1);
        r3 = three.charAt(three.length()-2);
        
        stringBuilder.append(opc);
        stringBuilder.append("0");
        stringBuilder.append(Integer.toBinaryString(r1).substring(2));
        stringBuilder.append("0");
        stringBuilder.append(Integer.toBinaryString(r2).substring(2));
        stringBuilder.append("0");
        stringBuilder.append(Integer.toBinaryString(r3).substring(2));
        stringBuilder.append("00000101101");
        finalString = stringBuilder.toString();
               
        System.out.println("Final: "+ finalString);
        
        decimal = Integer.parseInt(finalString,2);
        hexStr = Integer.toHexString(decimal); 
        String hex = decToHex(decimal); 
        System.out.println("Opcode: "+ hex);
        c[i].setbinOPC(finalString);//set binary version ng opcode sa Code Class
        c[i].setopc(hex);// set opcode sa code class
    }
    
    public static void ldsd(String opc, String base, String rt, String offset, Code[] c, int i){
        StringBuilder stringBuilder = new StringBuilder();
        String finalString, hexStr;
        char r1, rt2;
        int decimal;
        
        if(!checkReg(base) && checkReg(rt))
             errorscreen.append("Error: Invalid Register.");
        
          r1 = base.charAt(base.length()-1);
          rt2 = rt.charAt(rt.length()-2);

        
          stringBuilder.append(opc);
          stringBuilder.append("0");
          stringBuilder.append(Integer.toBinaryString(r1).substring(2));
          stringBuilder.append("0");
          stringBuilder.append(Integer.toBinaryString(rt2).substring(2));
          stringBuilder.append(hex2Bin(offset));
         finalString = stringBuilder.toString();
         
         String FFinal = String.format("%X", Long.parseLong(finalString,2));
     
       System.out.println("Final: "+ finalString);
   
     System.out.println("Opcode: "+ FFinal);
        c[i].setbinOPC(finalString);//set binary version ng opcode sa Code Class
        c[i].setopc(FFinal.toUpperCase());
        
    }
    
    
    public static void tempbeq(String opc, String rs, String rt, String offset, Code[] c, int i){
        StringBuilder stringBuilder = new StringBuilder();
        String finalString, hexStr;
        char r1, r2, r3;
        int decimal;
        r1 = rs.charAt(rs.length()-2);
        r2 = rt.charAt(rt.length()-2);
        
        if(!checkReg(rs) && checkReg(rt))
             errorscreen.append("Error: Invalid Register.");
        
        
        stringBuilder.append(opc);
        stringBuilder.append("0");
        stringBuilder.append(Integer.toBinaryString(r1).substring(2));
        stringBuilder.append("0");
        stringBuilder.append(Integer.toBinaryString(r2).substring(2));
        stringBuilder.append("0");
        stringBuilder.append(hex2Bin(offset.substring(1)));
        finalString = stringBuilder.toString();
        
       
        System.out.println("Final: "+ finalString);
        
        decimal = Integer.parseInt(finalString,2);
        String hex = decToHex(decimal); 
        System.out.println("Opcode: "+ hex);
        
        c[i].setbinOPC(finalString);//set binary version ng opcode sa Code Class
        c[i].setopc(hex.toUpperCase());
    }
    
    
    
    public static void tempslt(String opc, String one, String two, String three, Code[] c, int i){
        StringBuilder stringBuilder = new StringBuilder();
        String finalString, hexStr;
        char r1, r2, r3;
        int decimal;
        r1 = one.charAt(one.length()-2);
        r2 = two.charAt(two.length()-1);
        r3 = three.charAt(three.length()-2);
        
        if(!checkReg(one) && checkReg(two) && checkReg(three))
             errorscreen.append("Error: Invalid Register.");
        
        stringBuilder.append(opc);
        stringBuilder.append("0");
        stringBuilder.append(Integer.toBinaryString(r1).substring(2));
        stringBuilder.append("0");
        stringBuilder.append(Integer.toBinaryString(r2).substring(2));
        stringBuilder.append("0");
        stringBuilder.append(Integer.toBinaryString(r3).substring(2));
        stringBuilder.append("00000101010");
        finalString = stringBuilder.toString();
        
       
        System.out.println("Final: "+ finalString);
        
        decimal = Integer.parseInt(finalString,2);
        hexStr = Integer.toHexString(decimal); 
        String hex = decToHex(decimal);
        System.out.println("Opcode: "+ hex);
        c[i].setbinOPC(finalString);//set binary version ng opcode sa Code Class
        c[i].setopc(hex.toUpperCase());
     
    }
    
    public static void tempnop(String opc, Code[] c, int i){
        StringBuilder stringBuilder = new StringBuilder();
        String finalString, hexStr;
        int decimal;
        
        stringBuilder.append(opc);
        stringBuilder.append("00000000000000000000000000");
        finalString = stringBuilder.toString();
        
        System.out.println("Final: "+ finalString);
        
        decimal = Integer.parseInt(finalString,2);
        String hex = decToHex(decimal); 
        System.out.println("Opcode: "+ hex);
        c[i].setbinOPC(finalString);//set binary version ng opcode sa Code Class
        c[i].setopc(hex.toUpperCase());
       
    }

    public static void tempdsubu(String opc, String one, String two, String three, Code[] c, int i){
        StringBuilder stringBuilder = new StringBuilder();
        String finalString, hexStr;
        char r1, r2, r3;
        int decimal;
        r1 = one.charAt(one.length()-2);
        r2 = two.charAt(two.length()-1);
        r3 = three.charAt(three.length()-2);
        
        if(!checkReg(one) && checkReg(two) && checkReg(three))
             errorscreen.append("Error: Invalid Register.");
        
        stringBuilder.append(opc);
        stringBuilder.append("0");
        stringBuilder.append(Integer.toBinaryString(r1).substring(2));
        stringBuilder.append("0");
        stringBuilder.append(Integer.toBinaryString(r2).substring(2));
        stringBuilder.append("0");
        stringBuilder.append(Integer.toBinaryString(r3).substring(2));
        stringBuilder.append("00000101111");
        finalString = stringBuilder.toString();
        
       
        System.out.println("Final: "+ finalString);
        
        decimal = Integer.parseInt(finalString,2);
        hexStr = Integer.toHexString(decimal); 
        String hex = decToHex(decimal); 
        System.out.println("Opcode: "+ hex);
       c[i].setbinOPC(finalString);//set binary version ng opcode sa Code Class
        c[i].setopc(hex.toUpperCase());
    }
    
    
    public static void tempbltc(String opc, String rs, String rt, String distance, Code[] c, int i){
        StringBuilder stringBuilder = new StringBuilder();
        String finalString, hexStr;
        char r1, r2, r3;
        long decimal;
        r1 = rs.charAt(rs.length()-2);
        r2 = rt.charAt(rt.length()-2);
        
        if(!checkReg(rs) && checkReg(rt) )
             errorscreen.append("Error: Invalid Register.");
       
        stringBuilder.append(opc);
        stringBuilder.append("0");
        stringBuilder.append(Integer.toBinaryString(r1).substring(2));
        stringBuilder.append("0");
        stringBuilder.append(Integer.toBinaryString(r2).substring(2));
        hexStr = hex2Bin(distance);
        
        if(distance.contains("-"))  
            hexStr=hexStr.substring(16);
        
         stringBuilder.append(hexStr);
        finalString = stringBuilder.toString();
        System.out.println("Final: "+ finalString);
        
        decimal = Integer.parseInt(finalString,2);
        String hex = decToHex((int) decimal); 
        System.out.println("Opcode: "+ hex);
        c[i].setbinOPC(finalString);//set binary version ng opcode sa Code Class
        c[i].setopc(hex.toUpperCase());
    }
    
    public static void tempbc(String opc, String distance, Code[] c, int i){
        StringBuilder stringBuilder = new StringBuilder();
        String finalString, hexStr;
        long decimal;
        
        stringBuilder.append(opc);
        hexStr = hex2Bin(distance);
        if(!distance.contains("-"))
        {
            stringBuilder.append("00000");
            stringBuilder.append("00000");
        }
        else
            hexStr=hexStr.substring(6);
        
        stringBuilder.append(hexStr);
        finalString = stringBuilder.toString();
        
        System.out.println("Final: "+ finalString);
        
        decimal = Long.parseLong(finalString,2);
        String hex = decToHex((int) decimal); 
        System.out.println("Opcode: "+ hex);
        c[i].setbinOPC(finalString);//set binary version ng opcode sa Code Class
        c[i].setopc(hex.toUpperCase());
    }
    
}

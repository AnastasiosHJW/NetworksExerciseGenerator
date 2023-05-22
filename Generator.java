package networks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Generator {

	public static void main(String[] args) throws IOException {
		Random rand=new Random();
		for (int n=1;n<101;n++) {
		int type=rand.nextInt(2);
		int[] IP=new int[4];
		String[] IPbin=new String[4];
		for (int i=0;i<4;i++) {
			IPbin[i]=generateIPnumber();	
		}
		IP=convertIPfromBin(IPbin);
		
		int mask=rand.nextInt(11)+15;
		IPbin=maskIP(IPbin, mask);
		IP=convertIPfromBin(IPbin);
		String IPstr=IP[0] + "." + IP[1] + "."+IP[2]+"."+IP[3];
		String path=new File(".").getAbsolutePath();
		File exFile = new File(path+"\\Networks_Exercise_"+n+".txt");
		File solFile = new File(path+"\\Networks_Solution_"+n+".txt");
		//System.out.println(path);
		BufferedWriter writer = new BufferedWriter(new FileWriter(exFile));
		BufferedWriter writer2 = new BufferedWriter(new FileWriter(solFile));
		String writeString="";
		String solutionString="";
		if (type==0) {
			int subnets=rand.nextInt(7)+2;
			int subnetBits=Integer.toBinaryString(subnets).toCharArray().length;
			int newMask=mask+subnetBits;
			int totalSubnets=(int) Math.pow(2, subnetBits);
			int computerBits=32-newMask;
			int totalComputers=(int) Math.pow(2, computerBits);
			
			writeString= "Ένα δίκτυο έχει την διεύθυνση IP " + IPstr + "/"+ mask+". Θέλουμε να το διαχωρίσουμε σε τουλάχιστον "+ subnets+" υποδίκτυα.\n";
			writeString+="1)Ποιά είναι η νέα μάσκα δικτύου?\n2) Πόσα υποδίκτυα θα έχουμε συνολικά?\n3) Πόσους υπολογιστές θα έχει το κάθε υποδίκτυο?\n4) Ποιό είναι το εύρος διαυθύνσεων για κάθε υποδίκτυο?";
			
			writer.write(writeString);
			writer.close();
			
			String[] subnetAddress=new String[totalSubnets];
			int IPnum1=mask/8;
			int IPnum2=newMask/8;
			int diff=0;
			
			int startBit=newMask%8;
			char[] addressStr=IPbin[IPnum2].toCharArray();
			if (startBit>0) {
				addressStr=IPbin[IPnum2].toCharArray();
				addressStr[startBit-1]='1';
			}
			else {
				IPnum2=IPnum2-1;
				startBit=8;
				addressStr=IPbin[IPnum2].toCharArray();
				addressStr[startBit-1]='1';
			}
			String newAddress="";
			for (int i=0;i<8;i++) {
				newAddress+=addressStr[i];
			}
			diff=Integer.parseInt(newAddress, 2)-IP[IPnum2];
			
			

			for (int i=0;i<totalSubnets;i++)
			{
					
				String subnetString=IP[0] + "." + IP[1] + "."+IP[2]+"."+IP[3];
				IP[IPnum2]+=diff-1;
				subnetString+=" - "+IP[0] + "." + IP[1] + "."+IP[2]+"."+IP[3];
				IP[IPnum2]+=1;
				if (IP[IPnum2]>255) {
					IP[IPnum2]=0;
					IP[IPnum1]+=1;
				}
				subnetAddress[i]=subnetString;
			}
			
			solutionString="1) Νέα μάσκα: " + newMask + "\n2) Συνολικά υποδίκτυα: "+totalSubnets+"\n3) Υπολογιστές ανά υποδίκτυο: "+totalComputers+"\n4) Εύρος διευθύνσεων: \n";
			for (int i=0;i<totalSubnets;i++) {
				solutionString+=subnetAddress[i]+"\n";
			}
			writer2.write(solutionString);
			writer2.close();
			

		}
		else {
			int computers=rand.nextInt(100)+10;
			int computerBits=Integer.toBinaryString(computers).toCharArray().length;
			int newMask=32-computerBits;
			int totalComputers=(int) Math.pow(2,computerBits);
			int subnetBits=newMask-mask;
			int totalSubnets=(int) Math.pow(2, subnetBits);
			
			writeString= "Ένα δίκτυο έχει διεύθυνση IP " + IPstr + "/"+ mask+". Θέλουμε να το διαχωρίσουμε σε υποδίκτυα, το καθένα εκ των οποίων να έχει τουλάχιστον "+ computers+ " υπολογιστές.\n";
			writeString+="1) Ποιά είναι η νέα μάσκα δικτύου?\n2) Πόσοι υπολογισστές υπάρχουν συνολικά σε κάθε υποδίκτυο?\n3) Πόσα υποδίκτυα υπάρχουν?\n4) Ποιό είναι το εύρος διευθύνσεων για κάθε υποδίκτυο?";
			
			writer.write(writeString);
			writer.close();
			
			String[] subnetAddress=new String[totalSubnets];
			int IPnum1=mask/8;
			int IPnum2=newMask/8;
			int diff=0;
			
			int startBit=newMask%8;
			char[] addressStr=IPbin[IPnum2].toCharArray();

			if (startBit>0) {
				addressStr=IPbin[IPnum2].toCharArray();
				addressStr[startBit-1]='1';
			}
			else {
				IPnum2=IPnum2-1;
				startBit=8;
				addressStr=IPbin[IPnum2].toCharArray();
				addressStr[startBit-1]='1';
			}
			String newAddress="";
			for (int i=0;i<8;i++) {
				newAddress+=addressStr[i];
			}
			diff=Integer.parseInt(newAddress,2)-IP[IPnum2];
			
			

			for (int i=0;i<totalSubnets;i++)
			{
					
				String subnetString=IP[0] + "." + IP[1] + "."+IP[2]+"."+IP[3];
				IP[IPnum2]+=diff-1;
				subnetString+=" - "+IP[0] + "." + IP[1] + "."+IP[2]+"."+IP[3];
				IP[IPnum2]+=1;
				if (IP[IPnum2]>255) {
					IP[IPnum2]=0;
					IP[IPnum1]+=1;
				}
				subnetAddress[i]=subnetString;
			}
			
			solutionString="1)Νέα μάσκα: " + newMask + "\n2) Συνολικοί υπολογιστές ανά υποδίκτυο: "+totalComputers+"\n3) Πλήθος από υποδίκτυα: "+totalSubnets+"\n4) Εύρος διευθύνσεων: \n";
			for (int i=0;i<totalSubnets;i++) {
				solutionString+=subnetAddress[i]+"\n";
			}
			
			writer2.write(solutionString);
			writer2.close();
			
			
		}
		}
		
		
		
		

	}
	
	private static String generateIPnumber() {
		Random rand=new Random();
		
		String IPnum="";
		for (int i=0;i<8;i++)
		{
			int nextDigit=rand.nextInt(2);
			IPnum=IPnum+nextDigit;
		}
		return IPnum;
		
	}
	
	private static int[] convertIPfromBin(String[] IPbin) {
		int[] IP=new int[4];
		for (int i=0;i<4;i++) {
			IP[i]=Integer.parseInt(IPbin[i],2);
		}
		return IP;
	}
	
	private static String[] maskIP(String[] IPbin, int mask) {
		int IPchange=mask/8;
		int bitsToKeep=mask%8;
		char[] IPbits=IPbin[IPchange].toCharArray();
		for (int i=bitsToKeep;i<8;i++) {
			IPbits[i]='0';
		}
		String newIP="";
		for (int i=0;i<8;i++) {
			newIP+=IPbits[i];
		}
		for (int i=0;i<4;i++) {
			if (i==IPchange) {
				IPbin[i]=newIP;
			}
			else if (i>IPchange) {
				IPbin[i]="00000000";
			}
			
		}
		
		return IPbin;
		
	}
	
	
	
	

}

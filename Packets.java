package networks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Packets {

	public static void main(String[] args) throws IOException {
		Random rand = new Random();
		
		for (int n=0;n<100;n++) {
			
			int fragments=rand.nextInt(3)+3;
			int MTU=8*(rand.nextInt(80)+50)+20;
			int lastFragmentSize=MTU-(rand.nextInt(100)+50);
			int packetSizeData=0;
			for (int i=0;i<fragments-1;i++) {
				packetSizeData+=MTU;
			}
			packetSizeData+=lastFragmentSize;
			int packetSizeTotal=packetSizeData+20;
			int offset=(MTU-20)/8;
			
			String path=new File(".").getAbsolutePath();
			File exFile = new File(path+"\\Networks_Packet_Exercise_"+n+".txt");
			File solFile = new File(path+"\\Networks_Packet_Solution_"+n+".txt");
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(exFile));
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(solFile));
			String writeString="";
			String solutionString="";
			
				
			writeString="Ένα πακέτο IP έχει συνολικό μέγεθος " + packetSizeTotal + " byte, και περνάει από ένα δίκτυο με MTU " + MTU + "byte. Το μέγεθος κεφαλίδας είναι 20 byte.";
			writeString+="\n1) Σε πόσα τμήματα θα διασπαστεί το πακέτο? \n2) Να συμπληρώσετε σε έναν πίνακα τις τιμές των πεδίων: IHL, Συνολικό Μήκος, Μήκος Δεδομένων, MF, και Offset για κάθε τμήμα.";
			writer.write(writeString);
			writer.close();
				
			solutionString="1) Το πακέτο θα διασπαστεί σε " + fragments + "τμήματα. \n2) Το IHL θα είναι 5 για όλα τα τμήματα. \nΤο Συνολικό μήκος θα είναι " + MTU + "για κάθε τμήμα εκτός από το τελευταίο, το οποίο θα είναι " + (lastFragmentSize+20);
			solutionString+="\nΤο Μήκος Δεδομένων θα είναι " + (MTU-20) +"για κάθε τμήμα εκτός από το τελευταίο, το οποίο θα είναι " + lastFragmentSize +".\n Η σημαία MF θα είναι 1 για κάθε τμήμα εκτός από το τελευταίο, το οποίο θα είναι 0.";
			solutionString+="\nΤο offset για κάθε τμήμα θα είναι:";
			int tempOffset=0;
			for (int i=0;i<fragments;i++) {
					solutionString+=" " + tempOffset + "  ";
					tempOffset+=offset;
				}
			
			writer2.write(solutionString);
			writer2.close();
			
			
			
		}

	}

}

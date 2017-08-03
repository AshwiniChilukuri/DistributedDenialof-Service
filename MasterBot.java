import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;

public class MasterBot {
	public static Socket s0= null;
	public static ServerSocket S0 = null;
	public static void main(String arg[]) throws Exception
	{
		try{
		if(!arg[0].equals("p"))
		System.out.println("did not enter p");
		if(arg.length!=2)
		System.out.println("I am expecting 2 args p and portnumber");
		System.out.println("MasterBot is listning on port:" + arg[1]);
		S0 = new ServerSocket(Integer.parseInt(arg[1]));
		MasterBot M1 = new MasterBot();
		Runnable o1=M1.new thread1();
		Thread t1=new Thread(o1);//thread to listen to SlaveBots and write details in a file
		t1.start();
		MasterBot M2 = new MasterBot();
		Runnable o2=M2.new thread2();//thread for all the commands entered-list connect disconnect
		Thread t2=new Thread(o2);
		t2.start();
		}//try
		catch (Exception e){
			System.out.println(e);
		}
	}//ends main


class thread1 implements Runnable{
	
	public void run( ){
		try{
		
		while(true){
		s0=S0.accept();
		
		DataInputStream p1=new DataInputStream(s0.getInputStream());
		String msg=p1.readUTF();
		File file =new File("slave.txt");
		
		//writing slaves details into a file
			FileWriter fw= new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw=new PrintWriter(fw);
			if(!file.exists()){
    	   		file.createNewFile();
    			}
		
			pw.println(msg);
			pw.close();
}//ends while
}//endtry
catch (Exception e){
			System.out.println(e);
		}//endcatch
		}// ends run
}//ends thread1

class thread2 implements Runnable{

private Scanner x;

public void run(){
try{
	while(true){
	System.out.println("<");
	Scanner s = new Scanner(System.in);
	String sp= s.nextLine();
	String c[]= sp.split(" ");
	
	if(c[0].equals("list")){
        String line = null;

            FileReader fileReader = 
                new FileReader("slave.txt");

            
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);// BufferedReader to read entire line at once.

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }//close while
		bufferedReader.close();
}//close if



	else if(c[0].equals("ipscan"))
{
	String line = null;
	FileReader fileReader = new FileReader("slave.txt");

            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
		
            while((line = bufferedReader.readLine()) != null) {
		
		String p[]= line.split(" ");
		//Socket s0 = new Socket(p[1],Integer.parseInt(p[2]));
		DataOutputStream connectdetails=new DataOutputStream(s0.getOutputStream());//sending the details of target to slaves
		connectdetails.writeUTF(sp);
		DataInputStream din=new DataInputStream(s0.getInputStream());
		String msg=din.readUTF();
		System.out.println("Ipadresses responded to ICMP echo msgs- " +msg);
		}//close while
}//elseif
else if(c[0].equals("geoipscan"))
{
	String line = null;
	FileReader fileReader = new FileReader("slave.txt");

            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
		
            while((line = bufferedReader.readLine()) != null) {
		
		String p[]= line.split(" ");
		//Socket s0 = new Socket(p[1],Integer.parseInt(p[2]));
		DataOutputStream connectdetails=new DataOutputStream(s0.getOutputStream());//sending the details of target to slaves
		connectdetails.writeUTF(sp);
		DataInputStream din=new DataInputStream(s0.getInputStream());
		String msg=din.readUTF();
		System.out.println("Ipadresses responded to gICMP echo msgs- ");
		String cz[]= msg.split("split");
		for(int i=0; i<cz.length;i++)
		System.out.println(" " +cz[i]);
		}//close while
}//elseifgeo


else if(c[0].equals("tcpportscan")){
	String line = null;
	FileReader fileReader = new FileReader("slave.txt");

            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
		
            while((line = bufferedReader.readLine()) != null) {
		
		String p[]= line.split(" ");
		//Socket s0 = new Socket(p[1],Integer.parseInt(p[2]));
		DataOutputStream connectdetails=new DataOutputStream(s0.getOutputStream());//sending the details of target to slaves
		connectdetails.writeUTF(sp);
		DataInputStream din=new DataInputStream(s0.getInputStream());
		String msg=din.readUTF();
		System.out.println("open ports- " +msg);
		}//close while
	}// close else




	else {
	String line = null;
	FileReader fileReader = new FileReader("slave.txt");

            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
		
            while((line = bufferedReader.readLine()) != null) {
		
		String p[]= line.split(" ");
		//Socket s0 = new Socket(p[1],Integer.parseInt(p[2]));
		DataOutputStream connectdetails=new DataOutputStream(s0.getOutputStream());//sending the details of target to slaves
		connectdetails.writeUTF(sp);
		}//close while
	}// close else


}//`while end
		
}//close try
	catch (Exception e){
			System.out.println(e);
		}//endcatch
}//close run



}//close thread2

}// ends MasterBot














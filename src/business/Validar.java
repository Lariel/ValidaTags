package business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import util.MaskFieldUtil;

public class Validar extends Thread {
	//private ArrayList<String> tags;
	private ArrayList<Maquina> modelos;
	
	public Validar(){
		//tags=new ArrayList<String>();
		modelos=new ArrayList<Maquina>();
	}
	
	public boolean add(Maquina m){
		return modelos.add(m);
	}
	
	public ArrayList<Maquina> validar(String tags){
		
		Scanner sc = new Scanner(tags).useDelimiter("\n"); 
		
		while (sc.hasNext()) {
			String tag=sc.next();
			String modelo="";
			
			String stringURL = "http://quality.dell.com/Search?tag="+tag;
	        String consultaQuality = "";
	        
	        try {
	            URL url = new URL(stringURL);
	            URLConnection connection = url.openConnection();
	            BufferedReader in = new BufferedReader(
	                    new InputStreamReader(
	                    connection.getInputStream()));
	            String inputLine;
	            StringBuffer sb = new StringBuffer();
	            while ((inputLine = in.readLine()) != null) sb.append(inputLine);
	            consultaQuality = sb.toString();
	            in.close();
	        }catch (UnknownHostException e) {
	        	Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Aten��o!");
				alert.setHeaderText(null);
				alert.setContentText("Host http://quality.dell.com n�o acess�vel");
				alert.showAndWait();
	        } catch (MalformedURLException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        
	        if(consultaQuality.toLowerCase().contains("wyse")){
	        	modelo = "Wyse";
	        	Maquina m=new Maquina(tag,modelo);
				modelos.add(m);
	        }else modelo="outros";		
		}
		
		if(modelos.size()==0){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Aten��o!");
			alert.setHeaderText(null);
			alert.setContentText("Nenhum Wyse encontrado!");
			alert.showAndWait();			
		}

		sc.close();

		return modelos;
	}

}
//http://quality.dell.com/Search?tag=55VP2K2


package business;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Validar {
	private ArrayList<Maquina> maquinas;
	
	public Validar(){
		maquinas=new ArrayList<Maquina>();
		
	}
	
	public boolean add(Maquina tag){
		return maquinas.add(tag);
	}
	
	
	
	
	
}
//http://quality.dell.com/Search?tag=55VP2K2


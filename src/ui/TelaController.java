package ui;

import java.util.ArrayList;

import business.Maquina;
import business.Validar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class TelaController {
	
	Validar validador=new Validar();
	private ArrayList<Maquina> modelos; 
	
	ObservableList<Maquina> listaTags= FXCollections.observableArrayList(); 

    @FXML
    private TextArea taTags;

    @FXML
    private ListView<Maquina> lvModelos;
    
    @FXML
    private Button btValidar;
    
    @FXML
    void ValidarTags(ActionEvent event) {
    	modelos=validador.validar(taTags.getText().toString());
    	//System.out.println("aqui"+taTags.getText());
    	for(int i=0;i<modelos.size();i++){
    		listaTags.add(modelos.get(i));
    	}
    	lvModelos.setItems(listaTags);
    	
    }
    
    

}

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
    private Button btResetTags;

    @FXML
    private Button btResetResults;

    @FXML
    void ResetTags(ActionEvent event) {
    	taTags.clear(); //limpa a text area 
    	modelos.clear(); //limpa o array
    }
    
    @FXML
    void ResetResults(ActionEvent event) {
    	listaTags.clear(); //limpa a observable list 
    	lvModelos.getItems().clear(); //limpa a list view
    }
    
    @FXML
    void ValidarTags(ActionEvent event) {
    	
    	//metodo Validar recebe a lista de tags
    	//array de modelos recebe o resultado do metodo Validar
    	modelos=validador.validar(taTags.getText().toString()); 
    	
    	//ObservableList<Maquina> listaTags recebe todos os itens do array modelos
    	for(int i=0;i<modelos.size();i++){
    		listaTags.add(modelos.get(i));
    	}
    	
    	//ListView lvModelos recebe a ObservableList<Maquina> já preenchida
    	lvModelos.setItems(listaTags);
    	
    }
    
    

}

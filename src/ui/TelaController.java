package ui;

import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import business.Maquina;
import business.Validar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;

public class TelaController implements Initializable{
	
	Validar validador=new Validar();
	private ArrayList<Maquina> modelos; 
	final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();
	
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
    private Label lblStatus;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	String t="Cole aqui a lista de tags a consultar. "
    			+ "      " +
    			"Informe uma tag por linha, sem nenhum caracte especial";
		taTags.promptTextProperty().set(t);
    	taTags.setTooltip(new Tooltip("Informe as Tags aqui"));
		lvModelos.setTooltip(new Tooltip("Resultados"));
		btResetResults.setTooltip(new Tooltip("Limpar resultados"));
		btResetTags.setTooltip(new Tooltip("Limpar pesquisa"));
		// TODO Auto-generated method stub
		
	}
    

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
    
    @FXML
    void Copiar(MouseEvent event) {
    	
    	int index=lvModelos.getSelectionModel().getSelectedIndex(); //pega o indice do item clicado na view
    	String copiado=listaTags.get(index).getTag(); //pega a tag do item e add na String
    	
    	content.putString(copiado); //passa a String para content
        clipboard.setContent(content); //add content na clipboard
    	
    }

}

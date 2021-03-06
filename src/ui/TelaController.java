package ui;

import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import business.Maquina;
import business.Validar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import util.ProgressStatus;

public class TelaController implements Initializable{

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
    private Button btCopiarLista;
    
    @FXML
    private Label lblStatus;
    
    @FXML
    private ProgressIndicator pi1;
    
    final Service thread=new Service<Integer>(){
		@Override
		public Task createTask(){
			return new Task<Integer>(){
				@Override
				public Integer call() throws InterruptedException{
					int i;
					for(i=0;i<1000;i++){
						updateProgress(i,1000);
						Thread.sleep(10);
					}
					return i;
					
				}
			};
		}
	};
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	lblStatus.setVisible(false);
    	pi1.setVisible(false);
    	pi1.progressProperty().bind(thread.progressProperty());
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
    	btCopiarLista.setDisable(true);
    }
    
    @FXML
    void ValidarTags(ActionEvent event) {
    	
    	ProgressStatus ps=new ProgressStatus();
    	Thread tPs=new Thread(ps);
    	tPs.start();
    	
    	Validar validador=new Validar();
    	Thread tValidador=new Thread(validador);
    	tValidador.start();
    	
    	//metodo Validar recebe a lista de tags
    	//array de modelos recebe o resultado do metodo Validar
    	modelos=validador.validar(taTags.getText().toString()); 
    	
    	//ObservableList<Maquina> listaTags recebe todos os itens do array modelos
    	for(int i=0;i<modelos.size();i++){
    		listaTags.add(modelos.get(i));
    	}
    	if(modelos.size()!=0){
    		btCopiarLista.setDisable(false);
    	}
    	//ListView lvModelos recebe a ObservableList<Maquina> j� preenchida
    	lvModelos.setItems(listaTags);
    	
    }  
    
    @FXML
    void Copiar(MouseEvent event) {
    	int index=lvModelos.getSelectionModel().getSelectedIndex(); //pega o indice do item clicado na view
    	String copiado=listaTags.get(index).getTag(); //pega a tag do item e add na String
    	
    	content.putString(copiado); //passa a String para content
        clipboard.setContent(content); //add content na clipboard
    	
    }
    
    @FXML
    void CopiarLista(ActionEvent event) {
    	String listaCopiada= "Tags Wyse encontradas: \n";
    	for(int i=0;i<listaTags.size();i++){
    		listaCopiada=listaCopiada+listaTags.get(i).getTag()+"\n";
    		//listaCopiada=listaTags.toString()+"\n";
    	}
    	content.putString(listaCopiada); //passa a String para content
        clipboard.setContent(content); //add content na clipboard
    }

}

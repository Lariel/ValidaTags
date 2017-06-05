package business;

public class Maquina {
	private String tag, modelo;

	public Maquina(String tag, String modelo) {
		super();
		this.tag = tag;
		this.modelo = modelo;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Override
	public String toString() {
		return "Tag: " + tag + ", Modelo: " + modelo;
	}
	
	
}

package ohtu.verkkokauppa;

public interface KauppaInt {

	void aloitaAsiointi();

	void lisaaKoriin(int id);

	void poistaKorista(int id);

	boolean tilimaksu(String nimi, String tiliNumero);
	
}

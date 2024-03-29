package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa implements KauppaInt {

    private Varasto varasto;
    private Pankki pankki;
    private Ostoskori ostoskori;
    private Viitegeneraattori viitegeneraattori;
    private String kaupanTili;

//    public Kauppa() {
//        varasto = Varasto.getInstance();
//        pankki = Pankki.getInstance();
//        viitegeneraattori = Viitegeneraattori.getInstance();
//        kaupanTili = "33333-44455";
//    }

	@Autowired
	public Kauppa(Varasto varasto, Pankki pankki, Viitegeneraattori viitegeneraattori) {
		this.varasto = varasto;
		this.pankki = pankki;
		this.viitegeneraattori = viitegeneraattori;
		this.kaupanTili = "123456-123456";
	}

	@Override
    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

	@Override
    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

	@Override
    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

	@Override
    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}

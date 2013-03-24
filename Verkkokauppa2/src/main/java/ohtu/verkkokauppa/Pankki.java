package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Pankki implements PankkiInterface {

    private Kirjanpito kirjanpito;

	@Autowired // hae säilöstä sopiva kirjanpito niminen bean käytettäväksi
    public Pankki(Kirjanpito kirjanpito) {
        this.kirjanpito = kirjanpito;
    }

	@Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
//        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
//                + " viite " + viitenumero + " summa " + summa + "e");
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tililta + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}

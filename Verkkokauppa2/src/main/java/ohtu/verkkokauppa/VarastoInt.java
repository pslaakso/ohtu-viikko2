package ohtu.verkkokauppa;

public interface VarastoInt {

	Tuote haeTuote(int id);

	void otaVarastosta(Tuote t);

	void palautaVarastoon(Tuote t);

	int saldo(int id);
	
}

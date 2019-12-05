package model;

public class Pelit {
	private int peliId;
	private String nimi;
	private double koko;
	private String versio;
	private int julkaisuVuosi;
	private String jakeluAlusta;
	private String peliTyyppi;

	public Pelit() {

	}

	public Pelit(int peliId, String nimi, double koko, String versio, int julkaisuVuosi,
			String jakeluAlusta, String peliTyyppi) {
		super();
		this.peliId = peliId;
		this.nimi = nimi;
		this.koko = koko;
		this.versio = versio;
		this.julkaisuVuosi = julkaisuVuosi;
		this.jakeluAlusta = jakeluAlusta;
		this.peliTyyppi = peliTyyppi;

	}

	public int getPeliId() {
		return peliId;
	}

	public void setPeliId(int peliId) {
		this.peliId = peliId;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public double getKoko() {
		return koko;
	}

	public void setKoko(double koko) {
		this.koko = koko;
	}

	public String getVersio() {
		return versio;
	}

	public void setVersio(String versio) {
		this.versio = versio;
	}

	public int getJulkaisuVuosi() {
		return julkaisuVuosi;
	}

	public void setJulkaisuVuosi(int julkaisuVuosi) {
		this.julkaisuVuosi = julkaisuVuosi;
	}

	public String getJakeluAlusta() {
		return jakeluAlusta;
	}

	public void setJakeluAlusta(String jakeluAlusta) {
		this.jakeluAlusta = jakeluAlusta;
	}

	public String getPeliTyyppi() {
		return peliTyyppi;
	}

	public void setPeliTyyppi(String peliTyyppi) {
		this.peliTyyppi = peliTyyppi;
	}

	@Override
	public String toString() {
		return "Pelit [peliId=" + peliId + ", nimi=" + nimi + ", koko=" + koko + ", versio=" + versio
				+ ", julkaisuVuosi=" + julkaisuVuosi + ", jakeluAlusta=" + jakeluAlusta + 
				", peliTyyppi=" + peliTyyppi
				+ "]";
	}
	
	

}

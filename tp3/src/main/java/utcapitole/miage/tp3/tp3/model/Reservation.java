package utcapitole.miage.tp3.tp3.model;

public class Reservation {

    private int nbPersonnes;
    private String destination;
    private String nom;
    private String mail;
    private String moyenPay;
    private String IBAN;
    private boolean recevoirPromo;
    private boolean read;

    public Reservation(int nbP, String des, String nom, String mail, String payment, String iban, boolean promo,
                       boolean con) {
        this.nbPersonnes = nbP;
        this.destination = des;
        this.nom = nom;
        this.mail = mail;
        this.moyenPay = payment;
        this.IBAN = iban;
        this.recevoirPromo = promo;
        this.read = con;
    }

    public Reservation(boolean read, boolean recevoirPromo, String IBAN, String moyenPay, String mail, String nom,
                       String destination, int nbPersonnes) {
        this.read = read;
        this.recevoirPromo = recevoirPromo;
        this.IBAN = IBAN;
        this.moyenPay = moyenPay;
        this.mail = mail;
        this.nom = nom;
        this.destination = destination;
        this.nbPersonnes = nbPersonnes;
    }

    public Reservation(boolean read, String IBAN, String moyenPay, String mail, String nom, String destination,
                       int nbPersonnes) {
        this.read = read;
        this.IBAN = IBAN;
        this.moyenPay = moyenPay;
        this.mail = mail;
        this.nom = nom;
        this.destination = destination;
        this.nbPersonnes = nbPersonnes;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMoyenPay() {
        return moyenPay;
    }

    public void setMoyenPay(String moyenPay) {
        this.moyenPay = moyenPay;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public boolean isRecevoirPromo() {
        return recevoirPromo;
    }

    public void setRecevoirPromo(boolean recevoirPromo) {
        this.recevoirPromo = recevoirPromo;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "nbPersonnes=" + nbPersonnes +
                ", destination='" + destination + '\'' +
                ", nom='" + nom + '\'' +
                ", mail='" + mail + '\'' +
                ", moyenPay='" + moyenPay + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", recevoirPromo=" + recevoirPromo +
                ", read=" + read +
                '}';
    }
}

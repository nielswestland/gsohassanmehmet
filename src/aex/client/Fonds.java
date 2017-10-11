package aex.client;

public class Fonds implements IFonds
{
    private String naam;
    private double koers;
    private String afkorting;

    public Fonds(String naam, double koers, String afkorting)
    {
        this.naam = naam;
        this.koers = koers;
        this.afkorting = afkorting;
    }

    public String getAfkorting()
    {
        return afkorting;
    }

    @Override
    public String getNaam()
    {
        return naam;
    }

    @Override
    public double getKoers()
    {
        return koers;
    }

    @Override
    public void setKoers(double koers)
    {
        this.koers = koers;
    }
}

package aex.client;

import aex.shared.IEffectenBeurs;

import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController
{
    private AEXBanner banner;
    private IEffectenBeurs effectenbeurs;
    private Timer pollingTimer;

    //RMI
    private BeursClient client;

    public BannerController(AEXBanner banner)
    {
        //RMI client setup
        this.client = new BeursClient("localhost", 1099);
        client.setBinding("Effectenbeurs");

        //Banner setup
        this.banner = banner;
        this.effectenbeurs = client.getEffectenbeurs();

        //PollingTime voor het aanpassen van de koers
        pollingTimer = new Timer();

        pollingTimer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                banner.setKoersen(getKoersen());
                System.out.println(getKoersen());
            }
        }, 0, 1000);
    }

    // Stop banner controller
    public void stop()
    {
        pollingTimer.cancel();
    }

    public String getKoersen()
    {
        String koersenString = "";

        try
        {
            for (IFonds koers : effectenbeurs.getKoersen())
            {
                koersenString += koers.getNaam() + ": " + String.format("%.2f", koers.getKoers()) + " ";
            }
        }

        catch (RemoteException e)
        {
            e.printStackTrace();
        }

        return koersenString;
    }
}


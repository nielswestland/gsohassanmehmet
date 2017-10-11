package aex.client;

import java.util.Timer;
import java.util.TimerTask;

public class BannerController
{
    private AEXBanner banner;
    private IEffectenBeurs effectenbeurs;
    private Timer pollingTimer;

    public BannerController(AEXBanner banner)
    {
        this.banner = banner;
        this.effectenbeurs = new MockEffectenbeurs();

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();

        pollingTimer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                banner.setKoersen(getKoersen());
                System.out.println(getKoersen());
            }
        }, 0 , 1000);
    }

    // Stop banner controller
    public void stop()
    {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
    }

    public String getKoersen()
    {
        String koersenString = "";

        for(IFonds koers : effectenbeurs.getKoersen())
        {
            koersenString += koers.getNaam() + ": " + String.format("%.2f", koers.getKoers()) + " ";
        }

        return koersenString;
    }
}


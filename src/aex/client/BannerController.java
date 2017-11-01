package aex.client;

import aex.shared.IEffectenBeurs;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.RemotePublisher;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController extends UnicastRemoteObject implements IRemotePropertyListener
{
    private AEXBanner banner;
    private IEffectenBeurs effectenbeurs;

    //RMI
    private BeursClient client;

    public BannerController(AEXBanner banner) throws RemoteException
    {
        //RMI client setup
        this.client = new BeursClient("145.93.145.5", 1099);
        client.setBinding("Effectenbeurs");

        //Banner setup en ophalen effectenbeurs
        this.banner = banner;
        this.effectenbeurs = client.getEffectenbeurs();

        //Subscriben
        try
        {
            effectenbeurs.subscribeRemoteListener(this, "koersen");
        }

        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    public String getKoersen(List<IFonds> fondsen)
    {
        String koersenString = "";

        for (IFonds fonds : fondsen)
        {
            koersenString += fonds.getNaam() + ": " + String.format("%.2f", fonds.getKoers()) + " ";
        }

        return koersenString;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) throws RemoteException
    {
        banner.setKoersen(getKoersen((List<IFonds>)propertyChangeEvent.getNewValue()));
    }
}


package aex.server;

import aex.client.Fonds;
import aex.client.IEffectenBeurs;
import aex.client.IFonds;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Effectenbeurs extends UnicastRemoteObject implements IEffectenBeurs
{
    public Effectenbeurs() throws RemoteException
    {

    }

    private List<IFonds> koersen;

    @Override
    public List<IFonds> getKoersen()
    {
        if(koersen != null)
        {
            genereerRandomKoers();
        }

        else
        {
            koersen = new ArrayList<>();
            genereerKoersen();
            genereerRandomKoers();
        }

        return koersen;
    }

    private void genereerKoersen()
    {
        Fonds hizmet = new Fonds("Hizmet", 0, "HIZ");
        Fonds tesla = new Fonds("Tesla", 0, "TES");
        Fonds apple = new Fonds("Apple", 0, "APP");

        koersen.add(hizmet);
        koersen.add(tesla);
        koersen.add(apple);
    }

    private void genereerRandomKoers()
    {
        for (IFonds ifonds : koersen)
        {
            Fonds fonds = (Fonds) ifonds;

            Random randomOperand = new Random();
            Random random = new Random();

            double koers = random.nextInt(50);
            double kommaKoers = random.nextInt(100) / 100.0;

            int operand = randomOperand.nextInt(2);

            if(fonds.getKoers() == 0)
            {
                fonds.setKoers(koers + kommaKoers);
            }

            else if(operand == 0)
            {
                fonds.setKoers(fonds.getKoers() - kommaKoers);
            }

            else if(operand == 1)
            {
                fonds.setKoers(fonds.getKoers() + kommaKoers);
            }
        }
    }
}

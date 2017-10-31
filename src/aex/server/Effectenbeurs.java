package aex.server;

import aex.client.Fonds;
import aex.shared.IEffectenBeurs;
import aex.client.IFonds;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Effectenbeurs (remote effectenbeurs)
 * @author Niels Westland
 */

@SuppressWarnings("ALL")
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
        Fonds oracle = new Fonds("Oracle", 0, "ORA");
        Fonds apple = new Fonds("Apple", 0, "APP");
        Fonds audi = new Fonds("Audi", 0, "AUD");

        koersen.add(hizmet);
        koersen.add(audi);
        koersen.add(apple);
        koersen.add(oracle);
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

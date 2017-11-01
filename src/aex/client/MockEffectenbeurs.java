package aex.client;

import aex.shared.IEffectenBeurs;
import fontyspublisher.IRemotePropertyListener;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @deprecated
 * MockEffectenbeurs (locale effectenbeurs)
 * @author Niels Westland
 */

@SuppressWarnings("ALL")
public class MockEffectenbeurs implements IEffectenBeurs
{
    private List<IFonds> mockKoersen;

    @Override
    public List<IFonds> getKoersen()
    {
        if(mockKoersen != null)
        {
            genereerRandomKoers();
        }

        else
        {
            mockKoersen = new ArrayList<>();
            genereerKoersen();
            genereerRandomKoers();
        }

        return mockKoersen;
    }

    private void genereerKoersen()
    {
        Fonds hizmet = new Fonds("Hizmet", 0, "HIZ");
        Fonds tesla = new Fonds("Tesla", 0, "TES");
        Fonds apple = new Fonds("Apple", 0, "APP");

        mockKoersen.add(hizmet);
        mockKoersen.add(tesla);
        mockKoersen.add(apple);
    }

    private void genereerRandomKoers()
    {
        for (IFonds ifonds : mockKoersen)
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

    @Override
    public void subscribeRemoteListener(IRemotePropertyListener iRemotePropertyListener, String s) throws RemoteException
    {

    }

    @Override
    public void unsubscribeRemoteListener(IRemotePropertyListener iRemotePropertyListener, String s) throws RemoteException
    {

    }
}

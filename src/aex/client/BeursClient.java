package aex.client;

import aex.shared.IEffectenBeurs;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 * BeursClient voor het verbinden dmv RMI
 * @author Niels Westland
 */

public class BeursClient
{
    //Lokaal registry
    private Registry registry;
    private IEffectenBeurs effectenBeurs;

    public BeursClient(String ipaddr, int port)
    {
        try
        {
            registry = LocateRegistry.getRegistry(ipaddr, port);
        }
        catch (RemoteException e)
        {
            System.out.println("Registry niet gevonden!");
            registry = null;
        }

        printRegistryBindings();
    }

    private void printRegistryBindings()
    {
        try
        {
            if (registry.list() != null)
            {
                System.out.println("Mogelijke bindings:");

                for(String binding : registry.list())
                {
                    System.out.println(binding);
                }
            }
        }

        catch (RemoteException e)
        {
            System.out.println("Geen bindings in het registry");
        }
    }

    public void setBinding(String bindingName)
    {
        if(registry != null)
        {
            try
            {
                effectenBeurs = (IEffectenBeurs) registry.lookup(bindingName);
            }

            catch (RemoteException | NotBoundException e)
            {
                System.out.println("Kon geen binding maken met registry en bindingnaam: " + bindingName);
            }
        }
    }

    private void testEffectenbeurs()
    {
        try
        {
            List<IFonds> fondsen = effectenBeurs.getKoersen();
        }

        catch (RemoteException e)
        {
            System.out.println("Kon geen fondsen binnenhalen");
        }
    }

    public IEffectenBeurs getEffectenbeurs()
    {
        return this.effectenBeurs;
    }
}

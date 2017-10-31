package aex.shared;

import aex.client.IFonds;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface effectenbeurs
 * @author Niels Westland
 */

public interface IEffectenBeurs extends Remote
{
    List<IFonds> getKoersen() throws RemoteException;
}

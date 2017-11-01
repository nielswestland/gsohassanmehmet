package aex.shared;

import aex.client.IFonds;
import fontyspublisher.IRemotePublisherForListener;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface effectenbeurs
 * @author Niels Westland
 */

public interface IEffectenBeurs extends IRemotePublisherForListener
{
    List<IFonds> getKoersen() throws RemoteException;
}

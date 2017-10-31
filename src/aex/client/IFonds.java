package aex.client;

import java.io.Serializable;

/**
 * Interface fonds (ook Serializable voor RMI)
 * @author Niels Westland
 */

public interface IFonds extends Serializable
{
    String getNaam();
    double getKoers();
}

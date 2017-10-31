package aex.client;

import java.io.Serializable;
import java.rmi.Remote;

public interface IFonds extends Serializable
{
    String getNaam();
    double getKoers();
}

package aex.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        for (IFonds fonds : mockKoersen)
        {
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

package dao;

import model.Paiement;
import java.util.ArrayList;
import java.util.List;

public class PaiementDAO {
  private static final List<Paiement> paiementsEffectues = new ArrayList<>();

    public static void enregistrerPaiement(Paiement paiement) {
        paiementsEffectues.add(paiement);
        System.out.println("Paiement enregistré : " + paiement.getNomProprietaire() + " via " + paiement.getMethode());
    }

    public static List<Paiement> getHistoriquePaiements() {
        return paiementsEffectues;
    }
}

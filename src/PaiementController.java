package controller;

import model.Paiement;
import dao.PaiementDAO;

public class PaiementController {
    public static void validerPaiement(Paiement paiement) {
        PaiementDAO.enregistrerPaiement(paiement);
    }
}

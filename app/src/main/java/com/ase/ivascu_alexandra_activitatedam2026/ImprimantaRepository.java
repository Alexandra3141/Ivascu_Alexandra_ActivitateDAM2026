package com.ase.ivascu_alexandra_activitatedam2026;

import java.util.ArrayList;

public class ImprimantaRepository {

    private static ImprimantaRepository instance;
    private final ArrayList<Imprimanta> listaImprimante;

    private ImprimantaRepository() {
        listaImprimante = new ArrayList<>();
        listaImprimante.add(new Imprimanta("HP LaserJet Pro", 1250.0, false, "Wi-Fi", "Laser", 4.5f, true));
        listaImprimante.add(new Imprimanta("Canon PIXMA G3411", 720.0, true, "Wi-Fi", "Inkjet", 4.0f, false));
        listaImprimante.add(new Imprimanta("Epson EcoTank L3250", 980.5, true, "USB", "Multifuncțională", 5.0f, true));
    }

    public static synchronized ImprimantaRepository getInstance() {
        if (instance == null) {
            instance = new ImprimantaRepository();
        }
        return instance;
    }

    public ArrayList<Imprimanta> getListaImprimante() {
        return listaImprimante;
    }

    public void adaugaImprimanta(Imprimanta imprimanta) {
        if (imprimanta != null) {
            listaImprimante.add(imprimanta);
        }
    }

    public void stergeImprimanta(int position) {
        if (position >= 0 && position < listaImprimante.size()) {
            listaImprimante.remove(position);
        }
    }
}

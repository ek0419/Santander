package com.example.trabajo.Planetas.catalogo;

import java.util.ArrayList;

public interface CatalogoPlanetasInterface {

    interface View {
        void mostrarMensaje(String mensaje);

        void progress(boolean mostrar);

        void MostrarRecycler(ArrayList<ItemPlaneta> list);

    }

    interface Presenter {
        void consultaServicio();

        void mensaje(String mensaje);

    }

    interface Model {
        ArrayList<ItemPlaneta> getPlanetas();
    }
}

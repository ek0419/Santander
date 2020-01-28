package com.example.trabajo.Planetas.detalle;

public interface PlanetaDetalleInterface {

    interface View {
        void mostrarProgress(Boolean mostrar);
        void setDetalles(PlanetasDetalleModel detalles);
        void mostrarREsult(String result);
    }

    interface Presenter {
        void consultaDetallews(String url);
    }

    interface Model {
        PlanetasDetalleModel getDetalles(String url);
    }
}

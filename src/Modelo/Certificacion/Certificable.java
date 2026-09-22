package Modelo.Certificacion;

import Modelo.Estudiante;

public interface Certificable {

    String ENTIDAD_EMISORA = "UTN FRM";

    String generarCertificado(Estudiante estudiante);
}
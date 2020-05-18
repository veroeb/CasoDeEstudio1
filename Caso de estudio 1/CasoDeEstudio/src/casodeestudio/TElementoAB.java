package casodeestudio;

public class TElementoAB<T> implements IElementoAB<T> {

    private final Comparable etiqueta;
    private TElementoAB<T> hijoIzq;
    private TElementoAB<T> hijoDer;
    private final T datos;

    public TElementoAB() {
        etiqueta = null;
        hijoIzq = null;
        hijoDer = null;
        datos = null;
    }

    public TElementoAB(Comparable unaEtiqueta, T unDato) {
        etiqueta = unaEtiqueta;
        hijoIzq = null;
        hijoDer = null;
        datos = unDato;
    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public TElementoAB<T> getHijoIzq() {
        return hijoIzq;
    }

    @Override
    public TElementoAB<T> getHijoDer() {
        return hijoDer;
    }

    @Override
    public void setHijoIzq(TElementoAB<T> elemento) {
        this.hijoIzq = elemento;
    }

    @Override
    public void setHijoDer(TElementoAB<T> elemento) {
        this.hijoDer = elemento;
    }

    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (esVacio()) {
//            System.out.println("El contador imprime: " + contador);
            return null;
        } else if (unaEtiqueta.compareTo(etiqueta) == 0) {
//            System.out.println("El contador imprime: " + contador);
            return this;
        } else if (unaEtiqueta.compareTo(etiqueta) < 0 && hijoIzq != null) {
            return hijoIzq.buscar(unaEtiqueta);

        } else if (unaEtiqueta.compareTo(etiqueta) > 0 && hijoDer != null) {
            return hijoDer.buscar(unaEtiqueta);
        } else {
            return null;
        }
    }

    @Override
    public boolean insertar(TElementoAB<T> elemento) {
        if (elemento.getEtiqueta().compareTo(etiqueta) == 0) {
//            System.out.println("El contador imprime: " + contador);
            return false;
        } else if (elemento.getEtiqueta().compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return hijoIzq.insertar(elemento);
            } else {
                this.hijoIzq = elemento;
//                System.out.println("El contador imprime: " + contador);
                return true;
            }
        } else {
            if (hijoDer != null) {
                return hijoDer.insertar(elemento);
            } else {
                this.hijoDer = elemento;
//                System.out.println("El contador imprime: " + contador);
                return true;
            }
        }
    }

    @Override
    public String preOrden() {
        String res = this.etiqueta + "-";
        if (hijoIzq != null) {
            res += hijoIzq.preOrden();
        }
        if (hijoDer != null) {
            res += hijoDer.preOrden();
        }
        return res;
    }

    @Override
    public String inOrden() {
        String res = "";
        if (hijoIzq != null) {
            res = hijoIzq.inOrden();
        }
        res += this.etiqueta.toString() + "-";
        if (hijoDer != null) {
            res += hijoDer.inOrden();
        }
        return res;
    }

    @Override
    public String postOrden() {
        String res = "";
        if (hijoIzq != null) {
            res += hijoIzq.postOrden();
        }
        if (hijoDer != null) {
            res += hijoDer.postOrden();
        }
        res += this.etiqueta + "-";
        return res;
    }

    @Override
    public T getDatos() {
        return datos;
    }

    @Override
    public TElementoAB eliminar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(this.getEtiqueta()) < 0) {
            if (this.hijoIzq != null) {
                this.hijoIzq = hijoIzq.eliminar(unaEtiqueta);
            }
            return this;
        }
        if (unaEtiqueta.compareTo(this.getEtiqueta()) > 0) {
            if (this.hijoDer != null) {
                this.hijoDer = hijoDer.eliminar(unaEtiqueta);
            }
            return this;
        }
        return quitaElNodo();
    }

    private TElementoAB quitaElNodo() {
        if (hijoIzq == null) {
            return hijoDer;
        }

        if (hijoDer == null) {
            return hijoIzq;
        }

        TElementoAB elHijo = hijoIzq;
        TElementoAB elPadre = this;

        while (elHijo.getHijoDer() != null) {
            elPadre = elHijo;
            elHijo = elHijo.getHijoDer();
        }

        if (elPadre != this) {
            elPadre.setHijoDer(elHijo.getHijoIzq());
            elHijo.setHijoIzq(hijoIzq);
        }

        elHijo.setHijoDer(hijoDer);
        return elHijo;
    }

    public int sumaDeClavesEnNivel(int nivel) {
        if (nivel == 0) {
            return Integer.parseInt(this.etiqueta.toString());
        }
        int res = 0;

        if (this.hijoIzq != null) {
            res += this.hijoIzq.sumaDeClavesEnNivel(nivel - 1);
        }
        if (this.hijoDer != null) {
            res += this.hijoDer.sumaDeClavesEnNivel(nivel - 1);
        }
        return res;

    }

    public boolean esVacio() {
        return etiqueta == null;
    }

    public int altura() {
        int a = -1;
        int b = -1;
        if (hijoIzq != null) {
            a = hijoIzq.altura();
        }
        if (hijoDer != null) {
            b = hijoDer.altura();
        }
        if (a > b) {
            return a + 1;
        } else {
            return b + 1;
        }
    }

    public boolean esHoja() {
        return (hijoIzq == null && hijoDer == null);
    }

    public int cantHojas() {
        if (esHoja()) {
            return 1;
        } else if (hijoDer != null && hijoIzq != null) {
            return hijoDer.cantHojas() + hijoIzq.cantHojas();
        } else if (hijoDer != null) {
            return hijoDer.cantHojas();
        } else {
            return hijoIzq.cantHojas();
        }
    }

    public int tamaño() {
        if (esHoja()) {
            return 1;
        } else if (hijoDer != null && hijoIzq != null) {
            return 1 + hijoDer.tamaño() + hijoIzq.tamaño();
        } else if (hijoIzq != null) {
            return 1 + hijoIzq.tamaño();
        } else {
            return 1 + hijoDer.tamaño();
        }
    }

    public int obtenerNivel(Comparable unaEtiqueta) {
        int nivel = -1;
        if (etiqueta.compareTo(unaEtiqueta) == 0) {
            return 0;
        }
        if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return 1 + hijoIzq.obtenerNivel(unaEtiqueta);
            }
        }
        if (hijoDer != null) {
            return 1 + hijoDer.obtenerNivel(unaEtiqueta);
        }
        return nivel;
    }

    public Comparable claveInmediata(Comparable clave) {
        if (clave.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                if (this.hijoIzq.getEtiqueta().compareTo(clave) == 0) {
                    return this.getEtiqueta();
                } else {
                    return hijoIzq.claveInmediata(clave);
                }

            }
        } else if (clave.compareTo(etiqueta) > 0) {
            if (hijoDer != null) {
                if (this.hijoDer.getEtiqueta().compareTo(clave) == 0) {
                    return this.getEtiqueta();
                } else {
                    return hijoDer.claveInmediata(clave);
                }
            }
        }
        return null;
    }

    public int cantidadDeNodosEnNivel(int nivel) {
        int res = 0;
        if (nivel == 0) {
            res = 1;
        } else {
            if (hijoIzq != null) {
                res += hijoIzq.cantidadDeNodosEnNivel(nivel - 1);
            }
            if (hijoDer != null) {
                res += hijoDer.cantidadDeNodosEnNivel(nivel - 1);
            }
        }
        return res;
    }

    public void listarHojas(Lista<Integer> listaDeHojas, TElementoAB<T> raiz) {
        if (!esVacio()) {
            if (esHoja()) {
                Nodo<Integer> nodo = new Nodo<>(etiqueta, raiz.obtenerNivel(etiqueta));
                listaDeHojas.insertar(nodo);
            } else {
                if (hijoIzq != null) {
                    hijoIzq.listarHojas(listaDeHojas, raiz);
                }
                if (hijoDer != null) {
                    hijoDer.listarHojas(listaDeHojas, raiz);
                }
            }
        }
    }

    public boolean esDeBusqueda() {
        if (hijoIzq != null) {
            if (etiqueta.compareTo(hijoIzq.getEtiqueta()) < 0) {
                return false;
            }
            return hijoIzq.esDeBusqueda();
        }
        if (hijoDer != null) {
            if (etiqueta.compareTo(hijoDer.getEtiqueta()) > 0) {
                return false;
            }
            return hijoDer.esDeBusqueda();
        }
        return true;

    }

    public Comparable<T> menorClave() {
        if (hijoIzq != null) {
            return hijoIzq.menorClave();
        } else {
            return this.getEtiqueta();
        }
    }

    public Comparable<T> mayorClave() {
        if (hijoDer != null) {
            return hijoDer.mayorClave();
        } else {
            return this.getEtiqueta();
        }
    }

}

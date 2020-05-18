package casodeestudio;

public class TArbolBB<T> implements IArbolBB<T> {

    private TElementoAB<T> raiz;

    public TArbolBB() {
        raiz = null;
    }

    public boolean esVacio() {
        return (getRaiz() == null);
    }

    public TElementoAB<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(TElementoAB<T> raiz) {
        this.raiz = raiz;
    }

    @Override
    public boolean insertar(TElementoAB<T> unElemento) {
        boolean res = false;
        if (esVacio()) {
            raiz = unElemento;
        } else {
            res = this.raiz.insertar(unElemento);
        }
        return res;
    }

    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (esVacio()) {
            return null;
        } else {
            return raiz.buscar(unaEtiqueta);
        }
    }

    @Override
    public String preOrden() {
        if (esVacio()) {
            return "Arbol vacio";
        }
        return raiz.preOrden();
    }

    @Override
    public String inOrden() {
        if (esVacio()) {
            return "Arbol vacio";
        }
        return raiz.inOrden();
    }

    @Override
    public String postOrden() {
        if (esVacio()) {
            return "Arbol vacio";
        }
        return raiz.postOrden();
    }

    @Override
    public void eliminar(Comparable unaEtiqueta) {
        if (!esVacio()) {
            this.raiz = this.raiz.eliminar(unaEtiqueta);
        }

    }

    public int tamaño() {
        if (!esVacio()) {
            return raiz.tamaño();
        }
        return 0;
    }

    public int obtenerNivelNodo(Comparable unaEtiqueta) {
        if (esVacio()) {
            return -1;
        } else {
            return raiz.obtenerNivel(unaEtiqueta);
        }
    }

    public int sumaDeClavesEnNivel(int nivel) {
        if (nivel >= 0 && !this.esVacio()) {
            return this.raiz.sumaDeClavesEnNivel(nivel);
        } else {
            return 0;
        }
    }

    public Comparable<T> menorClave() {
        if (esVacio()) {
            return null;
        } else {
            return raiz.menorClave();
        }
    }

    public Comparable<T> mayorClave() {
        if (esVacio()) {
            return null;
        } else {
            return raiz.mayorClave();
        }
    }

    public Comparable<T> claveInmediata(Comparable<T> clave) {
        if (esVacio()) {
            return null;
        }
        return raiz.claveInmediata(clave);
    }

    public int cantidadDeNodosEnNivel(int nivel) {
        if (nivel < 0 || esVacio()) {
            return -1;
        } else {
            return raiz.cantidadDeNodosEnNivel(nivel);
        }
    }

    public boolean esDeBusqueda() {
        if (esVacio()) {
            return true;
        } else {
            return raiz.esDeBusqueda();
        }
    }

    public Lista<Integer> listarHojas() {
        if (esVacio()) {
            return null;
        } else {
            Lista<Integer> lista = new Lista<>();
            raiz.listarHojas(lista, raiz);
            return lista;
        }
    }

    public int cantHojas() {
        if (esVacio()) {
            return 0;
        } else {
            return raiz.cantHojas();
        }
    }

    public int altura() {
        if (esVacio()) {
            return -1;
        } else {
            return raiz.altura();
        }
    }
}

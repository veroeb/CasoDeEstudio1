package casodeestudio;

public class Lista<T> implements ILista<T> {

    public Nodo<T> primero;

    public Lista() {
        this.primero = null;
    }

    @Override
    public void insertar(Nodo<T> nodo) {
        nodo.setSiguiente(null);
        if (this.esVacia()) {
            this.primero = nodo;
        } else {
            Nodo<T> actual = this.primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nodo);
        }
    }

    /*
	 * public void insertarPrincipio(Nodo<T> nodo) { nodo.siguiente = this.primero;
	 * this.primero = nodo; }
     */

    @Override
    public boolean eliminar(Comparable dato) {
        if (!this.esVacia()) {
            Nodo<T> actual = this.primero;
            Nodo<T> previo = null;
            if (actual.compareTo(dato) == 0) {
                this.primero = actual.getSiguiente();
                return true;
            } else {
                while (actual != null && actual.compareTo(dato) != 0) {
                    previo = actual;
                    actual = actual.getSiguiente();
                }
                if (actual != null) {
                    previo.setSiguiente(actual.getSiguiente());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String imprimir() {
        String aux = "";
        if (!esVacia()) {
            Nodo<T> temp = this.primero;
            while (temp != null) {
                temp.imprimirEtiqueta();
                temp = temp.getSiguiente();
            }
        }
        return aux;
    }

    public String imprimir(String separador) {
        String aux = "";
        if (esVacia()) {
            return "";
        } else {
            Nodo<T> temp = this.primero;
            aux = "" + temp.getEtiqueta();
            while (temp.getSiguiente() != null) {
                aux = aux + separador + temp.getSiguiente().getEtiqueta();
                temp = temp.getSiguiente();
            }

        }
        return aux;

    }

    @Override
    public int cantElementos() {
        int contador = 0;
        if (esVacia()) {
            System.out.println("Cantidad de elementos 0.");
            return 0;
        } else {
            INodo aux = this.primero;
            while (aux != null) {
                contador++;
                aux = aux.getSiguiente();
            }
        }
        return contador;
    }

    @Override
    public boolean esVacia() {
        return this.primero == null;
    }

    public Nodo<T> getPrimero() {
        return this.primero;
    }

    @Override
    public void setPrimero(Nodo<T> unNodo) {
        this.primero = unNodo;
    }

    @Override
    public boolean estaOrdenada() {
        if (!esVacia()) {
            Nodo<T> actual = this.primero;
            while (actual.getSiguiente() != null) {
                if (actual.getSiguiente().compareTo(actual.getEtiqueta()) < 0) {
                    return false;
                }
                actual = actual.getSiguiente();
            }
        }
        return true;
    }

    @Override
    public Nodo<T> buscar(Comparable clave) {
        if (esVacia()) {
            return null;
        } else {
            Nodo<T> aux = primero;
            while (aux != null) {
                if (aux.getEtiqueta().equals(clave)) {
                    return aux;
                }
                aux = aux.getSiguiente();
            }
        }
        return null;
    }
}
